// -----------------------------------------------------------
// Estruturas de Dados 2018/2019 (CC1007) - DCC/FCUP
// http://www.dcc.fc.up.pt/~pribeiro/aulas/edados1819/
// -----------------------------------------------------------
// Invertendo um array (versao recursiva)
// Ultima alteracao: 21/04/2018
// -----------------------------------------------------------

import java.util.Arrays;

public class TestReverse {

   // Inverter array v entre posicoes start e end
   static void reverse(int v[], int start, int end) {
      if (start>=end) return;     // Caso base: array de tamanho < 2
      int tmp = v[start];         // Trocar primeiro com ultimo
      v[start] = v[end];
      v[end] = tmp;
      reverse(v, start+1, end-1); // Chamada recursiva para o resto
   }
   
   // -----------------------------------------------------------

   static boolean capicua(int v[], int start, int end) {
      if(start >= end) return true;
      if(v[start] == v[end]) return capicua(v, start+1, end-1);
      else return false;
   }
   
   public static void main(String[] args) {
      int v[] = {2,4,6,8,10}; // Inicializacao de array
      int v2[] = {1,2,3,2,1}; // Inicializacao de array

      System.out.println("Antes  do reverse: " + Arrays.toString(v));
      reverse(v, 0, v.length-1);
      System.out.println("Depois do reverse: " + Arrays.toString(v));

      System.out.println(capicua(v2, 0, v2.length-1));
   }
}
