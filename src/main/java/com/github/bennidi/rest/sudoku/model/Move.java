package com.github.bennidi.rest.sudoku.model;

import java.util.Date;

/**
 * Represents a single move within a Board board. Each move sets exactly
 * one cell in the board to its respective value.
 */
public class Move {

    // The index identifies the position of the cell this move refers to
    private int index;
    private int value;
    private Date tsCreated;

    public Move(int index, int value, Date tsCreated) {
        this.index = index;
        this.value = value;
        this.tsCreated = tsCreated;
    }
}