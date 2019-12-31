/*
	TODOS OS ALGORITMOS QUE SERÂO USADOS NO TESTE PRÁTICO
	1 - Componentes conexas em grafos não dirigidos
	2 - Componentes fortemente conexas
	3 - Pesquisa de caminho de s para t (DFS ou BFS)
	4 - Acessibilidade em grafos a partir de um nó (DFS ou BFS)
	5 - Algoritmo de Dijkstra
	6 - Caminhos com número mínimo de ramos (BFS)
	7 - Árvore geradora com peso ótimo (Algoritmo Dijkstra/Kruskal)
	8 - Pesquisa em BFS ou DFS a partir de um nó num DAG
	9 - Caminho máximo num DAG
*/
class algoritmos {
	//BFS
	static void BFS(Grafo grafo) {
		boolean[] visitado = new boolean[grafo.num_vertices()+1];
		int[] pai = new int[grafo.num_vertices()+1];
		Queue<Integer> q = new LinkedList<>();
		for(int i=1; i<=grafo.num_vertices(); i++) {
			if(!visitado[w])
				BFS_Visit(v,grafo,q, visitado, pai);
		}
	}
	static void BFS_Visit(int s, Grafo grafo, Queue<Integer> q, boolean[] visitado, int[] pai) {
		visitado[s] = true;
		q.add(s);
		while(!q.isEmpty()) {
			int v = q.remove();
			for(Arco adj: grafo.adjs_no(v)) {
				int w = adj.no_final();
				if(!visitado[w]) {
					q.add(w);
					visitado[w] = true;
					pai[w] = v;
				}
			}
		}
	}

	//Distância mínima do nó s a cada nó
	static void BFS_Visit_Distancia(int s, Grafo grafo) {
		boolean[] visitado = new boolean[grafo.num_vertices()+1];
		int[] pai = new int[grafo.num_vertices()+1];
		int[] dist = new int[grafo.num_vertices()+1];
		Arrays.fill(dist,9999999);

		visitado[s] = true;
		dist[s] = 0;
		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		while(!q.isEmpty()) {
			int v = q.remove();
			for(Arco adj: grafo.adjs_no(v)) {
				int w = adj.no_final();
				if(!visitado[w]) {
					dist[w] = dist[v] + 1;
					q.add(w);
					visitado[w] = true;
					pai[w] = v;
				}
			}			
		}
	}

	//Visita grafo em profundidade
	static void DFS(Grafo grafo) {
		// -1 = branco; 0 = cinzento; 1 = preto
		int[] cor = new int[grafo.num_vertices()+1];
		Arrays.fill(cor, -1);
		int[] pai = new int[grafo.num_vertices()+1];
		for(int i=1; i<=grafo.num_vertices(); i++)
			if(cor[v] == -1)
				DFS_Visit(v, grafo, cor, pai);
	}
	static void DFS_Visit(int v, Grafo grafo, int[] cor, int[] pai) {
		cor[v] = 0;
		for(Arco adj: grafo.adjs_no(v)) {
			int w = adj.no_final;
			if(cor[w] == -1) {
				pai[w] = v;
				DFS_Visit(w,grafo,cor,pai);
			}
		}
		cor[v] = 1;
	}

	//Versão DFS que produz stack com os nós ordenados por ordem decrescente de tempo de finalização
	static Stack<Integer> DFS(Grafo grafo) {
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] visitado = new boolean[grafo.num_vertices()+1];
		for(int i=1; i<=grafo.num_vertices();i++) {
			if(!visitado[v])
				DFS_Visit(v,grafo,visitado,stack);
			return stack;
		}
	}
	static void DFS_Visit(int v, Grafo grafo, boolean[] visitado, Stack<Integer> stack) {
		visitado[v] = true;
		for(Arco adj: grafo.adjs_no(v)) {
			int w = adj.no_final;
			if(!visitado[w])
				DFS_Visit(w,grafo,visitado,stack);
			stack.push(v);
		}
	}

	//Kosaraju-Sharir
	static void kosarajuSharir(Grafo grafo, Grafo grafoT) {
		Stack<Integer> stack = new Stack<Integer>();
		stack = DFS(grafo);
		int[] cor = new int[grafo.num_vertices()+1];
		Arrays.fill(cor,-1); // -1 = branco
		while(!stack.empty()) {
			int v = stack.pop();
			if(cor[v] == -1)
				DFS_Visit(v,grafoT);
		}
	}

	//Prim
	static void Prim(Grafo grafo, int s) {
		int[] pai = new int[grafo.num_vertices()+1];
		int[] dist = new int[grafo.num_vertices()+1];
		Arrays.fill(dist,9999999);
		boolean[] ok = new boolean[grafo.num_vertices()+1];
		dist[s] = 0;
		Heapmin q = new Heapmin(dist, grafo.num_vertices());
		while(!q.isEmpty()) {
			int v = q.extractMin();
			ok[v] = true;
			for(Arco adj: grafo.adjs_no(v)) {
				int w = adj.no_final;
				int cur = grafo.find_arc(v,w).valor_arco();
				if(!ok[w] && cur < dist[w]) {
					dist[w] = cur;
					pai[w] = v;
					q.decreaseKey(w,dist[w]);
				}
			}
		}
	}

	//Dijkstra
	static void Dijkstra(Grafo grafo, int s) {
		int[] pai = new int[grafo.num_vertices()+1];
		int[] dist = new int[grafo.num_vertices()+1];
		Arrays.fill(dist,9999999);
		dist[s] = 0;
		Heapmin q = new Heapmin(dist, grafo.num_vertices());
		while(!q.isEmpty()) {
			int v = q.extractMin();
			for(Arco adj: grafo.adjs_no(v)) {
				int w = adj.no_final;
				int cur = grafo.find_arc(v,w).valor_arco();
				if(dist[v] + cur < dist[w]) {
					dist[w] = dist[v] + cur;
					pai[w] = v;
					q.decreaseKey(w,dist[w]);
				}
			}
		}
	}

	//Caminhos capacidade maxima (adaptação algoritmo dijkstra)
	static void CaminhosCapacidadeMaxima(Grafo grafo, int s) {
		int[] pai = new int[grafo.num_vertices()+1];
		int[] cap = new int[grafo.num_vertices()+1];
		cap[s] = 9999999;
		Heapmin q = new Heapmax(cap, grafo.num_vertices());
		while(!q.isEmpty()) {
			int v = q.extractMax();
			for(Arco adj: grafo.adjs_no(v)) {
				int w = adj.no_final;
				int cur = grafo.find_arc(v,w).valor_arco();
				if(Math.min(cap[v],cur) > cap[w]) {
					capw] = Math.min(cap[v],cur);
					pai[w] = v;
					q.increaseKey(w,dist[w]);
				}
			}
		}
	}

























}