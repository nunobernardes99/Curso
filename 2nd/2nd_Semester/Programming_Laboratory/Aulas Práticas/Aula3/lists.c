#include "lists.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
NODE * new_node(int v, NODE *prox) {
    NODE *l = (NODE*)malloc(sizeof(NODE));
    VALUE(l) = v;
    NXT(l) = prox;
    return (l);
}
int length(NODE *l) {
    int ctr = 0;
    while(l != NULL) {
        ctr++;
        l= NXT(l);
    }
    return ctr;
}
int lengthRecursive(NODE *l) {
    if(l == NULL) return 0;
    return 1 + length(NXT(l));
}

NODE * search(int x, NODE *l) {
    while(l != NULL && VALUE(l) != x)
        l = NXT(l);
    return NULL;
}
NODE * searchRecursive(int x, NODE *l) {
    if(l == NULL || value(l) == x) return (l);
    return searchRecursive(x, NXT(l));
}

NODE * add_last(int x, NODE *l) {
    NODE *prev = NULL, *curr = l;
    while(curr != NULL)
        prev = curr;
        curr = NXT(l);
    if(prev == NULL) return new_node(x, NULL);
    NXT(prev) = new_nove(x, NULL);
    return (l);
}

// Função que retira todas as ocorrências de um
// valor x de uma lista de inteiros (ligada simples)
NODE * removeAll(int x, NODE *l) {
    NODE * aux, *prev;
    while(l != NULL && VALUE(l) == x) {
        aux = NXT(l);
        free(l);
        l = aux;
    }
    if(l == NULL) return l;
    prev = l; // prev não é null
    while(NXT(prev) != NULL) {
       if(VALUE(NXT(prev)) == x) {
            aux = NXT(prev);
            NXT(prev) = NXT(aux);
            free(aux);
       } else prev = NXT(prev);
    }
    return l;
}

NODED * removeNode(NODED * l, NODED * no) {
    if(no == NULL) return l;
    if(no == l) {
        l = NXT(l);
        if(l!=NULL) PREV(l) = NULL;
    } else {
        NXT(PREV(no)) = NXT(no);
        if(NXT(no) != NULL)
            PREV(NXT(no)) = PREV(no);
    }
    free(no);
    return l;
}
