#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAXDISC 100
#define MAXALUNOS 1000
#define MAXNOME 100
#define MAX 11
#define LETRA(C) (((C) >= 'a'&& (C)<= 'z')|| ((C)>= 'A' && (C)<= 'Z'))

typedef struct aluno {
  char *nome, codigo[MAX];
  int disc[MAX], nd;
} ALUNO;

typedef struct disciplina {
  char *nome;
  int ano;
} DISCIPLINA;

ALUNO Alunos[MAXALUNOS];
int NAlunos;

DISCIPLINA Disc[MAXDISC];
int NDisc;

//---------------------------------------
char *ler_nome(void);
void libertar_mem(void);
void ler_dados(void);
//----------------------------------------

void disc_ano(int ano) {
    printf("Disciplinas do ano %d \n\n", ano);
    for(int i=0; i<NDisc; i++)
        if(Disc[i].ano == ano)
            printf("%s\n", Disc[i].nome);
}
void disc_aluno(char *cod) {
    for(int i=0; i<NAlunos; i++) {
        if(Astrcmp(cod, Alunos[i].codigo) == 0) {
            printf("%s inscrito a: \n\n", &Alunos[i].nome);
            for(int j=0; j<Alunos[i].nd; j++)
                printf("%s\n", Disc[Alunos[i].disc[j]].nome);
            break;
        }
    }
}


char *ler_nome() 
{ int i=0;
  char aux[MAXNOME], c, *nome; 
  do c = getchar(); while (!LETRA(c));
  do {
    aux[i++] = c;
    c = getchar();
  } while (c != '\n');
  aux[i] = '\0';
  nome  = malloc(i+1);
  strcpy(nome,aux);
  return nome;
}

void ler_dados()
{ int i, j;
  scanf("%d",&NDisc);
  for(i=0; i<NDisc; i++) {
    Disc[i].nome = ler_nome();
    scanf("%d",&Disc[i].ano);
  }
  scanf("%d",&NAlunos);
  for(i=0; i<NAlunos; i++) {
    Alunos[i].nome = ler_nome();
    scanf("%s",Alunos[i].codigo);
    scanf("%d",&Alunos[i].nd);
    for (j=0; j<Alunos[i].nd; j++)
      scanf("%d",&Alunos[i].disc[j]);
  }
}

void libertar_mem()
{ int i;
  for(i=0; i<NDisc; i++) 
    free(Disc[i].nome);
  for(i=0; i<NAlunos; i++) 
    free(Alunos[i].nome);
}















/*
    int a;
    int *pa;
    scanf("%d", &a);
    printf("%d\n", a);
    printf("%p\n", &a);
    pa = &a;
    printf("%p\n", pa);
    printf("%p\n", &pa);
    *pa = 7;
    printf("%d\n", a);
    return 0;

    int x[7] = {5,7,4};
    double y[10] = {0.1, 1};
    printf("%p\n%p\n", &x[0], &y[0]); // retorna dois endereços de memória
    printf("%d\n%lf\n", x[0], y[0]); // retorna numero inteiro e numero flutuante
    return 0;

    char z[2];
    int x[7] = {5,7,4};
    double y[10] = {0.1, 1};
    printf("%p\n%p\n%p\n", x, y, z); // endereço de x | endereço de y | endereço de z
    printf("%p\n%p\n%p\n", x+1, y+1, z+1); //4 bytes de diferença | 8 bytes de diferença | 1 byte de diferença
    return 0;

    int conta (int *p, int x) {
        int conta (int *p, int x) {
        int c = 0;
        while(*p != x) {
            p++;
            c++;
        }
        return c;
    }

    void ler_seq_chars(char seq[]  ou char *seq ) {
    scanf("%s", seq);
    }

void swap(int *p, int *q) {
    int aux = *q;
    *q = *p;
    *p = aux;
}
void find_two_largest(int a[], int n, int *largest, int *second_largest) {
    if(n==1)
        *largest = *second_largest = a[0];
    else {
        if(a[0]>a[1]) {
            *largest = a[0];
            *second_largest = a[1];
        } else {
            *largest = a[1];
            *second_largest = a[0];
        }
        for(int i=2; i<n; i++) {
            if(a[i] > *largest) {
                *second_largest = *largest;
                *largest = a[i];
            } else
                if(a[i] > *second_largest)
                    *second_largest = a[i];
        } 
    }
}
int *find_number(int a[], int n, int num) {
    int i;
    for(i=0; i<n; i++)
        if(num == a[i]) return &a[i];
    return NULL;

    OU

    while( n>0 && *a!=num) 
        { a++; n--; }
    if(n>0)
        return a;
    return NULL;
}
int main() {
    int a[10]={1,124,124,213};
    int b=10;
    printf("%p\n%p\n", &a, &b);
    printf("%d\n%d\n", a, b);
    swap(&a, &b);
    printf("%p\n%p\n", &a, &b);
    printf("%d\n%d\n", a, b);
    int largest = 0;
    int second = 0;
    int index = *find_number(a, 4, 124);
    printf("%d\n", index);
    find_two_largest(a, 4, &largest, &second);
    printf("%d\n%d\n", largest, second);
}


*/