import java.util.*;
class ED213 {
    public static String maxSum(BTree<Integer> t) {
        if(t == null) return null;
        return maxSum(t.getRoot(), "", "", 0, 0);
    }

    private static String[] stringPath(BTNode<Integer> n, String s, int index) {
        if(n.getRight() == null && n.getRight() == null) {

        }
    }

    public static void main(String[] args) {
        // Ler arvore de inteiros em preorder
        Scanner in = new Scanner(System.in);
        BTree<Integer> t = LibBTree.readIntTree(in);
        
        pathSum(t);
        //System.out.println(pathSum(t));
    }
}