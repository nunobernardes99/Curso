import java.util.*;
import java.lang.*;
public class No {
    String label;
    No[] filhos;
    String[] nFilhos;
    int counter;
    // Construtor que recebe um atributo
    No(Atributos atr) {
        label = atr.nome;
        filhos = new No[atr.tipos.size()];
        nFilhos = new String[atr.tipos.size()];
        for(int i=0; i<nFilhos.length; i++)
            nFilhos[i] = atr.tipos.get(i);
    }
    // Construtor que recebe nome e contador
    No(String s, int ctr) {
        label = s;
        counter = ctr;
    }
    // Adiciona um ramo, que é um atributo
    void addBranch(No tree, int i) {
        filhos[i] = tree;
    }
    // Imprimir os filhos e o nome do No
    public String toString() {
        return Arrays.toString(nFilhos) + " " + label;
    }
}