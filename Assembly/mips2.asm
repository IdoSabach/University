########################################################### maman 12, question No 4###########################################################

.macro print %msg
    li $v0, 4
    la $a0, %msg
    syscall
.end_macro

#macro to print string (syscall 4)

################# Data segment ###########################
.data
    bool:           .space 4      # 4 bytes for the null terminate to print as a string
    guess:          .space 4
    input_msg:      .asciiz "\nPlease enter 3 different digits from '0' to '9'\n"
    number_is_ok:   .asciiz "\nOperation completed successfully\nThe number is:"
    error_msg:      .asciiz "\nWrong input\n"
    guess_msg:      .asciiz "\nGuess my number\n"
    end_game_msg:   .asciiz "\nBingo! Game end! Enter 'y' for another game, or enter 'n' to exit\n"
    print_b:        .asciiz "b"
    print_p:        .asciiz "p"
    print_n:        .asciiz "n"

################# Code segment #####################
.text
.globl main

main:
    # Main program entry
    # Call get_number
    la $a0, bool                 # Puts bool address as an argument for the function
    jal get_number

    # Call function get_number
    print number_is_ok
    print bool
    jal get_guess
    
    # Call get_guess again if the user didn't guess the number
    beq $v0, 0, main

    # Call again get_guess
    Play_again:
    print end_game_msg
    
    # Gets the char answer from user
    li $v0, 12
    syscall
    
    # Checks the answer
    beq $v0, 'y', main
    beq $v0, 'n', exit

    # In case of bad input, prints error message and branches to ask again for another game
    print error_msg
    j Play_again


exit:
    li $v0, 10               # Exit program
    syscall

################get_number ###########################
# Get 3 different '0'-'9' digits from the user to bool array
get_number:
    move $t0, $a0             # Copy the address of bool to $t0
    li $t1, 0                  # $t1 = the first legal digit
    li $t2, 0                  # $t2 = the second legal digit

    # Ask the user to enter 3 different numbers
    print input_msg

read_digit:
    li $v0, 12                 # syscall 12 for character input
    syscall

    # Check if the input is a valid digit in the range '0' to '9'
    blt $v0, '0', wrong_input
    bgt $v0, '9', wrong_input
    j legal_digit

wrong_input:
    print error_msg
    j read_digit

legal_digit:
    # Make sure we have 3 different digits
    bne $t1, $0, loop_validate_unique  # First digit is unique
    move $t1, $v0                        # The first legal digit
    j save_number

loop_validate_unique:
    beq $t1, $v0, wrong_input   # Equals the first digit
    bne $t2, $0, last_digit
    move $t2, $v0              # The second legal digit

save_number:
    sb $v0, 0($t0)
    addi $t0, $t0, 1            # The address of the next digit
    j read_digit

last_digit:
    beq $t2, $v0, wrong_input  # Equals the second digit
    sb $v0, 0($t0)
    jr $ra


##################get_guess ###############################
# Get 3 char guess from the user
get_guess:
    move $t0, $a0               # $t0 = the address of bool
    move $t1, $a1               # $t1 = the address of guess
    j start_to_guess

    # Skips error handling for the first time the user enters input
    error_guess:
    print error_msg
    j start_to_guess_exit  # Jump to exit to ensure proper return

start_to_guess:
    print guess_msg  # Gets a string of 3 characters from the user

    li $v0, 8
    move $a0, $t1
    li $a1, 4
    syscall

    lb $t3, 0($t1)  # $t3 is the first guessing char
    lb $t4, 1($t1)  # $t4 is the second guessing char
    lb $t5, 2($t1)  # $t5 is the third guessing char

    # Checks if the values are in the correct range
    blt $t3, '0', error_guess
    bgt $t3, '9', error_guess
    blt $t4, '0', error_guess
    bgt $t4, '9', error_guess
    blt $t5, '0', error_guess
    bgt $t5, '9', error_guess

    # Checks if the values are all different
    beq $t3, $t4, error_guess
    beq $t3, $t5, error_guess
    beq $t4, $t5, error_guess

    # Puts parameters for and calls compare
    move $a0, $t0  # $a0 is the address of bool
    move $a1, $t1  # $a1 is the address of guess
    jal compare

    # Return to main
    j start_to_guess_exit

start_to_guess_exit:
    jr $ra



################### compares the 2 arrays char by char, $a0 and $a1 are the addresses of the arrays.
# Return $v0 = -1 when guessed, 0 otherwise
compare:
    move $t0, $a0  # $t0 is the address of bool
    move $t1, $a1  # $t1 is the address of guess

    lb $t2, 0($t0)  # Load array values
    lb $t3, 1($t0)
    lb $t4, 2($t0)
    lb $t5, 0($t1)
    lb $t6, 1($t1)
    lb $t7, 2($t1)
    li $t8, 0       # bool counter
    li $t9, 0       # p counter

    bne $t2, $t5, test_second_bool
    addi $t8, $t8, 1  # Increment bool counter
    print print_b

test_second_bool:
    bne $t3, $t6, test_third_bool
    addi $t8, $t8, 1
    print print_b

test_third_bool:
    bne $t4, $t7, count_p
    addi $t8, $t8, 1
    print print_b

count_p:
    beq $t2, $t6, increment_p
    beq $t2, $t7, increment_p
    beq $t3, $t5, increment_p
    beq $t3, $t7, increment_p
    beq $t4, $t5, increment_p
    beq $t4, $t6, increment_p
    b print_n

increment_p:
    addi $t9, $t9, 1  # Increment p counter
    print print_p

    beq $t8, 3, guessed  # If all three characters are guessed, jump to the "guessed" label
    j continue_game

guessed:
    # If guessed, return -1
    li $v0, -1
    jr $ra

continue_game:
    # If not guessed, return 0
    li $v0, 0
    jr $ra

