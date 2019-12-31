#include "Cliente.h"
#include "Caixa.h"
#pragma once

void trata_primeiro(int passo, Caixa *caixa_atual);

void trata_cliente(int passo, Caixa *lista_caixas[]);

bool caixas_cheias( Caixa *lista_caixas[]);

void mostra_caixas(Caixa *lista_caixas[]);

void processa_resultados(Caixa *caixas[]);

void simulador(int afluencia,int apetencia, int n_caixas, int n_ciclos);



