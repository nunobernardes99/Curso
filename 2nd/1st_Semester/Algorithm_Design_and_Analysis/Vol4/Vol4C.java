import java.util.*;
import java.lang.*;
class Vol4C {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		int nos = scan.nextInt();
		int lMin = scan.nextInt();
		int lMax = scan.nextInt();
		int cMin = scan.nextInt();
		int cMax = scan.nextInt();
		int aMin = scan.nextInt();

		int origem = scan.nextInt();
		int destino = scan.nextInt();

		int fV = scan.nextInt();

		int ctr = 0;

		while(fV != -1) {
			int sV = scan.nextInt();
			int l = scan.nextInt();
			int c = scan.nextInt();
			int a = scan.nextInt();
			fV = scan.nextInt();
			if((lMin <= l) && (cMin <= c) && (aMin <= a)){
				ctr++;
			}
		}

		System.out.println(ctr);
	}
}