// -----------------------------------------------------------
// Estruturas de Dados 2018/2019 (CC1007) - DCC/FCUP
// http://www.dcc.fc.up.pt/~pribeiro/aulas/edados1819/
// -----------------------------------------------------------
// Implementacao do TAD Fila usando lista duplamente ligada
// Ultima alteracao: 06/04/2018
// -----------------------------------------------------------

public class LinkedListQueue<T> implements MyQueue<T> {
   private DoublyLinkedList<T> list;

   LinkedListQueue() { list = new DoublyLinkedList<T>();}
   
   public void enqueue(T v) { list.addLast(v); }   
   public T dequeue() {
      T ans = list.getFirst();
      list.removeFirst();
      return ans;
   }
   public T first() { return list.getFirst();}
   public int size() {return list.size();}
   public boolean isEmpty() {return list.isEmpty();}

   public MyQueue<Integer> merge(MyQueue<Integer> a, MyQueue<Integer> b) {
      MyQueue<Integer> q = new LinkedListQueue<Integer>();
      int aV = a.dequeue();
      int bV = b.dequeue();
      while(!a.isEmpty() && !b.isEmpty()) {
          if(bV < aV) {
              q.enqueue(bV);
              bV = b.dequeue();
          } else if(aV < bV) {
              q.enqueue(aV);
              aV = a.dequeue();
          } else {
             q.enqueue(aV);
             q.enqueue(bV);
             aV = a.dequeue();
             bV = b.dequeue();
          }
      }
      if(aV > bV) {
         q.enqueue(bV);
         q.enqueue(aV);
      } else {
         q.enqueue(aV);
         q.enqueue(bV);
      }
      if(a.isEmpty() && !b.isEmpty()) {
         while(!b.isEmpty()) q.enqueue(b.dequeue());
      } else if(!a.isEmpty() && b.isEmpty()) {
         while(!a.isEmpty()) q.enqueue(a.dequeue());
      }
      return q;
   }

   public String toString() {return list.toString();}
}
