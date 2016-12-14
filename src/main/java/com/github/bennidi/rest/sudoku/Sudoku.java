package com.github.bennidi.rest.sudoku;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sudoku {

    private String uuid;
    private List<Cell> cells;
    private int width = 9;
    private int height = 9;
    private int[] values;

    public String getUuid() {
        return uuid;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getValues() {
        return Arrays.copyOf(values, values.length);
    }

    public class Cell{

        private int index;
    }

}