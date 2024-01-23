msg1:.asciiz "\nPlease enter an integer -9999 to +9999:\n"
wrong_input:    .asciiz "\n wrong input Please Enter again:\n"
.text
.globl main
wrong:
	li $v0,4 
	la $a0,wrong_input
	syscall
main:

##########get an input (-9999  +9999) and validate it##############

li	$v0, 4
la	$a0, msg1 
syscall
li	$v0, 5 
syscall
# validation
bgt	$v0, 9999,wrong
blt	$v0, -9999,wrong
move    $s0,$v0 

##########print int in 16bit##############

li     $v0,1
li	$t0, 0x8000
print_16_bit:
	and	$a0, $s0, $t0
	beq     $a0,$0,print_digit
	li      $a0,1
print_digit:
	syscall
	srl	$t0 , $t0 , 1
	bne	$t0 , $0 , print_16_bit
	li	$v0 , 11
	li	$$a0 , '\n'
	syscall
	
##########print int in 16bit reverse  and prepare the reverse 16 bit for next part#############	

li	$v0,1
li	$t0, 0x0001
li      $t1,0x8000
li      $t2,0
print_16_bit_reverse:                
	and	$a0, $s0, $t0   # $a0 the mask bit
	beq     $a0,$0,not_1
	li      $a0,1
	or      $t2,$t2,$t1      
not_1:
	syscall
	sll	$t0, $t0, 1     #shift the mask left
	srl     $t1,$t1,1
	bne	$t1, $0, print_16_bit_reverse # loop until $t1=0 (16 iterations)
	li      $v0,11
	li     $a0,'\n' #End Of Line
	syscall
	
	
#########print int in 16bit reverse in decimal#############	
andi    $a0,$t2,0x8000 #check if the number is negative
beq     $a0,$0,positive
lui     $a0,0xffff  #in case of negative sign extend by 0xffff
positive:
	or      $a0,$a0,$t2.
	li      $v0,1
	syscall
	
	
#################exit program#################
li	$v0 , 10
syscall
	
	
	
	
	
	
	
	
	
	
	
