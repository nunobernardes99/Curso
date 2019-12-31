import java.util.*;
import java.lang.*;
class Arco {
    int no_final;
    int valor0;
    int valor1;
    
    Arco(int fim, int v0, int v1){
	no_final = fim;
	valor0  = v0;
	valor1 = v1;
    }

    int extremo_final() {
	return no_final;
    }

    int valor0_arco() {
	return valor0;
    }

    int valor1_arco() {
	return valor1;
    }

    void novo_valor0(int v) {
	valor0 = v;
    }

    void novo_valor1(int v) {
	valor1 = v;
    }
}


class No {
    //int label;
    LinkedList<Arco> adjs;

    No() {
	adjs = new LinkedList<Arco>();
    }
}


class Grafo2 {
    No verts[];
    int nvs, narcos;
			
    public Grafo2(int n) {
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
    
    public void insert_new_arc(int i, int j, int valor0, int valor1){
	verts[i].adjs.addFirst(new Arco(j,valor0,valor1));
        narcos++;
    }

    public Arco find_arc(int i, int j){
	for (Arco adj: adjs_no(i))
	    if (adj.extremo_final() == j) return adj;
	return null;
    }
}
class Vol6N {
	static int edmondsKarp(Grafo2 grafo, int s, int t, int valorAtualFluxo) {
		while(true) {
			int[] P = new int[grafo.num_vertices()+1];
			Arrays.fill(P, -1);
			P[s] = s;
			int[] M = new int[grafo.num_vertices()+1];
			M[s] = 9999999;
			Queue<Integer> Q = new LinkedList<Integer>();
			Q.offer(s);
			LOOP:
			while(!Q.isEmpty()) {
				int u = Q.poll();
				for(Arco adj = grafo.adjs_no(u)) {
					int w = adj.no_final;
					int cResidual = grafo.find_arc(u,w).valor0_arco() - grafo.find_arc(u,w).valor1_arco();
					if(cResidual > 0 &&  P[w] == -1) {
						P[w] = u;
						M[w] = Math.min(M[v], cResidual);
						if(w != t)
							Q.offer(w);
						else {
							while(P[w] != w) {
								u = P[w];
								grafo.find_arc(u,w).valor1_arco() += M[t];
								grafo.find_arc(w,u).valor1_arco() -= M[t];
								w = u;
							}
							break LOOP;
						}
					}
				}
			}
			if(P[t] == -1) {
				int sum = 0;
				for(int x : grafo.find_arc(x).valor0_arco())
					sum += x;
				return sum;
			}
		}
	}
	public static void main(String[] args) {
		Sanner scan = new Scanner(System.in);
		
	}
}
