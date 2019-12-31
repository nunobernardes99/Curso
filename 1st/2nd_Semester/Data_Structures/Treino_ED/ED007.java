import java.util.*;
class ED007 {
    public static String verificar(String s) {
        MyStack<Character> stack = new LinkedListStack<Character>();
        for(int i=0; i<s.length(); i+=2) {
            char c = s.charAt(i);
            if(c == '(' || c == '[') stack.push(c);
            else if(c == ')')
                if(stack.isEmpty() || stack.top() != '(') return "Erro na posicao " + i;
                else stack.pop();
            else if(c == ']')
            if(stack.isEmpty() || stack.top() != '[') return "Erro na posicao " + i;
            else stack.pop();
        }
        if(stack.size() != 0) return "Ficam parenteses por fechar";
        return "Expressao bem formada";
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int casos = scan.nextInt();
        scan.nextLine();
        for(int i=0; i<casos; i++) {
            String s = scan.nextLine();
            System.out.println(verificar(s));
        }
    }
}