import java.util.*;
import java.lang.*;
class ONI_PA {
    private static final int M = 27;
    public static boolean symbol(char a) {
        return (a=='!' || a=='_' || a=='?' || a=='.' || a==':' || a==';' || a==',');
    }
    public static String takeFromBegin(String toEval, int[] counter, int diffWords) {
        String aux = "";
        boolean prun = false;
        for(int i=0; i<toEval.length(); i++) {
            if(!prun) {
                if(counter[toEval.charAt(i)-'a'] != 0) {
                    counter[toEval.charAt(i)-'a']--;
                    if(counter[toEval.charAt(i)-'a'] == 0) prun = true;
                }
            } else aux+=toEval.charAt(i);
        }
        return aux;
    }
    public static String maximumDiffWords(String analyse, int diffWords) {
        String aux = "";
        int wordCount = 0;
        int[] counter = new int[M];
        String toReturn = "";
        int max = 0;
        for(int i=0; i<analyse.length(); i++) {
            if(wordCount < diffWords) {
                if(!symbol(analyse.charAt(i))) {
                    if(counter[analyse.charAt(i)-'a'] == 0) wordCount++;
                    counter[analyse.charAt(i)-'a']++;
                }
                aux += analyse.charAt(i);
            } else if(wordCount == diffWords) {
                if(!symbol(analyse.charAt(i))) {
                    if(counter[analyse.charAt(i)-'a'] != 0) {
                        counter[analyse.charAt(i)-'a']++;
                        aux+=analyse.charAt(i);
                    } else {
                        // RETIRAR ESPAÇO NO INÍCIO
                        aux = takeFromBegin(aux, counter, diffWords);
                        counter[analyse.charAt(i)-'a']++;
                    }
                } else aux+=analyse.charAt(i);
            }
            if(aux.length() > max) {
                max = aux.length();
                toReturn = aux;
            }
        }
        return toReturn;
    }
    public static void main(String[] args) {
        // Criação de scanner para leitura de input
        Scanner scan = new Scanner(System.in);
        // Input
        int diffWords = scan.nextInt();
        String analyse = scan.next();
        // Transformar analyse em minúsculas
        analyse = analyse.toLowerCase();
        
        String answer = maximumDiffWords(analyse, diffWords);
        System.out.println(answer);

        System.out.println(analyse.length());
        System.out.println(diffWords);
        System.out.println(analyse);


        scan.close();
    }
}