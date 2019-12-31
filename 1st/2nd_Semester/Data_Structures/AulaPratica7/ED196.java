import java.lang.*;
import java.util.*;
class ED196 {
    public static void process(MyQueue<String> q, MyQueue<String> a, MyQueue<String> b) {
        while(!q.isEmpty()) {
            String name = q.dequeue();
            String op = q.dequeue();
            if(op.equals("A")) a.enqueue(name);
            else if(op.equals("B")) b.enqueue(name);
            else {
                if(a.size() > b.size()) b.enqueue(name);
                else if(b.size() > a.size()) a.enqueue(name);
            }
        }
    }
}