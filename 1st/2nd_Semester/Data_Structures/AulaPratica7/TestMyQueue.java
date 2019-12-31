// -----------------------------------------------------------
// Estruturas de Dados 2018/2019 (CC1007) - DCC/FCUP
// http://www.dcc.fc.up.pt/~pribeiro/aulas/edados1819/
// -----------------------------------------------------------
// Exemplo de utilizacao do TAD Fila
// Ultima alteracao: 06/04/2018
// -----------------------------------------------------------

public class TestMyQueue {
   public static void main(String[] args) {

      // Criacao da fila
      MyQueue<Integer> a = new LinkedListQueue<Integer>();
      MyQueue<Integer> b = new LinkedListQueue<Integer>();
      a.enqueue(10);
      a.enqueue(20);
      a.enqueue(30);
      a.enqueue(40);
      a.enqueue(50);
      a.enqueue(60);
      a.enqueue(70);

      b.enqueue(1);
      b.enqueue(2);
      b.enqueue(3);
      b.enqueue(44);
      b.enqueue(50);
      b.enqueue(60);
      b.enqueue(70);
      b.enqueue(90);

      System.out.println(a);
      System.out.println(b);
      MyQueue<Integer> result = new LinkedListQueue<Integer>();
      result = result.merge(a,b);
      System.out.println(result);




      /*// Exemplo de insercao de elementos na fila
      for (int i=1; i<=8; i++)
         q.enqueue(i); // insere i no final da fila
      System.out.println(q);

      // Exemplo de remocao de elementos da fila
      for (int i=0; i<4; i++) {
         int aux = q.dequeue(); // retira o elemento no inicio da fila
         System.out.println("q.dequeue() = " + aux);
      }
      System.out.println(q);

      // Exemplo de utilizacao dos outros metodos
      System.out.println("q.size() = " + q.size());
      System.out.println("q.isEmpty() = " + q.isEmpty());
      System.out.println("q.first() = " + q.first());*/
   }
}
