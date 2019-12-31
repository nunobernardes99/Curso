import java.util.*;
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

class B_new {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int nodes = scan.nextInt();
		int nodePath = scan.nextInt();

		Grafo2 grafo = new Grafo2(nodes);
		for(int i=0; i<nodePath; i++)
			grafo.insert_new_arc(scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt());

		int test = scan.nextInt();
		//System.out.println(test); --------- returns 5

		for(int i=0; i<test; i++) {
			int persons = scan.nextInt();
			int p = scan.nextInt();
			int total = 0;
			int v1 = scan.nextInt();
			int v2;
			boolean possible = true;
			for(int j=1; j<p; j++) {
				v2 = scan.nextInt();
				System.out.println(possible + ", comparing " + v1 + " and " + v2);
				if(possible) {
					if(grafo.find_arc(v1,v2).valor0_arco() >= persons) {
						total = total + persons * grafo.find_arc(v1,v2).valor1_arco();
						grafo.find_arc(v1,v2).novo_valor0(grafo.find_arc(v1,v2).valor0_arco() - persons);
					} else {
						possible = false;
						System.out.println("Sem lugares suficientes em ("+v1+","+v2+")");
						break;
					}
					if(grafo.find_arc(v1,v2) == null) {
						System.out.println("(" + v1 + "," + v2 + ") inexistente");
						possible = false;
						break;
					}
				}
				v1 = v2;
				if(!possible)
					break;
			}
			System.out.println(possible);
			if(possible)
				System.out.println("Total a pagar: " + total);
		}
	}
}