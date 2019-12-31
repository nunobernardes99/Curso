import java.util.*;
import java.lang.*;
class Board {
    private int lines, cols;
    private char[][] m;
    Board(int l, int c) {
        lines = l;
        cols = c;
        m = new char[l][c];
    }
    public void read(Scanner in) {
        for(int i=0; i<lines; i++)
            m[i] = in.next().toCharArray();
    }
    private int checkMaxV(int col) { //m[i][col]
        int count = 0;
        int scdCount = 0;
        for(int i=0; i<lines; i++) {
            if(m[i][col] == '.' && count!= 0) {
                if(scdCount < count)
                    scdCount = count;
                count = 0;
            } if(m[i][col] == '#')
                count++;
        }
        //System.out.println("Col " + col + " : " + count + ", " + scdCount);
        if(scdCount > count) return scdCount;
        else return count;
    }
    private int checkMaxH(int line) { //m[line][i]
        int count = 0;
        int scdCount = 0;
        for(int i=0; i<cols; i++) {
            if(m[line][i]=='.' && count!= 0) {
                if(scdCount < count)
                    scdCount = count;
                count = 0;
            } if(m[line][i] == '#')
                count++;
        }
        //System.out.println("Line " + line + " : " + count + ", " + scdCount);
        if(scdCount > count) return scdCount;
        else return count;
    }
    public void result() {
        int repetition = 0;
        int max = 0;
        for(int i=0; i<this.cols; i++) {
            int auxMax = this.checkMaxV(i);
            if(auxMax > max) {
                max = auxMax;
                repetition = 0;
            }
            if(auxMax == max) repetition++;
        }
        for(int i=0; i<this.lines; i++) {
            int auxMax = this.checkMaxH(i);
            if(auxMax > max) {
                max = auxMax;
                repetition = 0;
            }
            if(auxMax == max) repetition++;
        }
        System.out.println(max + " " + repetition);
    }
}
class ED216 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int lines = scan.nextInt();
        int cols = scan.nextInt();
        Board initial = new Board(lines, cols);
        initial.read(scan);
        initial.result();
        scan.close();

    }
}