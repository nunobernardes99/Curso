import java.util.*;
import java.lang.*;
class ED164 {
    public static void main(String[] args) {
        Set<String> hash_set = new HashSet<String>();

        Scanner scan = new Scanner(System.in);
        int casos = scan.nextInt();

        for(int i=0; i<casos; i++)
            hash_set.add(scan.next());

        System.out.println(hash_set.size());

        scan.close();
    }
}