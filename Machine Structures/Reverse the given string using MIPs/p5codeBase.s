.data
    endl:    .asciiz  "\n"   # used for cout << endl;
    label:   .asciiz  "String: "
    string:   .byte    'w','e','l','c','o','m','e',' ', 't','o',' ','t', 'h','e',' ', 'r','e','a','l',' ','w','o','r','l','d'
.text

# addr of string --> $s0
# beg  --> $s1
# end  --> $s2
# temp --> $s3
main:
	la $s0,string
	li $s1,0
	li $s2,24
	li $s3,0
	lb $s4,($s0)
	ble $s2,$s1,next
loop:	
	add $t3,$s0,$s1
	lb $t4,1($t3)
	sb $t4,($t3)		
	#Properly pulls from the beginning
	move $s3,$t4
	#now pulling from back
	add $t1,$s0,$s2
	lb $t2,-1($t1)
	sb $t2,($t1)
	move $t4,$t2
	move $t2,$s3

	addi $s1,$s1,1
	addi $s2,$s2,-1
	blt $s1,$s2, loop
next:
	
exit:
  la   $a0, label     # puts label into arg0 (a0 register) for cout
  addi $v0, $0, 4     # puts 4 in v0 which denotes we are printing a string
  syscall             # make a syscall to system

  move $a0, $s0       # puts address of string into arg0 (a0 register) for cout
  addi $v0, $0, 4     # puts 4 in v0 to denote we are printing a String
  syscall             # make a syscall to system

  la   $a0, endl      # puts the address of the string endl into a0
  addi $v0, $0, 4     # puts 4 into v0 saying we are printing a string
  syscall

  addi $v0,$0, 10
  syscall
