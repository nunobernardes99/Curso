import java.lang.*;
import java.util.*;

class Qnode {
    int vert;
    int vertkey;
    
    Qnode(int v, int key) {
    vert = v;
    vertkey = key;
    }
}

class Heapmax {
    private static int posinvalida = 0;
    int sizeMax,size;
    
    Qnode[] a;
    int[] pos_a;

    Heapmax(int vec[], int n) {
    a = new Qnode[n + 1];
    pos_a = new int[n + 1];
    sizeMax = n;
    size = n;
    for (int i = 1; i <= n; i++) {
        a[i] = new Qnode(i,vec[i]);
        pos_a[i] = i;
    }

    for (int i = n/2; i >= 1; i--)
        heapify(i);
    }

    boolean isEmpty() {
    if (size == 0) return true;
    return false;
    }

    int extractMax() {
    int vertv = a[1].vert;
    swap(1,size);
    pos_a[vertv] = posinvalida;  // assinala vertv como removido
    size--;
    heapify(1);
    return vertv;
    }

    void increaseKey(int vertv, int newkey) {

    int i = pos_a[vertv];
    a[i].vertkey = newkey;

    while (i > 1 && compare(i, parent(i)) > 0) { 
        swap(i, parent(i));
        i = parent(i);
    }
    }


    void insert(int vertv, int key)
    { 
    if (sizeMax == size)
        new Error("Heap is full\n");
    
    size++;
    a[size].vert = vertv;
    pos_a[vertv] = size;   // supondo 1 <= vertv <= n
    increaseKey(vertv,key);   // aumenta a chave e corrige posicao se necessario
    }

    void write_heap(){
    System.out.printf("Max size: %d\n",sizeMax);
    System.out.printf("Current size: %d\n",size);
    System.out.printf("(Vert,Key)\n---------\n");
    for(int i=1; i <= size; i++)
        System.out.printf("(%d,%d)\n",a[i].vert,a[i].vertkey);
    
    System.out.printf("-------\n(Vert,PosVert)\n---------\n");

    for(int i=1; i <= sizeMax; i++)
        if (pos_valida(pos_a[i]))
        System.out.printf("(%d,%d)\n",i,pos_a[i]);
    }
    
    private int parent(int i){
    return i/2;
    }
    private int left(int i){
    return 2*i;
    }
    private int right(int i){
    return 2*i+1;
    }

    private int compare(int i, int j) {
    if (a[i].vertkey < a[j].vertkey)
        return -1;
    if (a[i].vertkey == a[j].vertkey)
        return 0;
    return 1;
    }

  
    private void heapify(int i) {
    int l, r, largest;

    l = left(i);
    if (l > size) l = i;

    r = right(i);
    if (r > size) r = i;

    largest = i;
    if (compare(l,largest) > 0)
        largest = l;
    if (compare(r,largest) > 0)
        largest = r;
    
    if (i != largest) {
        swap(i, largest);
        heapify(largest);
    }
    
    }

    private void swap(int i, int j) {
    Qnode aux;
    pos_a[a[i].vert] = j;
    pos_a[a[j].vert] = i;
    aux = a[i];
    a[i] = a[j];
    a[j] = aux;
    }
    
    private boolean pos_valida(int i) {
    return (i >= 1 && i <= size);
    }
}
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
class Vol4L {
    static void resolve(Grafo grafo, int s, int max) {
        int[] pai = new int[grafo.num_vertices()+1];
        int[] cap = new int[grafo.num_vertices()+1];
        cap[s] = 9999999;
        Heapmax q = new Heapmax(cap, grafo.num_vertices());
        while(!q.isEmpty()) {
            int v = q.extractMax();
            for(Arco adj: grafo.adjs_no(v)) {
                int w = adj.no_final;
                int cur = grafo.find_arc(v,w).valor_arco();
                if(Math.min(cap[v], cur) > cap[w] && cur != -1) {
                    cap[w] = Math.min(cap[v], cur);
                    pai[w] = v;
                    q.increaseKey(w, cap[w]);
                }
            }
        }

        boolean[] visitado = new boolean[grafo.num_vertices()+1];
        visitado[s] = true;
        boolean isPossible = false;

        for(int i=1; i<=grafo.num_vertices(); i++) {
            if(!visitado[i] && cap[i] < max) {
                System.out.println("No " + i + ": " + cap[i]);
                visitado[i] = true;
                isPossible = true;
            }
        }

        if(!isPossible)
            System.out.println("Ok todos destinos!");


        //System.out.println(Arrays.toString(cap));
        //System.out.println(Arrays.toString(pai));

    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int origem = scan.nextInt();
        int min = scan.nextInt();
        int max = scan.nextInt();

        int rotas = scan.nextInt();
        int nos = scan.nextInt();

        Grafo grafo = new Grafo(nos);
        for(int i=0; i<rotas; i++) {
            int roots = scan.nextInt();
            int fV = scan.nextInt();
            for(int j=1; j<roots; j++) {
                int peso = scan.nextInt();
                int sV = scan.nextInt();
                if(grafo.find_arc(fV, sV) == null) {
                    //System.out.println(fV + ", " + sV + ", com valor: " + max);
                    if(grafo.find_arc(fV, sV) == null) {
                        grafo.insert_new_arc(fV, sV, max);
                        grafo.insert_new_arc(sV, fV, max);
                    }
                }
                fV = sV;
            }
        }

        int casos = scan.nextInt();
        for(int i=0; i<casos; i++) {
            int noOrigem = scan.nextInt();
            int noDestino = scan.nextInt();
            int hMax = scan.nextInt();
            int cMax = scan.nextInt();
            if((min > hMax && hMax != -1) || (min > cMax && cMax != -1)) {
                if(grafo.find_arc(noOrigem, noDestino) != null) {
                    System.out.println("Valor de " + noOrigem + " e " + noDestino + " alterados para -1 ");
                    grafo.find_arc(noOrigem, noDestino).novo_valor(-1);
                    grafo.find_arc(noDestino, noOrigem).novo_valor(-1);
                    //System.out.println("Arco: " + noOrigem  + ", " + noDestino + ", passou a ter valor: " + grafo.find_arc(noOrigem, noDestino).valor_arco());
                }
            } else {
                if(min <= hMax && cMax == -1) {
                    System.out.println("Valor de " + noOrigem + " e " + noDestino + " alterados para " + hMax);
                    grafo.find_arc(noOrigem, noDestino).novo_valor(hMax);
                    grafo.find_arc(noDestino, noOrigem).novo_valor(hMax);
                }
                if(min <= cMax && hMax == -1) {
                    System.out.println("Valor de " + noOrigem + " e " + noDestino + " alterados para " + cMax);
                    grafo.find_arc(noOrigem, noDestino).novo_valor(cMax);
                    grafo.find_arc(noDestino, noOrigem).novo_valor(cMax);
                }
                if(min <= cMax && min <=hMax) {
                    int aux = Math.min(cMax, hMax);
                    System.out.println("Valor de " + noOrigem + " e " + noDestino + " alterados para " + aux);
                    grafo.find_arc(noOrigem, noDestino).novo_valor(aux);
                    grafo.find_arc(noDestino, noOrigem).novo_valor(aux);
                }
            }
        }

        resolve(grafo, origem, max);

    }
}