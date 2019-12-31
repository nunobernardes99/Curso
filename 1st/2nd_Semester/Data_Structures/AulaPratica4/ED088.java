/* -----------------------------------
  Estruturas de Dados 2018/2019
  Jogo da Vida [ED088]  
----------------------------------- */

import java.util.Scanner;

// Classe para representar um jogo
class Game {
    final char DEAD  = '.';  // Constante que indica uma celula morta
    final char ALIVE = 'O';  // Constante que indica uma celula viva
    private int rows, cols;  // Numero de linhas e colunas
    private char m[][];      // Matriz para representar o estado do jogo

    // Construtor: inicializa as variaveis tendo em conta a dimensao dada
    Game(int r, int c) {
	rows = r;
	cols = c;
	m = new char[r][c];
    }

    // Metodo para ler o estado inicial para a matriz m[][]
    public void read(Scanner in) {
	for (int i=0; i<rows; i++)
	    m[i] = in.next().toCharArray();
    }
    
    // Metodo para escrever a matriz m[][]
    public void write() {
        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++)
                System.out.print(m[i][j]);
            System.out.println("");
        }
            
    }


    private int countAlive(int y, int x) { //passa as coordenadas da célula (y,x)
        int count = 0;
        int xMin = x-1;
        int xMax = x+1;
        int yMin = y-1;
        int yMax = y+1;
        for (int i = yMin; i < yMax; i++){
            for (int j = xMin; j < xMax; j++){        //posição -1,-1 da posiçao a analizar (em um cubo 3x3)
                //if (m[i][j] != m[x][y]){            //se nao for a propria posiçao (porque só procura os 8 à volta)
                    if (m[i][j] == 'O') count++;    //conta como vizinho
            }
        }
        System.out.println(count);
        return count;
    }
    // Deve devolver o numero de celulas vivas que sao vizinhas de (y,x)
    /*private int countAlive(int y, int x) {
        
        //System.out.println("Y: " + y + ", X: " + x);
        int count = 0;
        if( y==0 && x== 0 ) {//canto superior esquerdo
            //System.out.println("Entrou");
            if(m[y][x+1] == 'O') count++;
            if(m[y+1][x] == 'O') count++;
            if(m[y+1][x+1] == 'O') count++;
            return count;
        } else if( y==0 && x==cols-1 ) { //canto superior direito
            //System.out.println("Entrou");
            if(m[y][x-1] == 'O') count++;
            if(m[y+1][x-1] == 'O') count++;
            if(m[y+1][x] == 'O') count++;
            return count;
        } else if( y==rows-1 && x==cols-1) { //canto inferior direito
            if(m[y-1][x-1] == 'O') count++;
            if(m[y-1][x] == 'O') count++;
            if(m[y][x-1] == 'O') count++;
            return count;
        } else if( y==rows-1 && x==0) { //canto inferior esquerdo
            if(m[y-1][x] == 'O') count++;
            if(m[y-1][x+1] == 'O') count++;
            if(m[y][x+1] == 'O') count++;
            return count;
        } else if( y==0 && (x>0 && x<cols-1)) { //cima meio
            if(m[y][x-1] == 'O') count++;
            if(m[y][x+1] == 'O') count++;
            if(m[y+1][x-1] == 'O') count++;
            if(m[y+1][x] == 'O') count++;
            if(m[y+1][x+1] == 'O') count++;
            return count;
        } else if( (y>0 && y<rows-1) && x==0 ) { //esquerda meio
            if(m[y-1][x] == 'O') count++;
            if(m[y-1][x+1] == 'O') count++;
            if(m[y][x+1] == 'O') count++;
            if(m[y+1][x] == 'O') count++;
            if(m[y+1][x+1] == 'O') count++;
            return count;
        } else if( y==rows-1 && (x>0 && x<cols-1)) { //baixo meio
            if(m[y-1][x-1] == 'O') count++;
            if(m[y-1][x] == 'O') count++;
            if(m[y-1][x+1] == 'O') count++;
            if(m[y][x-1] == 'O') count++;
            if(m[y][x+1] == 'O') count++;
            return count;
        } else if( (y>0 && y<rows-1) && x==cols-1) { //direita meio
            if(m[y-1][x-1] == 'O') count++;
            if(m[y-1][x] == 'O') count++;
            if(m[y][x-1] == 'O') count++;
            if(m[y+1][x-1] == 'O') count++;
            if(m[y+1][x] == 'O') count++;
            return count;
        } else {
            if(m[y+1][x-1] == 'O') count++;
            if(m[y+1][x] == 'O') count++;
            if(m[y+1][x+1] == 'O') count++;
            if(m[y][x-1] == 'O') count++;
            if(m[y][x+1] == 'O') count++;
            if(m[y-1][x-1] == 'O') count++;
            if(m[y-1][x] == 'O') count++;
            if(m[y-1][x+1] == 'O') count++;
            return count;
        }
    }*/

    // Deve fazer uma iteracao: cria nova geracao a partir da actual
    public void iterate() {
        char m2[][] = new char[rows][cols];
        if(rows==1 && cols == 1) {
            m2[rows-1][cols-1] = '.';
        } else {
            for(int i=0; i<rows; i++) {
                for(int j=0; j<cols; j++) {
                    int nmr = this.countAlive(i,j);
                    if(m[i][j] == 'O' && nmr <= 1)
                        m2[i][j] = '.';
                    else if(m[i][j] == 'O' && nmr >= 4)
                        m2[i][j] = '.';
                    else if(m[i][j] == 'O' && (nmr == 2 || nmr == 3))
                        m2[i][j] = 'O';
                    else if(m[i][j] == '.' && nmr == 3)
                        m2[i][j] = 'O';
                    else 
                        m2[i][j] = '.';
                }
            }
        }
        this.m = m2;
    }
}

// Classe principal com o main()
public class ED088 {
    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);

	// Ler linhas, colunas e numero de iteracoes
	int rows = in.nextInt();
	int cols = in.nextInt();
	int n    = in.nextInt();

	// Criar objecto para conter o jogo e ler estado inicial
    Game g = new Game(rows, cols);
    g.read(in);
    for(int i=0; i<n; i++)
        g.iterate();
    g.write();
    }
}