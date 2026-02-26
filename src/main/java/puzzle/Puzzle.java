package puzzle;

import java.util.ArrayList;
import java.util.Arrays;

public class Puzzle {
    // Puzzle Storage
    private final int [] puzzle; // len. 9
    private int blankIdx; // original Blank location
    //private int [] goal; // free for any given user goal

    /*public Puzzle (int [] puzzle, int [] goal) {
        this.puzzle = puzzle;
        this.blankIdx = findBlank(puzzle);
        this.goal = goal;
    }*/

    public Puzzle (int [] puzzle) {
        this.puzzle = puzzle;
        this.blankIdx = findBlank(puzzle);
    }

    // Find the Blank - Method iterates through the puzzle array and
    // locates blank box's position (index).
    public int findBlank (int[] puzzle){
        for(int i = 0; i < puzzle.length; i++) {
            if(puzzle[i] == 0) return i;
        }
        throw new IllegalArgumentException("!! Error - No blank tile located!");
    }

    // Child Propagation & Move Rules - Method creates new Puzzle children according to
    // legal moves that can be made and adds them to a new ArrayList.
    public ArrayList<Puzzle> getChildren() {
        ArrayList<Puzzle> children = new ArrayList<>();
        int bIdx = blankIdx;

        // Move Blank Up
        if (bIdx > 2){
            children.add(swapBlank(bIdx, bIdx - 3));
        }

        // Move Blank Down
        if (bIdx < 2){
            children.add(swapBlank(bIdx, bIdx + 3));
        }

        // Move Blank Left
        if (bIdx % 2 != 0){
            children.add(swapBlank(bIdx, bIdx - 1));
        }

        // Move Blank Right
        if (bIdx % 2 != 2){
            children.add(swapBlank(bIdx, bIdx + 1));
        }

        return children;
    }

    // Child Propagation Helper Method -
    // Swaps Blank with appropriate index depending on chosen move.
    // Then, produces a new child Puzzle.
    public Puzzle swapBlank (int i, int j) {
        int [] newPuzzle = puzzle;
        int temp = i;
        newPuzzle[i] = j;
        newPuzzle[j] = temp;
        return new Puzzle(newPuzzle /*, this.goal*/);
    }

    /*public boolean isGoal (Puzzle puzzle) {

    }*/

    // Puzzle Equality Checker (Avoid repeat states) - Overrides Java's equals() method
    // to evaluate each Puzzle instance against another.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Puzzles are the same obj. in memory; immediate true
        if (!(o instanceof Puzzle puzzle1)) return false; // Type compatibility check; avoid errors
        // Pattern variable declared; only typecasts if type match succeeds.
        return Arrays.equals(puzzle, puzzle1.puzzle); // Equality test
    }

    // Puzzle Equality Checker (Avoid repeat states) - Overrides Java's hashCode() method
    // to check Puzzle instances' hashcode against one another.
    @Override
    public int hashCode() {
        return Arrays.hashCode(puzzle);
    }

    // !! DELETE AT THE END !!
    // Valid Move Rules - Method sets the conditions for valid moves
    // with inequalities and modulo.
    /*public boolean validMove (int[] puzzle){
        boolean move = true;
        for(int i = 0; i < puzzle.length; i++){}

        return move;
    }*/
}
