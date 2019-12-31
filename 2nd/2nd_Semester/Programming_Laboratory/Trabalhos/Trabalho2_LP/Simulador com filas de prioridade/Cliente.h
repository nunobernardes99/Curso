
// Tipo do cliente
typedef struct Cliente {
    int items;
    int entrada;
} Cliente;


#pragma once

//Construtor
Cliente *initCliente(int i, int e);

//Retorna o número de artigos
int artigos( Cliente *cliente);

//Retorna o tempo de entrada
int tempo_entrada( Cliente *cliente);

//Retorna uma string do cliente
char *reprCliente( Cliente *cliente);
