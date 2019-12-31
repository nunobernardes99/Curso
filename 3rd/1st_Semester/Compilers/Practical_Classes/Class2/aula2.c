typedef enum { 
    ADD, MULT 
} Op;

typedef struct Exp_ *Exp;

struct Exp_ {
    enum {
        IntExp, ExpOp
    } kind;
    union {
        int num;
        struct {
            Op oper;
            Exp left;
            Exp right;
        } op;int main() {

    Exp e = mkOpExp(ADD, mkNum(5), mkNum(7));

    interp(e);

}

    } u;
};

Exp mkNum(int n);
Exp mkOpExp(Op o, Exp l, Exp r);
int interp(Exp e);

Exp mkNum(int n) {
    Exp p = malloc(sizeof(*p));
    p -> kind = IntExp;
    p -> u.num = n;
    return p;
}

Exp mkOpExp(Op o, Exp l, Exp r) {
    Exp p = malloc(sizeof(*p));
    p -> kind = ExpOp;
    p -> u.op.oper = o;
    p -> u.op.left = l;
    p -> u.op.right = r;
    return p;
}

int interp(Exp e) {
    switch (e->kind) {
        case IntExp:
            return e->u.num;
        case ExpOp:
            switch (e->u.op.oper) {
                case ADD:
                    return interp(e->u.op.left) + interp(e->u.op.right);
                case MULT:
                    return interp(e->u.op.left) + interp(e->u.op.right);
            }
    }
}

// FIM DA AULA 1
typedef struct Instr_ *Instr;

struct Instr_ {
    union {
        int n;
        int op;
    } value;
    enum { 
        PUSH, ADD, MULT
    } kind;
};

typedef struct Code_ *Code;

struct Code_ {
    Instr content;
    Code next;
};

typedef struct State_ *State;

struct State_ {
    Code c;
};

Instr mkInstrPUSH(int n);
Instr mkInstrADD();
Instr mkInstrMULT();
Code compile(Exp e);

Instr mkInstrPUSH(int n) {
    Instr i = malloc(sizeof(*i));
    i -> kind = PUSH;
    i -> value.n = n;
    return i;
}

Instr mkInstrADD() {
    Instr i = malloc(sizeof(*i));
    i -> kind = ADD;
    i -> value.op = 0;
    return i;
}

Instr mkInstrMULT() {
    Instr i = malloc(sizeof(*i));
    i -> kind = MULT;
    i -> value.op = 0;
    return i;
}

Code mkCode(Instr i) {
    Code c1 = malloc(sizeof(*i));
    c1 -> content = i;
    c1 -> next = null;
    join(Code c1, Code c2);
}

Code compile(Exp e) {
}


int main() {

    Exp e = mkOpExp(ADD, mkNum(5), mkNum(7));

    interp(e);

}
