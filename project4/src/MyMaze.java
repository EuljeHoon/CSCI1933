// Names: Jehoon Park, Madhav Mennon
// x500s: park2836, menon082

import java.util.Random;
import java.util.Scanner;

public class MyMaze {
    Cell[][] maze;
    int startRow;
    int endRow;

    public MyMaze(int rows, int cols, int startRow, int endRow) {
        maze = new Cell[rows][cols];
        this.startRow = startRow;
        this.endRow = endRow;

        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[i].length; j++) {
                maze[i][j] = new Cell();
            }
        }
    }

    /* TODO: Create a new maze using the algorithm found in the writeup. */
    public static MyMaze makeMaze() {
        Scanner myScanner = new Scanner(System.in);
        boolean userInputBool = false; // set to true when the user enters a valid input
        int userInputRows = 0;
        int userInputCols = 0;

        while(!userInputBool) {
            try {
                System.out.println("Please enter number of rows and columns (between 5 and 20 for both) separated by a space: ");
                String userInput = myScanner.nextLine();
                String[] userInputArr = userInput.split(" ");
                userInputRows = Integer.parseInt(userInputArr[0]); // number of rows
                userInputCols = Integer.parseInt(userInputArr[1]); // number of cols

                if(userInputRows >= 5 && userInputRows <= 20 && userInputCols >= 5 && userInputCols <= 20) {
                    userInputBool = true;
                }
                else {
                    System.out.println("Please enter valid numbers"); // if user inputs correct format, but invalid numbers
                }
            }
            catch(Exception e) {
                System.out.println("Please enter valid input"); // if user inputs incorrect format
            }

        }

        Random random = new Random();
        MyMaze newMaze = new MyMaze(userInputRows, userInputCols, random.nextInt(userInputRows), random.nextInt(userInputRows)); // MyMaze object initialized

        Stack1Gen<Cell> stack = new Stack1Gen<>();
        stack.push(newMaze.maze[newMaze.startRow][0]); // assigns the starting cell to the start attribute of the stack
        newMaze.maze[newMaze.startRow][0].setVisited(true);

        int rowIndex = newMaze.startRow; // keeps track of the "current" row. Initialized to the starting row, which was randomly assigned
        int colIndex = 0; // keeps track of the "current" column. Initialized to 0 because the starting point of the maze is always at column 0

        while(!stack.isEmpty()) {
            Cell currentCell = stack.top(); // gets the object at the top of the stack

            Cell[] neighbors = new Cell[4];

            // Try-catch blocks ensure that "out of bound" neighbors are handled
            try {
                neighbors[0] = newMaze.maze[rowIndex][colIndex+1]; // right neighbor
            }
            catch (Exception e) {
                neighbors[0] = null; // if a neighbor is out of bounds, they are assigned null to allow for easier handling
            }
            try {
                neighbors[1] = newMaze.maze[rowIndex][colIndex-1]; // left neighbor
            }
            catch (Exception e) {
                neighbors[1] = null;
            }
            try {
                neighbors[2] = newMaze.maze[rowIndex+1][colIndex]; // bottom neighbor
            }
            catch (Exception e) {
                neighbors[2] = null;
            }
            try {
                neighbors[3] = newMaze.maze[rowIndex-1][colIndex]; // top neighbor
            }
            catch (Exception e) {
                neighbors[3] = null;
            }


            boolean randomNeighborSelected = false;
            while(!randomNeighborSelected) {
                int randomNeighborNumber = random.nextInt(4); // random number between 0 and 3 (inclusive) that corresponds to a particular neighbor

                // if the right neighbor is selected, the neighbor is in bounds, and has not been visited
                if(randomNeighborNumber == 0 && neighbors[randomNeighborNumber] != null && !neighbors[randomNeighborNumber].getVisited()) {
                    stack.push(neighbors[0]); // adds neighbor to stack
                    neighbors[0].setVisited(true);
                    currentCell.setRight(false); // removes the wall between current cell and cell on the right
                    colIndex++; // the new current cell is now the one on the right of the previous cell
                    randomNeighborSelected = true; // setting this to true means this while loop will not run again
                }

                // if the left neighbor is selected, the neighbor is in bounds, and has not been visited
                else if(randomNeighborNumber == 1 && neighbors[randomNeighborNumber] != null && !neighbors[randomNeighborNumber].getVisited()) {
                    stack.push(neighbors[1]);
                    neighbors[1].setVisited(true);
                    neighbors[1].setRight(false); // removes wall between left cell and current cell
                    colIndex--; // the new current cell is now the one on the left of the previous cell
                    randomNeighborSelected = true;
                }

                // if the bottom neighbor is selected, the neighbor is in bounds, and has not been visited
                else if(randomNeighborNumber == 2 && neighbors[randomNeighborNumber] != null && !neighbors[randomNeighborNumber].getVisited()) {
                    stack.push(neighbors[2]);
                    neighbors[2].setVisited(true);
                    currentCell.setBottom(false); // removes the wall between current cell and bottom cell
                    rowIndex++; // the new current cell is now the one below the previous cell
                    randomNeighborSelected = true;
                }

                // if the top neighbor is selected, the neighbor is in bounds, and has not been visited
                else if(randomNeighborNumber == 3 && neighbors[randomNeighborNumber] != null && !neighbors[randomNeighborNumber].getVisited()) {
                    stack.push(neighbors[3]);
                    neighbors[3].setVisited(true);
                    neighbors[3].setBottom(false); // removes wall between top cell and current cell
                    rowIndex--; // the new current cell is now the one above the previous cell
                    randomNeighborSelected = true;
                }

                // if the neighbor is out of bounds of the maze, rerun the loop and choose another random number
                else if(neighbors[randomNeighborNumber] == null) {

                }
                // if all possible neighbors are either out of bounds or already visited
                else if((neighbors[0] == null || neighbors[0].getVisited()) && (neighbors[1] == null || neighbors[1].getVisited()) && (neighbors[2] == null || neighbors[2].getVisited()) && (neighbors[3] == null || neighbors[3].getVisited())) {
                    stack.pop();
                    try {
                        if (stack.top().equals(newMaze.maze[rowIndex][colIndex + 1])) { // if the new top cell is the right neighbor to the popped cell
                            colIndex++;
                        }
                    } catch (Exception e) { }

                    try {
                        if (stack.top().equals(newMaze.maze[rowIndex][colIndex - 1])) { // if the new top cell is the left neighbor to the popped cell
                            colIndex--;
                        }
                    } catch (Exception e) { }

                    try {
                        if (stack.top().equals(newMaze.maze[rowIndex + 1][colIndex])) { // if the new top cell is the bottom neighbor to the popped cell
                            rowIndex++;
                        }
                    } catch (Exception e) { }

                    try {
                        if (stack.top().equals(newMaze.maze[rowIndex - 1][colIndex])) { // if the new top cell is the top neighbor to the popped cell
                            rowIndex--;
                        }
                    } catch (Exception e) { }

                    randomNeighborSelected = true;
                }
            }
        }
        for(int i = 0; i < newMaze.maze.length; i++) {
            for(int j = 0; j < newMaze.maze[i].length; j++) {
                newMaze.maze[i][j].setVisited(false);
            }
        }
        return newMaze;
    } // end of makeMaze

    /* TODO: Print a representation of the maze to the terminal */
    public void printMaze() {
        System.out.println("|---".repeat(maze[0].length) + "|") ; // prints the very top of the maze // https://howtodoinjava.com/java11/repeat-string-n-times/

        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[i].length; j++) {
                if(j == 0) { // if cell is in the first column
                    if(i != startRow) { // if cell is not in the starting row, a border is printed at the start of the row
                        System.out.print("|");
                    }
                    else { // if cell is in the starting row, and open space is left before the row starts
                        System.out.print(" ");
                    }
                }
                if(maze[i][j].getVisited()) { // if the cell has been visited, mark it with a *
                    System.out.print(" * ");
                }
                else {
                    System.out.print("   ");
                }
                if (maze[i][j].getRight() && !maze[i][j].equals(maze[endRow][maze[i].length - 1])) { // if cell has a right border, and is not the ending point cell, print a |
                    System.out.print("|");
                }
                else { // if cell has no right border/is the ending point cell, then do not print |
                    System.out.print(" ");
                }
                if (j == maze[i].length - 1) { // if cell is at the last column, create new line
                    System.out.println();
                }
            }
            for(int k = 0; k < maze[i].length; k++) { // this for loop is for the space between cell rows
                if(i == maze.length - 1) { // if the cell is on the last row, print bottom border of the maze
                    if(k == maze[i].length - 1) {
                        System.out.print("|---|"); // end of the row
                    }
                    else {
                        System.out.print("|---"); // not end of the row
                    }
                }
                else { // cell is not on the last row
                    if (maze[i][k].getBottom()) {  // if cell has a bottom border, print bottom border "|---"
                        System.out.print("|---");
                    }
                    else { // if cell does not have a bottom border, leave empty space "|   "
                        System.out.print("|   ");
                    }
                    if(k == maze[0].length - 1) { // if cell is on the last column, print |
                        System.out.println("|");
                    }
                }
            }
        }
    }

    /* TODO: Solve the maze using the algorithm found in the writeup. */
    public void solveMaze() {
        // sets finish to the last cell
        int[] finish = new int[]{endRow, maze[0].length-1};
        Q1Gen<int[]> queue = new Q1Gen<>();
        queue.add(new int[]{startRow, 0}); //idea: https://www.geeksforgeeks.org/queue-add-method-in-java/
        int[] current;
        while (queue.length() != 0) {
            current = queue.remove();
            // sets the maze at current cell to visited
            maze[current[0]][current[1]].setVisited(true);
            if (current[0] == finish[0] && current[1] == finish[1] ) {
                break;
            }
            neighborChecker(queue, current);
        }
        printMaze();
    }

    /**
     *Two helper functions to help solveMaze to work (neighborChecker and getWall).
     */
    // helper for checking the neighbors.
    public void neighborChecker(Q1Gen<int[]> queue, int[] cords){
        // the 4 possible neighbors, upper, lower, right, left
        int[] upper, lower, right, left;
        // will store neighbors
        int[][] neighbors = new int[4][];
        // sets the neighbors
        upper = new int[]{cords[0] - 1, cords[1]}; //idea: https://leetcode.com/problems/map-of-highest-peak/discuss/1480035/Java-BFS-readable-solution
        lower = new int[]{cords[0] + 1, cords[1]};
        right = new int[]{cords[0], cords[1] + 1};
        left = new int[]{cords[0], cords[1] - 1};
        neighbors[0] = upper;
        neighbors[1] = lower;
        neighbors[2] = right;
        neighbors[3] = left;
        for (int i = 0; i < neighbors.length; i++) {
            if (!getWall(cords, neighbors[i])) {
                try {
                    if (!maze[neighbors[i][0]][neighbors[i][1]].getVisited()) { //checks the case that neighbor has not visited.
                        queue.add(neighbors[i]);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
    }
    //Helper function to check if there is not a wall between the current cell and neighbor
    public boolean getWall(int[] cell1, int[] cell2){
        try {
            //the case if in same row
            if (cell1[0] == cell2[0]) {
                return maze[cell1[0]][Math.min(cell1[1], cell2[1])].getRight();
            }
            //the case if in same col
            return maze[Math.min(cell1[0], cell2[0])][cell1[1]].getBottom();
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }

    }

    public static void main(String[] args) {
        /*Make and solve maze */
        MyMaze mazeOne = new MyMaze(5, 10, 0, 4);
        makeMaze().solveMaze();

    }
}