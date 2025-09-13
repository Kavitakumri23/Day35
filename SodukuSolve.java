public class SodukuSolve{
        public static void main(String[] args) {
            int[][] board = {
                    {3, 0, 6, 5, 0, 8, 4, 0, 0},
                    {5, 2, 0, 0, 0, 0, 0, 0, 0},
                    {0, 8, 7, 0, 0, 0, 0, 3, 1},
                    {0, 0, 3, 0, 1, 0, 0, 8, 0},
                    {9, 0, 0, 8, 6, 3, 0, 0, 5},
                    {0, 5, 0, 0, 9, 0, 6, 0, 0},
                    {1, 3, 0, 0, 0, 0, 2, 5, 0},
                    {0, 0, 0, 0, 0, 0, 0, 7, 4},
                    {0, 0, 5, 2, 0, 6, 3, 0, 0}
            };

            if (solve(board)) {
                display(board);
            } else {
                System.out.println("No solution exists!");
            }
        }

        static boolean solve(int[][] board) {
            int n = board.length;
            int row = -1, col = -1;
            boolean emptyLeft = true;

            // find empty cell
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 0) {
                        row = i;
                        col = j;
                        emptyLeft = false;
                        break;
                    }
                }
                if (!emptyLeft) break;
            }

            // no empty cell => solved
            if (emptyLeft) return true;

            // try numbers 1..9
            for (int number = 1; number <= 9; number++) {
                if (isSafe(board, row, col, number)) {
                    board[row][col] = number;
                    if (solve(board)) return true;
                    board[row][col] = 0; // backtrack
                }
            }
            return false;
        }

        static boolean isSafe(int[][] board, int row, int col, int num) {
            int n = board.length;

            // check row
            for (int i = 0; i < n; i++) {
                if (board[row][i] == num) return false;
            }

            // check column
            for (int i = 0; i < n; i++) {
                if (board[i][col] == num) return false;
            }

            // check 3x3 subgrid
            int sqrt = (int) Math.sqrt(n);
            int startRow = row - row % sqrt;
            int startCol = col - col % sqrt;
            for (int r = startRow; r < startRow + sqrt; r++) {
                for (int d = startCol; d < startCol + sqrt; d++) {
                    if (board[r][d] == num) return false;
                }
            }
            return true;
        }

        static void display(int[][] board) {
            for (int[] row : board) {
                for (int num : row) {
                    System.out.print(num + " ");
                }
                System.out.println();
            }
        }
    }
