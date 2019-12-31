import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


enum State{
  init, outside, inside
}

class User{
  String nickname;
  State state;
  String room;
  String message; //this variable will be used to avoid the Ctrl + D on netcat 

  User(){
    this.nickname = "";
    this.state = State.init;
    this.message="";
  }

  boolean isInRoom(){
    return room != null;
  }
}

public class ChatServer
{
  // A pre-allocated buffer for the received data
  static private final ByteBuffer buffer = ByteBuffer.allocate( 16384 );

  // Decoder for incoming text -- assume UTF-8
  static private final Charset charset = Charset.forName("UTF8");
  static private final CharsetDecoder decoder = charset.newDecoder();
  static private final CharsetEncoder encoder = charset.newEncoder();
  
  private static final String ERROR = "ERROR";
  private static final String OK = "OK";

  
  private static final Map<String, Set<SelectionKey>> rooms = new HashMap<>();
  private static final Map< String, SelectionKey> users = new HashMap<>();

  static public void main(String args[]) throws Exception {
    // Parse port from command line
    int port = Integer.parseInt( args[0] );
    
    try {
      // Instead of creating a ServerSocket, create a ServerSocketChannel
      ServerSocketChannel ssc = ServerSocketChannel.open();

      // Set it to non-blocking, so we can use select
      ssc.configureBlocking( false );

      // Get the Socket connected to this channel, and bind it to the
      // listening port
      ServerSocket ss = ssc.socket();
      InetSocketAddress isa = new InetSocketAddress( port );
      ss.bind( isa );

      // Create a new Selector for selecting
      Selector selector = Selector.open();

      // Register the ServerSocketChannel, so we can listen for incoming
      // connections
      ssc.register( selector, SelectionKey.OP_ACCEPT );
      //System.out.println( "Listening on port "+port );

      while (true) {
        // See if we've had any activity -- either an incoming connection,
        // or incoming data on an existing connection
        int num = selector.select();

        // If we don't have any activity, loop around and wait again
        if (num == 0) {
          continue;
        }

        //System.out.println();       // Get the keys corresponding to the activity that has been
        // detected, and process them one by one
        Set<SelectionKey> keys = selector.selectedKeys();
        Iterator<SelectionKey> it = keys.iterator();
        while (it.hasNext()) {
          // Get a key representing one of bits of I/O activity
          SelectionKey key = it.next();

          // What kind of activity is it?
          if ((key.readyOps() & SelectionKey.OP_ACCEPT) ==
            SelectionKey.OP_ACCEPT) {

            // It's an incoming connection.  Register this socket with
            // the Selector so we can listen for input on it
            Socket s = ss.accept();
            //System.out.println( "Got connection from "+s );
           

            // Make sure to make it non-blocking, so we can use a selector
            // on it.
            SocketChannel sc = s.getChannel();
            sc.configureBlocking( false );

            // Register it with the selector, for reading
            sc.register( selector, SelectionKey.OP_READ );
            
            
          } else if ((key.readyOps() & SelectionKey.OP_READ) ==
            SelectionKey.OP_READ) {

            SocketChannel sc = null;

            try {

              // Registering a new user
              if(key.attachment() == null){
                key.attach(new User());
                //users.add(key);
              }

              // It's incoming data on a connection -- process it
              sc = (SocketChannel)key.channel();
              boolean ok = processInput( key);

              // If the connection is dead, remove it from the selector
              // and close it
              if (!ok) {
                key.cancel();
                bye(key);

                Socket s = null;
                try {
                  s = sc.socket();
                  //System.out.println( "Closing connection to "+s );
                  s.close();
                } catch( IOException ie ) {                // System.out.println(ke                // System.out.println(keys);ys);
                  //System.err.println( "Error closing socket "+s+": "+ie );
                }
              }

            } catch( IOException ie ) {

              // On exception, remove this channel from the selector
              key.cancel();

              try {
                sc.close();
              } catch( IOException ie2 ) { 
                //System.out.println( ie2 ); 
                }

              //System.out.println( "Closed "+sc );
            }
          }
        }

        // We remove the selected keys, because we've dealt with them.
        keys.clear();
      }
    } catch( IOException ie ) {
      //System.err.println( ie );
    }
  }


  // Just read the message from the socket and send it to stdout
  static private boolean processInput( SelectionKey key) throws IOException {

    SocketChannel sc = (SocketChannel) key.channel();
     // Read the message to the buffer          
    buffer.clear();

    
    sc.read( buffer );

    buffer.flip();

    // If no data, close the connection
    if (buffer.limit()==0) {
      return false;
    }

    // Decode and print the message to stdout
    String messag = decoder.decode(buffer).toString();

    //checking if it's a \n or a Ctrl+D
    User user = (User) key.attachment();
    user.message = user.message + messag;
    //System.out.print(user.message);
    
    if(user.message.endsWith("\n")){
      for( String message :  user.message.split("\n")){
        // Different commands that clients can execute
        if(message.startsWith("/nick")) {
          changeNick(key, message.substring(6));
        }
        else if(message.startsWith("/join ")){
          joinRoom(key, message.substring(6));
        }
        else if(message.startsWith("/leave")){
          leaveRoom(key);
        }
        else if(message.startsWith("/bye")){
          bye(key);
        }
        else if(message.startsWith("/priv ")){
          sendPriv(key, message);
        }
        else {
          message(key, message);
        }
      }
      if ( user.message.split("\n").length > 1 ) bye(key);
      user.message="";
    }

    return true;
  }
  static void changeNick(SelectionKey key , String nick) throws IOException {
    User user = (User) key.attachment();
    String message = "";
    if(users.containsKey(nick)) {
      send(key, ERROR);
      return ;
    } 
    if(user.state == State.inside) {
      message = "NEWNICK " + user.nickname + " " + nick; 
      sendToOthersInRoom(rooms.get(user.room), key, message);     
    }
    users.remove(user.nickname);
    user.nickname = nick;
    if(user.state == State.init) {
      user.state= State.outside;
    }
    users.put(user.nickname, key);
    send(key, OK);
  }
  static void leaveRoom(SelectionKey key) throws IOException {
      User user = (User) key.attachment();
      if(user.state != State.inside){
        send(key, ERROR);
        return;
      }
      Set<SelectionKey> room = rooms.get(user.room);
      room.remove(key);
      user.room = null;
      user.state = State.outside;

      sendToRoom(room, "LEFT " + user.nickname);
      send(key, OK);
  }

  static void joinRoom(SelectionKey key , String sala) throws IOException {
    User user = (User) key.attachment();
    if(user.state == State.init){
      send(key, ERROR);
      return;
    }
    if(user.isInRoom()){
      leaveRoom(key);
    }else{
      send(key, OK);
    }
    Set<SelectionKey> room = rooms.computeIfAbsent(sala, k-> new HashSet<>());
    room.add(key);
    user.room = sala;
    user.state = State.inside;
    
    sendToOthersInRoom(room, key, "JOINED " + user.nickname);
  }

  static void sendPriv(SelectionKey key , String message) throws IOException{
    User user = (User) key.attachment();
    String temp[] = message.split(" ");
    message = "";
    if( temp.length > 1 ){
      if(users.get(temp[1]) == null ) {
        send(key, ERROR);    
      }else{
        message = String.join(" ", Arrays.copyOfRange(temp,2,temp.length));
        if(message.startsWith("//")) {
          message = message.substring(1);
        }
        send(users.get(temp[1]),"PRIVATE " + user.nickname + " " + message);     
      }
    }
    else{
      send(key, ERROR);
    }
  }
  static void send(SelectionKey key, String message) throws IOException {
    message = message + "\n";
    SocketChannel sc = (SocketChannel) key.channel();
    sc.write(encoder.encode(CharBuffer.wrap(message)));
  }

  static void sendToRoom(Set<SelectionKey> room, String message) throws IOException {
    for(SelectionKey user : room){
        send(user, message);
    }
  }
  static void sendToOthersInRoom(Set<SelectionKey> room, SelectionKey exception, String message) throws IOException {
    for(SelectionKey user : room){
      if(user == exception){
        continue;
      }
      send(user, message);
    }
  }

  static private void message(SelectionKey key, String message) throws IOException {
    User user = (User) key.attachment();
    if( user.state != State.inside){
      send(key,ERROR);
    }
    else{
      if(message.startsWith("//")) {
        message = message.substring(1);
      }
      message = "MESSAGE " + user.nickname + " " + message;
      sendToRoom(rooms.get(user.room), message);
    }    
  }

  static private void bye(SelectionKey key) throws IOException {
    User user = (User) key.attachment();
    if(user.state == State.inside){
      Set<SelectionKey> room = rooms.get(user.room); 
      sendToOthersInRoom(room, key, "LEFT "+ user.nickname);
      room.remove(key);
    }
    send(key, "BYE");
    users.remove(user.nickname);
    SocketChannel sc = (SocketChannel) key.channel();
    Socket s = null;
    try {
      s = sc.socket();
      //System.out.println( "Closing connection to "+s );
      s.close();
    } catch(IOException e){
      //System.err.println( "Error closing socket "+s+": "+e );
    }
  }
}