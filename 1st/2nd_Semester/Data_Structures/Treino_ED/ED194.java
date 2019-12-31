import java.util.*;
import java.lang.*;
class ED194 {
    public static void reverse(MyStack<Integer> s, int n) {
        MyStack<Integer> s1 = new LinkedListStack<Integer>();
        MyStack<Integer> s2 = new LinkedListStack<Integer>();
        for(int i=0; i<n; i++)
            s1.push(s.pop());
        for(int i=0; i<n; i++)
            s2.push(s1.pop());
        for(int i=0; i<n; i++)
            s.push(s2.pop());
    }
}