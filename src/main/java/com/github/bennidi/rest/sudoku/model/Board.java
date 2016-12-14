package com.github.bennidi.rest.sudoku.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jdk.nashorn.internal.objects.annotations.Getter;

import java.lang.reflect.Array;
import java.util.*;

/**
 * A Sudoku board implements the Sudoku board game rules
 * as defined in https://en.wikipedia.org/wiki/Sudoku
 *
 * It is modeled as rectangular grid (width,height) of cells.
 * A cell referes to exactly one position in the board.
 * Cells are logically grouped into columns/rows/sub-boards.
 *
 * A board starts with an initial configuration of values in some
 * of its cells.
 *
 * A board is completed by adding new moves to it. Each move will be
 * verified and applied only if valid
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Board {

    private String uuid;

    private List<Move> moves;
    // Board dimensions do not need to be configurable
    // -> A default boards size of 9*9 is assumed (standard game)
    // Using width/height dimensions internally allows to extend
    // the board easily and does add very little overhead in implementation
    private final int width = 9;
    private final int height = 9;
    private final Cell[] cells = new Cell[81];
    private final List<CellGroup> rows = new ArrayList<>();
    private final List<CellGroup> cols = new ArrayList<>();
    private final List<CellGroup> grids = new ArrayList<>();


    public Board(List<Move> moves){


    }

    public Board addMove(Move move){

    }

    public String getUuid() {
        return uuid;
    }

    public List<Move> getMoves() {
        return Collections.unmodifiableList(moves);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     *
     */
    class Cell {

        int value;
    }

    class CellGroup{

        List<Cell> members = new ArrayList<>();
        int size;

        CellGroup(int size){
            this.size = size;
        }

        CellGroup add(Cell cell){
            members.add(cell);
            return this;
        }

        boolean isSatisfied(){
            Map<Integer, Cell> used = new HashMap<>();
            for(Cell member : members){
                // Each number used only once
                if(used.containsKey(member.value))return false;
                used.put(member.value, member);
            }
            for(int i = 1; i < this.size; i++){
                if(!used.containsKey(i))return false;
            }
        }

    }

}