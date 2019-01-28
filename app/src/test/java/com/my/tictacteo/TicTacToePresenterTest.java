package com.my.tictacteo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.my.tictacteo.TicTacToePresenter.PLAYER_1;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TicTacToePresenterTest {

    @Mock
    private TicTacToeContracts.View mockView;

    private TicTacToeContracts.Presenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new TicTacToePresenter(mockView, 3);
    }

    @Test
    public void tryATakenPosition() {
        presenter.onBoardItemClicked(1, 0); // player 1
        verify(mockView, times(1)).updateClickedItem(1, 0, PLAYER_1);
        presenter.onBoardItemClicked(1, 0); // player 1
        verify(mockView, times(1)).updateClickedItem(1, 0, PLAYER_1);
    }

    /*
        Test case:
        X * X
        O O O
        * * *
        Player 1 won
     */
    @Test
    public void playerWonByRow() {
        presenter.onBoardItemClicked(1, 0); // player 1
        presenter.onBoardItemClicked(0, 0); // player 2
        presenter.onBoardItemClicked(1, 1); // player 1
        presenter.onBoardItemClicked(0, 2); // player 2
        verify(mockView, never()).showPlayerWon(anyInt());
        presenter.onBoardItemClicked(1, 2); // player 1
        verify(mockView).showPlayerWon(PLAYER_1);
    }

    /*
        Test case:
        X O X
        O X O
        O X O
        Player 1 won
     */
    @Test
    public void showTie() {
        presenter.onBoardItemClicked(0, 1); // player 1
        presenter.onBoardItemClicked(0, 0); // player 2
        presenter.onBoardItemClicked(1, 0); // player 1
        presenter.onBoardItemClicked(0, 2); // player 2
        presenter.onBoardItemClicked(2, 0); // player 1
        presenter.onBoardItemClicked(1, 1); // player 2
        presenter.onBoardItemClicked(1, 2); // player 1
        presenter.onBoardItemClicked(2, 1); // player 2
        presenter.onBoardItemClicked(2, 2); // player 1
        verify(mockView, never()).showPlayerWon(anyInt());
        verify(mockView).showTie();
    }

    @Test
    public void onResetClicked() {
        verify(mockView, times(1)).showCurrentPlayer(PLAYER_1);
        presenter.onResetClicked();
        verify(mockView, times(2)).showCurrentPlayer(PLAYER_1);
        verify(mockView).clearBoard();
    }
}
