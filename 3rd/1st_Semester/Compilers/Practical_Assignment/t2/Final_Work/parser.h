/* A Bison parser, made by GNU Bison 3.0.4.  */

/* Bison interface for Yacc-like parsers in C

   Copyright (C) 1984, 1989-1990, 2000-2015 Free Software Foundation, Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

#ifndef YY_YY_PARSER_H_INCLUDED
# define YY_YY_PARSER_H_INCLUDED
/* Debug traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif
#if YYDEBUG
extern int yydebug;
#endif
/* "%code requires" blocks.  */
#line 67 "parser.bison" /* yacc.c:1909  */

  #include <stdio.h>
  #include <stdlib.h>
  #include "ast.h"
  
  extern int yylex();
  extern int yyline;
  extern char* yytext;
  extern FILE* yyin;
  extern void yyerror(const char* msg);
  CmdList* root;

#line 57 "parser.h" /* yacc.c:1909  */

/* Token type.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
  enum yytokentype
  {
    INT = 258,
    PLUS = 259,
    SUB = 260,
    MUL = 261,
    MOD = 262,
    DIV = 263,
    EQ = 264,
    NE = 265,
    GT = 266,
    GTE = 267,
    LT = 268,
    LTE = 269,
    EQUAL = 270,
    IF = 271,
    ELSE = 272,
    WHILE = 273,
    LET = 274,
    SEMICOLON = 275,
    OPEN_PAREN = 276,
    CLOSE_PAREN = 277,
    OPEN_BRACKET = 278,
    CLOSE_BRACKET = 279,
    BTRUE = 280,
    BFALSE = 281,
    VAR = 282,
    PRINT = 283,
    MAIN = 284,
    STRING = 285,
    COLON = 286,
    COMERCIAL = 287,
    READ = 288
  };
#endif

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED

union YYSTYPE
{
#line 47 "parser.bison" /* yacc.c:1909  */

  int intValue;
  char* var;
  Expr* exprValue;
  BoolExpr* boolValue;
  Cmd* cmdValue;
  CmdList* cmdList;

#line 112 "parser.h" /* yacc.c:1909  */
};

typedef union YYSTYPE YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE yylval;

int yyparse (void);

#endif /* !YY_YY_PARSER_H_INCLUDED  */