.data
    endl:    .asciiz  "\n"   # used for cout << endl;
    sumlbl:    .asciiz  "Sum: " # label for sum
    revlbl:    .asciiz  "Reversed Number: " # label for rev
    pallbl:    .asciiz  "Is Palindrome: " # label for isPalindrome
    arr:       .word 1
               .word 2
               .word 3
               .word 4
               .word 5
               .word 4
               .word 3
               .word 2
               .word 1
.text

# sum            --> $s0
# rev            --> $s1
# num            --> $s2
# isPalindrome   --> $s3
# address of arr --> $s4
# i              --> $s5
# beg            --> $s6
# end            --> $s7
# d              --> $t0
# 10             --> $t1
# 100            --> $t3
main:
 	li $s0,0
	li $s5,1
	li $t3,100
	li $t1,10
loop:
	bgt $s5,$t3, next
	add $s0,$s0,$s5
	add $s5,$s5,1
	j loop
next:
	li $s2,45689
	li $s1,0
	li $t0,-1
	blt $s2,0, next_2
loop_2:
	rem $t0,$s2,$t1
	mul $s1,$s1,$t1
	add $s1,$s1,$t0
	div $s2,$s2,$t1
	bgt $s2,0 loop_2
next_2:
	la $s4,arr
	li $s6,0
	li $s7,8
	li $s3,1
	bgt $s6,$s7, exit
loop_3:
	sll $t4,$s6,2
	add $t4,$t4,$s4
	lw $v0,($t4)
	sll $t5,$s7,2
	add $t5,$t5,$s4
	lw $v1,($t5)
	beq $s6,$s7, inc
	beq $v0,$v1, cont
	add $s3,$0,-1
	j exit
cont:
	add $s6,$s6,1
	sub $s7,$s7,1
	j loop_3
inc:
	add $s3,$0,1
exit:
  la   $a0, sumlbl    # puts sumlbl into arg0 (a0 register) for cout
  addi $v0, $0, 4     # puts 4 in v0 which denotes we are printing a string
  syscall             # make a syscall to system

  move $a0, $s0       # puts sum into arg0 (a0 register) for cout
  addi $v0, $0, 1     # puts 1 in v0 to denote we are printing an int
  syscall             # make a syscall to system

  la   $a0, endl      # puts the address of the string endl into a0
  addi $v0, $0, 4     # puts 4 into v0 saying we are printing a string
  syscall

  la   $a0, revlbl    # puts revlbl into arg0 (a0 register) for cout
  addi $v0, $0, 4     # puts 4 in v0 which denotes we are printing an string
  syscall             # make a syscall to system

  move $a0, $s1       # puts rev into arg0 (a0 register) for cout
  addi $v0, $0, 1     # puts 1 in v0 to denote we are printing an int
  syscall             # make a syscall to system

  la   $a0, endl      # puts the address of the string endl into a0
  addi $v0, $0, 4     # puts 4 into v0 saying we are printing a string
  syscall

  la   $a0, pallbl    # puts pallbl into arg0 (a0 register) for cout
  addi $v0, $0, 4     # puts 4 in v0 which denotes we are printing a string
  syscall             # make a syscall to system

  move $a0, $s3       # puts isPalindrome into arg0 (a0 register) for cout
  addi $v0, $0, 1     # puts 1 in v0 to denote we are printing an int
  syscall             # make a syscall to system

  la   $a0, endl      # puts the address of the string endl into a0
  addi $v0, $0, 4     # puts 4 into v0 saying we are printing a string
  syscall


  addi $v0,$0, 10
  syscall
