import java.util.*;
class ED212 {
    public static int[] sumLevels(BTree<Integer> t) {
        if(t == null) return null;
        int[] sum = new int[t.depth()+1];
        Arrays.fill(sum, 0);
        return sumLevels(t.getRoot(), sum, 0);
    }

    private static int[] sumLevels(BTNode<Integer> n, int[] sum, int curr_level) {
        if(n == null) return sum;
        sum[curr_level] += n.getValue();
        sumLevels(n.getLeft(), sum, curr_level+1);
        sumLevels(n.getRight(), sum, curr_level+1);
        return sum;
    }
    /*public static void main(String[] args) {
        // Ler arvore de inteiros em preorder
        Scanner in = new Scanner(System.in);
        BTree<Integer> t = LibBTree.readIntTree(in);
        
        System.out.println(Arrays.toString(sumLevels(t)));
    }*/
}