import java.util.*;
import java.lang.*;
class BigNumber {
    int[] arr = new int[1000];
    int size;
    BigNumber(String n) {
        size = n.length();
        for(int i = size-1, aux = 0; i>=0; i--, aux++)
            arr[aux] = toDigit(n.charAt(i));
    }
    /* Converte um char de um valor inteiro num dígito inteiro */
    private int toDigit(char a) { return a - '0'; }
    /* Verifica se dois BigNumbers são iguais */
    public boolean equals(BigNumber n) {
        if(this.size != n.size) return false;
        for(int i=0; i<this.size; i++)
            if(this.arr[i]!=n.arr[i]) return false;
        return true;
    }
    /* Soma dois bignumbers e retorna esse valor num novo bignumber */
    public BigNumber add(BigNumber n) {
        String add = "";
        int remainer = 0;
        for(int i=0; i<Math.max(this.size, n.size)+1; i++) {
            add += (remainer + this.arr[i] + n.arr[i])%10;
            remainer = (remainer + this.arr[i] + n.arr[i])/10;
        }
        BigNumber addition = new BigNumber(add);
        return addition;
    }
    /* Multiplica dois números e retorna esse valor num novo bignumber */
    public BigNumber multiply(BigNumber n) {
        BigNumber[] arrOfNumbers = new 
    }

    /* Devolve a representação do bignumber em string */
    public String toString() {
        String aux = "";
        for(int i=0; i<size; i++)
            aux += arr[i];
        return aux;  
    }



}
class TestBigNumber {
    public static void main(String[] args) {
       BigNumber n1 = new BigNumber("1234567890");
       System.out.println(n1); // Escreve "1234567890"
 
       BigNumber n2 = new BigNumber("42");
       BigNumber n3 = new BigNumber("1234567890");
       System.out.println(n1.equals(n2)); // Escreve "false"
       System.out.println(n1.equals(n3)); // Escreve "true"
       
       BigNumber n4 = new BigNumber("46711237126582920746212");
       BigNumber n5 = new BigNumber("8765432110");
       BigNumber n6 = n1.add(n3);
       System.out.println(n6); // Escreve "2469135780"
       BigNumber n7 = n1.add(n4);
       System.out.println(n7); // Escreve "46711237126584155314102"
       BigNumber n8 = n1.add(n5);
       System.out.println(n8); // Escreve "10000000000"
 
       /*BigNumber n9 = n1.multiply(n3);
       System.out.println(n9); // Escreve "1524157875019052100"
       BigNumber n10 = n1.multiply(n4);
       System.out.println(n10); // Escreve "57668193458655139375688174332680"*/
    }
 }