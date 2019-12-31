#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "lista.h"
// Cria uma nova lista
LIST newList(int n, char *c, LIST l ){
  LIST new = malloc(sizeof(struct list));
  new-> elem = n;
  new-> string = strdup(c);
  new-> next = l;
  return new;
}
// Retorna o elemento na cabeça da lista
char* head(LIST l){
  if( l == NULL ){
    printf( "Lista vazia\n" );
    return NULL;
  }
  return l->string;
}
// Retorna a lista excepto o primeiro elemento
LIST tail(LIST l){
  if(l == NULL ) {
    printf( "Lista vazia\n" );
    return NULL;
  }
  return l->next;
}
// Retorna o tamanho da lista
int length( LIST l ){
  if( l == NULL ) return 0;
  return 1 + length( tail( l ));
}
// Retorna o elemento na posição n
int elem_n( int n, LIST l){
  if( l == NULL ) return -1;
  if( n == 0 ) return l-> elem;
  return elem_n( n-1, l-> next);
}
// Concatenar uma lista com outra
LIST append( LIST l1, LIST l2){
  return addLast( l1->elem, l1->string, l2);
}
// Imprime uma lista
void printList( LIST l ){
  if( l == NULL ) {
    printf("\n");
    return;
  }
  printf("%d %s", l->elem, l->string);
  printList( tail( l ) );
}
// Adiciona ao fim da lista
LIST addLast( int n, char *c, LIST l){
  if( l == NULL ) return newList( n , c, NULL);
  return newList( l->elem, l->string, addLast( n, c, tail(l) ) );
}
