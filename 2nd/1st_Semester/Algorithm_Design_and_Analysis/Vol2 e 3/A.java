import java.util.*;
import java.util.LinkedList;

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

class A {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int nmrNodes = scan.nextInt();
		int nmrRoots = scan.nextInt();

		int i=0;
		Grafo0 root = new Grafo0(20000);
		while(i<nmrRoots) {
			int sizeOfGraph = scan.nextInt();

			//System.out.println("sizeOfGraph" + sizeOfGraph);

			int nmbArcs = sizeOfGraph -1;
			int firstValue = scan.nextInt();

			//System.out.println("firstValue" + firstValue);

			for(int j=0; j<nmbArcs; j++) {
				int secondValue = scan.nextInt();
				//System.out.println("newfirstValue" + firstValue);
				//System.out.println("secondValue" + secondValue);

				if(root.find_arc(firstValue, secondValue) == null)
					root.insert_new_arc(firstValue, secondValue);
				firstValue = secondValue;
			}
			i++;
		}
		for(int y=1; y<=nmrNodes; y++)
			System.out.println(root.adjs_no(y).size());	
	}
}

//6 3
//4 1 2 5 4
//9 2 3 2 1 5 6 3 5 2
//5 3 1 6 5 2
