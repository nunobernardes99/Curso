import java.util.*;
import java.lang.*;
class ONI_RQ {
    public static long nmrP(int n) {
        if(n==0)
            return 1;
        return (long) Math.pow(2,n); 
    }
    public static long nmrB(int n) {
        if(n==0)
            return 0;
        return (long) nmrP(n-1) + 2*(nmrB(n-1)); 
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int questoes = scan.nextInt();
        for(int i=0; i<questoes; i++) {
            int line = scan.nextInt();
            long pretos =  nmrP(line);
            long brancos =  nmrB(line);
            System.out.println("Numero de quadrados pretos: " + pretos + ", Numero de bracos: " + brancos);
        }
        scan.close();
    }
}


// Quadrados preto = 2^n