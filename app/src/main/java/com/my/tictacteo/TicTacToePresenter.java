package com.my.tictacteo;

public class TicTacToePresenter implements TicTacToeContracts.Presenter {

    public static final int PLAYER_1 = 1;
    public static final int PLAYER_2 = 2;

    // Player can only be either 1 or 2 because this is a 2-people game
    private int currentPlayer;

    private TicTacToeContracts.View view;

    private TicTacToeBoard board;

    private boolean gameFinished = false;

    private int moveCount = 0;

    public TicTacToePresenter(TicTacToeContracts.View view, int size) {
        this.view = view;
        currentPlayer = PLAYER_1;
        board = new TicTacToeBoard(size);
        view.showCurrentPlayer(currentPlayer);
    }

    @Override
    public void onBoardItemClicked(int row, int col) {
        if (gameFinished || board.isTaken(row, col)) {
            return;
        }

        board.addOneMove(row, col, currentPlayer);
        view.updateClickedItem(row, col, currentPlayer);
        moveCount++;

        int size = board.getSize();
        if (moveCount == size * size) {
            view.showTie();
            gameFinished = true;
        } else if (board.isPlayerWon(currentPlayer)) {
            view.showPlayerWon(currentPlayer);
            gameFinished = true;
        } else {
            changePlayer();
            view.showCurrentPlayer(currentPlayer);
        }
    }

    @Override
    public void onResetClicked() {
        gameFinished = false;
        moveCount = 0;

        clearBoard();

        resetPlayer();
    }

    private void resetPlayer() {
        currentPlayer = PLAYER_1;
        view.showCurrentPlayer(PLAYER_1);
    }

    private void clearBoard() {
        board.clear();
        view.clearBoard();
    }

    private void changePlayer() {
        currentPlayer = currentPlayer == PLAYER_1 ? PLAYER_2 : PLAYER_1;
    }
}
