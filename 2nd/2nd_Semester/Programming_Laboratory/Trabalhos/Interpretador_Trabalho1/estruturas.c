#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <string.h>
#include "estruturas.h"



#define MAX 100
#define VALUE(p) ((p) -> elem)
#define NXT(p) ((p) -> next)

LISTA *new_node(Instr elem, LISTA *prox){
	
	 LISTA *l=(LISTA*)malloc(sizeof(LISTA));
	 
	 VALUE(l)=elem;
	 NXT(l)= prox;
	 return l;
 }
 
 LISTA *add_last(Instr elem, LISTA *l){
	 
	 LISTA *curr=l;
	 
	 if(l==NULL){
		 LISTA *aux= new_node(elem, l);
			 l=aux;
			 return l;
		 }
	
	while(NXT(curr) !=NULL){
		curr=NXT(curr);
	}
	
	NXT(curr) = new_node(elem, NULL);
	return l;
}

 Elem mkVar(char *str){
	 Elem novo;
	 novo.kind=STRING;
	 novo.contents.name= strdup(str);
	 return novo;
 }
 
 Elem mkInt(int x){
	 Elem novo;
	 novo.kind=INT_CONST;
	 novo.contents.val=x;
	 return novo;
 }
 
 Elem mkEmpty(){
	 Elem novo;
	 novo.kind=EMPTY;
	 return novo;
 }
 
 Instr mkInstrucao(OpKind operacao, Elem primeiro, Elem segundo, Elem terceiro){
	 Instr novo;
	 novo.op=operacao;
	 novo.first=primeiro;
	 novo.second=segundo;
	 novo.third=terceiro;
	 return novo;
 }
 
 int getValue(Elem x){
	 if(x.kind==INT_CONST){
		 return x.contents.val;
	 }
	 return 1;
 }
 
 char *getName(Elem x){
	 if(x.kind==STRING){
		 return x.contents.name;
	 }
	 return NULL;
 }
