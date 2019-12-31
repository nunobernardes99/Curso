#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include "Cliente.h"
#include "QueueP.h"
// funcao auxiliar (privada)

static void queue_exit_error(char *msg);

static void queue_exit_error(char *msg)
{ 
  fprintf(stderr,"Error: %s.\n",msg);
  exit(EXIT_FAILURE);
}




// criar fila com capacidade para n inteiros
QUEUEP *mk_empty_queueP(int n)
{
  QUEUEP *q=(QUEUEP*)malloc(sizeof(QUEUEP));
  q->queueN=mk_empty_queue(n);
  q->queueU=mk_empty_queue(n);
  return q;
}

// libertar fila
void free_queueP(QUEUEP *q)
{
  if (q != NULL) {
    free(q ->queueU);
    free(q->queueN);
    free(q);
  } else 
    queue_exit_error("fila mal construida");
}


// colocar valor na fila
void enqueueP(Cliente *v,QUEUEP *q, char A)
{  QUEUE *aux;
	if(A=='N')
		aux=q->queueN;
	if(A=='U')
		aux=q->queueU;
		
  if (queue_is_full(aux) == true ) 
    queue_exit_error("fila sem lugar");

  if (aux==NULL) 
    queue_exit_error("fila mal construida");

  if (queue_is_empty(aux)==true)
    aux-> inicio = aux -> fim; // fila fica com um elemento
  
  aux -> queue[aux->fim] = v;
  aux-> fim = (aux -> fim+1)%(aux->nmax);
}

// retirar valor na fila

Cliente *dequeueP(QUEUEP *q){
	Cliente *aux;
	if(!queue_is_empty(q->queueU))
		aux=dequeue(q->queueU);
	else
		aux=dequeue(q->queueN);
		
	return aux;
}
		

// verificar se a fila está vazia
bool queueP_is_empty(QUEUEP *q)
{ 
  if (q == NULL) 
    queue_exit_error("fila mal construida");

  if (q ->queueN-> inicio == -1 && q->queueU->inicio==-1) return true;
  return false;
}
// verificar se a fila não admite mais elementos
bool queueP_is_full(QUEUEP *q)
{ 
  if (q == NULL) 
    queue_exit_error("fila mal construida");
	if(queue_is_full(q->queueU)==true || queue_is_full(q->queueN)==true){
		return true;
	}
  return false;
}

Cliente * getFirstQueueP(QUEUEP *q){
	Cliente *aux;
	if(!queue_is_empty(q->queueU))
		aux=q->queueU->queue[q->queueU->inicio];
	
	else
		aux=q->queueN->queue[q->queueN->inicio];
	
	return aux;
} 


char* reprQueueP(QUEUEP *q){

	QUEUE *aux1=q->queueU;
	QUEUE *aux2=q->queueN;
	char *temp1=reprQueue(aux1);
	char *temp2=reprQueue(aux2);
	char str[200000]="Urgente -> ";
	strcat(str, temp1);
	strcat(str, "\n	       Normal -> ");
	strcat(str, temp2);
	
	char *temp=(char*)malloc(sizeof(QUEUEP)*100000);
	strcpy(temp, str);
	return temp;
}
	

	

	
