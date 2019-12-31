// AST definitions
#ifndef __ast_h__
#define __ast_h__

// AST for expressions
struct _Expr {
  enum { 
    E_INTEGER,
    E_VAR,
    E_OPERATION
  } kind;
  union {
    int value; // for integer values
    char* var;
    struct {
      int operator; // PLUS, MINUS, etc 
      struct _Expr* left;
      struct _Expr* right;
    } op; // for binary expressions
  } attr;
};//nao aceitavel (so aceita expressoes aritemicas nos dois lados da boolena expressions)

struct _BoolExpr {
  enum { 
    B_INTEGER,  
    B_OPERATION
  } kind;
  union {
    int value; // for integer values
    struct { 
      int operator;  
      struct _Expr* left;
      struct _Expr* right;
    } op; // for binary expressions
  } attr;
};


struct _Cmd {
  enum{
    CMD_IF,
    CMD_ELSE,
    CMD_WHILE,
    CMD_EQUAL,
    CMD_READ,
    CMD_PRINT
  } kind;
  struct { 
    struct _CmdList* list;
    struct _CmdList* list2;
    char* text;
    enum{
      ARITH,
      BOOL
    } exprKind;
    union{
      struct _Expr* expr;
      struct _BoolExpr* bol;
    } op;
  } attr;
};

struct _CmdList{
  struct _Cmd* value;
  struct _CmdList* next;
};


typedef struct _Expr Expr; // Convenience typedef
typedef struct _BoolExpr BoolExpr;
typedef struct _Cmd Cmd;
typedef struct _CmdList CmdList;

// Constructor functions (see implementation in ast.c)
Expr* ast_integer(int v);
Expr* ast_var(char* v);
Expr* ast_operation(int operator, Expr* left, Expr* right);

BoolExpr* ast_bInteger(int v);
BoolExpr* ast_bOperation(int operator, Expr* left, Expr* right);

Cmd* ast_cmdAtri(void* attr, char* var); 

Cmd* ast_cmdIf(BoolExpr* check, CmdList* body);
Cmd* ast_cmdIfElse(BoolExpr* check, CmdList* ifbody, CmdList* elsebody);
Cmd* ast_cmdWhile(BoolExpr* check, CmdList* body);
Cmd* ast_cmdPrintString(char* body);
Cmd* ast_cmdPrintVar(char* body, char* var);
Cmd* ast_cmdReadVar(char* var);

CmdList* listConstr(Cmd* current, CmdList* next);

#endif
