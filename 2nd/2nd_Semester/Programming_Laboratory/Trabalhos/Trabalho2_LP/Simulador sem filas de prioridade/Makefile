all: simulador
simulador: Caixa.o Cliente.o Queue.o 
	gcc -g -o simulador simulador.c Caixa.o Cliente.o Queue.o
Caixa.o: Caixa.c
	gcc -g -c Caixa.c
Cliente.o: Cliente.c
	gcc -g -c Cliente.c
Queue.o: Queue.c
	gcc -g -c Queue.c

clear:
	rm -rf *.o
