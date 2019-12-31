#include "ast.h"
#include "parser.h"

typedef enum {
   CHAR, NUM, BINOP
} VarKind;

typedef enum {
    CONDII, VARI, GOTOI, LABELI, READI, PRINTI
} InstKind;

typedef enum {
    IFI, ELSEI, WHILEI
} CondiKind;



typedef struct _li listaInstr;
typedef struct _Instr Instr;
typedef struct _var Var;
typedef struct _goto GoTo;
typedef struct _label Label;
typedef struct _condi Condi;


struct _Instr {
    char *regist;
    InstKind kind;
    union {
        struct _var *var;
        struct _goto *go;
        struct _label *label;
        struct _condi *condi;
    } args;
};

struct _li {
    Instr *head;
    struct _li *tail;
};

struct _condi {
    CondiKind kind;
    struct {
        struct _li *left;
        char *operator;
        struct _li *right;
    };
    struct _li *se;
    struct _li *senao;
};

struct _goto {
    struct _label *label;
};
struct _label {
    struct _var *text;
};
struct _var {
    VarKind kind;
    union {
        char *var;
        int num;
        struct {
            struct _var *left;
            struct _var *right;
            char *signal;
        };
    };
};

Instr *mkInstrGoto(char *got, char *reg);
Instr *mkInstrLabel(char *lab, char *reg);
Instr *mkInstrCondi(BoolExpr *e, listaInstr *left, listaInstr *right, listaInstr *t, listaInstr *f, char *reg);
Instr *mkInstrNumber(int n, char *reg); //for numbers
Instr *mkInstrVar(char *c, char *reg); // for chars like t1, t2, etc...
Instr *mkInstrBinop(Instr *left, Instr *right, char *c, char *reg); // for chars like t1, t2, etc...

GoTo *mkGoto(char *regist);
Label *mkLabel(char *reg);
Condi *mkCondi(BoolExpr *e, listaInstr *left, listaInstr *right, listaInstr *t, listaInstr *f);

Var *mkVarChar(char *c);
Var *mkVarInt(int n);
Var *mkVarBinop(char *left, char *right, char *sign);

Instr *head(listaInstr *l1); // head of list
listaInstr *tail(listaInstr *l1); //tail of list
listaInstr *append(listaInstr *l1, listaInstr *l2); //append l2 to l1
listaInstr *mkList(Instr *instr, listaInstr *l1);
Instr *getLast(listaInstr *l1); //tail of list

