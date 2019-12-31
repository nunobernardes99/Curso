import java.util.*;
import java.lang.*;
class ED196 {
    public static void process(MyQueue<String> q, MyQueue<String> a, MyQueue<String> b) {
        while(!q.isEmpty()) {
            String s = q.dequeue();
            String c = q.dequeue();
            if(c.equals("A")) a.enqueue(s);    
            else if(c.equals("B")) b.enqueue(s);
            else if(c.equals("X"))
                if(a.size() > b.size()) b.enqueue(s);
                else if(a.size() < b.size()) a.enqueue(s);
        }
    }
}