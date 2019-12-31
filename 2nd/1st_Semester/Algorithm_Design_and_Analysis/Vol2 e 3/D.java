import java.util.*;
class D {
	static String resolveD(int size, String s1, String s2) {
		for(int i=0; i<size; i++)
			if(s1.charAt(i) != s2.charAt(i))
				return "" + s1.charAt(i) + "" + s2.charAt(i);
		return "Nenhum";

	}
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);

		String firstWord = scan.next();
		String secondWord = scan.next();

		System.out.println(resolveD(Math.min(firstWord.length(), secondWord.length()), firstWord, secondWord));
	}
}