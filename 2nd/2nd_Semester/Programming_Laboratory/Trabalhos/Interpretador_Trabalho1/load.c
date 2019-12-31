#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <string.h>
#include <stdbool.h>
#include "load.h"
#define MAX 100

LISTA *fila=NULL;

int whiteSpace(char str[], int aux){  //salta as posições da str que são espaços e retorna a posição do primeiro caracter que não é espaço
	int i=aux;
	char ch=str[i];
	while(ch==' '){
		i++;
		ch=str[i];
	}
	return i;
}

int whileIsDifferent(char str[], char *aux, char flag, int i){  //guarda em aux os caracteres até que sejam diferentes da flag e retorna i que é a posição do caracter flag
	
	char ch=str[i];
	int j=0;
	while(ch!=flag){
		aux[j]=ch;
		i++;
		ch=str[i];
		j++;
	}
	aux[j]='\0';
	
	return i;
}

int charToInt(char str[]){  //Transforma String em inteiro
	int res=0;
	
	int size=strlen(str);
	
	for(int i=0; i<size; i++){
		res= 10*res+(str[i]-'0');
	}
	return res;
}

void removeSpaces(char *str) {  //Função para remover espaços
    int count = 0; 
   
    for (int i = 0; str[i]; i++) 
        if (str[i] != ' ') 
            str[count++] = str[i]; 
                                    
    str[count] = '\0'; 
}

void check_variable_operacoes(char str[], char *guardar, char *primeiro, char *segundo){  //devolve as variáveis para as operações aritmeticas
	int i=0;
	int j=0;
	char ch=str[0];
	
	i=(whileIsDifferent(str, guardar, '=', i)); //guarda o nome da variável onde é para guardar o resultado da operação
	i++;
	ch=str[i];
	
	while(ch!='+' && ch!='-' && ch!='*' && ch!='/'){  //guarda a primeira variável da operação
		primeiro[j]=ch;
		i++;
		ch=str[i];
		j++;
	}
	primeiro[j]='\0';
	i++;	
	i=whileIsDifferent(str, segundo, ';', i); //guarda a ultima variável da operação
}
	
void check_variable_escrever_ler(char str[], char *variable){ //devolve as variaveis para as operacoes de ler
	int j=0;
	int i=0;
	char ch=str[0];
	char *temp=(char*)malloc(sizeof(char)*MAX);
	
	i=whileIsDifferent(str, temp, '(', i);  //salta as posições iniciais da string até encontrar (
	i++;
	
	i=whileIsDifferent(str, variable, ')', i); //guarda a variável que está entre ()
	
	free(temp);
}

void check_variable_goto_label(char str[], int inicio, char *variable){ //devolve o nome da variavel da operacao label e goto
	int i=inicio;
	char ch=str[i];
	
	i=whileIsDifferent(str, variable, ';',i); //guarda a variável da operação
}

void check_variable_ifStatement(char str[], char *var, char *tag){
	int i=2;
	char ch=str[2];
	
	i=whiteSpace(str, i); //salta os espaços em branco depois de if
	
	i=whileIsDifferent(str, var, ' ', i); //guarda a variável da operação
	
	i=whiteSpace(str, i); //salta os espaços em branco depois de ler a variável
	i+=4; //salta a palavra goto
	
	i=whiteSpace(str,i); //salta os espaços em branco entre goto e a variável do tipo label
	
	i=whileIsDifferent(str, tag, ';', i); //guarda a variável label
	removeSpaces(var);
	removeSpaces(tag);
}


void check_variable_atribuicao(char str[], char*var, char*tag){
	int i=0;
	
	int j=0;
	i=whileIsDifferent(str, var,'=',i); //guarda a variável onde é para guardar o resultado da atribuição
	i++;

	j=0;
	i=whileIsDifferent(str, tag, ';', i); //guarda o valor que é para atribuir
}

Instr construtor_ler_escrever_goto_label(OpKind op, char str[]){
	char *variable=(char*)malloc(sizeof(char)*MAX);
	
	switch(op){
		case READ: case PRINT:
			check_variable_escrever_ler(str, variable); //guarda o nome da variável de ler e escrever
			break;
		case GOTO_I:
			check_variable_goto_label(str,4,variable); //guarda o nome da variável da função goto
			break;
		case LABEL:
			check_variable_goto_label(str, 5,variable);  //guarda a variável de label
			break;
		default:
			printf("Erro\n");
			break;
		}
		
	Elem primeiro=mkVar(variable);
	Elem segundo= mkEmpty();
	Elem terceiro= mkEmpty();
	
	Instr aux= mkInstrucao(op, primeiro, segundo,terceiro);  //cria o quadruplo da instrução
	
	free(variable);
	
	return aux;
}


Instr construtor_ifStatement_Atribuicao(OpKind op, char str[]){
	
		char *var=(char*)malloc(sizeof(char*)*MAX);
		char *tag=(char*)malloc(sizeof(char*)*MAX);
		Elem primeiroElem;
		Elem segundoElem;
		Elem terceiroElem;
			
		Instr aux;
		switch(op){
			case IF_I: //cria o quádruplo da instrução IF
			
					check_variable_ifStatement(str, var,tag);
					primeiroElem=mkVar(var);
					segundoElem=mkVar(tag);
					terceiroElem=mkEmpty();
		
					aux=mkInstrucao(op, primeiroElem, segundoElem, terceiroElem);
					free(var);
					free(tag);
					return aux;
			case ATRIB: //cria o quádruplo da instrução de atribuição
					
					check_variable_atribuicao(str, var,tag); 
					
					if(isdigit(*tag)){
						int valor1=charToInt(tag);
						primeiroElem=mkInt(valor1);
					}
					else{
						primeiroElem=mkVar(tag);
					}
					segundoElem=mkVar(var);
					
					terceiroElem=mkEmpty();
					
					aux=mkInstrucao(op, primeiroElem,segundoElem,terceiroElem);
					free(var);
					free(tag);
					return aux;
			default:
				break;
				
				}	
		
		return aux;
	
}

Instr construtor_oper_aritmeticas(OpKind op,char str[]) {
	
	char *guardar=(char*)malloc(sizeof(char)*MAX);
	char* primeiro=(char*)malloc(sizeof(char)*MAX);
	char*segundo=(char*)malloc(sizeof(char)*MAX);
	
	check_variable_operacoes(str, guardar, primeiro, segundo);  //guarda as variáveis da conta
		
	
	Elem terceiroElem=mkVar(guardar);
	Elem primeiroElem;
	Elem segundoElem;
		
		if(isdigit(*primeiro)){ //se a primeira variável da conta for string guarda a string, se for inteiro transforma a string em inteiro
			int valor1=charToInt(primeiro);
			primeiroElem=mkInt(valor1);
		}
		else{ 
			primeiroElem=mkVar(primeiro);
		}
		
		
		if(isdigit(*segundo)){ //se a segunda variável da conta for string guarda a string, se for inteiro transforma a string em inteiro
			int valor2=charToInt(segundo);
			segundoElem=mkInt(valor2);
		}
		else{ 
			segundoElem=mkVar(segundo);
		}	
		
		Instr operacao = mkInstrucao(op, primeiroElem,segundoElem,terceiroElem); //constroi o quadruplo da conta
			
			free(guardar);
			free(primeiro);
			free(segundo);		
			return operacao;
}
//Funções para todas as operações
 void ler(char str[]){
	
	Instr leitura=construtor_ler_escrever_goto_label(READ, str); //cria o quadruplo da função ler
	fila=add_last(leitura, fila);
	}
	
	void escrever(char str[]){
		
		Instr escrever=construtor_ler_escrever_goto_label(PRINT, str); //cria o quadruplo para a funçao escrever
		fila=add_last(escrever, fila);	
	}
	
	void label(char str[]){
		
		Instr label=construtor_ler_escrever_goto_label(LABEL, str); //cria o quadruplo para a função label
		fila=add_last(label, fila);
	}
	
	void if_statement(char str[]){
		
		Instr ifStatement =construtor_ifStatement_Atribuicao(IF_I, str); //cria o quadruplo da função IF
		
		fila=add_last(ifStatement, fila);
		
		
	}
	
	void goto_statement (char str[]){
		
		Instr vaiPara =construtor_ler_escrever_goto_label(GOTO_I, str); //cria o quadruplo da função goto

		fila=add_last(vaiPara, fila);
		
	}
	
	void atribuicao(char str[]){ //cria o quadruplo da função atribuição
		
		Instr atribuicao = construtor_ifStatement_Atribuicao(ATRIB, str);
		
		fila=add_last(atribuicao,fila);
	}
	
	//Operações de aritmética
	void adicao(char str[]){
		
		Instr adicao=construtor_oper_aritmeticas(ADD,str); //cria o quadruplo da adição
		fila=add_last(adicao, fila);

	}
	
	void subtracao(char str[]){
		
		Instr subtracao=construtor_oper_aritmeticas(SUB,str); //cria o quadruplo da função subtracao
	
		fila=add_last(subtracao, fila);
		
		}	
	void divisao(char str[]){

		Instr divisao=construtor_oper_aritmeticas(DIV,str); //cria o quadruplo da função divisão

		fila=add_last(divisao, fila);
		
}
	void multiplicacao(char str[]){  
		
		Instr multiplicacao=construtor_oper_aritmeticas(MUL,str); //cria o quadruplo da multiplicacao
		fila=add_last(multiplicacao, fila);
	}
	
	//Função para verificar se é para adicionar,subtrair,dividir,multiplicar ou atribuir
	int  operations(char str[]){
		int count=0;
		char ch;
		for(int i=0; i<strlen(str); i++){
			ch=str[i];
			if(ch== '+'){
				adicao(str);
				return 0;
			}
			if(ch=='-'){
				subtracao(str);
				return 0;
			}
			if(ch== '/'){
				divisao(str);
				return 0;
			}
			if(ch== '*'){
				multiplicacao(str);
				return 0;
			}
		}
	
	atribuicao(str); //se não for operação de aritmética é operação de atribuição
	return 0;	
	}
	
	 int check_instruction_aux(int i, int size, char str[]){
	 
	 char matriz[5][8]={"ler",
						"escrever",
						"label",
						"if",
						"goto"};
						
	 for(int j=0; j<size; j++){
		 if(matriz[i][j] != str[j]){
			 return -1;
		 }
	 }
	 return 0;
 }
 
 int check_instruction(char str[]){
	
	if(check_instruction_aux(0, 3, str)==0){ // Verifica se a instrução é ler
		removeSpaces(str);
		ler(str);
		return 0;
	}
	else if(check_instruction_aux(1,8, str)==0){ //Verifica se a instrução é escrever
		removeSpaces(str);
		escrever(str);
		return 0;
	}
	else if(check_instruction_aux(2, 5, str)==0){ //Verifica se a instrução é label
		removeSpaces(str);
		label(str);
		return 0;
	}
	else if(check_instruction_aux(3, 2, str)==0){ //Verifica se a instrução é if
		if_statement(str);
		return 0;
	}
	else if(check_instruction_aux(4, 4, str)==0){ //verifica se a instrução é goto
		removeSpaces(str);
		goto_statement(str);
		return 0;
	}
	else{
		removeSpaces(str);
		operations(str);
		return 0;
	}
	
	return 0;
}		
LISTA* read_file(char path[]){
	
	FILE *arq;
	arq=fopen(path, "r");
	
	if(arq==NULL){
		printf("Ficheiro não encontrado\n");
		exit(0);
	}
	
	char aux[MAX];

	while((fgets(aux, sizeof(char*)*MAX, arq)) !=NULL){
		
			
		if(strcmp(aux, "\n") ==0){
			continue;
		}
		if((strcmp(aux, "quit;\n"))==0){
			fclose(arq);
			break;
		}
		
		check_instruction(aux);
	}
	return fila; 	
} 
