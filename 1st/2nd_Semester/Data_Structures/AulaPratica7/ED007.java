import java.util.*;
import java.lang.*;
class ED007 {
    public static boolean isOperator(String c) {
        return ((c.equals("+")) || (c.equals("-")) || (c.equals("/")) || (c.equals("*")));
    }
    public static String checkError(String si, MyStack<String> s) {
        Scanner inString = new Scanner(si);
        int i=0;
        while(inString.hasNext()) {
            if(!inString.hasNextInt()) {
                String aux = inString.next();
                if(!isOperator(aux)) {
                    if(aux.equals("(") || aux.equals("["))
                        s.push(aux);
                    else if(aux.equals(")")) {
                        //System.out.println(s.top());
                        if(s.top()==null || !s.top().equals("(")) return "Erro na posicao " + i;
                        else s.pop();
                    } else if(aux.equals("]")) {
                        //System.out.println(s.top());
                        if(s.top()==null || !s.top().equals("[")) return "Erro na posicao " + i;
                        else s.pop();
                    }
                }
            } else inString.nextInt();
            i+=2;
        }
        if(!s.isEmpty()) return "Ficam parenteses por fechar";
        else return "Expressao bem formada";
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int casos = in.nextInt();
        in.nextLine();
        for(int i=0; i<casos; i++) {
            String line = in.nextLine();
            MyStack<String> s = new LinkedListStack<String>();
            System.out.println(checkError(line, s));
        }
    }
}