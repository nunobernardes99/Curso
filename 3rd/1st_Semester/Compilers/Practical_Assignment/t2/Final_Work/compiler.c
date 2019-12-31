#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "compiler.h"
#include "ast.h"

char temp[3] = {'$', 't', '0'};

listaInstr *compileExpr(Expr* e, char* r) { 
    switch (e->kind) {
        case E_INTEGER:
            increaseRegist(r);
            return mkList(mkInstrNumber(e->attr.value,r), NULL);
        break;
        case E_VAR:
            increaseRegist(r);
            return mkList(mkInstrVar(e->attr.var, r), NULL);
        break;
        default:
            recursiveCall(e, r);
        break;
        
    }
}
listaInstr *compileCmd(Cmd *c) {
    switch (c->kind){
        case CMD_IF:
            return compileIf(c);
        break;
        case CMD_ELSE:
            return compileElse(c);
        break;
        case CMD_WHILE:
            return compileWhile(c);
        break;
        case CMD_EQUAL:
            return compileAttri(c);
        break;
        case CMD_READ:
            return compileRead(c);
        break;
        case CMD_PRINT:
            return compilePrint(c);
        break;
    }
}

listaInstr *compileRead(Cmd *c) {
    increaseRegist(temp);
    Instr *instr = mkInstrVar(c->attr.text, temp);
    instr->kind=READI;
    return mkList(instr, NULL);
}
listaInstr *compilePrint(Cmd *c){
    increaseRegist(temp);
    Instr *instr = mkInstrVar(c->attr.text, temp);
    instr->kind=PRINTI;
    return mkList(instr,NULL);
}


listaInstr *compileElse(Cmd *c){
    BoolExpr *expr= c->attr.op.bol;
    Instr *label1 = mkInstrLabel("A", temp);
    listaInstr *left = compileExpr(expr->attr.op.left, temp);
    listaInstr *right = compileExpr(expr->attr.op.right, temp);
    
    Instr *label2 = mkInstrLabel("A", temp);
    listaInstr *code1 = mkList( label2, compileCmdList(c->attr.list));

    Instr *label3 = mkInstrLabel("B", temp);
    listaInstr *code2 = mkList(label3, compileCmdList(c->attr.list2));
    listaInstr *label4 = mkList(mkInstrLabel("C", temp),NULL);
    code2= append(code2, label4); 

    Instr *cond = mkInstrCondi(c->attr.op.bol, left, right, code1, code2, label1->regist);
    cond->args.condi->kind=ELSEI;
    return mkList(cond, NULL);
}


listaInstr *compileWhile(Cmd *c){
    BoolExpr *expr= c->attr.op.bol;
    Instr *label1 = mkInstrLabel("A", temp);
    listaInstr *left = compileExpr(expr->attr.op.left, temp);
    listaInstr *right = compileExpr(expr->attr.op.right, temp);

    listaInstr *code1 =  compileCmdList(c->attr.list);
    listaInstr *label3 = mkList(mkInstrLabel("done", temp),NULL);
    code1= append(code1, label3); 

    Instr *cond = mkInstrCondi(c->attr.op.bol, left, right, code1, NULL, label1->regist);
    cond->args.condi->kind=WHILEI;
    return mkList(cond, NULL);
}
listaInstr *compileIf(Cmd *c){
    BoolExpr *expr= c->attr.op.bol;

    Instr *label1 = mkInstrLabel("A", temp);
    listaInstr *left = compileExpr(expr->attr.op.left, temp);
    listaInstr *right = compileExpr(expr->attr.op.right, temp);

    listaInstr *code1 =  compileCmdList(c->attr.list);
    listaInstr *label3 = mkList(mkInstrLabel("B", temp),NULL);
    code1= append(code1, label3); 

    Instr *cond = mkInstrCondi(c->attr.op.bol, left, right, code1, NULL, label1->regist);
    cond->args.condi->kind=IFI;
    return mkList(cond, NULL);
}

listaInstr *compileAttri(Cmd *c) {
    listaInstr *expr = compileExpr(c->attr.op.expr, temp);
    Instr *var = mkInstrVar(c->attr.text, getLast(expr)->regist);
    return append( expr,  mkList(var, NULL));
}

listaInstr *compileCmdList(CmdList *list) {
    if(list == NULL){
        return NULL;
    }
    return append( compileCmd(list->value), compileCmdList(list->next));        
}

listaInstr *recursiveCall(Expr* e, char* r) {
    listaInstr *leftList = compileExpr(e->attr.op.left, r);
    Instr *leftInstr = mkInstrVar(r, r);

    listaInstr *rightList = compileExpr(e->attr.op.right, r);
    Instr *rightInstr = mkInstrVar(r, r);

    char *signal;
    switch (e->attr.op.operator) {
        case PLUS:
            signal = "add";
            break;
        case SUB:
            signal = "sub";
            break;
        case MUL:
            signal = "mult";
            break;
        case DIV:
            signal = "div";
        break;
    }
    listaInstr *combined = mkList(mkInstrBinop(leftInstr, rightInstr, signal, r), NULL);
    return append(append(leftList, rightList), combined);
}

void increaseRegist(char *r) {
    if (r[2]=='9') {
        r[2] = '1';
    } 
    else{
        r[2] += 1;
    }
}

int main(int argc, char **argv) {
    --argc;
    ++argv;
    if (argc != 0) {
        yyin = fopen(*argv, "r");
        if (!yyin) {
            printf("'%s':could not open file\n", *argv);
            return 1;
        }
    }
    if (yyparse() == 0) {
        printf("main: \n\n");
        listaInstr *l = compileCmdList(root);
        printListInstr(l);

        printf("exit:\n");
        printf("li      $v0, 10\n");
        printf("syscall\n");
    }
    return 0;
}