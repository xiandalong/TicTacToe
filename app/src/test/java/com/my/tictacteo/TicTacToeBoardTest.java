package com.my.tictacteo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class TicTacToeBoardTest {

    private TicTacToeBoard board;

    @Before
    public void setup() {
        board = new TicTacToeBoard(3);
    }

    @Test
    public void addOneMoveAndVerifyIsTaken() {
        board.addOneMove(1, 1, 1);
        assertTrue(board.isTaken(1, 1));
    }

    @Test
    public void clear() {
        board.clear();
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                assertTrue(!board.isTaken(i, j));
            }
        }
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
        board.addOneMove(1, 0, 1);
        board.addOneMove(0, 0, 2);
        board.addOneMove(1, 1, 1);
        board.addOneMove(0, 2, 2);
        assertTrue(!board.isPlayerWon(2));

        board.addOneMove(1, 2, 1);
        assertTrue(board.isPlayerWon(1));
    }

    /*
        Test case:
        X O X
        * O *
        * O *
        Player 1 won
     */
    @Test
    public void playerWonByColumn() {
        board.addOneMove(0, 1, 1);
        board.addOneMove(0, 0, 2);
        board.addOneMove(1, 1, 1);
        board.addOneMove(0, 2, 2);
        assertTrue(!board.isPlayerWon(2));

        board.addOneMove(2, 1, 1);
        assertTrue(board.isPlayerWon(1));
    }

    /*
        Test case:
        O X X
        * O *
        * * O
        Player 1 won
     */
    @Test
    public void playerWonByDiagonal() {
        board.addOneMove(0, 0, 1);
        board.addOneMove(0, 1, 2);
        board.addOneMove(1, 1, 1);
        board.addOneMove(0, 2, 2);
        assertTrue(!board.isPlayerWon(2));

        board.addOneMove(2, 2, 1);
        assertTrue(board.isPlayerWon(1));
    }

    @Test
    public void getSize() {
        assertEquals(3, board.getSize());
    }
}
