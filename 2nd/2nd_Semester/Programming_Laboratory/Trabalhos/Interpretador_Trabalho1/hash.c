#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "hash.h"

// Recebe uma string e retorna o multiplicador para a posição (key) onde é para adicionar na hash
unsigned int hash( char *s ){
  unsigned int h;
  unsigned char *p;
  h=0;
  for (p = (unsigned char*) s; *p != '\0'; p++) {
    h = MULTIPLIER * h + *p;
  }
  return h;
}
// Percorre a lista à procura da string, se encontrar retorna a lista onde a string se encontra,
// senão retorna null
LIST lookup( char *s){
  LIST l =  table[ hash(s) ];
  while( l != NULL){
    if( strcmp( l->string, s) == 0){
      return l;
    }
    l= tail(l);
  }
  return NULL;
}
// Retorna o elem onde está a variável s
int get( char*s ){
  return lookup(s)->elem;
}
// Insere uma variável na hash, colocando na key correta
// Se esse valor ja existir na key, substitui
// Se o valor não existir, cria uma lista nessa linha de key
void insert( char *s, int value ){
  LIST l = lookup (s);
  if( l == NULL ){
     table[ hash(s) ] = newList(value,s,table[ hash(s) ]);
  }
  else {
    l->elem = value;
  }
}
// Inicia a hash
void init_table(){
  for( int i = 0; i < HASH_SIZE; i++){
    table[i] = NULL;
  }
}
// Imprime um valor de uma variável c
void printHashV( char *c ){
  LIST l = lookup (c);
  if(l == NULL)
    printf("Variable %s has not been initialized\n", c);
  else 
    printf("%s = %d\n", l->string, l->elem);
}
