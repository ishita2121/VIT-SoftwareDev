# VIT-SoftwareDev

Problem Statement:  
https://docs.google.com/document/d/16CNXNQ-VLLSoVe7CvJFBzueawgrCjWorB7zMLNvOF94/edit?usp=sharing

# Problem Statement:

## Level 1:

You have to make a game.

There are 2 players playing it.

The game is played on a square board with 5 by 5 blocks.

Each player has 5 characters that start from his end.

Characters can be of the following types.
1. Pawn:	In one move, it moves 1 block straight in any direction (L or R or F or B) - Left, Right, Forward, Backward - These are relative to the player

Player can't move his character to a position where another character exists.

Each player can arrange his characters on his end in any order. He will have 5 pawns to start. He'll deploy all of them at the start of the game.

When a player moves his character into a block with the opponent's character present, the opponent's character dies..

You need to take input of first player characters first (L-R on his side), then for the other player.
After that alternatively take input for the next move from both players. 
(Input of any one character e.g. P1:L, P2:R, P3:F, P3:B)
If a input is invalid ie. it's not possible to move the character to the desired position, then you need to show an error indicating reason (Invalid input format (Not in format of x:y), Character doesn't exist, Invalid Move, Own character standing at the target position, or out of board) and let the player choose another move.

After every input display a simple 5*5 matrix with each character. Use A- or B- as prefix to indicate which player's character it is. (Use tab as space)



Let the game run until all characters of one player have died. And declare the other player as the winner.









## Level 2:

Add below character types to the game:

1. Hero1:	In one move, it moves 2 blocks straight in any direction, and kills anything in its path
2. Hero2:	In one move, it moves 2 blocks diagonally in any direction, and kills anything in its path

H1:L means Hero1 to left, H2:FL means move H2 to forward-left direction

Implement all R,L,F,B for H1 and FL, FR, BL, BR for H2

## Level 3:

Add another character type:

Hero 3: In one move, it moves 2 steps straight and one to the side, and kills only where it finally lands

H3:FR means it moves 2 steps front and one to the right,
H3:RF means it moves 2 steps right and one to the front

Implement all FL, FR, BL, BR, RF, RB, LF, and LB for H3


