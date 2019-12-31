import java.util.*;
import java.lang.*;
class palindrome {
    public static void isPalindrome(char[] newChar) {
        int fLength = newChar.length-2;
        boolean pass = true;
        for(int i=0; i<newChar.length/2 && pass;i++) {
            //System.out.println("Comparing " + newChar[i] + " with, " + newChar[fLength]);
            if(newChar[i] != newChar[fLength]) {
                System.out.println("nao");
                pass = false;
            }
            fLength--;
        }
        if(pass)  System.out.println("sim");
    }
    public static void onlyLetters(String test) {
        int size=1;
        for(int i=0; i<test.length(); i++)
            if(test.charAt(i)>='a' && test.charAt(i)<='z') size++;
        char[] auxChar = new char[size];
        int ctr = 0;
        for(int i=0; i<test.length(); i++)
            if(test.charAt(i)>='a' && test.charAt(i)<='z') {
                auxChar[ctr] = test.charAt(i);
                ctr++;
            }

        /*for(int i=0; i<auxChar.length; i++) System.out.print(auxChar[i]);
        System.out.println("");*/
        isPalindrome(auxChar);
    }
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        int nmrCasos = scan.nextInt();
        scan.nextLine();
        System.out.println(nmrCasos);
        for(int i=1; i<=nmrCasos; i++) {
            String test = scan.nextLine();
            test = test.toLowerCase();
            onlyLetters(test);
        }
        scan.close();
    }
}