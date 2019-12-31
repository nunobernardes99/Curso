// -----------------------------------------------------------
// Estruturas de Dados 2018/2019 (CC1007) - DCC/FCUP
// http://www.dcc.fc.up.pt/~pribeiro/aulas/edados1819/
// -----------------------------------------------------------
// Exemplo de utilizacao da lista ligada simples
// Ultima alteracao: 01/04/2018
// -----------------------------------------------------------

public class TestSinglyLinkedList {
   public static void main(String[] args) {

      // Criacao de lista de inteiros
      SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
      System.out.println(list);
      list.addLast(42);
      list.addLast(200);
      list.addLast(42);
      list.addLast(9999);
      list.addLast(42);
      list.addLast(200);
      list.addLast(42);
      System.out.println(list);
      System.out.println(list.size());
      list.removeAll(42);
      //System.out.println("remove = " + list.removeAll(42));
      System.out.println(list);
      System.out.println(list.size());
   }
}