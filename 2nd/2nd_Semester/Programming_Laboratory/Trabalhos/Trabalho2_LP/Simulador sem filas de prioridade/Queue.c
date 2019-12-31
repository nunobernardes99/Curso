#include <stdio.h>
#include <stdlib.h>
#include "Cliente.h"
#include "Queue.h"
// funcao auxiliar (privada)

static void queue_exit_error(char *msg);

static void queue_exit_error(char *msg)
{ 
  fprintf(stderr,"Error: %s.\n",msg);
  exit(EXIT_FAILURE);
}




// criar fila com capacidade para n inteiros
QUEUE *mk_empty_queue(int n)
{
  QUEUE *q = (QUEUE *) malloc(sizeof(QUEUE));
  if (q == NULL) 
    queue_exit_error("sem memoria");

  q -> queue = malloc(sizeof(Cliente)*n);
  if (q -> queue == NULL) 
    queue_exit_error("sem memoria");

  q -> nmax = n;
  q -> inicio = -1;
  q -> fim = 0;
  return q;
}

// libertar fila
void free_queue(QUEUE *q)
{
  if (q != NULL) {
    free(q -> queue);
    free(q);
  } else 
    queue_exit_error("fila mal construida");
}


// colocar valor na fila
void enqueue(Cliente *v,QUEUE *q)
{  
  if (queue_is_full(q) == true) 
    queue_exit_error("fila sem lugar");

  if (q -> queue == NULL) 
    queue_exit_error("fila mal construida");

  if (queue_is_empty(q)==true) 
    q -> inicio = q -> fim; // fila fica com um elemento
  q -> queue[q->fim] = v;
  q -> fim = (q -> fim+1)%(q->nmax);
}

// retirar valor na fila
Cliente* dequeue(QUEUE *q)
{  
  Cliente *aux;
  if (queue_is_empty(q) == true) 
    queue_exit_error("fila sem valores");

  if (q -> queue == NULL) 
    queue_exit_error("fila mal construida");

  aux = q ->queue[q ->inicio];
  q -> inicio = (q -> inicio+1)%(q -> nmax);
  if (q -> inicio ==  q -> fim) {  // se só tinha um elemento
    q -> inicio = -1; q -> fim = 0;  
  }
  return aux;
}

// verificar se a fila está vazia
bool queue_is_empty(QUEUE *q)
{ 
  if (q == NULL) 
    queue_exit_error("fila mal construida");

  if (q -> inicio == -1) return true;
  return false;
}
// verificar se a fila não admite mais elementos
bool queue_is_full(QUEUE *q)
{ 
  if (q == NULL) 
    queue_exit_error("fila mal construida");

  if (q -> fim == q -> inicio) return true;
  return false;
}

char* reprQueue(QUEUE *q){
	
	char str[2000000]="<";

	int i = q->inicio;
	int j= q->fim;
	int max=q->nmax;
	if(!queue_is_empty(q)){
	while(i!=j){
		char *aux=reprCliente(q->queue[i]);
		strcat(str, aux);
		i=(i+1)%max;
		}
	}
	strcat(str, "<");
	
	char *aux=(char*)malloc(sizeof(QUEUE)*100000);
	strcpy(aux, str);
	return aux;
	
	
	
}

	

	
