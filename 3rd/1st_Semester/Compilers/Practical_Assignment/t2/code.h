// - Tarefa 1:

// Defina ficheiros code.h e code.c para implementar uma estrutura (struct Instr) e respectivos construtores de um código intermédio de três endereços definido por:

// instruction -> VAR := atom | VAR := atom binop atom

// atom -> VAR
// atom -> NUMBER

// binop -> PLUS | MINUS | DIV | MULT
// Entrei este link: https://en.wikipedia.org/wiki/Three-address_code

struct _Atom {
    enum {
        VAR, NUMBER
    } kind;
    union {
        char* var;
        int num;
    } op;
};


// typedef enum {
//     PLUS, MINUS, DIV, MULT
// } Binop;

struct _Instr {
    enum {
        SINGLE, PLURAL // distinguir os dois tipos da instruction, no caso de varAtom so uso o var e o left
    } kind;
    union {
        struct _Atom* var;
        struct {
            int operator;
            struct _Atom* left;
            struct _Atom* right;
        } op;
    } attr;
};

struct _InstrList {
    struct _Instr* root;
    struct _InstrList* next;
};


typedef struct _InstrList InstrList;
typedef struct _Instr Instr;
typedef struct _Atom Atom;


Instr* mkSingle(Atom* a);
Instr* mkPlurar(Atom* l, int oper, Atom* r);
Atom* mkVar(char* var);
Atom* mkNumber(int num);

// 1.b) Defina uma estrutura para listas de instruções, InstrList (lista de apontadores para structs _Instr), respectivos construtores e funções de acesso getFirst e nextInstrs, e uma função append para concatenação de listas de instruções.

// 1.c) Defina uma função printInstr para imprimir uma instrução.

// 1.d) Defina uma função printListIntrs para imprimir uma lista de instruções.



InstrList* mkList(Instr* i, Instr* next);
Instr* getValue(InstrList* l);
Instr* getNext(InstrList* l);
InstrList* appendLists(InstrList* l1, InstrList* l2);
void printInstr(Instr* i);
void printListInstr(InstrList* l);

// - Tarefa 2:

// Implemente um compilador da árvore abstracta para expressões (struct _Expr) para uma lista de instruções (lista de struct _Instr).

// Uma sugestão de protótipo para a função que compila expressões é:

// InstrList compileExp(Expr e, char *r); onde r é o registo onde está o valor da availação da expressão na lista de instruções InstrList. Por exemplo:

// O resultado da compilação da expressão: x-2*5 é a lista de instruções:

// t1 := x;
// t2 := 2;
// t3 := 5;
// t4 := t1*t2;
// t5 := t1-t4;

// e neste caso o registo r é igual a t5.

InstrList* compileExp(Expr* e, char* r);