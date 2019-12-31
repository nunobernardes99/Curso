#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include "Cliente.h"
#include "Queue.h"




typedef struct filaP {
  QUEUE *queueN; //Queue com prioridade normal
  QUEUE *queueU; //Queue com prioridade urgente
} QUEUEP;
#pragma once
//typedef enum {FALSE,TRUE} BOOL;

//criar filaP (com prioridades) com capacidade para n inteiros para cada fila 
QUEUEP *mk_empty_queueP(int n);
// colocar valor na fila
void enqueueP(Cliente *v,QUEUEP *f, char A);
// retirar valor na fila
Cliente* dequeueP(QUEUEP *f);
// verificar se a fila está vazia
bool queueP_is_empty(QUEUEP *f);
// verificar se a fila não admite mais elementos
bool queueP_is_full(QUEUEP *f);
// liberta fila
void free_queueP(QUEUEP *f);
//Escreve a queue
char* reprQueueP(QUEUEP *f);
 
Cliente* getFirstQueueP(QUEUEP *f);

