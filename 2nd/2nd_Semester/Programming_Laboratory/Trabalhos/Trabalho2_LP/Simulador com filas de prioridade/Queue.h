#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include "Cliente.h"



typedef struct fila {
  int inicio, fim, nmax;
  struct Cliente **queue; 
} QUEUE;
#pragma once

// criar fila com capacidade para n inteiros
QUEUE *mk_empty_queue(int n);
// colocar valor na fila
void enqueue(Cliente *v,QUEUE *f);
// retirar valor na fila
Cliente* dequeue(QUEUE *f);
// verificar se a fila está vazia
bool queue_is_empty(QUEUE *f);
// verificar se a fila não admite mais elementos
bool queue_is_full(QUEUE *f);
// liberta fila
void free_queue(QUEUE *f);
//Escreve a queue
char* reprQueue(QUEUE *f);
