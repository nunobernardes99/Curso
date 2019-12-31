#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "printer.h"

void printCondi(Instr *i) {
    switch(i->args.condi->kind) {
        case IFI:
            printListInstr(i->args.condi->left);
            printListInstr(i->args.condi->right);
            printf("%s      $s1 %s %s\n", i->args.condi->operator, getLast(i->args.condi->left)->regist, getLast(i->args.condi->right)->regist);
            printf("beq     $zero, $s1, B\n");
            printListInstr(i->args.condi->se);
        break;
        case ELSEI:
            printListInstr(i->args.condi->left);
            printListInstr(i->args.condi->right);
            printf("%s      $s1 %s %s\n", i->args.condi->operator, getLast(i->args.condi->left)->regist, getLast(i->args.condi->right)->regist);
            printListInstr(i->args.condi->se);
            printf("b   C\n");
            printListInstr(i->args.condi->senao);
        break;
        case WHILEI:
            printListInstr(i->args.condi->left);
            printListInstr(i->args.condi->right);
            printf("%s      $s1 %s %s\n", i->args.condi->operator, getLast(i->args.condi->left)->regist, getLast(i->args.condi->right)->regist);
            printf("loop: \n");
            printf("beq     $zero, $s1, done\n");
            printListInstr(i->args.condi->se);
        break;
    }
}
void printInstr(Instr *i) {
    switch (i->kind){
        case VARI:
            printVar(i->args.var, i->regist);
        break;
        case CONDII:
            printCondi(i);
        break;
        case LABELI:
            printLabel(i->args.label, i->regist);    
        break;
        case READI:
            printf("addi    $v0, $0, 5\n");
            printf("syscall");
        break;
        case PRINTI:
            printf("move    $t0, %s\n", i->regist);
            printf("syscall");
        break;
    }
    printf("\n");
}
void printVar(Var *variavel, char *reg){
    switch (variavel->kind) {
        case NUM:
            printf("li      %s,  %d", reg, variavel->num);
        break;
        case CHAR:
            printf("lw      %s,  %s", reg, variavel->var);
        break;
        case BINOP:
            printf("%s      %s, %s, %s", variavel->signal, reg, variavel->right->var, variavel->left->var);
        break;
    }
}
void printLabel(Label *lab, char *reg) {
    printf("%s:", lab->text->var);
}
void printListInstr(listaInstr *l1) {
    if(l1 == NULL){
        return;
    }
    printInstr(l1->head);
    printListInstr(l1->tail);
}