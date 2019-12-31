#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "code1.h"


Instr *mkInstrGoto(char *got, char *reg){
    Instr *instr = (Instr *) malloc(sizeof(Instr));
    instr->kind = GOTOI;
    instr->regist = strdup(reg);
    instr->args.go = mkGoto(got);    
    return instr;
}

Instr *mkInstrLabel(char *lab, char *reg){
    Instr *instr = (Instr *) malloc(sizeof(Instr));
    instr->kind = LABELI;
    instr->regist = strdup(reg);
    instr->args.label=mkLabel(lab);        
    return instr;
}

Instr *mkInstrCondi(BoolExpr *e, listaInstr *left, listaInstr *right, listaInstr *tReg, listaInstr *fReg, char *reg){
    Instr *instr = (Instr *) malloc(sizeof(Instr));
    instr->kind = CONDII;
    instr->regist = strdup(reg);
    instr->args.condi=mkCondi(e, left, right, tReg, fReg);
    return instr;
}

Instr *mkInstrBinop(Instr *left, Instr *right, char *c, char *reg) {
    Instr *instr = (Instr *) malloc(sizeof(Instr));
    instr->kind = VARI;
    instr->regist = strdup(reg);
    instr->args.var=mkVarBinop(left->regist, right->regist, c);

    return instr;
}

Instr *mkInstrNumber(int n, char *reg){ //for numbers
    Instr *instr = (Instr *) malloc(sizeof(Instr));
    instr->kind = VARI;
    instr->args.var= mkVarInt(n);
    instr->regist = strdup(reg);
    return instr;
}

Instr *mkInstrVar(char *c, char *reg) {
    Instr *instr = (Instr *) malloc(sizeof(Instr));
    instr->kind = VARI;
    instr->args.var= mkVarChar(c);
    instr->regist = strdup(reg);
    return instr;
}
Var *mkVarBinop(char *left, char *right, char *sign){
    Var *variavel = (Var *) malloc(sizeof(Var));  
    variavel->kind = BINOP;
    variavel->left = mkVarChar(left);
    variavel->right = mkVarChar(right);
    variavel->signal = strdup(sign);
    return variavel;
}

Var *mkVarChar(char *c){
    Var *variavel = (Var *) malloc(sizeof(Var));
    variavel->kind= CHAR;
    variavel->var = strdup(c);
    return variavel;
}

Var *mkVarInt(int n){
    Var *variavel = (Var *) malloc(sizeof(Var));
    variavel->kind=NUM;
    variavel->num = n;
    return variavel;
}

Condi *mkCondi(BoolExpr *e, listaInstr *left, listaInstr *right, listaInstr *t, listaInstr *f){
    Condi *condi = (Condi *) malloc(sizeof(Condi));
    condi->left = left;

    condi->right = right;
    condi->se = t;
    condi->senao = f;
    switch(e->attr.op.operator) {
        case EQ:
            condi->operator = "seq";
        break;
        case NE:
            condi->operator = "sne";
        break;
        case GT:
            condi->operator = "sgt";
        break;
        case GTE:
            condi->operator = "sge";
        break;
        case LT:
            condi->operator = "slt";
        break;
        case LTE:
            condi->operator = "sle";
        break;
    }
    return condi;
}

GoTo *mkGoto(char *regist) {
    GoTo *goeto = (GoTo *) malloc(sizeof(GoTo));
    goeto->label = mkLabel(regist);
    return goeto;
}

Label *mkLabel(char *reg){
    Label *lab = (Label *) malloc(sizeof(Label));
    lab->text = mkVarChar(reg);
    return lab;
}

listaInstr *mkList(Instr *instr, listaInstr *l1) {
    listaInstr *l = (listaInstr *) malloc(sizeof(listaInstr));
    l->head = instr;
    l->tail = l1;
    return l;
}

Instr* head(listaInstr* l1) {
    return l1->head;
}

listaInstr* tail(listaInstr* l1) {
    return l1->tail;
}

listaInstr* append(listaInstr* l1, listaInstr* l2) {
    if (l1 == NULL) {
        return l2;
    }
    return mkList(head(l1), append(tail(l1), l2));
}

Instr* getLast(listaInstr *l1){
    if (l1==NULL) return NULL;
    if (l1->tail == NULL) {
        return l1->head;
    }
    return getLast(tail(l1));
}