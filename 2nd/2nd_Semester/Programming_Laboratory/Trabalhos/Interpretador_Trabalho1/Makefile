all: main
main: estruturas.o hash.o hashOperators.o lista.o load.o sintaxe.o
	gcc -g -o main main.c estruturas.o hash.o hashOperators.o lista.o load.o sintaxe.o
estruturas.o: estruturas.c
	gcc -g -c estruturas.c
hash.o: hash.c
	gcc -g -c hash.c
hashOperators.o: hashOperators.c
	gcc -g -c hashOperators.c
lista.o: lista.c
	gcc -g -c lista.c
load.o: load.c
	gcc -g -c load.c
sintaxe.o: sintaxe.c
	gcc -g -c sintaxe.c

clear:
	rm -rf *.o