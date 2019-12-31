import java.util.*;
import java.util.LinkedList;

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


class B {
	public static void main(String[] args) {
		
		//Create Scanner
		Scanner scan = new Scanner(System.in);

		int numberOfNodes = scan.nextInt();
		int numberOfConnections = scan.nextInt();

		Grafo2 grafo = new Grafo2(numberOfConnections);

		int a = 0;
		while(a < numberOfConnections) {
			grafo.insert_new_arc(scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt());
			a++;
		}

		int numberOfReservations = scan.nextInt();

		int b = 0;
		while(b < numberOfReservations) {
			int numberOfPassengers = scan.nextInt();
			int numberOfPassNodes = scan.nextInt();

			int c=1;
			int firstValue = scan.nextInt();
			int secondValue;
			boolean isPossible = true;
			int[] array = new int[numberOfNodes + 1];
			while(c < numberOfPassNodes) {
				if(isPossible) {
					secondValue = scan.nextInt();
					array[firstValue] = secondValue;
					if(grafo.find_arc(firstValue, secondValue) != null) {
						if(numberOfPassengers > grafo.find_arc(firstValue, secondValue).valor0_arco()) {
							isPossible = false;
							System.out.println("Sem lugares suficientes em (" + firstValue + "," + secondValue + ")");
							if(scan.hasNextLine()) scan.nextLine();
						}

					} else {
						isPossible = false;
						System.out.println("(" + firstValue + "," + secondValue + ") inexistente");
						if(scan.hasNextLine()) scan.nextLine();
					}
					firstValue = secondValue;
				}
				c++;
			}
			b++;

			if(isPossible) {
				int vPay = 0;
				int d;
				for(d=1; d< array.length; d++) {
					if(array[d] > 0) {
						vPay = vPay + numberOfPassengers * grafo.find_arc(d, array[d]).valor1_arco();
						grafo.find_arc(d, array[d]).novo_valor0(grafo.find_arc(d, array[d]).valor0_arco() - numberOfPassengers);
					}
				}
				System.out.println("Total a pagar: " + vPay);
			}
		}	
	}
}





















