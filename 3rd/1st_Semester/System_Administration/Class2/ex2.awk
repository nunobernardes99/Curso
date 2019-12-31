-v small=$(ls /etc/ -S -l | tail -1)
-v big=$(ls /etc/ -S -l | head -2|tail -1)
BEGIN {
	print small;
    print big;
}
{
	sum += $5; 
    n++;
}
END {
    print "Average = "sum/n;
}