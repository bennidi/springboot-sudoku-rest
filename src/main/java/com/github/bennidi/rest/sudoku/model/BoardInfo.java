package com.github.bennidi.rest.sudoku.model;

/**
 * Summary about a given board.
 */
public class BoardInfo {

    private String boardId;
    private Move lastSuccessfulMove;
    private int movesRemaining;

    public BoardInfo(String boardId, Move lastSuccessfulMove, int movesRemaining) {
        this.boardId = boardId;
        this.lastSuccessfulMove = lastSuccessfulMove;
        this.movesRemaining = movesRemaining;
    }

    public String getBoardId() {
        return boardId;
    }

    public Move getLastSuccessfulMove() {
        return lastSuccessfulMove;
    }

    public int getMovesRemaining() {
        return movesRemaining;
    }
}
