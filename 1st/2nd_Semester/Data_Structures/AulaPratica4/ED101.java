import java.lang.*;
import java.util.*;
class Tartaruga {
    private char m[][];
    int lines, cols, incx , incy, x, y;
    private boolean penDown;
    Tartaruga (int l, int c) {
        lines = l;
        cols = c;
        m = new char[l][c];
        x = 0;
        y = 0;
        incx = 0;
        incy = 1;
        penDown = false;
    }
    public void readMatrix() {
        for(int i=0; i<lines; i++)
            for(int j=0; j<cols; j++)
                m[i][j] = '.';
    }
    public void up() { penDown = false; }
    public void down() { penDown = true; }
    public void forward(int pos) {
        int size = valueForward(pos);
        System.out.println("A mover-se " + size + " posições");
        for (int i=0, xx=x, yy=y; i<size; i++, xx+=incx, yy+=incy)
            if (penDown) m[xx][yy] = '*';
        x += size*incx;
        y += size*incy;
    }
    private int valueForward(int pos) {
        int aux = Math.abs(Math.abs(x*incx + y*incy) - Math.abs(pos*incx + pos*incy));
        System.out.println(aux);
        if(aux >= 0) return aux;
        else return Math.abs(Math.abs(lines*incx + cols*incy) - Math.abs(x*incx + y*incy))-1;
    }
    public void left() {
        if(incx == 0 && incy == 1) {incx = -1; incy = 0;}
        else if(incx == -1 && incy == 0) {incx = 0; incy = -1;}
        else if(incx == 0 && incy == -1) {incx = 1; incy = 0;}
        else {incx = 0; incy = 1;}
    }
    public void right() {
        if(incx == 0 && incy == 1) {incx = 1; incy = 0;}
        else if(incx == 1 && incy == 0) {incx = 0; incy = -1;}
        else if(incx == 0 && incy == -1) {incx = -1; incy = 0;}
        else {incx = 0; incy = 1;}
    }

    public void writeFinal() {
        int i, j;
        for(i=0; i<lines; i++) {
            for(j=0; j<cols-1; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println(m[i][j]);
        }
    }
    public void statistic() {
        int maxLines = 0;
        int maxCols = 0;
        for(int i=0; i<lines; i++)
            maxLines += hasPoint(i, 0 , 1);
        for(int i=0; i<cols; i++)
            maxCols += hasPoint(i, 1, 0);
        System.out.println(100*nmrPoints()/(lines*cols) + " " + maxLines + " " + maxCols);
    }
    private int hasPoint(int k, int incx, int incy) {
        //System.out.println("");
        for (int i=0, xx=k*incx, yy=k*incy; i<lines*incx+cols*incy; i++, xx+=incx, yy+=incy) {
            //System.out.println("Comparar m " + xx + ", com " + yy + ": " + m[xx][yy]);
            if(m[xx][yy] == '*') return 0;
        }
        return 1;
    }
    private int nmrPoints() {
        int sum = 0;
        for(int i=0; i<lines; i++) {
            for(int j=0; j<cols; j++)
                if(m[i][j] == '*') sum++;
        }
        return sum;
    }
    public String pattern(char[][] pattern, int pline, int pcol) {
        for(int i=0; i<lines-pline+1; i++) {
            for(int j=0; j<cols-pcol+1; j++) {
                if(this.match(pattern, i, j, pline, pcol))
                    return "Sim";
            }
            //System.out.println("");
        }

       return "Nao";
    }
    private boolean match(char[][] pattern, int beginLine, int beginCol, int line, int col) {
        for(int i=0; i<line; i++) {
            for(int j=0; j<col; j++) {
                //System.out.println("Pattern em " + i + ", " + j + ", " + pattern[i][j]);
                //System.out.println("Normal em " + (beginLine+i) + ", " + (beginCol+j));
                if(m[beginLine+i][beginCol+j] != pattern[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
class ED101 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int flag = scan.nextInt();
        int lins = scan.nextInt();
        int cols = scan.nextInt();
        Tartaruga lazy = new Tartaruga(lins, cols);
        lazy.readMatrix();
        String instruction = scan.next();
        while(!instruction.equals("end")) {
            int possibleValue = 0;
            System.out.println("Tartaruga em: " + lazy.x + ", " + lazy.y);
            System.out.println("Direção: " + lazy.incx + ", " + lazy.incy);
            if(scan.hasNextInt()) possibleValue = scan.nextInt();
            System.out.println(instruction + " " + possibleValue);
            if(instruction.equals("D")) lazy.down();
            else if(instruction.equals("U")) lazy.up();
            else if(instruction.equals("F")) lazy.forward(possibleValue);
            else if(instruction.equals("L")) lazy.left();
            else lazy.right();
            instruction = scan.next();

            System.out.println("");
        }
        if(flag == 0) lazy.writeFinal();
        else if(flag == 1) lazy.statistic();
        else {
            int patternLine = scan.nextInt();
            int patternCol = scan.nextInt();
            char[][] pattern = new char[patternLine][patternCol];
            String p;
            for(int i=0; i<patternLine; i++) {
                for(int j=0; j<patternCol; j++) {
                    p = scan.next();
                    //System.out.print(p);
                    pattern[i][j] = p.charAt(0);
                }
                //System.out.println();
            }
            System.out.println(lazy.pattern(pattern, patternLine, patternCol));
        }
        //lazy.writeFinal();
        scan.close();
    }
}