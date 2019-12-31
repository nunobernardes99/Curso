import java.util.*;
//import java.lang.*;
class visor_Calculadora {
	public static void zero(String a, String b, int j, boolean end) {
		if(!end) {
			if(j == 0 || j == 6) System.out.print(b + "" + a + "" + a + "" + b + " ");
			if(j == 1 || j == 2 || j == 4 || j == 5) System.out.print(a + "" + b + "" + b + "" + a + " ");
			if(j == 3) System.out.print(b + "" + b + "" + b + "" + b + " ");
		} else {
			if(j == 0 || j == 6) System.out.print(b + "" + a + "" + a + "" + b);
			if(j == 1 || j == 2 || j == 4 || j == 5) System.out.print(a + "" + b + "" + b + "" + a);
			if(j == 3) System.out.print(b + "" + b + "" + b + "" + b);
		}
	}
	public static void one(String a, String b, int j, boolean end) {
		if(!end) {
			if(j == 0 || j == 3 || j == 6) System.out.print(b + "" + b + "" + b + "" + b + " ");
			if(j == 1 || j == 2 || j == 4 || j == 5) System.out.print(b + "" + b + "" + b + "" + a + " ");
		} else {
			if(j == 0 || j == 3 || j == 6) System.out.print(b + "" + b + "" + b + "" + b);
			if(j == 1 || j == 2 || j == 4 || j == 5) System.out.print(b + "" + b + "" + b + "" + a);
		}
	}
	public static void two(String a, String b, int j, boolean end) {
		if(!end) {
			if(j == 1 || j == 2) System.out.print(b + "" + b + "" + b + "" + a + " ");
			if(j == 4 || j == 5) System.out.print(a + "" + b + "" + b + "" + b + " ");
			if(j == 0 || j == 3 || j == 6) System.out.print(b + "" + a + "" + a + "" + b + " ");
		} else {
			if(j == 1 || j == 2) System.out.print(b + "" + b + "" + b + "" + a);
			if(j == 4 || j == 5) System.out.print(a + "" + b + "" + b + "" + b);
			if(j == 0 || j == 3 || j == 6) System.out.print(b + "" + a + "" + a + "" + b);
		}
	}
	public static void three(String a, String b, int j, boolean end) {
		if(!end) {
			if(j == 1 || j == 2 || j == 4 || j == 5) System.out.print(b + "" + b + "" + b + "" + a + " ");
			if(j == 0 || j == 3 || j == 6) System.out.print(b + "" + a + "" + a + "" + b + " ");
		} else {
			if(j == 1 || j == 2 || j == 4 || j == 5) System.out.print(b + "" + b + "" + b + "" + a);
			if(j == 0 || j == 3 || j == 6) System.out.print(b + "" + a + "" + a + "" + b);
		}
	}
	public static void four(String a, String b, int j, boolean end) {
		if(!end) {
			if(j == 0 || j == 6) System.out.print(b + "" + b + "" + b + "" + b + " ");
			if(j == 1 || j == 2) System.out.print(a + "" + b + "" + b + "" + a + " ");
			if(j == 3) System.out.print(b + "" + a + "" + a + "" + b + " ");
			if(j == 4 || j == 5) System.out.print(b + "" + b + "" + b + "" + a + " ");
		} else {
			if(j == 0 || j == 6) System.out.print(b + "" + b + "" + b + "" + b);
			if(j == 1 || j == 2) System.out.print(a + "" + b + "" + b + "" + a);
			if(j == 3) System.out.print(b + "" + a + "" + a + "" + b);
			if(j == 4 || j == 5) System.out.print(b + "" + b + "" + b + "" + a);
		}
	}
	public static void five(String a, String b, int j, boolean end) {
		if(!end) {
			if(j == 0 || j == 3 || j == 6) System.out.print(b + "" + a + "" + a + "" + b + " ");
			if(j == 1 || j == 2) System.out.print(a + "" + b + "" + b + "" + b + " ");
			if(j == 4 || j == 5) System.out.print(b + "" + b + "" + b + "" + a + " ");
		} else {
			if(j == 0 || j == 3 || j == 6) System.out.print(b + "" + a + "" + a + "" + b);
			if(j == 1 || j == 2) System.out.print(a + "" + b + "" + b + "" + b);
			if(j == 4 || j == 5) System.out.print(b + "" + b + "" + b + "" + a);
		}
	}
	public static void six(String a, String b, int j, boolean end) {
		if(!end) {
			if(j == 1 || j == 2) System.out.print(a + "" + b + "" + b + "" + b + " ");
			if(j == 0 || j == 3 || j == 6) System.out.print(b + "" + a + "" + a + "" + b + " ");
			if(j == 4 || j == 5) System.out.print(a + "" + b + "" + b + "" + a + " ");
		} else {
			if(j == 1 || j == 2) System.out.print(a + "" + b + "" + b + "" + b);
			if(j == 0 || j == 3 || j == 6) System.out.print(b + "" + a + "" + a + "" + b);
			if(j == 4 || j == 5) System.out.print(a + "" + b + "" + b + "" + a);
		}
	}
	public static void seven(String a, String b, int j, boolean end) {
		if(!end) {
			if(j == 0) System.out.print(b + "" + a + "" + a + "" + b + " ");
			if(j == 4 || j == 5) System.out.print(b + "" + b + "" + b + "" + a + " ");
			if(j == 3 || j == 6) System.out.print(b + "" + b + "" + b + "" + b + " ");
			if(j == 1 || j == 2) System.out.print(a + "" + b + "" + b + "" + a + " ");
		} else {
			if(j == 0) System.out.print(b + "" + a + "" + a + "" + b);
			if(j == 4 || j == 5) System.out.print(b + "" + b + "" + b + "" + a);
			if(j == 3 || j == 6) System.out.print(b + "" + b + "" + b + "" + b);
			if(j == 1 || j == 2) System.out.print(a + "" + b + "" + b + "" + a);
		}
	}
	public static void eight(String a, String b, int j, boolean end) {
		if(!end) {
			if(j == 0 || j == 6 || j == 3) System.out.print(b + "" + a + "" + a + "" + b + " ");
			if(j == 1 || j == 2 || j == 4 || j == 5) System.out.print(a + "" + b + "" + b + "" + a + " ");
		} else {
			if(j == 0 || j == 6 || j == 3) System.out.print(b + "" + a + "" + a + "" + b);
			if(j == 1 || j == 2 || j == 4 || j == 5) System.out.print(a + "" + b + "" + b + "" + a);
		}
	}
	public static void nine(String a, String b, int j, boolean end) {
		if(!end) {
			if(j == 0 || j == 6 || j == 3) System.out.print(b + "" + a + "" + a + "" + b + " ");
			if(j == 1 || j == 2) System.out.print(a + "" + b + "" + b + "" + a + " ");
			if(j == 4 || j == 5) System.out.print(b + "" + b + "" + b + "" + a + " ");
		} else {
			if(j == 0 || j == 6 || j == 3) System.out.print(b + "" + a + "" + a + "" + b);
			if(j == 1 || j == 2) System.out.print(a + "" + b + "" + b + "" + a);
			if(j == 4 || j == 5) System.out.print(b + "" + b + "" + b + "" + a);
		}
	}

	public static void resultado(String value, String charA, String charB) {
		//Representar o valor
		char aux;
		for(int j=0; j<7; j++) {
			boolean end = false;
			for(int i=0; i<value.length();i++) {
				aux = value.charAt(i);
				if(i == value.length()-1)
					end = true;
				if(Character.toString(aux).equals("0"))
					zero(charA, charB, j, end);
				if(Character.toString(aux).equals("1"))
					one(charA, charB, j, end);
				if(Character.toString(aux).equals("2"))
					two(charA, charB, j, end);
				if(Character.toString(aux).equals("3"))
					three(charA, charB, j, end);
				if(Character.toString(aux).equals("4"))
					four(charA, charB, j, end);
				if(Character.toString(aux).equals("5"))
					five(charA, charB, j, end);
				if(Character.toString(aux).equals("6"))
					six(charA, charB, j, end);
				if(Character.toString(aux).equals("7"))
					seven(charA, charB, j, end);
				if(Character.toString(aux).equals("8"))
					eight(charA, charB, j, end);
				if(Character.toString(aux).equals("9"))
					nine(charA, charB, j, end);
			}
			System.out.println("");
		}
	}
	public static void main(String[] args) {
		//Incializar Scanner
		Scanner scan = new Scanner(System.in);

		//Perguntar ao user qual os caracters que quer usar para representar o valor
		String charA = "#";
		String charB = ".";
		//System.out.print("Com que caracter quer representar os numeros? "); charA = scan.next();
		//System.out.print("Com que caracter quer representar o exterior dos numeros? "); charB = scan.next();
		
		//Perguntar o valor que quer representar
		String value = scan.next();
		//System.out.print("Que valor quer representar no visor? "); value = scan.next(); //12345

		resultado(value, charA, charB);

		scan.close();
	}
}