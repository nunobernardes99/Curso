
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include "Cliente.h"

// Construtor
Cliente * initCliente(int i, int e) {
    Cliente *c = (Cliente*)malloc(sizeof(Cliente));
    c -> items = i;
    c -> entrada = e;
    return c;
}
// Retorna o número de artigos
int artigos( Cliente  *cliente) {
    return cliente -> items;
}
// Retorna o tempo de entrada
int tempo_entrada( Cliente  *cliente) {
    return cliente -> entrada;
}
// Retorna uma string do cliente
char * reprCliente(Cliente  *cliente) {
    char * str = (char *) malloc(sizeof(Cliente));
    sprintf(str, "[%d : %d] ", cliente -> items, cliente -> entrada);
    return str;
}
