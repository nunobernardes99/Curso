import java.util.*;
import java.lang.*;
class ED195 {
    public static boolean balanced(String s) {
        MyStack<Character> open = new LinkedListStack<Character>();
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(' || c == '[') open.push(c);
            else if(c == ')') {
                if(open.size() == 0) return false;
                if(open.top() == '(') open.pop();
                else return false;
            } else if(c == ']') {
                if(open.size() == 0) return false;
                if(open.top() == '[') open.pop();
                else return false;
            }
        }
        if(open.size()!=0) return false;
        return true;
    }
    /*public static void main(String[] args) {
        System.out.println(balanced("[("));
        System.out.println(balanced("(]"));
        System.out.println(balanced("]"));
        System.out.println(balanced(")"));
        System.out.println(balanced("[[[()))"));
        System.out.println(balanced("[]()[[])"));
        System.out.println(balanced("()[](()]"));

    }*/
}