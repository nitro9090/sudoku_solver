sudoku_solver
=============

A sudoku solver for any size sudoku setup 

I created a sudoku solver to teach me the basics of Java and learn GitHub.  
The solver can handle any regular square sudoku

To use it, you need to create a text file with the appropriate format, place it in the source folder, and replace the filename in the program with the text file name.
The example sudoku files shows the text file format.  
The first three values of the text file are the dimensions (9x9 = 9), # columns between lines and # rows between lines. 
The next lines are the sudoku, use 0s for blank spaces.

Note, I have not implemented a guessing algorithm, so it can't solve sudoku problems that requires it to guess a number.
