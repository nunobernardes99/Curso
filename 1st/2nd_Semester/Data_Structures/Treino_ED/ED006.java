import java.util.*;
class ED006 {
    public static String canta(CircularLinkedList<String> list, int n) {
        while(list.size() != 1) {
            for(int i=0; i<n; i++) list.rotate();
            list.removeLast();
        }
        if(list.getFirst().equals("Carlos")) return "O Carlos nao se livrou";
        else return "O Carlos livrou-se (coitado do " + list.getFirst() + "!)";
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int casos = scan.nextInt();
        for(int i=0; i<casos; i++) {
            scan.nextLine();
            int ctr=0;
            String musica = scan.nextLine();
            Scanner scanMusic = new Scanner(musica);
            while(scanMusic.hasNext()) { ctr++; scanMusic.next(); }
            int players = scan.nextInt();
            CircularLinkedList<String> list = new CircularLinkedList<String>();
            for(int j=0; j<players; j++)
                list.addLast(scan.next());
            System.out.println(canta(list, ctr));
            scanMusic.close();
        }
        scan.close();
    }
}