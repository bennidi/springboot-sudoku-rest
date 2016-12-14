package com.github.bennidi.rest.sudoku.model;

import com.github.bennidi.rest.sudoku.SudokuRepository;
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
        Board givenBoard = SudokuRepository.createPredefinedBoard();
        assertFalse(givenBoard.isComplete());
        assertEquals(81-29, givenBoard.getNumberOfRemainingMoves());
        assertTrue(givenBoard.isAdmissible(new Move(8,9)));
        assertTrue(givenBoard.isAdmissible(new Move(37,4)));
        assertFalse(givenBoard.isAdmissible(new Move(2,7)));
    }

    @Test
    public void testRowCompletion(){
        Board givenBoard = SudokuRepository.createPredefinedBoard();
        assertFalse(givenBoard.isComplete());
        assertFalse(givenBoard.getRow(3).isSatisfied());
        givenBoard.addMove(new Move(30,8));
        givenBoard.addMove(new Move(32,4));
        givenBoard.addMove(new Move(33,2));
        givenBoard.addMove(new Move(34,7));
        assertTrue(givenBoard.getRow(3).isSatisfied());
        assertFalse(givenBoard.getRow(4).isSatisfied());
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
