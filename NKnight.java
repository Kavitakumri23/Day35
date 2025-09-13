public class NKnight {
        public static void main(String[] args) {
            int n = 4;
            boolean[][] board = new boolean[n][n];
            System.out.println(knights(board, 0, 0, n));
        }

        static int knights(boolean[][] board, int row, int col, int knights) {
            if (knights == 0) {
                display(board);
                System.out.println();
                return 1;
            }

            if (row == board.length - 1 && col == board.length) {
                return 0;
            }
            if (col == board.length) {
                return knights(board, row + 1, 0, knights);
            }

            int count = 0;
            if (isSafe(board, row, col)) {
                board[row][col] = true;
                count += knights(board, row, col + 1, knights - 1);
                board[row][col] = false;
            }
            count += knights(board, row, col + 1, knights);
            return count;
        }

        static boolean isSafe(boolean[][] board, int row, int col) {
            int[][] moves = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}};
            for (int[] move : moves) {
                int r = row + move[0];
                int c = col + move[1];
                if (r >= 0 && c >= 0 && r < board.length && c < board.length) {
                    if (board[r][c]) return false;
                }
            }
            return true;
        }

        static void display(boolean[][] board) {
            for (boolean[] row : board) {
                for (boolean element : row) {
                    if (element) {
                        System.out.print(" K ");
                    } else {
                        System.out.print(" X ");
                    }
                }
                System.out.println();
            }
        }
    }

