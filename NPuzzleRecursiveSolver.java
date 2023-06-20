/*Dilara TOSUN      - 20050111041
* Arda Şafak PARLAR - 20050111043*/
public class NPuzzleRecursiveSolver {
    protected Board solutionBoard;
    Board solve(Board board) {
        initializeSearch();
        searchSolution(new Board(board));
        return solutionBoard;
    }
    void initializeSearch() {solutionBoard = null;}
    private void searchSolution(Board boardConfiguration) {
        // If the board is full, we made it !!!!
        if (boardConfiguration.isFull()) {
            solutionBoard = boardConfiguration;
            return;
        }

        // Get the first empty column to place a queen.
        Integer emptyColumn = boardConfiguration.getFirstEmptyColumn();
        if (emptyColumn == null) return;

        //trace all the row to see, if a queen can attack or not
        for (int row = 0; row < boardConfiguration.height; row++) {
            if (!boardConfiguration.canAttackOtherQueens(emptyColumn, row)) {
                // Place a queen at the current position.
                boardConfiguration.addQueen(emptyColumn, row);

                // Recursive search
                searchSolution(new Board(boardConfiguration));
                if (solutionBoard != null) {return;}

                // Remove the queen from the current position (backtrack).
                // Because it can be attacked from the other queen.
                boardConfiguration.removeQueen(emptyColumn);
            }
        }
    }

}

