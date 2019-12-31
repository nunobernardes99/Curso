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
class Vol4E {
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
                    //System.out.println(dist[w]);
                    q.add(w);
                    visitado[w] = true;
                    pai[w] = v;
                }
            }
        }
        if(dist[destino] == 0) {
            System.out.println("Impossivel");
            return;
        } else {
            System.out.println(dist[destino]);
            return;
        }
        //System.out.println(Arrays.toString(visitado));
        //System.out.println(Arrays.toString(pai));
        //System.out.println(Arrays.toString(dist));

    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int nos = scan.nextInt();
        Grafo0 grafo = new Grafo0(nos);

        int lMin = scan.nextInt();
        int lMax = scan.nextInt();
        int cMin = scan.nextInt();
        int cMax = scan.nextInt();
        int hMin = scan.nextInt();

        int origem = scan.nextInt();
        int destino = scan.nextInt();

        int fV = scan.nextInt();
        while(fV != -1) {
            int sV = scan.nextInt();
            int l = scan.nextInt();
            int c = scan.nextInt();
            int h = scan.nextInt();
            if(lMin <= l && cMin <= c && hMin <= h) {
                grafo.insert_new_arc(fV, sV);
                grafo.insert_new_arc(sV, fV);
            }
            fV = scan.nextInt();
        }
        //System.out.println(fV);
        //System.out.println(grafo.find_arc(4,6));

        resolve(grafo, origem, destino);

        
    }
}























