package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzle {
    // Puzzle Storage
    private final int [] puzzle; // len. 9
    private int blankIdx; // original Blank location
    private int [] goal; // free for any given user goal

    public Puzzle (int [] puzzle, int [] goal) {
        this.puzzle = puzzle;
        this.blankIdx = findBlank(puzzle);
        this.goal = goal;
    }

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
        return new Puzzle(newPuzzle, this.goal);
    }

    public boolean isGoal () {
        return Arrays.equals(puzzle, goal);
    }

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

    // Visual Puzzle State Printer - Overrides Java's toString() method
    // to print a user-friendly 3x3 grid portrayal of the 8 puzzle.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); // Mutable string

        for (int i : puzzle) { // Build the 3x3 grid
            int box = puzzle[i];
            sb.append(box == 0 ? "_" : box); // Ternary operator; if true, uses blank, if false, uses box
            sb.append(" ");

            if ((i & 3) == 2) sb.append("\n");
        }
        return sb.toString().trim(); // Return 3x3 Grid String without excess whitespace
    }

    // (Do this in its own place) Random 8 Puzzle Generator - Generate random puzzles for the BFS and DFS to solve.
//    public int [] puzzleGen (){
//        while (true) {
//            List<Integer> boxes = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);
//        }
//    }

    // !! DELETE AT THE END !!
    // Valid Move Rules - Method sets the conditions for valid moves
    // with inequalities and modulo.
    /*public boolean validMove (int[] puzzle){
        boolean move = true;
        for(int i = 0; i < puzzle.length; i++){}

        return move;
    }*/
}
