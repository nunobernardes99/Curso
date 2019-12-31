import java.util.*;
class ED201 {
    
    // Escrever todos os subconjuntos do array v[]
    static void sets(int v[], int total) {
        // array de booleanos para representar o conjunto
        boolean used[] = new boolean[v.length];
        int max = goSets(0, v, used, total);
        System.out.println(max);
    }
    // Gera todos os subconjuntos a partir da posicao 'cur'
    static int goSets(int cur, int v[], boolean used[], int total) {
        if (cur == v.length) {  // Caso base: terminamos o conjunto
            // Escrever conjunto
            int sum = 0;
            for (int i=0; i<v.length; i++) 
                if (used[i]) sum+=v[i];
            if(sum<=total) return sum;
            else return 0;
        } else {                  // Se nao terminamos, continuar a gerar
            used[cur] = true;      // Subconjuntos que incluem o elemento actual
            int sum1 =  goSets(cur+1, v, used, total);// Chamada recursiva
            used[cur] = false;     // Subconjuntos que nao incluem o el. actual
            int sum2 = goSets(cur+1, v, used, total);// Chamada recursiva
            return Math.max(sum1, sum2);
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int total = scan.nextInt();
        int nmrMusicas = scan.nextInt();
        int[] lista = new int[nmrMusicas];
        for(int i=0; i<nmrMusicas; i++)
            lista[i] = scan.nextInt();
        sets(lista, total);
    }
}

