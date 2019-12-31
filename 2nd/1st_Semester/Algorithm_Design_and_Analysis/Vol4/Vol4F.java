import java.util.*;
import java.lang.*;
class Arco {
    int no_final;
    
    Arco(int fim){
    no_final = fim;
    }

    int extremo_final() {
    return no_final;
    }
}


class No {
    //int label;
    LinkedList<Arco> adjs;

    No() {
    adjs = new LinkedList<Arco>();
    }
}


class Grafo0 {
    No verts[];
    int nvs, narcos;
            
    public Grafo0(int n) {
    nvs = n;
    narcos = 0;
    verts  = new No[n+1];
    for (int i = 0 ; i <= n ; i++)
        verts[i] = new No();
        // para vertices numerados de 1 a n (posicao 0 nao vai ser usada)
    }
    
    public int num_vertices(){
    return nvs;
    }

    public int num_arcos(){
    return narcos;
    }

    public LinkedList<Arco> adjs_no(int i) {
    return verts[i].adjs;
    }
    
    public void insert_new_arc(int i, int j){
    verts[i].adjs.addFirst(new Arco(j));
        narcos++;
    }

    public Arco find_arc(int i, int j){
    for (Arco adj: adjs_no(i))
        if (adj.extremo_final() == j) return adj;
    return null;
    }
}


class Vol4F {
    static void resolve(Grafo0 grafo, int origem, int destino) {
        boolean[] visitado = new boolean[grafo.num_vertices() +1];
        int[] pai = new int[grafo.num_vertices() + 1];
        int[] dist = new int[grafo.num_vertices() + 1];
        visitado[origem] = true;
        dist[origem] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(origem);
        while(!q.isEmpty()) {
            int v = q.remove();
            for(Arco adj: grafo.adjs_no(v)) {
                int w = adj.no_final;
                if(!visitado[w]) {
                    dist[w] = dist[v] + 1;
                    q.add(w);
                    visitado[w] = true;
                    pai[w] = v;
                }
            }
        }
        if(dist[destino] == 0) {
            System.out.println("Nao");
            return;
        } else {
            System.out.println("Sim " + dist[destino]);
            return;
        }

    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int tMin = scan.nextInt();
        int tMax = scan.nextInt();
        int origem = scan.nextInt();
        int destino = scan.nextInt();

        int nos = scan.nextInt();
        int ramos = scan.nextInt();
        Grafo0 grafo = new Grafo0(nos);

        for(int i=0; i<ramos; i++) {
            int fV = scan.nextInt();
            int sV = scan.nextInt();
            int temp = scan.nextInt();
            int custo = scan.nextInt();
            if(temp >= tMin && temp <= tMax) {
                grafo.insert_new_arc(fV, sV);
                grafo.insert_new_arc(sV, fV);
            }
        }

        resolve(grafo, origem, destino);    
    }
}






















