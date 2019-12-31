import java.util.*;
import java.lang.*;
class Geometry {
    public static int verificaPontosSemRec(Point[] pontos, Rectangle[] rectangulos) {
        int ctr = 0;
        for(int i=0; i<pontos.length; i++) {
            int auxCtr = 0;
            for(int j=0; j<rectangulos.length; j++)
                if(rectangulos[j].pointInside(pontos[i])) auxCtr++;
            if(auxCtr==0) ctr++;
        }
        return ctr;
    }
    public static int verificaRectSemPontos(Point[] pontos, Rectangle[] rectangulos) {
        int ctr = 0;
        for(int i=0; i<rectangulos.length; i++) {
            int auxCtr = 0;
            for(int j=0; j<pontos.length; j++)
                if(rectangulos[i].pointInside(pontos[j])) auxCtr++;
            if(auxCtr==0) ctr++;
        }
        return ctr;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int nmrPoints = scan.nextInt();
        Point[] pontos = new Point[nmrPoints];
        for(int i=0; i<nmrPoints; i++)
            pontos[i] = new Point(scan.nextInt(), scan.nextInt());

        int nmrRectangles = scan.nextInt();
        Rectangle[] rectangulos = new Rectangle[nmrRectangles];
        for(int i=0; i<nmrRectangles; i++) {
            Point p1 = new Point(scan.nextInt(), scan.nextInt());
            Point p2 = new Point(scan.nextInt(), scan.nextInt());
            rectangulos[i] = new Rectangle(p1,p2);
        }

        scan.close();
        int fst = verificaPontosSemRec(pontos, rectangulos);
        int scd = verificaRectSemPontos(pontos, rectangulos);
        
        System.out.println(fst + " " + scd);

        System.out.printf("%d");
    }
}