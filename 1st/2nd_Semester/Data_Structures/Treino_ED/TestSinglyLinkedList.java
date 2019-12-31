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

      list.addLast(2);
      list.addLast(4);
      list.addLast(6);

      System.out.println(list);

      list.remove(0);
      System.out.println(list);

      list.remove(1);
      System.out.println(list);

      list.remove(2);
      System.out.println(list);

   }
}
