#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <string.h>

#include "load.h"
#include "hashOperators.h"
#include "estruturas.h"
#include "sintaxe.h"

void print(LISTA *inst) {
  switch (inst->elem.op) {
   
    case ATRIB:
		printf("ATRIB ");
		if(inst->elem.first.kind==STRING){	
			printf("%s ", getName(inst->elem.first));
		}
		else{
			printf("%d ", getValue(inst->elem.first));
		}
		if( inst->elem.second.kind==STRING){
			printf("%s ", getName(inst->elem.second));
		}
		else{
			printf("%d ", getValue(inst->elem.second));
		}
		
		printf("%d\n", inst->elem.third.kind);
 
    break;
    case ADD: 
		printf("ADD ");
		if(inst->elem.first.kind==INT_CONST){
			printf("%d ", getValue(inst->elem.first));
		}
		if(inst->elem.first.kind==STRING){
			printf("%s ", getName(inst->elem.first));
		}
		
		if(inst->elem.second.kind==INT_CONST){
			printf("%d ", getValue(inst->elem.second));
		}
		if(inst->elem.second.kind==STRING){
			printf("%s ", getName(inst->elem.second));
		}
		printf("%s\n", getName(inst->elem.third));
		break;
    
    
    
    case SUB: 
    
		printf("SUB ");
		if(inst->elem.first.kind==INT_CONST){
			printf("%d ", getValue(inst->elem.first));
		}
		if(inst->elem.first.kind==STRING){
			printf("%s ", getName(inst->elem.first));
		}
		
		if(inst->elem.second.kind==INT_CONST){
			printf("%d ", getValue(inst->elem.second));
		}
		if(inst->elem.second.kind==STRING){
			printf("%s ", getName(inst->elem.second));
		}
		printf("%s\n", getName(inst->elem.third));
		break;
    
    
    case DIV: 
		printf("DIV ");
		if(inst->elem.first.kind==INT_CONST){
			printf("%d ", getValue(inst->elem.first));
		}
		if(inst->elem.first.kind==STRING){
			printf("%s ", getName(inst->elem.first));
		}
		
		if(inst->elem.second.kind==INT_CONST){
			printf("%d ", getValue(inst->elem.second));
		}
		if(inst->elem.second.kind==STRING){
			printf("%s ", getName(inst->elem.second));
		}
		printf("%s\n", getName(inst->elem.third));
		break;
    
    
    
    case MUL:
		printf("MUL ");
		if(inst->elem.first.kind==INT_CONST){
			printf("%d ", getValue(inst->elem.first));
		}
		if(inst->elem.first.kind==STRING){
			printf("%s ", getName(inst->elem.first));
		}
		
		if(inst->elem.second.kind==INT_CONST){
			printf("%d ", getValue(inst->elem.second));
		}
		if(inst->elem.second.kind==STRING){
			printf("%s ", getName(inst->elem.second));
		}
		printf("%s\n", getName(inst->elem.third));
		break;
		
	case IF_I:
		printf("IF_I %s %s %d\n", getName(inst->elem.first), getName(inst->elem.second), inst->elem.third.kind);
		break;
	case READ:
		printf("READ %s %d %d\n", getName(inst->elem.first), inst->elem.second.kind, inst->elem.third.kind);
		break;

	case PRINT:
		printf("PRINT %s %d %d\n", getName(inst->elem.first), inst->elem.second.kind, inst->elem.third.kind);
		break;
	case GOTO_I:
		printf("GOTO_I %s %d %d\n", getName(inst->elem.first), inst->elem.second.kind, inst->elem.third.kind);
		break;
	case LABEL:
		printf("LABEL %s %d %d\n", getName(inst->elem.first), inst->elem.second.kind, inst->elem.third.kind);
		break;

	default:
		printf("não implementado");
	}
}

LISTA * salta_instrucoes(LISTA *fila){
			
			char *aux;
			char label[MAX];
			char tag[MAX];
			char *var=fila->elem.first.contents.name;
			int count=0;
			
			if(fila->elem.op==IF_I &&(lookup(var)!=NULL)){ //se a variável do if não válida não salta instruções
				
				aux=getName(fila->elem.second);
				strcpy(label,aux);
				count=1;
			}
			
			if(fila->elem.op==GOTO_I){	
				aux=getName(fila->elem.first);
				strcpy(label,aux);
				count=1;
			}
			
			if(count ==1){  
			
			while(1){
				while(fila->elem.op !=LABEL){
					fila=NXT(fila);
				}
			
			aux=getName(fila->elem.first);
			strcpy(tag,aux);
			
			if((strcmp(label, tag)) ==0){
				break;
			 }
			 fila=NXT(fila);
			 
			 if(fila==NULL){
				 printf("Erro: Label não encontrada\n");
				 exit(0);
			 }
		}
	}
	return fila;
}

int main(int argc,char* argv[]){
	int count=0;
	LISTA *fila=NULL;
	
	if(argv[1]==NULL){
		printf("Indique o nome do ficheiro a ser lido\n");
		return 0;
	}
	
	if((check_sintaxe(argv[1])==false)){
		return 0;
	}
	
	
	fila=read_file(argv[1]);
	
	while(fila!=NULL){
		//print(fila); //função usada para testar o programa
		//printf("\n");
		
		if(fila->elem.op ==IF_I || fila->elem.op==GOTO_I){ //se for if ou goto vai saltar instruções até à instrução indicada pelo if ou pelo goto
			fila=salta_instrucoes(fila);
	}
	
		if(fila==NULL) return 0; 
		
		hashOperations(fila->elem);
		fila=NXT(fila);
		count++;
	
	}
}

