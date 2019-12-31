
#include <stdio.h>
#include <stdbool.h>
#include "sintaxe.h"



bool check_isEmpty(char *str){

	if(str[0] == '\0'){
		 printf("Variable is empty, please write a variable");
		 return true;
	 }
	 
	return false;
}

bool check_isReserved(char *str){
		int aux=0;
	if((strcmp(str, "ler")) ==0) aux++;
	
	if((strcmp(str, "escrever")) ==0) aux++;
	
	if((strcmp(str, "if")) ==0) aux++;
	
	if((strcmp(str, "goto")) ==0) aux++;
	
	if((strcmp(str, "label")) ==0) aux++;
	
	if((strcmp(str, "quit")) ==0) aux++;
	
	if(aux>0){
		printf("The variable is reserved, please rename your variable");
		return true;
	}
	return false;
}

bool check_semicolon(char *str){
	char aux[MAX];
	strcpy(aux, str);
	
	int size=strlen(aux)-2;

	
	if(aux[size] != ';'){
		printf("expected ; at the end of line"); 
		return false;
	}
	
	return true;
	
}

	
bool check_ler_escrever(char str[]){
	int count=0;
	if((check_semicolon(str))==false){
		return false;
	}
	else{
	char *variable=(char*)malloc(sizeof(char)*MAX);
	
	check_variable_escrever_ler(str, variable);
	
	if((check_isReserved(variable))==true){
		count++;
	}
	
	else{
		if((check_isEmpty(variable)) ==true){
			count++;
		}
	}
	free(variable);
}


	if(count >0) return false;

return true;
}

bool check_label_goto(char str[], char op){
	int inicio=0;
	if(op=='l') inicio=5;
	
	if(op=='g') inicio=4;
	
	int count=0;
	if((check_semicolon(str)) ==false){
		return false;
	}
	
	else{
	
	char *variable=(char*)malloc(sizeof(char)*MAX);
	
	check_variable_goto_label(str, inicio, variable);
	
	if((check_isReserved(variable))==true){
		count++;
	}
	
	else{
		if((check_isEmpty(variable)) ==true){
			count++;
		}
	}
	free(variable);
}

	
	if(count >0) return false;

return true;
}

bool check_if(char str[]){
	int count=0;

	
	if((check_semicolon(str)) ==false){
		return false;
	}
	else{
	
	char *var=(char*)malloc(sizeof(char)*MAX);
	char *tag=(char*)malloc(sizeof(char)*MAX);
	
	check_variable_ifStatement(str, var, tag);
	
	if((strcmp(var, "goto"))==0){
		printf("wrong syntax");
		return false;
	 }
	
	if((check_isReserved(var))==true || (check_isReserved(tag))==true){
		count++;
	}
	
	if((check_isEmpty(var)) ==true || (check_isEmpty(tag))==true ||(strcmp(tag, ";"))==0){
			count++;
		}
	
	free(var);
	free(tag);
	}

if(count >0) return false;

return true;

}

bool check_operacoes(char str[]){
	int count=0;
	char *guardar=(char*)malloc(sizeof(char)*MAX);
	char *primeiro=(char*)malloc(sizeof(char)*MAX);
	char *segundo=(char*)malloc(sizeof(char)*MAX);
	
	if((check_semicolon(str))==false){
		return false;
	}
	else{
	
	check_variable_operacoes(str, guardar,primeiro,segundo);
	
	if((check_isReserved(guardar))==true || (check_isReserved(primeiro))==true ||(check_isReserved(segundo))==true){
		count++;
	}
	
	if((check_isEmpty(guardar)) ==true || (check_isEmpty(primeiro))==true || (check_isEmpty(segundo))==true){
			count++;
		}
	
	free(guardar);
	free(primeiro);
	free(segundo);
	}
	
	
if(count>0) return false;
return true;
}

bool check_atribuicao(char str[]){
	int count=0;
	char *var=(char*)malloc(sizeof(char)*MAX);
	char *tag=(char*)malloc(sizeof(char)*MAX);
	int i=0;
	int size=strlen(str);
	for(i=0; i<size ;i++){
		if(str[i]=='='){
			break;
		}
	}
		
		if(i==strlen(str)){
			printf("Wrong syntax");
			return false;
		}
		
	if((check_semicolon(str))==false){
		return false;
	}
	
	else{
		
		check_variable_atribuicao(str, var,tag);
	
	if((check_isReserved(var))==true || (check_isReserved(tag))==true){
		count++;
	}
	
	if((check_isEmpty(var)) ==true || (check_isEmpty(tag))==true){
			count++;
		}
	
	free(var);
	free(tag);
	}

	
	
	if(count>0) return false;
	return true;	
	}
	
bool operations_sintaxe(char str[]){
		int count=0;
		char ch;
		bool aux;
		for(int i=0; i<strlen(str); i++){
			ch=str[i];
			if(ch== '+'){
				aux=check_operacoes(str);
				return aux;
			}
			if(ch=='-'){
				aux=check_operacoes(str);
				return aux;
			}
			if(ch== '/'){
				aux=check_operacoes(str);
				return aux;
			}
			if(ch== '*'){
				aux=check_operacoes(str);
				return aux;
			}
		}
	
	aux=check_atribuicao(str); //se não for operação de aritmética é operação de atribuição
	return aux;	
	}

		
		

 bool check_instruction_sintaxe(char str[]){
	bool aux;
	if(check_instruction_aux(0, 3, str)==0){ // Verifica se a instrução é ler
		removeSpaces(str);
		aux=check_ler_escrever(str);
		return aux;
	}
	else if(check_instruction_aux(1,8, str)==0){ //Verifica se a instrução é escrever
		removeSpaces(str);
		aux=check_ler_escrever(str);
		return aux;
	}
	else if(check_instruction_aux(2, 5, str)==0){ //Verifica se a instrução é label
		removeSpaces(str);
		aux=check_label_goto(str,'l');
		return aux;
	}
	else if(check_instruction_aux(3, 2, str)==0){ //Verifica se a instrução é if
		aux=check_if(str);
		return aux;
	}
	else if(check_instruction_aux(4, 4, str)==0){ //verifica se a instrução é goto
		removeSpaces(str);
		aux=check_label_goto(str, 'g');
		return aux;
	}
	else{ //verifica a sintaxe de operações aritméticas e atribuição
		removeSpaces(str);
		aux=operations_sintaxe(str);
		return aux;
	}
	
	return 0;
}

bool check_sintaxe(char path[]){
	FILE *arq;
	arq=fopen(path, "r");
	
	if(arq==NULL){
		printf("Ficheiro não encontrado\n");
		exit(0);
	}
	
	char *linha=(char*)malloc(sizeof(char)*MAX);
	int count=0;
	bool teste=true;
	
	while((fgets(linha, sizeof(char*)*MAX, arq)) !=NULL){
			count++;
		if(strcmp(linha, "\n") ==0){
			continue;
		}
		if((strcmp(linha, "quit;\n"))==0){
			//fclose(arq);
			break;
		}
		
		if((check_instruction_sintaxe(linha)) ==false){
			teste=false;
			printf(" -> at line %d\n", count);
		}
		
	}
	fclose(arq);
	return teste;
}
