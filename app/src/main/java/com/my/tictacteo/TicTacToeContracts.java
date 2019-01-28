package com.my.tictacteo;

interface TicTacToeContracts {

    interface View{
        void updateClickedItem(int row, int col, int player);

        void clearBoard();

        void showCurrentPlayer(int player);

        void showPlayerWon(int player);

        void showTie();
    }

    interface Presenter{

        void onBoardItemClicked(int row, int col);

        void onResetClicked();
    }
}
