all: simulador
simulador: Caixa.o Cliente.o Queue.o QueueP.o
	gcc -g -o simulador simulador.c Caixa.o Cliente.o Queue.o QueueP.o
Caixa.o: Caixa.c
	gcc -g -c Caixa.c
Cliente.o: Cliente.c
	gcc -g -c Cliente.c
Queue.o: Queue.c
	gcc -g -c Queue.c
QueueP.o: QueueP.c
	gcc -g -c QueueP.c

clear:
	rm -rf *.o
