import java.util.*;
class Aviao {
    String nome;
    int minuto_desejado;
    int minuto_espera;
    Aviao(String s, int min) {
        nome = s;
        minuto_desejado = min;
        minuto_espera = 0;
    }
}
class ED029 {
    public static void organizar(MyQueue<Aviao> levanta, MyQueue<Aviao> aterra) {
        int nLevanta = levanta.size();
        int nAterra = aterra.size();
        int t = Math.min(levanta.first().minuto_desejado, aterra.first().minuto_desejado);

        MyQueue<Aviao> levantaFinal = new LinkedListQueue<Aviao>();
        MyQueue<Aviao> aterraFinal = new LinkedListQueue<Aviao>();

        System.out.println(nLevanta + " " + nAterra);

        for(int i = t; !aterra.isEmpty() || !levanta.isEmpty(); i++) {
            if(levanta.isEmpty()) {
                if(aterra.first().minuto_desejado <= i) {
                    aterra.first().minuto_espera = i - aterra.first().minuto_desejado;
                    aterraFinal.enqueue(aterra.dequeue());
                }
            } else if(aterra.isEmpty()) {
                if(levanta.first().minuto_desejado <= i) {
                    levanta.first().minuto_espera = i - levanta.first().minuto_desejado;
                    levantaFinal.enqueue(levanta.dequeue());
                }
            } else {
                if(levanta.first().minuto_desejado <= i && aterra.first().minuto_desejado <= i) {
                    if(levanta.first().minuto_espera > aterra.first().minuto_espera) {
                        levanta.first().minuto_espera = i - levanta.first().minuto_desejado;
                        levantaFinal.enqueue(levanta.dequeue());
                        aterra.first().minuto_espera++;
                    } else {
                        aterra.first().minuto_espera = i - aterra.first().minuto_desejado;
                        aterraFinal.enqueue(aterra.dequeue());
                        levanta.first().minuto_espera++;
                    }
                } else if(levanta.first().minuto_desejado <= i && aterra.first().minuto_desejado > i) {
                    levanta.first().minuto_espera = i - levanta.first().minuto_desejado;
                    levantaFinal.enqueue(levanta.dequeue());
                } else if(levanta.first().minuto_desejado > i && aterra.first().minuto_desejado <= i) {
                    aterra.first().minuto_espera = i - aterra.first().minuto_desejado;
                    aterraFinal.enqueue(aterra.dequeue());
                }
            }
        }

        for(int i=0; i<nLevanta; i++) {
            System.out.println(levantaFinal.first().nome + " " + levantaFinal.first().minuto_espera);
            levantaFinal.dequeue();
        }
        for(int i=0; i<nAterra; i++) {
            System.out.println(aterraFinal.first().nome + " " + aterraFinal.first().minuto_espera);
            aterraFinal.dequeue();
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int casos = scan.nextInt();
        for(int i=0; i<casos; i++) {
            int nLevanta = scan.nextInt();
            MyQueue<Aviao> levanta = new LinkedListQueue<Aviao>();
            int nAterra = scan.nextInt();
            MyQueue<Aviao> aterra = new LinkedListQueue<Aviao>();
            for(int j=0; j<nLevanta; j++)
                levanta.enqueue(new Aviao(scan.next(), scan.nextInt()));
            for(int j=0; j<nAterra; j++)
                aterra.enqueue(new Aviao(scan.next(), scan.nextInt()));
            organizar(levanta, aterra);
        }
        scan.close();
    }
}