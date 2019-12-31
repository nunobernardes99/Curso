// -----------------------------------------------------------
// Estruturas de Dados 2018/2019 (CC1007) - DCC/FCUP
// http://www.dcc.fc.up.pt/~pribeiro/aulas/edados1819/
// -----------------------------------------------------------
// Interface que define o TAD conjunto de numeros inteiros
// Ultima alteracao: 10/03/2018
// -----------------------------------------------------------

// Interface que define o TAD conjunto de numeros inteiros
public interface IntSet {
    public boolean contains(int x); // Retorna true se x esta no conjunto
    public boolean add(int x);      // Adiciona x ao conjunto
    public boolean remove(int x);   // Remove x do conjunto
    public int     size();          // Retorna o numero de elementos do conjunto
    public void    clear();         // Limpa o conjunto (torna-o vazio)
 }