package com.my.tictacteo;

public class TicTacToeBoard {

    // This grid can be N * N
    private int[][] grid;

    // size of the board
    private int size;

    public TicTacToeBoard(int size) {
        this.size = size;
        this.grid = new int[size][size];
    }

    public void addOneMove(int row, int col, int player) {
        grid[row][col] = player;
    }

    public boolean isTaken(int row, int col) {
        return grid[row][col] != 0;
    }

    public boolean isPlayerWon(int player) {
        int rows = grid.length;
        int cols = grid[0].length;


        // any row
        for (int i = 0; i < rows; i++) {
            boolean won = true;
            for (int j = 0; j < cols; j++) {
                won = won && player == grid[i][j];
            }
            if (won) {
                return true;
            }
        }

        // any col
        for (int i = 0; i < cols; i++) {
            boolean won = true;
            for (int j = 0; j < rows; j++) {
                won = won && player == grid[j][i];
            }
            if (won) {
                return true;
            }
        }

        // the diagonals
        boolean won = true;
        for (int i = 0; i < rows; i++) {
            won = won && player == grid[i][i];

        }
        if (won) {
            return true;
        }

        won = true;
        for (int i = 0; i < rows; i++) {
            won = won && player == grid[rows - 1 - i][i];

        }
        return won;
    }

    public void clear() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = 0;
            }
        }
    }

    public int getSize() {
        return size;
    }
}
