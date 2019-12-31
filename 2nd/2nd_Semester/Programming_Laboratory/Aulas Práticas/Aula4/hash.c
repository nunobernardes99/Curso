#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define NHASH 100

unsigned int hash(char *str) {
    unsigned int res = 0;
    while( *str != '\0' ) {
        res = res*MULTIPLICADOR + str*;
        str++;
    }
    return res%NHASH;
}

NODE * hashTable[NHASH];