import java.util.*;
import java.lang.*;
class ED165 {
    public static String contains(int[] sums, int k) {
        for(int i=0; i<sums.length; i++)
            if(sums[i] == k) return "sim";
        return "nao";
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int nmr_size = scan.nextInt();
        int[] nmrs = new int[nmr_size+1];
        for(int i=0; i<nmr_size; i++)
            nmrs[i] = scan.nextInt();
        
        int[] sums = new int[(nmr_size+1)*2];
        int ctr = 0;
        for(int i=0; i<nmr_size; i++)
            for(int j=i; j<nmr_size; j++) {
                sums[ctr] = nmrs[i] + nmrs[j];
                ctr++;
            }
        
        int casos = scan.nextInt();
        for(int i=0; i<casos; i++)
            System.out.println(contains(sums, scan.nextInt()));

        scan.close();
    }
}