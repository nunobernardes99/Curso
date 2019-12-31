%{
// HEADERS
#include <stdlib.h>
#include "parser.h"

// variables maintained by the lexical analyser
int yyline = 1;
%}

%option noyywrap

%%
[ \t]+ {  }
#.*\n { yyline++; }
\n { yyline++; }
\-?[0-9]+ { 
   yylval.intValue = atoi(yytext);
   return INT; 
}

"%"  { return MOD; }
"/"  { return DIV; }
"*"  { return MUL; }
"+"  { return PLUS; }
"-"  { return SUB; }

"==" { return EQ; }
"!=" { return NE; }
">"  { return GT; }
">=" { return GTE; }
"<"  { return LT; }
"=<" { return LTE; }

"let" { return LET; }
"if" { return IF; }
"else" { return ELSE; }
"while" { return WHILE; }
"read_line" { return READ; }
"&" { return COMERCIAL; }
"print!" { return PRINT; }
"=" { return EQUAL; }
";" { return SEMICOLON; }
"," { return COLON; }
"(" { return OPEN_PAREN; }
")" { return CLOSE_PAREN; }
"{" { return OPEN_BRACKET; }
"}" { return CLOSE_BRACKET; }
"true" { return BTRUE; }
"false" { return BFALSE; }
"fn main()" {return MAIN; }

[a-z][a-zA-Z]* {
	yylval.var = strdup(yytext);
	return VAR;
}
\".*\" {
   yylval.var = strdup(yytext);
   return STRING;
}
.  { yyerror("unexpected character"); }
%%

