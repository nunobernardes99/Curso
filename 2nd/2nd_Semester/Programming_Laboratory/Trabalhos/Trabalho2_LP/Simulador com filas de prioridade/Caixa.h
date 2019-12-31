#include <stdbool.h>
#include "QueueP.h"


// Tipo de caixa
typedef struct Caixa {
	QUEUEP *lista;
    int eta;
    int clientes;
    int produtos;
    int espera;
    int numero;
    int velocidade;
} Caixa;

#pragma once 
//Construtor
Caixa * initCaixa(int n);

//Modificadores
void disponivel(Caixa *c, int valor);

void cliente_atendido(Caixa *c);

void atualiza_produtos(Caixa *c, int p);

void atualiza_espera(Caixa *c, int v);

void remove_clientes(Caixa *c);

//seletores
QUEUEP *fila_caixa(Caixa *c);

int numero(Caixa * c);

int pronta(Caixa * c);

int clientes(Caixa * c);

int produtos(Caixa * c);

int espera(Caixa * c);

int tempo_processamento(Caixa * c);

//Reconhecedores
bool vazia(Caixa *c);

//Retorna uma string do cliente
char * reprCaixa(Caixa * c);
