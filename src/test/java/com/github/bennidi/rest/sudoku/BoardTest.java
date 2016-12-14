package com.github.bennidi.rest.sudoku;

import com.github.bennidi.rest.sudoku.model.Board;
import com.github.bennidi.rest.sudoku.model.Move;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;


public class BoardTest {

    @Test
    public void testEmptyBoard(){
        Board emptyBoard = new Board(new ArrayList<>());
        assertFalse(emptyBoard.isComplete());
        assertEquals(emptyBoard.getNumberOfRemainingMoves(), 81);
    }

    @Test
    public void testGivenBoard(){
        Board givenBoard = new Board(Arrays.asList(
                new Move(0, 7), new Move(4, 4), new Move(6, 5), new Move(7, 3),
                new Move(11, 5), new Move(14, 8), new Move(16, 1),
                new Move(20, 8), new Move(21, 5), new Move(23, 9), new Move(25, 4),
                new Move(27, 5), new Move(28, 3), new Move(29, 9), new Move(31, 6), new Move(35, 1),
                new Move(40, 1), new Move(44, 5),
                new Move(45, 8), new Move(48, 7), new Move(49, 2), new Move(51, 9),
                new Move(54, 9), new Move(56, 7), new Move(57, 4),
                new Move(67, 5), new Move(68, 7),
                new Move(72, 6), new Move(79, 5)
        ));
        assertFalse(givenBoard.isComplete());
        assertEquals(81-29, givenBoard.getNumberOfRemainingMoves());
        assertTrue(givenBoard.isAdmissible(new Move(8,9)));
        assertTrue(givenBoard.isAdmissible(new Move(37,4)));
        assertFalse(givenBoard.isAdmissible(new Move(2,7)));
    }




    @Test
    public void testColumnIndexCalculation(){
        Board board = new Board(new ArrayList<>());
        assertEquals(0, board.getColumnIndex(0));
        assertEquals(5, board.getColumnIndex(5));
        assertEquals(0, board.getColumnIndex(9));
        assertEquals(0, board.getColumnIndex(27));
        assertEquals(8, board.getColumnIndex(80));
    }

    @Test
    public void testRowIndexCalculation(){
        Board board = new Board(new ArrayList<>());
        assertEquals(0, board.getRowIndex(0));
        assertEquals(0, board.getRowIndex(5));
        assertEquals(2, board.getRowIndex(25));
        assertEquals(3, board.getRowIndex(29));
        assertEquals(8, board.getRowIndex(80));
    }

    @Test
    public void testGridIndexCalculation(){
        Board board = new Board(new ArrayList<>());
        assertEquals(0, board.getGridIndex(0));
        assertEquals(0, board.getGridIndex(1));
        assertEquals(0, board.getGridIndex(2));
        assertEquals(1, board.getGridIndex(3));
        assertEquals(1, board.getGridIndex(4));
        assertEquals(1, board.getGridIndex(5));
        assertEquals(2, board.getGridIndex(6));
        assertEquals(3, board.getGridIndex(29));
        assertEquals(8, board.getGridIndex(80));
    }

}
