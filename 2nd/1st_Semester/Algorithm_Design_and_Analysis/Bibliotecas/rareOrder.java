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

class rareOrder {
	static void parLetras(String f1, String f2, Grafo0 grafo) {

		for(int i=0; f1[i]!='\0' && f2[i]!='\0'; i++) {
			if(f1[i] != f2[i]) {
				grafo.insert_new_arc(f1[i], f2[i]);
		}
		return;
	}
	static void letrasOcorrem(char[] seq, boolean[] letras) {
		
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Grafo0 grafo = new Grafo0(26);
		char[] letras = new char[27];
		Arrays.fill(letras, true);

		String f1 = scan.next();
		String f2;
		while(!scan.hasNext("#")) {
			f2 = scan.next();
			//System.out.println(f1 + " " + f2);
			f1= f2;
			parLetras(f1,f2,grafo);
		}


	}
}