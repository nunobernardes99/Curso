import java.util.*;
class Bombeiro {
    String nome;
    int minutos;
    int pedidos;
    Bombeiro(String s) {
        nome = s;
        minutos = 0;
        pedidos = 0;
    }
}
class Evento {
    String tipo;
    int id;
    int nmrBombeiros;
    int inicio;
    int fim;
    MyQueue<Bombeiro> destacados;
    MyQueue<Bombeiro> participantes;
    Evento(String s, int a, int b, int c) {
        tipo = s;
        id = a;
        nmrBombeiros = b;
        inicio = c;
        destacados = new LinkedListQueue<Bombeiro>();
        participantes = new LinkedListQueue<Bombeiro>();
    }
    Evento(String s, int a, int b) {
        tipo = s;
        id = a;
        fim = b;
    }
}
class ED095 {
    public static int findPartida(Evento[] decorrer, int atual_id, int it) {
        for(int i=0; i<it; i++)
            if(decorrer[i].id == atual_id)
                return i;
        return 0;
    }
    public static void divide(MyQueue<Bombeiro> livres, MyQueue<Evento> eventos, int flag) {
        Evento[] decorrer = new Evento[eventos.size()];
        int it = 0;
        while(!eventos.isEmpty()) {
            Evento atual = eventos.dequeue();
            if(atual.tipo.equals("PARTIDA")) {
                if(atual.nmrBombeiros > livres.size()) {
                    int size=livres.size();
                    for(int i=0; i<size; i++) {
                        Bombeiro aux = livres.dequeue();
                        atual.destacados.enqueue(aux);
                        atual.participantes.enqueue(aux);
                    }
                } else if(atual.nmrBombeiros <= livres.size())
                    for(int i=0; i<atual.nmrBombeiros; i++) {
                        Bombeiro aux = livres.dequeue();
                        atual.destacados.enqueue(aux);
                        atual.participantes.enqueue(aux);
                    }
                decorrer[it] = atual;
                it++;
            } else if(atual.tipo.equals("CHEGADA")) {
                Evento partida = decorrer[findPartida(decorrer, atual.id, it)];
                int minutos_trabalho = atual.fim - partida.inicio;
                int size = partida.destacados.size();
                for(int i=0; i<size; i++) {
                    Bombeiro aux = partida.destacados.dequeue();
                    aux.minutos += minutos_trabalho;
                    aux.pedidos++;
                    livres.enqueue(aux);
                }
            }
        }
        if(flag==2) {
            System.out.println("Bombeiros Destacados");
            for(int i=0; i<it; i++) {
                Evento aux = decorrer[i];
                System.out.println("EVENTO " + aux.id);
                int size = aux.participantes.size();
                if(size==0) System.out.println("Nenhum");
                else 
                    for(int j=0; j<size; j++)
                        System.out.println(aux.participantes.dequeue().nome);
            }
        }
        if(flag==3) {
            System.out.println("Listagem de Bombeiros");
            int size = livres.size();
            for(int i=0; i<size; i++) {
                Bombeiro aux = livres.dequeue();
                System.out.println(aux.nome + " " + aux.pedidos + " " + aux.minutos);
            }
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int flag = scan.nextInt();
        int nBomb = scan.nextInt();
        MyQueue<Bombeiro> livres = new LinkedListQueue<Bombeiro>();
        for(int i=0; i<nBomb; i++)
            livres.enqueue(new Bombeiro(scan.next()));

        String evento = scan.next();
        int flag1=0;
        MyQueue<Evento> eventos = new LinkedListQueue<Evento>();
        while(!evento.equals("FIM")) {
            if(evento.equals("PARTIDA")) {
                flag1++;
                eventos.enqueue(new Evento(evento, scan.nextInt(), scan.nextInt(), scan.nextInt()));
            } else if(evento.equals("CHEGADA"))
                eventos.enqueue(new Evento(evento, scan.nextInt(), scan.nextInt()));
            evento = scan.next();
        }

        if(flag==1) System.out.println("Ocorreram " + flag1 + " eventos");
        else if(flag == 2) divide(livres, eventos, 2);
        else if(flag == 3) divide(livres, eventos, 3);

        scan.close();
    }
}