import java.util.*;
import java.lang.*;
class ED197 {
    public static MyQueue<Integer> merge(MyQueue<Integer> a, MyQueue<Integer> b) {
        MyQueue<Integer> q = new LinkedListQueue<Integer>();
        while(true) {
            if(a.isEmpty() && b.isEmpty()) return q;
            else if(!a.isEmpty() && b.isEmpty()) q.enqueue(a.dequeue());
            else if(a.isEmpty() && !b.isEmpty()) q.enqueue(b.dequeue());
            else if(!a.isEmpty() && !b.isEmpty()) {
                if(a.first() < b.first()) q.enqueue(a.dequeue());
                else if(a.first() > b.first()) q.enqueue(b.dequeue());
                else if(a.first() == b.first()) {
                    q.enqueue(a.dequeue());
                    q.enqueue(b.dequeue());
                }
            }
        }
    }
}