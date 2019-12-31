#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define VALUE(P) ((P) -> value)
#define NXT(P) ((P) -> nxt)
typedef struct node {
    int value;
    struct node *nxt;
} NODE;
NODE * new_node(int v, NODE *prox) {
    NODE *l = (NODE*)malloc(sizeof(NODE));
    VALUE(l) = v;
    NXT(l) = prox;
    return (l);
}

int main() {
    NODe *lista = NULL;
    lista = new_node(5, lista);
    lista = new_node(7, lista);
    lista = new_node(10, lista);
    return 0;
}














//codesign -fs gdb-cert /path/to/gdb

/*

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

char *ler_nome(FILE *f);
void libertar_mem(void);
void ler_dados(FILE *f);

char *ler_nome(FILE *f) 
{ int i=0;
  char aux[MAXNOME], c, *nome; 
  do c = fgetc(); while (!LETRA(c));
  do {
    aux[i++] = c;
    c = fgetc(f);
  } while (c != '\n');
  aux[i] = '\0';
  nome  = malloc(i+1);
  strcpy(nome,aux);
  return nome;
}

void ler_dados(FILE *f)
{ int i, j;
  fscanf(f,"%d",&NDisc);
  for(i=0; i<NDisc; i++) {
    Disc[i].nome = ler_nome();
    fscanf(f,"%d",&Disc[i].ano);
  }
  fscanf(f,"%d",&NAlunos);
  for(i=0; i<NAlunos; i++) {
    Alunos[i].nome = ler_nome();
    fscanf(f,"%s",Alunos[i].codigo);
    fscanf(f,"%d",&Alunos[i].nd);
    for (j=0; j<Alunos[i].nd; j++)
      fscanf(f,"%d",&Alunos[i].disc[j]);
  }
}

void libertar_mem()
{ int i;
  for(i=0; i<NDisc; i++) 
    free(Disc[i].nome);
  for(i=0; i<NAlunos; i++) 
    free(Alunos[i].nome);
}

void max_alunos_curso(char *curso) {
    printf("Disciplinas com mais alunos do curso %c\n\n", *curso);

}

void corrige_nome_aluno(char *cod, char novonome[]) {
    int i=0;
    for(i=0; i<NAlunos; i++)
        if(strcmp(cod, Alunos[i].codigo) == 0) {

        }
}

int main(int argc, char *argv[])
{
  FILE *fdados;
  fdados = fopen(argv[1], "r");
  ler_dados(fdados);
  fclose(fdados);
  char codigo[MAX];
  scanf("%s", codigo);
  char *novonome = ler_nome(stdin);
  corrige_nome_aluno(codigo, novonome);
  return 0;
}
*/