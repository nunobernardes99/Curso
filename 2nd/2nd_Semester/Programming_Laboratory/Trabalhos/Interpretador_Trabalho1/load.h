

#include <stdio.h>
#include "estruturas.h"

#pragma once


//transforma String em digito
int charToInt(char str[]);
//remove espaços

void removeSpaces(char *str);

//devolve as variaveis para as operacoes de ler
//escrever, subtrair, adicionar,multiplicar e atribuir
void check_variable_operacoes(char str[], char *guardar, char *primeiro, char *segundo);


//devolve o nome da variavel da operacao label e goto
void check_variable_goto_label(char str[], int inicio, char *variable);

 
 //devolve a variável a analisar e a LABEL
void check_variable_ifStatement(char str[], char *var, char *tag);
 //devolve a variável de ler e escrever
 void check_variable_escrever_ler(char str[], char *variable);
//devolve a variável onde guardar e a variável que é para guardar
void check_variable_atribuicao(char str[], char*var, char*tag);
 
 //Constroi os quadruplos das funçoes de ler,escever,goto e label
 Instr construtor_ler_escrever_goto_label(OpKind op, char str[]);
 
 //constroi os quadruplos da funçao if
 Instr construtor_ifStatement(OpKind op, char str[]);
 
 //constroi os quadruplos das operacoes aritmeticas
 Instr construtor_oper_aritmeticas(OpKind op,char str[]);
 
 //Função que chama as funçoes necessárias para analisar e constroir o quadruplo de ler
 void ler(char str[]);
 
 //Função que chama as funçoes necessárias para analisar e constroir o quadruplo de escrever
 void escrever(char str[]);
 
//Função que chama as funçoes necessárias para analisar e constroir o quadruplo de label
void label(char str[]);

//Função que chama as funçoes necessárias para analisar e constroir o quadruplo de if
void if_statement(char str[]);

//Função que chama as funçoes necessárias para analisar e constroir o quadruplo de goto
void goto_statement (char str[]);

//Função que chama as funçoes necessárias para analisar e constroir o quadruplo da adicao
void adicao(char str[]);

//Função que chama as funçoes necessárias para analisar e constroir o quadruplo da subtracao
void subtracao(char str[]);

//Função que chama as funçoes necessárias para analisar e constroir o quadruplo da divisao
void divisao(char str[]);

//Função que chama as funçoes necessárias para analisar e constroir o quadruplo da multiplicaçao
void multiplicacao(char str[]);


//Função para verificar se é para adicionar,subtrair,dividir,multiplicar ou atribuir
int  operations(char str[]);

//Função auxiliar
int check_instruction_aux(int i, int size, char str[]);

//Funão que verifica se é uma instrução de ler, escrever, label, if ou goto
int check_instruction(char str[]);

//Função que lê o ficheiro e chama as funções para criar o quadruplo de cada funcao
LISTA *read_file(char path[]);

