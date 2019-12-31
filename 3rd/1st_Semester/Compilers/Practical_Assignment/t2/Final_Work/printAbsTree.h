#include "parser.h"

#define __printAbsTree_h__

void printExpr(Expr* exp, int nmrTabs);
void printBool(BoolExpr* exp, int nmrTabs);
void printCmdList(CmdList* cmdlist, int tabs);
void printCmd(Cmd* exp, int tabs);
