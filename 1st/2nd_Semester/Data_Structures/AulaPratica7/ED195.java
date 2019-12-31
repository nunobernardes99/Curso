import java.util.*;
import java.lang.*;
class ED195 {
    public static boolean isOpen(char c) {
        return (c == '(' || c == '[');
    }
    public static boolean balanced(String s) {
        MyStack<Character> aux = new LinkedListStack<Character>();
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(isOpen(c)) aux.push(c);
            else {
                if(aux.top() == null) return false;
                if(c == ')')
                    if(aux.top() == '(') aux.pop();
                    else return false;
                if(c == ']')
                    if(aux.top() == '[') aux.pop();
                    else return false;
            }
        }
        if(aux.size() != 0) return false;
        return true;
    }
}