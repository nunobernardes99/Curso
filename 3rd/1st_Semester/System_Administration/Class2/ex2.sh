Recorrendo ao perl ou ao awk escreva uma linha de comandos encadeados que determine o tamanho médio dos ficheiros existentes sob a pasta /etc. Esse comando deverá também listar os detalhes do ficheiro mais pequeno e do ficheiro maior (veja os slides para script inicial para o awk; note que o perl  também tem blocos END e BEGIN).


Ficheiro mais pequeno       = ls -S -l | tail -1
Ficheiro maior              = ls -S -l | head -2 | tail -1
Tamanho medio dos ficheiros = ls -S -l | awk '{sum += $5; n++;} END {print sum/n;}'

ls -S -l | awk 'BEGIN {small=$(tail -1);big=$(head -2 | tail -1);print small; print big} {sum += $5; n++} END {print sum/n;}'

awk -v a="$var1" -v b="$var2" 'BEGIN {print a,b}'


$ cat book-calculation.awk
BEGIN {
	small=$(tail -1);
    big=$(head -2 | tail -1);
    n=0;
}
{
	sum += $5; 
    n++;
}
END {
    print "Average = $"sum/n;
	print "Smallest = $"small;
    print "Biggest = $"big;
}

$ awk -f book-calculation.awk bookdetails.txt
1   Linux-programming 	 $900
2   Advanced-Linux 	 $900
3   Computer-Networks 	 $1600
4   OOAD&UML 	 $1350
5   Java2 	 $1000
Total Amount = $5750
