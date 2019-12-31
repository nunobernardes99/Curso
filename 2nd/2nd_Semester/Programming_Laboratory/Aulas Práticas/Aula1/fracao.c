#include <stdio.h>
#include <stdlib.h>
#define DEN(Z) ((Z).den)
#define NUM(Z) ((Z).num)
#define SIN(Z) ((Z).sinal)
#define MAX(X,Y) ((X>=Y) ? (X) : (Y))
#define MIN(X,Y) ((X<=Y) ? (X) : (Y))
#define ISDIGIT(X) ((X)>='0' && (X)<='9')
#define MAXCHARS 50

typedef struct frac {
	int num, den;
	int sinal;
} FRAC;

FRAC sum(FRAC f1, FRAC f2);  //soma
FRAC prod(FRAC f1, FRAC f2); //produto
FRAC quot(FRAC f1, FRAC f2); //quociente
FRAC sub(FRAC f1, FRAC f2);  //subtração
FRAC sym(FRAC f1);			 //simétrico
FRAC simplify(FRAC f1);		 //simplificar
FRAC ler_frac();
void escrever_frac(FRAC f1);

int main() {
	/*FRAC f1; FRAC f2;
	NUM(f1) = 2; DEN(f1) = 5; SIN(f1) = 1;
	NUM(f2) = 5; DEN(f2) = 6; SIN(f2) = 1;

	FRAC a = simplify(f1);
	FRAC b = sum(f1,f2);
	FRAC c = prod(f1,f2);
	FRAC d = quot(f1,f2);
	FRAC e = sym(f1);

	printf("Simplify : %d/%d, com sinal %d\n", NUM(a), DEN(a), SIN(a));
	printf("Sum: %d/%d, com sinal %d\n", NUM(b), DEN(b), SIN(b));
	printf("Prod: %d/%d, com sinal %d\n", NUM(c), DEN(c), SIN(c));
	printf("Quot: %d/%d, com sinal %d\n", NUM(d), DEN(d), SIN(d));
	printf("Sym: %d/%d, com sinal %d\n", NUM(e), DEN(e), SIN(e));*/
	char seq[MAXCHARS];
	scanf("%s",seq);

	return 0;
}

FRAC sum(FRAC f1, FRAC f2) {
	int sinal = SIN(f1)*SIN(f2);
	FRAC q;
	NUM(q) = NUM(f1)*DEN(f2)+NUM(f2)*DEN(f1);
	DEN(q) = DEN(f1)*DEN(f2);
	SIN(q) = sinal;
	return simplify(q);
}
FRAC prod(FRAC f1, FRAC f2) {
	int sinal = SIN(f1)*SIN(f2);
	if(NUM(f1)==0 || NUM(f2)==0) { abort(); }
	FRAC q;
	NUM(q) = NUM(f1)*NUM(f2);
	DEN(q) = DEN(f1)*DEN(f2);
	SIN(q) = sinal;
	return simplify(q);
}
FRAC quot(FRAC f1, FRAC f2) {
	int sinal = SIN(f1)*SIN(f2);
	if(NUM(f2) == 0) { abort(); }
	FRAC q;
	NUM(q) = NUM(f1)*DEN(f2);
	DEN(q) = DEN(f1)*NUM(f2);
	SIN(q) = sinal;
	return simplify(q);
}
FRAC sym(FRAC f1) {
	FRAC q;
	NUM(q) = -NUM(f1);
	DEN(q) = DEN(f1);
	SIN(q) = SIN(f1);
	return q;
}
FRAC sub(FRAC f1, FRAC f2) { return sum(f1, sym(f2)); }
FRAC simplify(FRAC f1) {
	int a = MAX(NUM(f1),DEN(f1));
	int b = MIN(NUM(f1),DEN(f1));
	while(b!=0) {
		int resto = a%b;
		a = b;
		b = resto;
	}
	FRAC q;
	NUM(q) = NUM(f1)/a;
	DEN(q) = DEN(f1)/a;
	SIN(q) = SIN(f1);
	return q;
}







