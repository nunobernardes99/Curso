import java.util.*;
import java.lang.*;
class ED029 {
    public static void checkTrip(MyQueue<String> qDep, MyQueue<String> qLand) {
        
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int casos = in.nextInt();
        for(int j=0; j<casos; j++) {
            int depart = in.nextInt();
            int landing = in.nextInt();
            in.nextLine();
            MyQueue<String> qDepart = new LinkedListQueue<String>();
            for(int i=0; i<depart; i++)
                qDepart.enqueue(in.nextLine());
            MyQueue<String> qLanding = new LinkedListQueue<String>();
            for(int i=0; i<landing; i++)
                qLanding.enqueue(in.nextLine());
            checkTrip(qDepart, qLanding);
        }

    }
}