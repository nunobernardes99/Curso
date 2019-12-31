import java.util.*;
//import java.lang.*;
public class Sieve {

    // Procedimento para usar o algoritmo do Crivo de Eratóstenes
    // [procedimento ainda por completar]
    public static void sieve(int n, boolean prime[]) {
    	Arrays.fill(prime, true);
    	prime[0] = false;
    	prime[1] = false;
    	for(int i=2; i<=n; i++) {
    		int number = i;
    		if(prime[i]) {
    			boolean isPrime = true;
    			for(int j=2; j<number || !isPrime; j++)
    				if(number%j == 0)
    					isPrime = false;
    			if(isPrime)
    				for(int j=2*number; j<=n; j+=number)
    					prime[j] = false;
    		}
    	}
    }
    
    public static void main(String[] args) {
	
	int n=50; // Limite dos números

	// Cria um array de inteiros com tamanho n+1
	// (arrays começam na posição 0)
	boolean[] prime = new boolean[n+1];

	// Chama o procedimento sieve para números até 'n' no array 'prime'
	sieve(n, prime);

	// Escreve todos os números marcados a 'true' no array 'prime'
	for (int i=2; i<=n; i++)
	    if (prime[i])
		System.out.print(i + " ");
	System.out.println("");
    }
}