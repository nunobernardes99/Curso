import java.util.*;
import java.lang.*;
class Cliente {
    String nome;
    int tempoChegada, nmrProdutos;
    Cliente(String s, int t, int p) {
        nome = s;
        tempoChegada = t;
        nmrProdutos = p;
    }
}
class Caixa {
    MyQueue<Cliente> clientesCaixa;
    int totalProdutos;
    Caixa() {
        clientesCaixa = new LinkedListQueue<Cliente>();
        totalProdutos = 0;
    }
}
public class ED115 {
    public static void firstOutput(int rapidez, MyQueue<Cliente> clientes) {
        int currentTime = 0;
        int clientTime = 0;
        int atendimento = 0;
        while(!clientes.isEmpty()) {
            Cliente aux = clientes.dequeue();
            clientTime = aux.tempoChegada;
            if(currentTime > clientTime)
                atendimento = currentTime + 10 + (aux.nmrProdutos*rapidez);
            else {
                currentTime = clientTime;
                atendimento = currentTime + 10 + (aux.nmrProdutos*rapidez);
            }
            System.out.println(aux.nome + " " + clientTime + " " + atendimento);
            currentTime = atendimento;
        }
    }
    public static void secondOutput(Caixa[] caixas, MyQueue<Cliente> clientes) {
        caixas[0].totalProdutos += clientes.first().nmrProdutos;
        caixas[0].clientesCaixa.enqueue(clientes.dequeue());
        
        while(!clientes.isEmpty()) {
            Cliente atual = clientes.dequeue();
            int lowestClients = verificarClientesCaixa(caixas);
            System.out.println("lowestClients: " + lowestClients);
            if(lowestClients == -1) {
                int lowestLast = verificarUltimoCaixa(caixas);
                System.out.println("lowestLast: " + lowestLast);
                if(lowestLast == -1) { caixas[0].clientesCaixa.enqueue(atual); caixas[0].totalProdutos+=atual.nmrProdutos; }
                else { caixas[lowestLast].clientesCaixa.enqueue(atual); caixas[lowestLast].totalProdutos+=atual.nmrProdutos; }
            } else { caixas[lowestClients].clientesCaixa.enqueue(atual); caixas[lowestClients].totalProdutos+=atual.nmrProdutos; }
        }
        for(int i=0; i<caixas.length; i++)
            System.out.println("Caixa #"+ (i+1) + ": " + caixas[i].clientesCaixa.size() + " " + caixas[i].totalProdutos);
    }

    // AS duas funções de verfiicação estão mal
    // ULTIMO ELEMENTO DA FILA_!!!
    public static int verificarUltimoCaixa(Caixa[] caixas) {
        int min = Integer.MAX_VALUE;
        int index = 0;
        int productLast = 0;
        for(int i=caixas.length-1; i>0; i--) {
            if(caixas[i].clientesCaixa.size() == 0) productLast = -1;
            else productLast = caixas[i].clientesCaixa.first().nmrProdutos;
            if(productLast < min) {
                productLast = min;
                index = i;
            } else if(productLast == min) return -1;
        }
        return index;
    }
    public static int verificarClientesCaixa(Caixa[] caixas) {
        int min = Integer.MAX_VALUE;
        int index = 0;
        for(int i=caixas.length-1; i>0; i--) {
            if(caixas[i].clientesCaixa.size() < min) {
                min = caixas[i].clientesCaixa.size();
                index = i;
            } else if(caixas[i].clientesCaixa.size() == min) return -1;
        }
        return index;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int flag = scan.nextInt();
        //Info Caixas
        int nmrCaixas = scan.nextInt();
        int[] rapidez = new int[nmrCaixas];
        Caixa[] caixas = new Caixa[nmrCaixas];
        for(int i=0; i<nmrCaixas; i++) {
            Caixa aux = new Caixa();
            rapidez[i] = scan.nextInt();
            caixas[i] = aux;
        }
        //Info Clientes
        MyQueue<Cliente> clientes = new LinkedListQueue<Cliente>();
        int nmrClientes = scan.nextInt();
        for(int i=0; i<nmrClientes; i++) {
            Cliente aux = new Cliente(scan.next(), scan.nextInt(), scan.nextInt());
            clientes.enqueue(aux);
        }
        //Flag option
        if(flag == 1) firstOutput(rapidez[0], clientes);
        else secondOutput(caixas, clientes);

        scan.close();
    }

}