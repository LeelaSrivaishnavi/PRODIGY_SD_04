public class SudokuSolver {
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

    public static void main(String[] args) {
        int[][] grid = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        if (solveSudoku(grid)) {
            System.out.println("Sudoku puzzle solved successfully:");
            printGrid(grid);
        } else {
            System.out.println("No solution exists for the given Sudoku puzzle.");
        }
    }
}
