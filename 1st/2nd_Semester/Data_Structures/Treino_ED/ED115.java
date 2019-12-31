import java.util.*;
class Cliente{
    String nome;
    int tempChegada;
    int produtos;
    int tempSaida;
    //Construtor
    Cliente(String a, int prod, int cheg){
        nome=a;
        produtos=prod;
        tempChegada=cheg;
        tempSaida=0;
    }
       
       
}
 
class Caixas{
    SinglyLinkedList<Cliente> fila = new SinglyLinkedList<Cliente>();
    int atendimento;
    int totalClientes;
    int totalProdutos;
    int tempoSaida;
    int produtosUltimo;
   
    //Construtor
    Caixas(int a){
        //fila=null;
        atendimento=a;
        totalClientes=0;
        totalProdutos=0;
        tempoSaida=0;
    }
   
    void novoCliente(Cliente a){
        fila.addLast(a);
        totalClientes++;
        totalProdutos+=a.produtos;
        tempoSaida=a.tempSaida;
        produtosUltimo=a.produtos;
}
}
public class ED115{
    private static Caixas listaCaixas[];//array com as Caixas existentes
    private static MyQueue<Cliente> filaClientes= new LinkedListQueue<Cliente>(); //Queue com os clientes que ainda não estão alocados a nenhuma caixa
    private static Cliente atendidos[]; //array com clientes atendidos
    private static int numCaixas;
    private static int numClientes;
    private static int clock=0;
   
   
    private static void lerClientes(Scanner in){   
       
        for(int i=0; i<numClientes; i++){
            String nome=in.next();
           
            int chegada=in.nextInt();
            int prods= in.nextInt();
            Cliente aux= new Cliente(nome,prods,chegada);
            filaClientes.enqueue(aux);
            in.nextLine();
        }
       
        atendidos= new Cliente[numClientes];
    }
       
    private static void lerCaixas(Scanner in){
            listaCaixas= new Caixas[numCaixas];
           
            for(int i=0; i<numCaixas; i++){
                int atendimento= in.nextInt();
                Caixas aux= new Caixas(atendimento);
               
                listaCaixas[i]=aux;
                //System.out.println(listaCaixas[i].atendimento);
            }
           
            in.nextLine();
        }
   
private static void mostraClientes(){
   
    for(int i=0; i<numClientes; i++){
        Cliente aux=atendidos[i];
        System.out.println(aux.nome +" "+ aux.tempChegada +" " + aux.tempSaida);
    }
}
 
private static int tempEspera(int index, Cliente aux){
    int espera=0;
    try{
    int tempo= listaCaixas[index].fila.getLast().tempSaida;
   
    int chegada= aux.tempChegada;
   
    if(chegada < tempo){
        espera=tempo-chegada;
    }
}
    catch(Exception e){
        return 0;
    }
    return espera;
}
 
private static void processarClientes(){
    for(int i=0; i<numClientes; i++){
        Cliente aux=filaClientes.dequeue();
        clock=aux.tempChegada;
        int index=escolheCaixa();
        atualizaCliente(aux, index);
        listaCaixas[index].novoCliente(aux);
        atendidos[i]=aux;  
    }
}
private static int escolheCaixa(){
   
    for(int i=0; i<numCaixas; i++){
        Cliente aux=listaCaixas[i].fila.getFirst();
        if(aux!=null){
            if(aux.tempSaida < clock){
                listaCaixas[i].fila.removeFirst();
            }
        }
    }
   
    int best=listaCaixas[0].fila.size();
    int index=0;
   
    for(int i=0; i<numCaixas; i++){
        if(listaCaixas[i].fila.isEmpty()){
            index=i;
            return index;
        }
        else if(listaCaixas[i].fila.size() < best){
            best=listaCaixas[i].fila.size();
           
            index=i;
        }
        else if(listaCaixas[i].fila.size() == best){ //Situação de empate
            int aux1=listaCaixas[index].produtosUltimo;
            int aux2=listaCaixas[i].produtosUltimo;
            if(aux2 < aux1) index =i;
            if(aux2==aux1){
            if(i<index) index=i;
            }
        }
    }
   
    return index;
}
 
private static void atualizaCliente(Cliente aux, int index){
    int processamento= (listaCaixas[index].atendimento)*aux.produtos +10;
    int espera=listaCaixas[index].tempoSaida - aux.tempChegada;
    if(espera <0) espera=0;
 
    int total=processamento+clock+ espera;
    aux.tempSaida=total;
}
 
       
   
   
private static void lerInput(Scanner in){
    numCaixas=in.nextInt();
    in.nextLine();
 
    lerCaixas(in);
    numClientes=in.nextInt();
 
    lerClientes(in);
}
 
private static void estatisticasCaixas(){
   
    for(int i=0; i<numCaixas; i++){
        System.out.println("Caixa #" +(i+1) +": " + listaCaixas[i].totalClientes + " " + listaCaixas[i].totalProdutos);
    }
}
               
               
               
public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    int flag = in.nextInt();
    lerInput(in);
   
    processarClientes();
    if(flag==1) mostraClientes();
    if(flag==2) estatisticasCaixas();
}
   
   
}