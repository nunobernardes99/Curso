import java.util.*;
import java.lang.*;
class Team implements Comparable<Team>{
    String nome;
    Integer total_pontos;
    Integer goal_average;
    Team(String s, int a, int b, int c, int d, int e) {
        nome = s;
        total_pontos = a*3 + b;
        goal_average = d - e;
    }

    public int compareTo(Team t) {
        if(total_pontos != t.total_pontos)
            return total_pontos.compareTo(t.total_pontos);
        else if(goal_average != t.goal_average)
            return goal_average.compareTo(t.goal_average);
        else return nome.compareTo(t.nome);
    }

    public String toString() {
        return nome + " " + total_pontos + " " + goal_average;
    }
}
class ED163 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int casos = scan.nextInt();
        Team[] equipas = new Team[casos];
        for(int i=0; i<casos; i++)
            equipas[i] = new Team(scan.next(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt());

        Arrays.sort(equipas);

        for(int i=casos-1; i>=0; i--)
            System.out.println(equipas[i]);

        scan.close();
    }
}