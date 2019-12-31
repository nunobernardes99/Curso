#include "load.h"
#pragma once

//verifica se a palavra passada está vazia
bool check_isEmpty(char *str);

//verifica se a palavra passada é uma das palavras reservadas
bool check_isReserved(char *str);

//verifica se a linha passada tem ; no final
bool check_semicolon(char *str);

//verifica se a função ler/escrever tem erros sintáxicos
bool check_ler_escrever(char str[]);

//verifica se a função label/goto tem erros sintáxicos
bool check_label_goto(char str[], char op);

//verifica se a função if te, erros sintáxicos
bool check_if(char str[]);

//verifica erros sintáxicos nas operações aritméticas
bool check_operacoes(char str[]);

//verifica se a funçao atribuição tem erros sintáxicos
bool check_atribuicao(char str[]);

//verifica qual a operação aritmética e retorna false se a sintaxe estiver incorreta
bool operations_sintaxe(char str[]);

//verifica qual é a instrução a ser verificada
bool check_instruction_sintaxe(char str[]);

//lê a linha e chama a função para verificar a sintaxe
bool check_sintaxe(char path[]);


