import java.util.*;
class ED005 {
    public static int quickMath(int b, int a, String s) {
        if(s.equals("+")) return a+b;
        else if(s.equals("-")) return a-b;
        else if(s.equals("*")) return a*b;
        else if(s.equals("/")) return a/b;
        return 0;
    }
    public static int calculator(Scanner scan) {
        MyStack<Integer> s = new LinkedListStack<Integer>();
        while(scan.hasNext()) {
            if(scan.hasNextInt()) s.push(scan.nextInt());
            else
                if(s.size()>=2) s.push(quickMath(s.pop(), s.pop(), scan.next()));
                else return Integer.MIN_VALUE;
        }
        if(s.size()!=1) return Integer.MIN_VALUE;
        return s.pop();
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.nextLine();
        for(int i=0; i<n; i++) {
            String s = scan.nextLine();
            Scanner stringScan = new Scanner(s);
            int fValue = calculator(stringScan);
            if(fValue != Integer.MIN_VALUE) System.out.println(fValue);
            else System.out.println("Expressao Incorreta");
            stringScan.close();
        }
        scan.close();
    }
}