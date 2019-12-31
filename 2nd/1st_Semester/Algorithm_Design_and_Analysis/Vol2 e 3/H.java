import java.util.*;
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
class H {
	static int avaliacao(int[] aux, int origem, int destino, int nmrElementosGrupo, Grafo grafo) {

		boolean foundOrigin = false;
		boolean foundDestiny = false;
		int problemCounter = 0;
		int i=1;
		int pV = aux[i-1];
		for(i=1; i<aux.length-1; i=i+2) {
			int nL = aux[i];
			int sV = aux[i+1];
			if(grafo.find_arc(pV, sV).valor_arco() == 1)
				problemCounter++;
			if(pV == origem)
				foundOrigin=true;
			if(foundOrigin == true) {
				if(nmrElementosGrupo > nL)
					return 200000;
				if(sV==destino)
					return problemCounter;
			}
			pV = sV;
		}
		return 200000;
	}

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);

		//Information about the reserve
		int nmrElementosGrupo = scan.nextInt();
		int origem = scan.nextInt();
		int destino = scan.nextInt();
		//Informatio about the graph
		int nmrNos = scan.nextInt();
		int nmrRotas = scan.nextInt();

		Grafo grafo = new Grafo(nmrNos);
		for(int i=0; i<nmrRotas; i++)
			grafo.insert_new_arc(scan.nextInt(), scan.nextInt(), scan.nextInt());

		int rotas = scan.nextInt();
		int[] resultado = new int[rotas+1];
		resultado[0] = 200000;

		int t=200000;
		int index=-1;
		for(int i=1; i<resultado.length; i++) {
			int nmrNosAva = scan.nextInt();
			int size = nmrNosAva*2;
			int[] aux = new int[size-1];
			for(int j=0; j<aux.length; j++)
				aux[j] = scan.nextInt();
			if(avaliacao(aux, origem, destino, nmrElementosGrupo, grafo) < t) {
				t = avaliacao(aux, origem, destino, nmrElementosGrupo, grafo);
				index = i;
			}
		}

		if(t!=200000)
			System.out.println("Reserva na rota " + index + ": " + t);
		else
			System.out.println("Impossivel");
	}
}