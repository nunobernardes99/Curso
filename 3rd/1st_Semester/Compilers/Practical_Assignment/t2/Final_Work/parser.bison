// Tokens
%token 
  INT  
  PLUS
  SUB
  MUL
  MOD
  DIV
  EQ
  NE
  GT
  GTE
  LT 
  LTE
  EQUAL
  IF
  ELSE
  WHILE
  LET
  SEMICOLON
  OPEN_PAREN
  CLOSE_PAREN
  OPEN_BRACKET
  CLOSE_BRACKET
  BTRUE
  BFALSE
  VAR
  PRINT
  MAIN
  STRING
  COLON
  COMERCIAL
  READ
  
// Operator associativity & precedence
%left PLUS SUB
%left MUL DIV 
%left MOD 
%left GT LT GTE LTE
%left EQ NE 

// Root-level grammar symbol
%start input;

// Types/values in association to grammar symbols.

%union {
  int intValue;
  char* var;
  Expr* exprValue;
  BoolExpr* boolValue;
  Cmd* cmdValue;
  CmdList* cmdList;
}


%type <intValue> INT
%type <exprValue> expr
%type <boolValue> bexpr
%type <cmdValue> cmd
%type <cmdList> cmdList
%type <var> VAR
%type <var> STRING

// Use "%code requires" to make declarations go
// into both parser.c and parser.h
%code requires {
  #include <stdio.h>
  #include <stdlib.h>
  #include "ast.h"
  
  extern int yylex();
  extern int yyline;
  extern char* yytext;
  extern FILE* yyin;
  extern void yyerror(const char* msg);
  CmdList* root;
}


%%
input: 
  MAIN OPEN_BRACKET cmdList CLOSE_BRACKET { root = $3; }
;
cmdList:
  { $$ = NULL; }
  |
  cmd cmdList { $$ = listConstr($1, $2); }
  ;
cmd:
  VAR EQUAL expr SEMICOLON {
    $$ = ast_cmdAtri($3, $1);
  }
  |
  LET VAR EQUAL expr SEMICOLON {
    $$ = ast_cmdAtri($4, $2);
  }
  |
  IF bexpr OPEN_BRACKET cmdList CLOSE_BRACKET {
    $$ = ast_cmdIf($2, $4);
  }
  |
  IF bexpr OPEN_BRACKET cmdList CLOSE_BRACKET ELSE OPEN_BRACKET cmdList CLOSE_BRACKET{
    $$ = ast_cmdIfElse($2, $4, $8);
  }
  |
  WHILE bexpr OPEN_BRACKET cmdList CLOSE_BRACKET {
    $$ = ast_cmdWhile($2, $4);
  }
  |
  PRINT OPEN_PAREN STRING COLON VAR CLOSE_PAREN SEMICOLON {
    $$ = ast_cmdPrintVar($3, $5);
  }
  |
  PRINT OPEN_PAREN STRING CLOSE_PAREN SEMICOLON {
    $$ = ast_cmdPrintString($3);
  }
  |
  READ OPEN_PAREN COMERCIAL VAR CLOSE_PAREN SEMICOLON {
    $$ = ast_cmdReadVar($4);
  }
;
expr:
  INT {
    $$ = ast_integer($1);
  }
  | 
  expr PLUS expr { 
    $$ = ast_operation(PLUS, $1, $3); 
  }
  | 
  expr SUB expr { 
    $$ = ast_operation(SUB, $1, $3); 
  }
  | 
  expr MUL expr { 
    $$ = ast_operation(MUL, $1, $3); 
  }
  | 
  expr DIV expr { 
    $$ = ast_operation(DIV, $1, $3); 
  }
  | 
  expr MOD expr {
    $$ = ast_operation(MOD, $1, $3); 
  }
  |
  VAR {
    $$ = ast_var($1);
  }
;
bexpr:
  BTRUE { 
    $$ = ast_bInteger(1); 
  }
  | 
  BFALSE { 
    $$ = ast_bInteger(0);  
  }
  | INT {
    $$ = ast_bInteger($1);
  }
  | expr EQ expr {
    $$ = ast_bOperation(EQ, $1, $3);
  }
  | expr NE expr {
    $$ = ast_bOperation(NE, $1, $3);
  }
  | expr LT expr {
    $$ = ast_bOperation(LT, $1, $3);
  }
  | expr LTE expr {
    $$ = ast_bOperation(LTE, $1, $3);
  }
  | expr GT expr {
    $$ = ast_bOperation(GT, $1, $3);
  } 
  | expr GTE expr {
    $$ = ast_bOperation(GTE, $1, $3);
  }
;
%%

void yyerror(const char* err) {
  printf("Line %d: %s - '%s'\n", yyline, err, yytext  );
}