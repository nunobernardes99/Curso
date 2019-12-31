import java.util.*;
//import java.lang.*;
class losango {
	static void resultado(int n, String type) {
		int numberSpaces = (n-1)/2;
		int numberType = 1;
		for(int i=0; i<n; i++) {
			//First Spaces
			for(int j=0; j<numberSpaces; j++)
				System.out.print(".");
			//Mid Type
			for(int k=0; k<numberType; k++)
				System.out.print(type);
			//Second Spaces
			for(int l=0; l<numberSpaces; l++)
				System.out.print(".");
			if(i < (n-1)/2) {
				// Debug : System.out.println("Aumentou");
				numberSpaces--;
				numberType += 2;
			} else {
				// Debug : System.out.println("Diminuiu");
				numberSpaces++;
				numberType -=2;
			}
			System.out.println("");
		}
	}
	public static void main(String[] args) {
		//Criar scanner
		Scanner scan = new Scanner(System.in);

		//Tipo de caracter que quer usar para representar o losango
		String type = "#";

		//Tamanho do losango
		int n = scan.nextInt();

		resultado(n, type);
		scan.close();
	}
}