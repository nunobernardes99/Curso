import java.lang.*;
import java.util.*;
class ED197 {
    public static MyQueue<Integer> merge(MyQueue<Integer> a, MyQueue<Integer> b) {
        MyQueue<Integer> q = new LinkedListQueue<Integer>();
        int aV = a.dequeue();
        int bV = b.dequeue();
        while(!a.isEmpty() && !b.isEmpty()) {
            if(bV < aV) {
                q.enqueue(bV);
                bV = b.dequeue();
            } else if(aV < bV) {
                q.enqueue(aV);
                aV = a.dequeue();
            } else {
                q.enqueue(aV);
                q.enqueue(bV);
                aV = a.dequeue();
                bV = b.dequeue();
            }
        }
        if(aV > bV) {
            q.enqueue(bV);
            q.enqueue(aV);
        } else {
            q.enqueue(aV);
            q.enqueue(bV);
        }
        if(a.isEmpty() && !b.isEmpty()) {
            while(!b.isEmpty()) q.enqueue(b.dequeue());
        } else if(!a.isEmpty() && b.isEmpty()) {
            while(!a.isEmpty()) q.enqueue(a.dequeue());
        }
        return q;
    }
}