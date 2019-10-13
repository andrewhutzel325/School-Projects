.data
  expVal23:    .asciiz  "Expected Value : 23  Your Value : "
  expVal21:    .asciiz  "Expected Value : 21  Your Value : "
  endl:        .asciiz  "\n"

.text

# #
# int getDigit(int number);
# number --> $t0
# result of modulo --> $t1
# result of div --> $t2
# 10 --> $t3
# sum  --> $v0
getDigit:
    add $t0, $a0, $0 #make a copy of arg0
    li $v0 , 0
    li $t3, 10
    bge $t0, $t3, else
    add $v0, $t0, $0
    j func_return
  else:
    rem $t1, $t0, $t3
    div $t2, $t0, $t3
    add $v0, $t1, $t2
  func_return:
    jr $ra


##
# int sumOfDoubleEvenPlace(int number);
# List Used Registers Here:
# number -> $s4
# sum -> $s5
# digit ->$s6
# return -> $s7
##
sumOfDoubleEvenPlace:
	add $s4,$a0,$0
	li $s5,0
	li $s6,0
	div $s4,$s4,10
beginning:
	rem $s6,$s4,10
	mul $s6,$s6,2
	add $a0,$0,$s6
	jal getDigit
	add $s7,$0,$v0
	add $s5,$s5,$s7
	div $s4,$s4,100
	bgt $s4,0,beginning
end:
	jr $ra
##
# test1   --> s0
# test2   --> s1
# result1 --> s2
# result2 --> s3
##
  main:
  li $s0, 89744563  # int test1 = 89744563;
  li $s1, 98756421  # int test2 = 98756421;
  li $s2, 0         # int result1 = 0;
  li $s3, 0         # int result2 = 0;
  # code for first function call
  add $a0, $0, $s0
  jal sumOfDoubleEvenPlace
  add $s2, $0, $v0 
  la   $a0, expVal23     

  addi $v0, $0, 4     

  syscall             



  move $a0, $s2       

  addi $v0, $0, 1     

  syscall             



  la   $a0, endl      

  addi $v0, $0, 4     

  syscall



   # code for first function call



  add $a0, $0, $s1

  jal sumOfDoubleEvenPlace

  add $s3, $0, $v0 



  la   $a0, expVal21     

  addi $v0, $0, 4     

  syscall             



  move $a0, $s3       

  addi $v0, $0, 1     

  syscall             



  la   $a0, endl      

  addi $v0, $0, 4     

  syscall



  li $v0, 10

 syscall
