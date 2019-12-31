#include <stdbool.h> 
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "Cliente.h"
#include "Caixa.h"
#include "Queue.h"
#include "simulador.h"

int numCaixas;
void trata_primeiro(int passo, Caixa *caixa_atual){
		//Obtem informação sobre o primeiro cliente na fila
		
		Cliente *cliente=caixa_atual->lista->queue[caixa_atual->lista->inicio];
		int espera=passo - pronta(caixa_atual);
		int processados = espera * tempo_processamento(caixa_atual);
		
		//atualizar informação da caixa caso o primeiro cliente da fila esteja despachado 
		
		if(processados >=artigos(cliente)){
			disponivel(caixa_atual, passo +1);
			cliente_atendido(caixa_atual);
			atualiza_produtos(caixa_atual, artigos(cliente));
			atualiza_espera(caixa_atual, passo - tempo_entrada(cliente));			
			printf("-->Processado cliente com %d artigos  na caixa %d tempo de espera %d \n", artigos(cliente), numero(caixa_atual), espera);
			printf("\n");
			remove_clientes(caixa_atual);
		}
	}
		
		
void trata_cliente(int passo, Caixa *lista_caixas[]){
		
	Caixa *aux;
	for(int i=0; i<numCaixas; i++){
		aux=lista_caixas[i];
			if(vazia(aux)){
				disponivel(aux, passo);
			}
			else trata_primeiro(passo, aux);
		} 
		
}

bool caixas_cheias( Caixa *lista_caixas[]){

	int flag=0;
	for(int i=0; i<numCaixas; i++){
		if( !vazia(lista_caixas[i])){
			flag=1;
		}
	}
	if(flag==1) return true;
	return false;
}

void mostra_caixas(Caixa *lista_caixas[]){
	
	for(int i=0; i<numCaixas; i++){
		char *aux=(reprCaixa(lista_caixas[i]));
		
		printf("%s\n", aux);
	}
}

void processa_resultados(Caixa *caixas[]){
	
	for(int i=0; i<numCaixas; i++){
		printf("Caixa %d (atendimento %d produtos por ciclo)\n", numero(caixas[i]), tempo_processamento(caixas[i]));
		printf("\n");
		
		if(clientes(caixas[i]) !=0){
			printf("%d Clientes atendidos, media produtos/cliente %.1d,\n", clientes(caixas[i]), (produtos(caixas[i]))/(clientes(caixas[i])));
			printf("Tempo médio de espera %.2f \n", (float)espera(caixas[i])/clientes(caixas[i]));
			printf("\n");
		}
		else{
			printf("Não atendeu clientes\n" );
			printf("\n");
		}
	}
}

void simulador(int afluencia,int apetencia, int n_caixas, int n_ciclos){
	//Inicializa caixas
	srand((unsigned)time(NULL));
	Caixa *caixas[n_caixas];
	
	
	for(int i=0; i<n_caixas; i++){
		caixas[i]=initCaixa(i);
	}
	
	//Inicializa simulacao 
	
	for(int i=1; i<=n_ciclos; i++){
		printf("== CICLO %d ==\n", i);
		
		//Processa clientes nas caixas
		trata_cliente(i, caixas);
		
		//Cria novo cliente aleatoriamente
		
		double aux=(double)rand() / (double)RAND_MAX;
		
		if(aux<=(double)(afluencia/100.0)){
			//Cria número de produtos aleatoriamente
			
			int produtos=(rand()%(3*apetencia))+1;
			
			Cliente *novo_cliente=initCliente(produtos, i);
			
			printf("--> Criado cliente com  %d produtos\n", produtos);
			printf("\n");
			
			//seleciona caixa aleatoriamente
			
			int caixa_escolhida= rand()%n_caixas;
			
			//Coloca cliente na fila da caixa escolhida
			
			enqueue(novo_cliente, caixas[caixa_escolhida]->lista);
		}
	
	mostra_caixas(caixas);
}
	
	//Fecho da simulação
	printf("\n");
	printf("== FECHO DE CAIXAS ==\n");
	printf("\n");
	
	int t=n_ciclos+1;
	
	while(caixas_cheias(caixas)){
		printf("== CICLO %d ==\n", t);
		trata_cliente(t, caixas);
		mostra_caixas(caixas);
		t=t+1;
	}
	processa_resultados(caixas);
}

int  main(){
	int afluencia, apetencia, n_caixas, n_ciclos;
	printf("Indique a afluência desejada (1-100): ");
	scanf("%d", &afluencia);
	printf("\n");
	if(afluencia>100 || afluencia <1){
		printf("Escreva um valor para a afluência entre 1 e 100\n");
		exit(0);
	}
	
	printf("Indique a apetência desejada(1-100): ");
	scanf("%d", &apetencia);
	printf("\n");
	
	if(apetencia>100 || apetencia <1){
		printf("Escreva um valor para a apetência entre 1 e 100\n");
		exit(0);
	}
	
	printf("Indique o número de caixas desejadas: ");
	scanf("%d", &n_caixas);
	printf("\n");
	
	printf("Indique os ciclos desejados: ");
	scanf("%d", &n_ciclos);
	printf("\n"); 
	
	numCaixas=n_caixas;
	
	simulador(afluencia,apetencia,n_caixas, n_ciclos);
	
	return 0;
	}
	
	
	
			
		




	
		
		
			
		
	
	
	
