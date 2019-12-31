%define api.value.type { double } 

%left ADD_TOKEN
%left SUB_TOKEN
%left MUL_TOKEN
%left DIV_TOKEN
%start prog;
%token NUMBER_TOKEN  // Numbers 
%token ADD_TOKEN // +
%token SUB_TOKEN // -
%token MUL_TOKEN // *
%token DIV_TOKEN // /

%{
#include <stdio.h>
#include "common.h"
%}

%%
prog: /* empty */
    | expr prog

expr: NUMBER_TOKEN
   | expr ADD_TOKEN expr
   | expr SUB_TOKEN expr
   | expr MUL_TOKEN expr
   | expr DIV_TOKEN expr
   ;
%%
#include <stdio.h>

int main(int argc, char **argv)
{
  if (argc > 0) yyin = fopen(argv[1], "r");
  yyparse();
  return 0;
}

