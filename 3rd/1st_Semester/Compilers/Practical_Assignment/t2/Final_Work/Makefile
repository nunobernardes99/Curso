
OBJS= scanner.o parser.o ast.o code1.o compiler.o printer.o printAbsTree.o
INTERM=scanner.c parser.c parser.h
PROGRAM=./compiler
CFLAGS=-g 

all: $(PROGRAM)

scanner.c: scanner.flex parser.h
	flex -o scanner.c scanner.flex

parser.c parser.h: parser.bison
	bison --defines=parser.h -o parser.c parser.bison

$(PROGRAM): $(OBJS)
	$(CC) -o $(PROGRAM) $(OBJS)
clean:
	rm -f $(PROGRAM) $(OBJS) $(INTERM)

test: $(PROGRAM)
	$(PROGRAM) example1.txt > output1.asm
	$(PROGRAM) example2.txt > output2.asm
	$(PROGRAM) example3.txt > output3.asm
	$(PROGRAM) example4.txt > output4.asm
