import java.util.*;
class ED198 {
    public static int sequence(int[] arr, int start, int end, int interval) {
        if(interval==0) return Integer.MAX_VALUE;
        

        return sum;

    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        int[] arr = new int[size];
        for(int i=0; i<size; i++)
            arr[i] = scan.nextInt();

        int max = sequence(arr, 0, size, size-1);
        System.out.println(max);
    }
} 