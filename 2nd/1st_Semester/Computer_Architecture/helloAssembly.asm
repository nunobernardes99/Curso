.data
	firstInteger: .word 10
	secondInteger: .word 11
.text
	lw $t0, firstInteger
	lw $t1, secondInteger
	
	add $t2, $t0, $t1 # t2 = t0 + t1
	
	li $v0, 1
	add $a0, $zero, $t2
	
	syscall
	