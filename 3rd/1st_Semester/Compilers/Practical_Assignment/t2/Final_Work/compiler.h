#include "code1.h"

listaInstr *compileAttri(Cmd *c);

listaInstr *compileWhile(Cmd *c);

listaInstr *compilePrint(Cmd *c);

listaInstr *compileRead(Cmd *c);

listaInstr *compileCmdList(CmdList *list);

listaInstr *compileExpr(Expr *e, char *r);

listaInstr *compileElse(Cmd *c);

listaInstr *compileIf(Cmd *c);

listaInstr *compileCmd(Cmd *c);

listaInstr *recursiveCall(Expr* e, char* r);

void increaseRegist(char *r);