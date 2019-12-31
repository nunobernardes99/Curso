import java.util.*;
import java.lang.*;
class Palavra implements Comparable<Palavra>{
    String nome;
    int ctr;
    Palavra(String s) {
        nome = s;
        ctr = 1;
    }

    public String getNome() {
        return nome;
    }
    public int getCtr() {
        return ctr;
    }

    public int compareTo(Palavra p) {
        return nome.compareTo(p.nome);
    }

    public String toString() {
        return nome + ": " + ctr;
    }
}
class ED172 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        
        while(scan.hasNext()) {
            String s = scan.next();
            
        }

        scan.close();

    }

}