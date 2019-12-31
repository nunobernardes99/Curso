#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>
#include "Caixa.h"
#include "Queue.h"


// Construtor
Caixa * initCaixa(int n) {
    Caixa* c = (Caixa*)malloc(sizeof(Caixa));
    QUEUEP * a = mk_empty_queueP(500);
    c -> lista=a;
    c -> eta = 0;
    c -> clientes = 0;
    c -> produtos = 0;
    c -> espera = 0;
    c -> numero = n;
 
    int aux=1+(rand()%5);
    c -> velocidade = aux; 
    return c;
}

//Modificadores
void disponivel(Caixa * c, int valor) {
    c -> eta = valor;
}
void cliente_atendido(Caixa * c) {
    c -> clientes += 1;
}
void atualiza_produtos(Caixa * c, int p) {
    c -> produtos += p;
}
void atualiza_espera(Caixa * c, int v) {
    c -> espera += v;
}
void remove_clientes(Caixa * c) {
    dequeueP(c->lista);
}

// Selectores
// FUNÇÃO QUE DEVOLVE FILA ASSOCIACAO À CAIXA (FUNÇÃO ABAIXO)
QUEUEP * fila_caixa(Caixa * c) {
    //Verificar se está correto
    return c ->lista;
}
int numero(Caixa * c) {
    return c -> numero;
}
int pronta(Caixa * c) {
    return c -> eta;
}
int clientes(Caixa * c) {
    return c-> clientes;
}
int produtos(Caixa * c) {
    return c -> produtos;
}
int espera(Caixa * c) {
    return c -> espera;
}
int tempo_processamento(Caixa * c) {
    return c -> velocidade;
}

// Reconhecedores
// Verfiica se a fila de uma determinada caixa está vazia
bool  vazia(Caixa * c) {
    return queueP_is_empty(c->lista);
}

// Transformador de saída
// Retorna uma string do cliente
char * reprCaixa(struct Caixa * c) {
    char * str = (char*)malloc(sizeof(char)*100000);
    sprintf(str, "Caixa: %d (%d): %s\n", c->numero, c->eta, reprQueueP(c->lista));
    
    return str;
} 
