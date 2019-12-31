// AST constructor functions

#include <stdlib.h> // for malloc
#include <stdio.h>
#include <string.h>
#include "ast.h" // AST header

Expr* ast_integer(int v) {
  Expr* node = (Expr*) malloc(sizeof(Expr));
  node->kind = E_INTEGER;
  node->attr.value = v;
  return node;
}

Expr* ast_var(char* c) {
  Expr* node = (Expr*) malloc(sizeof(Expr));
  node->kind=E_VAR;
  node->attr.var = strdup(c); 
  return node;
}

Expr* ast_operation(int operator, Expr* left, Expr* right) {
  Expr* node = (Expr*) malloc(sizeof(Expr));
  node->kind = E_OPERATION;
  node->attr.op.operator = operator;
  node->attr.op.left = left;
  node->attr.op.right = right;
  return node;
}

BoolExpr* ast_bInteger(int v){
  BoolExpr* node = (BoolExpr*) malloc(sizeof(BoolExpr));
  node->kind = B_INTEGER;
  node->attr.value = v;
  return node;
}

BoolExpr* ast_bOperation(int operator, Expr* left, Expr* right) {
  BoolExpr* node = (BoolExpr*) malloc(sizeof(BoolExpr));
  node->kind = B_OPERATION;
  node->attr.op.operator = operator;
  node->attr.op.left = left;
  node->attr.op.right = right;
  return node;
}
 
Cmd* ast_cmdAtri(void* attr, char* var){
  Cmd* node = (Cmd*) malloc(sizeof(Cmd));
  node->kind = CMD_EQUAL;
  node->attr.exprKind = ARITH;
  node->attr.op.expr = (Expr*) attr;
  node->attr.text = strdup(var);
  return node;
}

Cmd* ast_cmdIf(BoolExpr* check, CmdList* body){
  Cmd* node = (Cmd*) malloc(sizeof(Cmd));
  node->kind = CMD_IF;
  node->attr.exprKind=BOOL;
  node->attr.list = body;
  node->attr.op.bol=check;
  return node;
}

Cmd* ast_cmdIfElse(BoolExpr* check, CmdList* ifbody, CmdList* elsebody) {
  Cmd* node = ast_cmdIf(check, ifbody);
  node->kind = CMD_ELSE;
  node->attr.list2 = elsebody;
  return node;
}
Cmd* ast_cmdWhile(BoolExpr* check, CmdList* body) {
  Cmd* node = (Cmd*) malloc(sizeof(Cmd));
  node->kind = CMD_WHILE;
  node->attr.op.bol=check;
  node->attr.exprKind=BOOL;
  node->attr.list = body;
  return node;
}
Cmd* ast_cmdPrintString(char* body) { 
  Cmd* node = (Cmd*) malloc(sizeof(Cmd));
  node->kind = CMD_PRINT;
  node->attr.text = strdup(body);
  return node;
}

Cmd* ast_cmdPrintVar(char* body, char* var) { 
  Cmd* node = (Cmd*) malloc(sizeof(Cmd));
  node->kind = CMD_PRINT;
  node->attr.exprKind=ARITH;
  node->attr.op.expr = ast_var(var);
  node->attr.text = strdup(body);
  return node;
}

Cmd* ast_cmdReadVar(char* var) {
  Cmd* node = (Cmd*) malloc(sizeof(Cmd));
  node->kind = CMD_READ;
  node->attr.text = strdup(var);
  return node;
}

CmdList* listConstr(Cmd* current, CmdList* next){
    CmdList* node = (CmdList*) malloc(sizeof(CmdList));
    node->value = current;
    node->next = next;
}