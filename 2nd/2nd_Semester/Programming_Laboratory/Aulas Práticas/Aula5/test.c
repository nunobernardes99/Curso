#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#define NHASH 100
typedef enum {ATRIB, ADD, SUB, MUL, IF_I, PRINT, READ, GOTO_I, LABEL} OpKind;

typedef enum {EMPTY, INT_CONST, STRING} ElemKind;

typedef struct {
    ElemKind kind;
    union { 
        int val;
        char *name;
    } contents;
} Elem;

typedef struct {
    OpKind op;
    Elem first, second, third;
} Instr;

char* removeSpaces(char* input) {
    int i,j;
    char *output=input;
    for (i = 0, j = 0; i<strlen(input); i++,j++) {
        if (input[i]!=' ') output[j]=input[i];                     
        else j--;                                     
    }
    output[j]=0;
    return output;
}

void criarInstrs(char *str) {
    //Instr aux;
    removeSpaces(str);
    printf("%s", str);
    //return aux;
}

unsigned int hash(char *str) {
	int multiplicador=31;
	unsigned int res=0;
	while(*str!='\0'){
		res= res*multiplicador + *str;
        //printf("%d\n", res);
		str++;
	}
	return res%NHASH;
}

int main() {
    char *test = "p = 2*p";
    printf("%s\n", test);
    printf("%s\n", test);
    //printf("%d", hash(test));
    return 0;
}