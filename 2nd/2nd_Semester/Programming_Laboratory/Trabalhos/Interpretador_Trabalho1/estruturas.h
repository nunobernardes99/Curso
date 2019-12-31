
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <string.h>


#define MAX 100
#define VALUE(p) ((p) -> elem)
#define NXT(p) ((p) -> next)

#pragma once


typedef enum {ATRIB, ADD, SUB, MUL,DIV, IF_I, PRINT, READ, GOTO_I, LABEL} OpKind;

typedef enum {EMPTY, INT_CONST, STRING} ElemKind;

typedef struct{
     ElemKind kind;
      union{ 
        int val;
        char *name;
        } contents;
} Elem;

typedef struct{
   OpKind op;
   Elem first, second, third;
} Instr;

typedef struct lista{
  Instr elem;
  struct lista* next;
  
} LISTA;


//Função que cria um novo nó com o "objeto" Instr
LISTA *new_node(Instr elem, LISTA *prox);

//Funçao que adiciona um novo nó ao fim da lista
LISTA *add_last(Instr elem, LISTA *l);

//Função que define o ElemKind para STRING e o contentes.name para o valor de str
Elem mkVar(char *str);

//Função que define o ElemKing para INT_CONST e o contents.val para x
Elem mkInt(int x);

//Função que define o ElemKind para EMPTY
Elem mkEmpty();

//Função que cria uma nova Instr e define os seus valores
Instr mkInstrucao(OpKind operacao, Elem primeiro, Elem segundo, Elem terceiro);

//Função que retorna valor inteiro do Elem x
int getValue(Elem x);

//Função que retorna o nome do Elem x
char *getName(Elem x);

