// -----------------------------------------------------------
// Estruturas de Dados 2018/2019 (CC1007) - DCC/FCUP
// http://www.dcc.fc.up.pt/~pribeiro/aulas/edados1819/
// -----------------------------------------------------------
// Exemplo de utilizacao do TAD Conjunto
// Ultima alteracao: 10/03/2018
// -----------------------------------------------------------

public class TestSet {
    public static void main(String[] args) {
        IntSet s = new MyIntSet(); // Chama o construtor padrão

        s.clear();
        System.out.println(s.size());      // Escreve "0"
        
        System.out.println(s.add(1));      // Escreve "true"
        System.out.println(s.add(5));      // Escreve "true"
        System.out.println(s.add(7));      // Escreve "true"
        System.out.println(s.add(1));      // Escreve "false"
        System.out.println(s.size());      // Escreve "3
  
        System.out.println(s.remove(5));   // Escreve "true"
        System.out.println(s.remove(5));   // Escreve "false"
        System.out.println(s.size());      // Escreve "2"
  
        System.out.println(s.contains(1)); // Escreve "true"
        System.out.println(s.contains(2)); // Escreve "false"
  
        s.clear();
        System.out.println(s.size());      // Escreve "0"
     }
 }