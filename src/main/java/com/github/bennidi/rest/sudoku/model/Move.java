package com.github.bennidi.rest.sudoku.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Represents a single move within a Board board. Each move sets exactly
 * one cell in the board to its respective value.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class Move {

    // The index identifies the position of the cell this move refers to
    private int index;
    private int value;
    private Date tsCreated;

    public Move(int index, int value) {
        this.index = index;
        this.value = value;
        this.tsCreated = new Date();
    }

    public int getIndex() {
        return index;
    }

    public int getValue() {
        return value;
    }

    public Date getTsCreated() {
        return tsCreated;
    }
}
