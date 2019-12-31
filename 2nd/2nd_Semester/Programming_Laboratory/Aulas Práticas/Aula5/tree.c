#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#define MAX 20
typedef union {
    char * nome;
    int valor;
} NO;
int main() {
    NO a;
    char aux[MAX];
    scanf("%s", aux);
    a.nome = malloc(sizeof(char)*(strlen(aux)+1));
    strcpy(a.nome,aux);
    printf("%s\n", a.nome);
    a.valor = 7;
    printf("%d\n", a.valor);
    return 0;
}