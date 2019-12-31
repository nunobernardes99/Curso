#include <stdio.h>
#include "parser.h"
#include "printAbsTree.h"

int eval(Expr* expr) {
  int result = 0;
  if (expr == 0) {
    yyerror("Null expression!!");
  }
  else if (expr->kind == E_INTEGER) {
    result = expr->attr.value; 
  } 
  else if (expr->kind == E_OPERATION) {
    int vLeft = eval(expr->attr.op.left);
    int vRight = eval(expr->attr.op.right);
    printf("%d %d %d \n", vLeft, expr->attr.op.operator, vRight);
    switch (expr->attr.op.operator) {
      case PLUS: 
        result = vLeft + vRight; 
        break;
      case SUB:
        result = vLeft - vRight; 
        break;
      case MUL:
        result = vLeft * vRight;
        break;
      case DIV:
        result = vLeft / vRight; 
        break;  
      case MOD:
        result = vLeft % vRight; 
        break;   
      default: yyerror("Unknown operator!");
    }
  }
  return result;
}
int boolEval(BoolExpr* boolexpr) {
  int result = 0;
 
  if (boolexpr == 0) {
    yyerror("Null expression!!");
  }
  else if (boolexpr->kind == B_INTEGER) {
    result = boolexpr->attr.value; 
  } 
  else if (boolexpr->kind == B_OPERATION) {
    int vLeft = eval(boolexpr->attr.op.left);
    int vRight = eval(boolexpr->attr.op.right);
    switch (boolexpr->attr.op.operator) {
      case EQ: 
        result = vLeft == vRight; 
        break;
      case LT:
        result = vLeft < vRight; 
        break; 
      case LTE:
        result = vLeft <= vRight; 
        break;  
      case GT:
        result = vLeft > vRight; 
        break;  
      case GTE:
        result = vLeft >= vRight; 
        break;
      case NE:
        result = vLeft != vRight; 
        break; 
      default: yyerror("Unknown operator!");
    }
  }
  return result;
}
int cmdEval(Cmd* cmd) {
  int result;
  if (cmd == 0) {
    yyerror("Null expression!!");
  }
  else if (cmd->kind == CMD_EQUAL) {
    switch (cmd->attr.exprKind){
      case BOOL:
        result = boolEval(cmd->attr.op.bol);
        break;
      case ARITH:
        result = eval(cmd->attr.op.expr);
        printExpr(cmd->attr.op.expr, 0);
      break;
    }
  } 
 return result;
}
int main(int argc, char** argv) {
  --argc; ++argv;
  if (argc != 0) {
    yyin = fopen(*argv, "r");
    if (!yyin) {
      printf("'%s': could not open file\n", *argv);
      return 1;
    }
  } //  yyin = stdin

  // THIS IS FOR NORMAL EXPRESSIONS
  if (yyparse() == 0) {
    printf("fn main()\n");
    printCmdList( root, 1);
  }
  // printExpr(root, 1);
  compileExpr(root->value->attr.op.expr , "t5");


  return 0;
}
