#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include "hashOperators.h"

// Verifica se um op de uma instrução é do tipo operador (mul, sub, div, sum)
bool isOperator(Instr instr) {
    return (instr.op == ADD || instr.op == MUL || instr.op == DIV || instr.op == SUB);
}
void hashOperations(Instr instr) {
    int var1, var2;
    if(isOperator(instr)) {
        // QUÁDRUPLOS DO TIPO (OP, var1, var2, var3)
        // Primeira variável
        if(instr.first.kind == EMPTY) var1 = 0;
        else if(instr.first.kind == INT_CONST) var1 = instr.first.contents.val;
        else var1 = get(instr.first.contents.name); // Se der null, var as not been initialized
        // Segunda variável
        if(instr.second.kind == EMPTY) var2 = 0;
        else if(instr.second.kind == INT_CONST) var2 = instr.second.contents.val;
        else var2 = get(instr.second.contents.name); // Se der null, var as not been initialized
        // Terceira variável
        char* var3 = instr.third.contents.name;
        // Cálculo do multiplicador para saber onde guardar a variável
        int OP = instr.op;
        switch(OP) {
            case ADD:
                insert(var3, var1+var2);
                break;
            case MUL:
                insert(var3, var1*var2);
                break;
            case SUB:
                insert(var3, var1-var2);
                break;
            case DIV:
                insert(var3, var1/var2);
                break;
        }
    }
    int OP = instr.op;
    switch(OP) {
        case READ: {
            // QUÁDRUPLOS DO TIPO (OP, var3, 0, 0), var1 irá ser um valor dado pelo utilizador
            char* var3 = instr.first.contents.name;
            scanf("%d", &var1);
            insert(var3, var1);
            break;
        } case ATRIB: {
            // QUÁDRUPLOS DO TIPO (OP, var2, var3, 0)
            char* var3 = instr.second.contents.name;
            if(instr.first.kind == INT_CONST) var2 = instr.first.contents.val;
            else var2 = get(instr.first.contents.name); // Se der null, var as not been initialized
            insert(var3, var2);
            break;
        } case PRINT: {
            // QUÁDRUPLOS DO TIPO (OP, var3, 0, 0)
            char* var3 = instr.first.contents.name;
            printHashV(var3);
            break;
        } case LABEL: {
            // QUÁDRUPLOS DO TIPO (OP, var3, var1, 0)
            char* var3 = instr.first.contents.name;
            if(instr.second.kind == EMPTY) var1 = 0;
            else if(instr.second.kind == INT_CONST) var1 = instr.second.contents.val;
            else var1 = get(instr.second.contents.name); // Se null, var as not been initialized
            insert(var3, var1);
            break;
        }
    }
}