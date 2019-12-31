import java.util.Scanner;
import java.util.Arrays;
 
class Letras{
    int linhas,colunas;
    char m[][];
    Letras(int a,int b){
        linhas=a;
        colunas=b;
        m = new char [a][b];
    }
    public void ler(Scanner teclado){
        for(int i=0;i<linhas;i++){
            m[i]=teclado.next().toCharArray();
        }
    }
    public void lerCom(){
        for(int i=0;i<linhas;i++){
            for(int j=0;j<colunas;j++){
                m[i][j]='.';
            }
        }
    }
    public void escrever(int counter){
        System.out.println("Input #"+counter);
        for(int i=0;i<linhas;i++){
            for(int j=0;j<colunas;j++){
                System.out.print(m[i][j]);
            }
            System.out.println();
        }
    }
    public int verificar(Letras z,String a){
        int auxiliar=0;
        for(int i=0;i<z.linhas;i++){
            for(int j=0;j<z.colunas;j++){
                if(z.m[i][j]==a.charAt(auxiliar)){
                    auxiliar++;
                }
                else{
                    auxiliar=0;
                }
                if(auxiliar==a.length()){
                    while(auxiliar!=0){
                            m[i][j-auxiliar+1]=a.charAt((a.length()-auxiliar));
                            auxiliar--;
                        }
                    return 0;
                }
            }
            auxiliar=0;
        }
        auxiliar=0;
        for(int i=0;i<z.colunas;i++){
            for(int j=0;j<z.linhas;j++){
                 if(z.m[j][i]==a.charAt(auxiliar)){
                    auxiliar++;
                }
                else{
                    auxiliar=0;
                }
                if(auxiliar==a.length()){
                    while(auxiliar!=0){
                            m[j-auxiliar+1][i]=a.charAt((a.length()-auxiliar));
                            auxiliar--;
                        }
                    return 1;
                }
            }
            auxiliar=0;
        }
        return 0;
    }
    public String inversa(String a){
        String aInvertida="";
        for(int i=a.length()-1;i>=0;i--){
            aInvertida+=a.charAt(i);
        }
        return aInvertida;
    }
}
 
public class ED015{
    public static void main(String args[]){
        Scanner teclado= new Scanner(System.in);
        int counter=0;
        int linhas=teclado.nextInt();
        int colunas=teclado.nextInt();
        while(linhas!=0&&colunas!=0){
            counter++;
            Letras sopaOriginal = new Letras(linhas,colunas);
            sopaOriginal.ler(teclado);
            Letras sopaEncontrada = new Letras(linhas,colunas);
            sopaEncontrada.lerCom();
            int numeroPalavras=teclado.nextInt();
            teclado.nextLine();
            for(int i=0;i<numeroPalavras;i++){
                String a = teclado.nextLine();
                sopaEncontrada.verificar(sopaOriginal,a);
                sopaEncontrada.verificar(sopaOriginal,sopaOriginal.inversa(a));
            }
            sopaEncontrada.escrever(counter);
            linhas=teclado.nextInt();
            colunas=teclado.nextInt();
        }
    }
   
}




/*import java.util.*;
import java.lang.*;
class SopaLetras {
    private int lines, cols;
    private char m[][];
    SopaLetras(int l, int c) {
        lines = l;
        cols = c;
        m = new char[l][c];
    }
    public void read(Scanner in) {
        for(int i=0; i<lines; i++)
            m[i] = in.next().toCharArray();
    }
    private void changeResult(boolean[][] toResult, int y, int x,int incy, int incx, String word) {
        //System.out.println("Entrou");
        for (int i=0, yy=y, xx=x; i<word.length(); i++, yy+=incy, xx+=incx)
            toResult[yy][xx] = true;
    }
    private void verify(int y, int x, int incy, int incx, boolean[][] toResult, String word) {
        for (int i=0, yy=y, xx=x; i<word.length(); i++, yy+=incy, xx+=incx)
            if (word.charAt(i) != m[yy][xx]) return;
        changeResult(toResult, y, x, incy, incx, word);
    }
    public void findWord(String word, boolean[][] toResult) {
        String reverseWord = new StringBuilder(word).reverse().toString();
        char fstNormal = word.charAt(0);
        char fstReverse = reverseWord.charAt(0);
        int maxHorizontal = (cols - word.length())+1;
        int maxVertical = (lines - word.length())+1;
        //Horizontal
        //ystem.out.println("Horizontal");
        for(int i=0; i<lines; i++) {
            for(int j=0; j<maxHorizontal; j++) {
                //System.out.println("H: A comparar " + m[i][j] + " com " + fstNormal + " e " + fstReverse);
                if(m[i][j] == fstNormal)   verify(i, j, 0, 1, toResult, word);
                if(m[i][j] == fstReverse) verify(i, j, 0, 1, toResult, reverseWord);
            }
        }
        //Vertical
        //System.out.println("Vertical");
        for(int j=0; j<cols; j++) {
            for(int i=0; i<maxVertical; i++) {
                //System.out.println("V: A comparar " + m[i][j] + " com " + fstNormal + " e " + fstReverse);
                if(m[i][j] == fstNormal)   verify(i, j, 1, 0, toResult, word);
                if(m[i][j] == fstReverse) verify(i, j, 1, 0, toResult, reverseWord);
            }
        }
    }
    public void writeFinal(boolean[][] toResult) {
        for(int i=0; i<lines; i++) {
            for(int j=0; j<cols; j++) {
                if(toResult[i][j]) System.out.print(m[i][j]);
                else System.out.print(".");
            }
            System.out.println("");
        }
    }
}
class ED015 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int lins = scan.nextInt();
        int cols = scan.nextInt();
        int ctr = 1;
        while(lins != 0 && cols != 0) {
            SopaLetras begin = new SopaLetras(lins, cols);
            boolean[][] toResult = new boolean[lins][cols];
            begin.read(scan);
            int nmrWords = scan.nextInt();
            for(int i=0; i<nmrWords; i++) {
                String wordToSearch = scan.next();
                begin.findWord(wordToSearch, toResult);
            }
            System.out.println("Input #" + ctr);
            begin.writeFinal(toResult);
            ctr++;
            lins = scan.nextInt();
            cols = scan.nextInt();
        }
    }
}*/