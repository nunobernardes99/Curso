#include "lists.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
int main() {
    NODE *lista = NULL;
    lista = new_node(5, lista);
    lista = new_node(7, lista);
    lista = new_node(10, lista);
    return 0;
}