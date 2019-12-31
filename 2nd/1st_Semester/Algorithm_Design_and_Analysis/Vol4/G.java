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

class G {
	static void graphCreator(int[][] matrix, int[] array, Grafo0 grafo) {
		for(int i=0; i<matrix.length; i++) {
			//System.out.println(Arrays.toString(matrix[i]));
			for(int j=0; j<matrix.length; j++) {
				if(array[i] != matrix[i][j]) {
					//System.out.println(array[i] + " " + matrix[i][j]);
					grafo.insert_new_arc(array[i], matrix[i][j]);
				} else
					break;
			}
		}
	}

	static boolean DFS(Grafo0 grafo, int n) {
		boolean visited[] = new boolean[n+1];
		boolean recStack[] = new boolean[n+1];

		for(int v=1; v<visited.length; v++) {
			if(DFS_Visit(v,grafo,visited, recStack))
				return true;
		}
		return false;
	}
	static boolean DFS_Visit(int v, Grafo0 grafo, boolean[] visited, boolean[] recStack) {
		
		if(recStack[v]) return true;

		if(visited[v]) return false;

		visited[v] = true;
		recStack[v] = true;

		for(Arco a: grafo.adjs_no(v)) {
			int w = a.extremo_final();
			//System.out.println(cor[w] + " " + w);
			if(DFS_Visit(w, grafo, visited, recStack))
				return true;
		}
		recStack[v] = false;
		return false;
	}

	static int depth(Grafo0 grafo, int[] depth) {
		int maxdepth=0;
		for(int s=1; s<depth.length; s++) {
			boolean[] visitados = new boolean[grafo.num_vertices()+1];
			Arrays.fill(visitados, false);
			visitados[s] = true;
			LinkedList<Integer> list = new LinkedList<Integer>();
			list.add(s);
			int v,w;
			while(!(list.isEmpty())) {
				v = list.remove();
				for(Arco a: grafo.adjs_no(v)) {
					w = a.extremo_final();
					if(!visitados[w]) {
						depth[w] = depth[v] + 1;
						if(depth[w] > maxdepth)
							maxdepth = depth[w];
						list.add(w);
						visitados[w] = true;
					}
				}
			}
		}
		System.out.println(Arrays.toString(depth));
		return maxdepth;
	}

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);

		int nmrTasks = scan.nextInt();
		int[][] matrix = new int[nmrTasks][nmrTasks];

		for(int i=0; i<matrix.length; i++)
			for(int j=0; j<matrix.length; j++)
				matrix[i][j] = scan.nextInt();

		int nmrResults = scan.nextInt();

		for(int i=0; i<nmrResults; i++) {
			Grafo0 grafo = new Grafo0(nmrTasks);
			int[] array = new int[nmrTasks];
			for(int j=0; j<nmrTasks; j++)
				array[j] = scan.nextInt();
			//cSystem.out.println(Arrays.toString(array));
			graphCreator(matrix, array, grafo);
			boolean isCicle = DFS(grafo,nmrTasks);
			if(isCicle)
				System.out.println("Indeterminado (nao Pareto-optima)");
			else {
				
				int[] depth = new int[nmrTasks+1];
				System.out.println(depth(grafo, depth));
			}
		}		
	}
}