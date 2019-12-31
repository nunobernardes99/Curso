#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include "code.h"
#include "printAbsTree.h"
#include "ast.h"

Instr* mkSingle(Atom* a) {
    Instr* i = malloc(sizeof(*i));
    i -> kind = VAR;
    i -> attr.var = a;
    return i;
}
Instr* mkPlural(Atom* l, Binop* op, Atom* r) {
    Instr* i = malloc(sizeof(*i));
    i -> kind = PLURAL;
    i -> attr.op.left = l;
    i -> attr.op.oper = op;
    i -> attr.op.right = r;
    return i;
}
Atom* mkVar(char* v) {
    Atom* a = malloc(sizeof(*a));
    a -> kind = SINGLE;
    a -> op.var = strdup(v);
    return a;
}
Atom* mkNumber(int n) {
    Atom* a = malloc(sizeof(*a));
    a -> kind = NUMBER;
    a -> op.num = n;
    return a;
}

InstrList* mkList(Instr* i, Instr* next) {
    InstrList* l = malloc(sizeof(*l));
    l->root = i;
    l->next = next;
    return l;
}

Instr* getValue(InstrList* l) {
    return l -> root;
}
Instr* getNext(InstrList* l) {
    return l -> next;
}

InstrList* appendLists(InstrList* l1, InstrList* l2){
  if( l1 == NULL ) return l2;
  return newList( getValue(l1), appendLists( getNext(l1) ,l2 ) );
}

InstrList* appendLists(InstrList* l1, InstrList* l2) {
    InstrList* curr = malloc(sizeof(*curr));
    while(l1 != NULL) {
        curr->root = l1->root;
        curr->next = l1->next;
        l1 = l1 -> next;
        curr = curr -> next;
    }   
    while(l2 != NULL) {
        curr->root = l2->root;
        curr->next = l2->next;
        l2 = l2 -> next;
        curr = curr -> next;
    }
    return curr;
}

void printInstr(Instr* i) {
     if(i->attr.op.left->kind == VAR) {
        printf("%s := %s", i->attr.var, i->attr.op.left->op.var);
    } else {
        printf("%s := %d", i->attr.var, i->attr.op.left->op.num);
    }
    if(i->kind == PLURAL){
        printf(i->attr.op.oper);
        if(i->attr.op.right->kind == VAR) {
            printf("%s",i->attr.op.left->op.var);
        } else {
            printf("%d",i->attr.op.left->op.num);
        }
    }
    printf("\n");
}

void printListInstr(InstrList* l) {
    while(l != NULL) {
        printInstr(l->root);
        l = l->next;
    }
}

InstrList* compileExp(Expr* e, char* r) {
    switch (e->kind) {
    case E_INTEGER :
        increaseRegist(r);
        return mkList( mkSingle( mkNumber(e->attr.value)) ,NULL);
        break;
    case E_VAR :
        increaseRegist(r);
        return mkList( mkSingle( mkVar( e->attr.var) ), NULL);
        break; 
    case E_OPERATION:
        InstrList* left = compileExp( e->attr.op.left, r);
        Atom* atom1 = mkVar(r);
        
        InstrList* right = compileExp( e->attr.op.right, r);
        Atom* atom2 = mkVar(r);

        InstrList* combined = mkList(mkPlural( atom1,  e->attr.op.operator, atom2), NULL);
        return appendLists ( appendLists(left, right), combined);
        break;
    }
    printExpr(e, 1);
}

int increaseRegist(char* r) { //| 6 | 2 |
    r = strcat("t", ++r);
    return r;
}

