#ifndef LISTS_H
#define LISTS_H
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define VALUE(P) ((P) -> value)
#define NXT(P) ((P) -> nxt)
#define PREV(P) ((P) -> prev)


typedef struct node {
    int value;
    struct node *nxt;
} NODE;

typedef struct nodeDouble {
    PONTO p;
    struct nodeDouble *prev, *nxt;
} NODED, *PNODED;

typedef struct ponto {
    int x, y;
} PONTO;

int length(NODE *l);
NODE * new_node(int v, NODE *prox);
NODE * search(int x, NODE *l);
NODE * add_last(int x, NODE *l);
NODE * removeAll(int x, NODE *l);

#endif