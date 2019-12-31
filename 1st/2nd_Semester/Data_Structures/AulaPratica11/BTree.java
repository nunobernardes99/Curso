// -----------------------------------------------------------
// Estruturas de Dados 2018/2019 (CC1007) - DCC/FCUP
// http://www.dcc.fc.up.pt/~pribeiro/aulas/edados1819/
// -----------------------------------------------------------
// Arvore binaria "normal"
// Ultima alteracao: 26/04/2018
// -----------------------------------------------------------

public class BTree<T> {   
    private BTNode<T> root; // raiz da arvore
 
    // Construtor
    BTree() {
       root = null;
    }
 
    // Getter e Setter para a raiz
    public BTNode<T> getRoot() {return root;}
    public void setRoot(BTNode<T> r) {root = r;}
 
    // Verificar se arvore esta vazia
    public boolean isEmpty() {
       return root == null;
    }
 
    // --------------------------------------------------------
 
    // Numero de nos da arvore   
    public int numberNodes() {
       return numberNodes(root);
    }
 
    private int numberNodes(BTNode<T> n) {
       if (n == null) return 0;
       return 1 + numberNodes(n.getLeft()) + numberNodes(n.getRight());
    }
 
    // --------------------------------------------------------
 
    // Altura da arvore
    public int depth() {
       return depth(root);
    }
 
    private int depth(BTNode<T> n) {
       if (n == null) return -1;
       return 1 + Math.max(depth(n.getLeft()), depth(n.getRight()));
    }
 
    // --------------------------------------------------------
    
    // O elemento value esta contido na arvore?
    public boolean contains(T value) {
       return contains(root, value);
    }
 
    private boolean contains(BTNode<T> n, T value) {
       if (n==null) return false;
       if (n.getValue().equals(value)) return true;
       return contains(n.getLeft(), value) || contains(n.getRight(), value);
    }
 
    // --------------------------------------------------------
 
    // Imprimir arvore em PreOrder
    public void printPreOrder() {
       System.out.print("PreOrder:");
       printPreOrder(root);
       System.out.println();
    }
 
    private void printPreOrder(BTNode<T> n) {
       if (n==null) return;
       System.out.print(" " + n.getValue() );
       printPreOrder(n.getLeft());
       printPreOrder(n.getRight());
    }
 
    // --------------------------------------------------------
    
    // Imprimir arvore em InOrder
    public void printInOrder() {
       System.out.print("InOrder:");
       printInOrder(root);
       System.out.println();
    }
 
    private void printInOrder(BTNode<T> n) {
       if (n==null) return;
       printInOrder(n.getLeft());
       System.out.print(" " + n.getValue());
       printInOrder(n.getRight());
    }
 
    // --------------------------------------------------------
 
    // Imprimir arvore em PostOrder
    public void printPostOrder() {
       System.out.print("PostOrder:");
       printPostOrder(root);
       System.out.println();
    }
 
    private void printPostOrder(BTNode<T> n) {
       if (n==null) return;
       printPostOrder(n.getLeft());
       printPostOrder(n.getRight());
       System.out.print(" " + n.getValue());
    }
 
    // --------------------------------------------------------
 
    // Imprimir arvore numa visita em largura (usando TAD Fila)
    public void printBFS() {
       System.out.print("BFS:");
       
       MyQueue<BTNode<T>> q = new LinkedListQueue<BTNode<T>>();
       q.enqueue(root);
       while (!q.isEmpty()) {
          BTNode<T> cur = q.dequeue();
          if (cur != null) {
             System.out.print(" " + cur.getValue());
             q.enqueue(cur.getLeft());
             q.enqueue(cur.getRight());
          }
       }
       System.out.println();
    }
 
    // --------------------------------------------------------
    
    // Imprimir arvore numa visita em profundidade (usando TAD Pilha)
    public void printDFS() {
       System.out.print("DFS:");
       
       MyStack<BTNode<T>> q = new LinkedListStack<BTNode<T>>();
       q.push(root);
       while (!q.isEmpty()) {
          BTNode<T> cur = q.pop();
          if (cur != null) {
             System.out.print(" " + cur.getValue());
             q.push(cur.getLeft());
             q.push(cur.getRight());
          }
       }
       System.out.println();
    }
    

    // Contar folhas da árvore
    public int numberLeafs() {
        if(this == null) return 0;
        return numberLeafs(root);
    }

    private int numberLeafs(BTNode<T> n) {
        if(n == null) return 0;
        if(n.getLeft() == null && n.getRight() == null) return 1;
        return numberLeafs(n.getLeft()) + numberLeafs(n.getRight());
    }

    // Árvore estritamente binária
    public boolean strict() {
        if(this == null) return false;
        return strict(root);
    }

    private boolean strict(BTNode<T> n) {
        if(n == null) return true;
        if((n.getLeft() != null && n.getRight() == null) 
        || (n.getLeft() == null && n.getRight() != null)) return false;
        return strict(n.getLeft()) && strict(n.getRight());
    }

    // Percorrendo caminhos
    public T path(String s) {
        if(this == null) return null;
        return path(root, s, 0);
    }
    
    private T path(BTNode<T> n, String s, int index) {
        if(index >= s.length()) return n.getValue();
        if(s.charAt(index) == 'R') return root.getValue();
        else if(s.charAt(index) == 'E') return path(n.getLeft(), s, index + 1);
        else return path(n.getRight(), s, index + 1);
    }

    // Nós profundos
    public int nodesLevel(int k) {
        if(this == null) return 0;
        return nodesLevel(root, k, 0);
    }

    private int nodesLevel(BTNode<T> n, int k, int curr_level) {
        if(n == null) return 0;
        if(curr_level == k) return 1;
        else return nodesLevel(n.getLeft(), k, curr_level+1) + nodesLevel(n.getRight(), k, curr_level+1);
    }
 }
 