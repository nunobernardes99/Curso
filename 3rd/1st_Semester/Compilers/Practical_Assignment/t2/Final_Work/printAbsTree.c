#include "printAbsTree.h"
#include "string.h"
void printTabs(int size) {
    while(size>0) {
        printf("|      ");
        size--;
    }
}
void printExpr(Expr* exp, int nmrTabs) {
    printTabs(nmrTabs);
    if( exp->kind == E_INTEGER){
        printf("%d\n", exp->attr.value);
    }else if(exp->kind == E_VAR){
        printf("%s\n", exp->attr.var);
    }
    else{
        char c;
        switch(exp->attr.op.operator) {
            case PLUS: 
                c = '+';
                break;
            case SUB:
                c= '-';
                break; 
            case MUL:
                c = '*'; 
                break;  
            case DIV:
                c = '/';
                break;  
            case MOD:
                c = '%';
                break;  
        }
        printf("%c\n", c);
        printExpr(exp->attr.op.left, nmrTabs+1);
        printExpr(exp->attr.op.right, nmrTabs+1);
    }
}

void printBool(BoolExpr* exp, int nmrTabs) {
    printTabs(nmrTabs);
    if( exp->kind == B_INTEGER){
        printf("%d\n", exp->attr.value);
    }
    else{
        char* c;
        switch(exp->attr.op.operator) {  
            case EQ: 
                c = strdup("==");
                break;
            case LT:
                c = strdup("<"); 
                break; 
            case LTE:
                c = strdup("<="); 
                break;  
            case GT:
                c = strdup(">");
                break;  
            case GTE:
                c = strdup(">=");
                break;
            case NE:
                c = strdup("!=");
                break; 
        }
        printf("%s\n", c);
        printExpr(exp->attr.op.left, nmrTabs+1);
        printExpr(exp->attr.op.right, nmrTabs+1);
    }
}
void printCmdList(CmdList* cmdlist, int tabs) {
    while( cmdlist != NULL){
        printCmd(cmdlist->value, tabs);
        cmdlist = cmdlist->next;
    }
}
void printCmd(Cmd* exp, int tabs) {
    printTabs(tabs);
    switch(exp->kind) {
        case CMD_EQUAL:
            printf("=\n");
            printTabs(tabs+1);
            printf("%s\n", exp->attr.text);
            if( exp->attr.exprKind == BOOL){
                printBool(exp->attr.op.bol, tabs+1);       
            }else if( exp->attr.exprKind == ARITH){
                printExpr(exp->attr.op.expr, tabs+1);       
            }
        break;
        case CMD_IF:
            printf("IF\n");
            printBool(exp->attr.op.bol,tabs+1);
            printCmdList(exp->attr.list, tabs+1);
        break;
        case CMD_ELSE:
            printf("IF\n");
            printBool(exp->attr.op.bol,tabs+1);
            printCmdList(exp->attr.list, tabs+1);
            printTabs(tabs);
            printf("ELSE\n");
            printCmdList(exp->attr.list2, tabs+1);
        break;
        case CMD_WHILE:
            printf("WHILE\n");
            printBool(exp->attr.op.bol,tabs+1);
            printCmdList(exp->attr.list, tabs+1);
        break;
        case CMD_PRINT:
            printf("PRINT\n");
            printTabs(tabs+1);
            printf("%s\n", exp->attr.text);
            if( exp->attr.op.expr != NULL ){
                printTabs(tabs+1);
                printf("%s\n", exp->attr.op.expr->attr.var);
            }
        break;
        case CMD_READ:
            printf("READ_LINE\n");
            printTabs(tabs+1);
            printf("%s\n", exp->attr.text);
        break;
    }
}
