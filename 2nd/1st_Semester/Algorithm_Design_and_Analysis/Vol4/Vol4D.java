import java.util.*;
import java.lang.*;
class Arco {
    int no_final;
    int valor;
    
    Arco(int fim, int v){
	no_final = fim;
	valor = v;
    }

    int extremo_final() {
	return no_final;
    }

    int valor_arco() {
	return valor;
    }

    void novo_valor(int v) {
	valor = v;
    }
}


class No {
    //int label;
    LinkedList<Arco> adjs;

    No() {
	adjs = new LinkedList<Arco>();
    }
}


class Grafo {
    No verts[];
    int nvs, narcos;
			
    public Grafo(int n) {
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
    
    public void insert_new_arc(int i, int j, int valor_ij){
	verts[i].adjs.addFirst(new Arco(j,valor_ij));
        narcos++;
    }

    public Arco find_arc(int i, int j){
	for (Arco adj: adjs_no(i))
	    if (adj.extremo_final() == j) return adj;
	return null;
    }
}

class Vol4D {
	static void resolve(int[] array, Grafo grafo, int origem, int destino, int lMax) {
		int min=Integer.MAX_VALUE;
		if(array[0]!=origem || array[array.length-1]!=destino) {
			System.out.println("Nao");
			return;
		} else {
			for(int i=0; i<array.length-1; i++) {
				if(grafo.find_arc(array[i], array[i+1]) != null) {
					int w = grafo.find_arc(array[i], array[i+1]).valor_arco();
					min = Math.min(min, w);
				} else {
					System.out.println("Nao");
					return;
				}
			}
			if(min > lMax)
				min = lMax;
			System.out.println(min);
		}
		return;
	}
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int nos = scan.nextInt();
		int lMin = scan.nextInt();
		int lMax = scan.nextInt();
		int cMin = scan.nextInt();
		int cMax = scan.nextInt();
		int hMin = scan.nextInt();
		int origem = scan.nextInt();
		int destino = scan.nextInt();


		Grafo grafo = new Grafo(nos);

		int fV = scan.nextInt();
		while( fV != -1 ) {
			int sV = scan.nextInt();
			int l = scan.nextInt();
			int c = scan.nextInt();
			int h = scan.nextInt();
			if(l>=lMin && c>=cMin && h>=hMin) {
				grafo.insert_new_arc(fV, sV, l);
				grafo.insert_new_arc(sV, fV, l);
			}
			fV = scan.nextInt();
		}

		int nmrNos = scan.nextInt();
		while(nmrNos != 0) {
			int[] array = new int[nmrNos];
			for(int i=0; i<array.length; i++)
				array[i] = scan.nextInt();
			resolve(array, grafo, origem, destino, lMax);
			nmrNos = scan.nextInt();
		}
	}
}



















