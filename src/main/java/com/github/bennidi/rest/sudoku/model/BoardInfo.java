package com.github.bennidi.rest.sudoku.model;

import lombok.Getter;

/**
 * Summary about a given board.
 */
@Getter
public class BoardInfo {

    private String boardId;
    private Move lastSuccessfulMove;
    private int movesRemaining;
    private boolean isComplete;

    public BoardInfo(String boardId, Move lastSuccessfulMove, int movesRemaining) {
        this.boardId = boardId;
        this.lastSuccessfulMove = lastSuccessfulMove;
        this.movesRemaining = movesRemaining;
        this.isComplete = movesRemaining == 0;
    }

}
