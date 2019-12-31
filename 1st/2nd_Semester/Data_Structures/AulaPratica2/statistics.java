import java.util.*;
import java.lang.*;
class statistics {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int nmrCasos = scan.nextInt();
        int max = -1000000;
        int min = 1000000;
        float sum = 0;
        for(int i=0; i<nmrCasos; i++) {
            int num = scan.nextInt();
            sum += num;
            if(num > max) max = num;
            if(num < min) min = num;
        }
        float avg = sum/nmrCasos;
        int amp = max - min;
        System.out.printf("%.2f\n%d", avg, amp);
        System.out.println("");

        scan.close();
    }
}