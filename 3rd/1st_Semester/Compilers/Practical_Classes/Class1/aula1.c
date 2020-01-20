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
        } op;
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

int main() {

    Exp e = mkOpExp(ADD, mkNum(5), mkNum(7));

    interp(e);

}

