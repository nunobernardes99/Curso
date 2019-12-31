// -----------------------------------------------------------
// Estruturas de Dados 2018/2019 (CC1007) - DCC/FCUP
// http://www.dcc.fc.up.pt/~pribeiro/aulas/edados1819/
// -----------------------------------------------------------
// Lista ligada simples
// Ultima alteracao: 03/04/2018
// -----------------------------------------------------------

public class SinglyLinkedList<T> {
   private Node<T> first;    // Primeiro no da lista
   private int size;         // Tamanho da lista

   // Construtor (cria lista vazia)
   SinglyLinkedList() {
      first = null;
      size = 0;
   }

   // Retorna o tamanho da lista
   public int size() {
      return size;
   }

   // Devolve true se a lista estiver vazia ou falso caso contrario
   public boolean isEmpty() {
      return (size == 0);
   }
   
   // Adiciona v ao inicio da lista
   public void addFirst(T v) {
      Node<T> newNode = new Node<T>(v, first); 
      first = newNode;
      size++;
   }

   // Adiciona v ao final da lista
   public void addLast(T v) {
      Node<T> newNode = new Node<T>(v, null); 
      if (isEmpty()) {
         first = newNode;
      } else {
         Node<T> cur = first;
         while (cur.getNext() != null)
            cur = cur.getNext();
         cur.setNext(newNode);         
      }
      size++;
   }

   // Retorna o primeiro valor da lista (ou null se a lista for vazia)
   public T getFirst() {
      if (isEmpty()) return null;
      return first.getValue();
   }

   // Retorna o ultimo valor da lista (ou null se a lista for vazia)
   public T getLast() {
      if (isEmpty()) return null;
      Node<T> cur = first;
      while (cur.getNext() != null)
         cur = cur.getNext();
      return cur.getValue();      
   }

   // Remove o primeiro elemento da lista (se for vazia nao faz nada)
   public void removeFirst() {
      if (isEmpty()) return;
      first = first.getNext();
      size--;
   }

   // Remove o ultimo elemento da lista (se for vazia nao faz nada)
   public void removeLast() {
      if (isEmpty()) return;
      if (size == 1) {
         first = null;
      } else {
         // Ciclo com for e uso de de size para mostrar alternativa ao while
         Node<T> cur = first;
         for (int i=0; i<size-2; i++)
            cur = cur.getNext();
         cur.setNext(cur.getNext().getNext());
      }
      size--;
   }
   
   // Converte a lista para uma String
   public String toString() {
      String str = "{";      
      Node<T> cur = first;
      while (cur != null) {
         str += cur.getValue();
         cur = cur.getNext();
         if (cur != null) str += ",";                     
      }      
      str += "}";
      return str;
   }

   // Devoler elemento numa dada posição
   public T get(int pos) {
      if(pos<0 || pos>=size) return null;
      Node<T> cur = first;
      for(int i=0; i<pos; i++)
         cur = cur.getNext();
      return cur.getValue();
   }

   // Remover elemento numa dada posição
   public T remove(int pos) {
      if(pos<0 || pos>=size) return null;
      Node<T> cur=first;
      T value;
      if(pos == 0) {
         value = first.getValue();
         first = first.getNext();
      } else {
         for(int i=0; i<pos-1; i++)
            cur = cur.getNext();
         value = cur.getNext().getValue();
         cur.setNext(cur.getNext().getNext());
      }
      size--;
      return value;
   }

   // Cópia de uma lista
   public SinglyLinkedList<T> copy() {
      SinglyLinkedList<T> list = new SinglyLinkedList<T>();
      Node<T> cur = first;
      for(int i=0; i<size; i++) {
         list.addLast(cur.getValue());
         cur = cur.getNext();
      }
      return list;
   }

   // Duplicar elementos
   public void duplicate() {
      int ctr = size;
      for(int i=0; i<ctr; i++) {
         T value = getFirst();
         removeFirst();
         addLast(value);
         addLast(value);
      }
   }

   // Contando elementos
   public int count(T value) {
      if(isEmpty()) return 0;
      int ctr = 0;
      for(int i=0; i<size; i++)
         if(get(i).equals(value)) ctr++;
      return ctr;
   }

   // Removendo todas as ocorrências de um elemento
   public void removeAll(T value) {
      if(isEmpty()) return;
      int i=0;
      while(get(i)!=null) {
         if(get(i).equals(value)) remove(i);
         else i++;
      }
   }
}
