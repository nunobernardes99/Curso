import java.util.*;
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
class A_new {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);

		int nodes = scan.nextInt();
		int nmrAnalise = scan.nextInt();

		Grafo0 grafo = new Grafo0(20000);

		for(int i=0; i<nmrAnalise; i++) {
			int aux = scan.nextInt();
			int v1 = scan.nextInt();
			int v2;
			for(int j=0; j<aux-1; j++) {
				v2 = scan.nextInt();
				//System.out.println(v1 + " " + v2);
				if(grafo.find_arc(v1,v2) == null)
					//cSystem.out.println("Added " + v1 + " and " + v2);
					grafo.insert_new_arc(v1,v2);
				v1 = v2;
			}
		}
		for(int i=1; i<=nodes; i++)
			System.out.println(grafo.adjs_no(i).size());
	}
}