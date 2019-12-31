import java.lang.*;
import java.util.*;
class ED194 {
    public static void reverse(MyStack<Integer> s, int n) {
        MyStack<Integer> aux1 = new LinkedListStack<Integer>();
        for(int i=0; i<n; i++)
            aux1.push(s.pop());
        MyStack<Integer> aux2 = new LinkedListStack<Integer>();
        for(int i=0; i<n; i++)
            aux2.push(aux1.pop());
        for(int i=0; i<n; i++)
            s.push(aux2.pop());
    }
}