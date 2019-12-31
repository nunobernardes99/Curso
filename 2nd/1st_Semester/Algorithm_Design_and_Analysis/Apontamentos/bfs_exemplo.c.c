#include <stdio.h>
#include <stdlib.h>

#include "DAA1819_EstruturasDados/Mooshak_Clang/grafos0.h"
#include "DAA1819_EstruturasDados/Mooshak_Clang/queue.h"

#define UNDEF 0
// define pai inexistente


//===================  Prototipos das funções
void BFS_visit(int s,GRAFO *g, int pai[]);
GRAFO *ler_construir_grafo();
void escreve_caminho(int v,int pai[]);

#define DEBUG
// comentar a linha acima e recompilar para não DEBUG

#ifdef DEBUG
void escreve_grafo(GRAFO *g);  // auxiliar
#endif

//=====================================================

int main() {
  int s, *pai, n, v;

  GRAFO *g = ler_construir_grafo();

#ifdef DEBUG
  escreve_grafo(g);
#endif

  
  n = NUM_VERTICES(g);
  pai = (int *) malloc(sizeof(int)*(n+1));

  scanf("%d",&s);
  
  BFS_visit(s,g,pai);

  printf("Caminhos a partir de %d:\n", s);

  for(v=1; v<= n; v++) 
    if (pai[v] != UNDEF) {
      escreve_caminho(v,pai);
      putchar('\n');
    }

  free(pai);  destroy_graph(g);
  return 0;
}


void BFS_visit(int s,GRAFO *g, int pai[]) {
   int v, w, n = NUM_VERTICES(g);
   QUEUE *q;  
   ARCO *adjs;
   BOOL visitado[n+1];

   for(v=1; v <= n; v++) {  // assume nós numerados de 1 a n
      visitado[v] = FALSE;   pai[v] = UNDEF;   // sem pai
   }

   visitado[s] = TRUE;
   q = mk_empty_queue(n);
   enqueue(s,q);

   do {
      v = dequeue(q);
      adjs = ADJS_NO(v,g);
      while (adjs != NULL) {
         w = EXTREMO_FINAL(adjs);
         if (visitado[w] == FALSE) {
            enqueue(w,q);
            visitado[w] = TRUE;
            pai[w] = v;
         }
         adjs = PROX_ADJ(adjs);
      }
   } while (queue_is_empty(q) == FALSE);
   free_queue(q);
}


GRAFO *ler_construir_grafo(){
  int nvs, nramos, u, v;
  GRAFO *g;

  scanf("%d%d",&nvs,&nramos);

  g = new_graph(nvs);

  while(nramos > 0) {
    scanf("%d%d",&u,&v);
    insert_new_arc(u,v,g);
    nramos--;
  }

  return g;
}

void escreve_caminho(int v,int pai[]) {
  if (pai[v]!= UNDEF) {
    escreve_caminho(pai[v],pai);
    putchar(' ');
  }
  printf("%d",v);
}

//===============  auxiliar (debug)

#ifdef DEBUG
void escreve_grafo(GRAFO *g) {
  int nvs = NUM_VERTICES(g), v;
  ARCO *adjs;

  printf("Dados do grafo\n\nnverts = %d  nramos = %d\n",nvs,NUM_ARCOS(g));

  for(v=1; v <= nvs; v++) {
    printf("%d:",v);
    adjs = ADJS_NO(v,g);
    while(adjs != NULL) {
      printf(" %d",EXTREMO_FINAL(adjs));
      adjs = PROX_ADJ(adjs);
    }
    putchar('\n');
  }
  putchar('\n');  putchar('\n');
}
#endif
