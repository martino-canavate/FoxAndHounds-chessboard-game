### This code allows you to play Fox And Hounds against your computer from the terminal

This was created following a coursework from the University of Edinburgh. The pdf file walks you through the implementation

#### Rules of the Game
The following section will introduce you to the specific rules of the game. The game is played on a classic 8x8 checker board (some of the exercises might ask you to make this more interesting by offering arbitrary sizes). There are two parties playing the game at any time. One party plays the hounds and the other plays the fox. The hounds try to catch the fox while the fox tries to break through the line of hounds to escape.
You can see the starting setup displayed in Figure 1. In the classic version, there are four hounds lined up on one side of the board and one fox on the other side.
4
Game pieces can only move on black fields and only one field at a time in a diagonal direction. Hounds can only move forward, while the fox can go in both directions (see Figure 2). Each player can only move one piece per turn and the fox is always the first to move in a new game.
Figures can not move onto each others’ fields. As soon as the hounds have blocked the fox into a corner or between themselves so that it can no longer move, they have won. If the fox manages to break through and reaches the other side of the board, it has won.


