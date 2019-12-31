import java.util.*;
import java.lang.*;
class ED005 {
    public static boolean isOperator(char c) {
        return ((c=='+') || (c=='-') || (c=='/') || (c=='*'));
    }
    public static void calculate(String si, MyStack<Integer> s) {
        Scanner inString = new Scanner(si);
        while(inString.hasNext()) {
            if(inString.hasNextInt())
                s.push(inString.nextInt());
            else {
                int v1, v2;
                String aux = inString.next();
                if(aux.equals("+")) {
                    if(s.size() < 2) { System.out.println("Expressao Incorrecta"); return; }
                    v1 = s.pop();
                    v2 = s.pop();
                    int v3 = v2 + v1;
                    //System.out.println("SUM Valor 1: " + v1 + ", Valor 2: " + v2);
                    s.push(v3);
                } else if(aux.equals("-")) {
                    if(s.size() < 2) { System.out.println("Expressao Incorrecta"); return; }
                    v1 = s.pop();
                    v2 = s.pop();
                    //System.out.println("SUB Valor 1: " + v1 + ", Valor 2: " + v2);
                    int v3 = v2 - v1;
                    s.push(v3);
                } else if(aux.equals("/")) {
                    if(s.size() < 2) { System.out.println("Expressao Incorrecta"); return; }
                    v1 = s.pop();
                    v2 = s.pop();
                    //System.out.println("DIV Valor 1: " + v1 + ", Valor 2: " + v2);
                    int v3 = (int) v2 / v1;
                    s.push(v3);
                } else if(aux.equals("*")) {
                    if(s.size() < 2) { System.out.println("Expressao Incorrecta"); return; }
                    v1 = s.pop();
                    v2 = s.pop();
                    //System.out.println("MUL Valor 1: " + v1 + ", Valor 2: " + v2);
                    int v3 = v2 * v1;
                    s.push(v3);
                }

            }
        }
        if(s.size() != 1) { System.out.println("Expressao Incorrecta"); return; }
        System.out.println(s.pop());
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int casos = in.nextInt();
        in.nextLine();
        for(int i=0; i< casos; i++) {
            MyStack<Integer> s = new LinkedListStack<Integer>();
            String si = in.nextLine();
            calculate(si, s);
        }
    }
}