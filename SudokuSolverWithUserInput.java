import java.util.Scanner;

public class SudokuSolverWithUserInput {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] grid = new int[9][9];

        System.out.println("Enter the Sudoku puzzle to solve (use 0 for empty cells):");
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                grid[row][col] = scanner.nextInt();
            }
        }

        if (solveSudoku(grid)) {
            System.out.println("Sudoku puzzle solved successfully:");
            printGrid(grid);
        } else {
            System.out.println("No solution exists for the given Sudoku puzzle.");
        }

        scanner.close();
    }

    public static boolean solveSudoku(int[][] grid) {
        int N = grid.length;
        int row = -1;
        int col = -1;
        boolean isEmpty = true;

        // Check if the grid is solved
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        if (isEmpty) {
            return true; // Grid is solved
        }

        // Try numbers from 1 to 9
        for (int num = 1; num <= 9; num++) {
            if (isSafe(grid, row, col, num)) {
                grid[row][col] = num;

                if (solveSudoku(grid)) {
                    return true; // If solution is found
                }

                grid[row][col] = 0; // Backtrack if solution not found
            }
        }
        return false; // No solution exists
    }

    private static boolean isSafe(int[][] grid, int row, int col, int num) {
        return !usedInRow(grid, row, num) &&
                !usedInCol(grid, col, num) &&
                !usedInBox(grid, row - row % 3, col - col % 3, num);
    }

    private static boolean usedInRow(int[][] grid, int row, int num) {
        for (int col = 0; col < grid.length; col++) {
            if (grid[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean usedInCol(int[][] grid, int col, int num) {
        for (int row = 0; row < grid.length; row++) {
            if (grid[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean usedInBox(int[][] grid, int boxStartRow, int boxStartCol, int num) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (grid[row + boxStartRow][col + boxStartCol] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void printGrid(int[][] grid) {
        int N = grid.length;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
    }
}
