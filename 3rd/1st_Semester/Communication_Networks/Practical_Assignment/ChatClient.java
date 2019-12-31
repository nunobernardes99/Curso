import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ChatClient {

    // Variáveis relacionadas com a interface gráfica --- * NÃO MODIFICAR *
    JFrame frame = new JFrame("Chat Client");
    private JTextField chatBox = new JTextField();
    private JTextArea chatArea = new JTextArea();
    // --- Fim das variáveis relacionadas coma interface gráfica
    
    // Se for necessário adicionar variáveis ao objecto ChatClient, devem
    // ser colocadas aqui
    private Socket socket;
    private BufferedReader receiver;
    private PrintWriter sender;
    


    
    // Método a usar para acrescentar uma string à caixa de texto
    // * NÃO MODIFICAR *
    public void printMessage(String message) {
        if(message != null) {
            String[] tokens = message.split(" ");
            // Por formato mais amigável entende-se, por exemplo, que quando é recebida do servidor a mensagem MESSAGE nome mensagem seja mostrado na área de chat nome: mensagem, que quando é recebida do servidor a mensagem NEWNICK nome_antigo nome_novo seja mostrado na área de chat nome_antigo mudou de nome para nome_novo, etc. 
            switch(tokens[0]) {
                case "MESSAGE": {
                    message = message.replaceFirst("MESSAGE","").replaceFirst(tokens[1],"");
                    message = tokens[1] + ":" + message;
                    break;
                }
                case "NEWNICK": {
                    message = tokens[1] + " changed his nickname to " + tokens[2];
                    break;
                }
                case "JOINED":{
                    message = tokens[1] + " joined the room";
                    break;
                }
                case "LEFT":{
                    message = tokens[1] + " left the room";
                    break;
                }
                case "PRIVATE":{
                    message = message.replaceFirst("PRIVATE","").replaceFirst(tokens[1],"");
                    message = "private -> " + tokens[1] + ":" + message;
                    break;
                }
            }
            chatArea.append(message+"\n");
        }
    }

    
    // Construtor
    public ChatClient(String server, int port) throws IOException {

        // Inicialização da interface gráfica --- * NÃO MODIFICAR *
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(chatBox);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.SOUTH);
        frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);
        frame.setSize(500, 300);
        frame.setVisible(true);
        chatArea.setEditable(false);
        chatBox.setEditable(true);
        chatBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    newMessage(chatBox.getText());
                } catch (IOException ex) {
                } finally {
                    chatBox.setText("");
                }
            }
        });
        frame.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                chatBox.requestFocus();
            }
        });
        // --- Fim da inicialização da interface gráfica

        // Se for necessário adicionar código de inicialização ao
        // construtor, deve ser colocado aqui
        this.socket = new Socket(server, port);
        this.receiver = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));
        this.sender = new PrintWriter(socket.getOutputStream(), true);
    }


    // Método invocado sempre que o utilizador insere uma mensagem
    // na caixa de entrada
    public void newMessage(String message) throws IOException {
        // PREENCHER AQUI com código que envia a mensagem ao servidor
        this.sender.println(message);  
    }

    
    // Método principal do objecto
    public void run() throws IOException {
        do {
            printMessage(this.receiver.readLine());
        } while (true);
    }

    // Instancia o ChatClient e arranca-o invocando o seu método run()
    // * NÃO MODIFICAR *
    public static void main(String[] args) throws IOException {
        ChatClient client = new ChatClient(args[0], Integer.parseInt(args[1]));
        client.run();
    }

}