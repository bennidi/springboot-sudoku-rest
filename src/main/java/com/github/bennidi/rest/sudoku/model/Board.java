package com.github.bennidi.rest.sudoku.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.NotNull;
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
@NoArgsConstructor
public class Board {

    @Getter @NotNull @Id
    private String id = UUID.randomUUID().toString();

    private List<Move> moves = new ArrayList<>(81);
    // Board dimensions do not need to be configurable
    // -> A default boards size of 9*9 is assumed (standard game)
    // Using width/height dimensions internally allows to extend
    // the board easily and does add very little overhead in implementation
    @JsonIgnore private final int width = 9;
    @JsonIgnore private final int height = 9;
    @JsonIgnore private final int gridLength = 3;
    @JsonIgnore private final int gridSize = 9;
    @Transient private final Cell[] cells = new Cell[81];
    @Transient private final List<CellGroup> rows = new ArrayList<>();
    @Transient private final List<CellGroup> cols = new ArrayList<>();
    @Transient private final List<CellGroup> grids = new ArrayList<>();

    public Board(List<Move> moves){
        // initialize cell groups
        for(int i = 0; i < gridSize; i++){
            rows.add(i, new CellGroup(width)); // width defines number of cells in a row
            cols.add(i, new CellGroup(height)); // height defines number of cells in a column
            grids.add(i, new CellGroup(gridSize)); // gridSize defines number of cells in a sub-grid
        }
        // Assign cells to their respective groups
        // Each cell is part of one row, column and sub-grid
        for(int position=0;position<cells.length; position++){
            cells[position] = new Cell(-1, position);
            int rowIdx = getRowIndex(position);
            int colIdx = getColumnIndex(position);
            int gridIdx = getGridIndex(position);
            rows.get(rowIdx).add(cells[position]);
            cols.get(colIdx).add(cells[position]);
            grids.get(gridIdx).add(cells[position]);
        }
        for(Move move : moves){
            addMove(move);
        }
    }

    public int getColumnIndex(int position){return position % width;}
    public int getRowIndex(int position){return position / width;}
    public int getGridIndex(int position){
        int gridRow = getRowIndex(position) / gridLength  * gridLength;
        int gridCol = getColumnIndex(position) / gridLength;
        return gridRow + gridCol;
    }

    CellGroup getRow(int index){
        return rows.get(index);
    }

    public int getNumberOfRemainingMoves(){
        int remaining = 0;
        for(Cell cell: cells){
           if(!cell.hasValue()) remaining++;
        }
        return remaining;
    }

    public boolean isComplete() {
        return getNumberOfRemainingMoves() == 0;
    }

    public boolean isAdmissible(Move move){
        if(move.getIndex()>= cells.length || move.getIndex()< 0){
           return false;
        }
        Cell target = cells[move.getIndex()];
        if(target.hasValue()){
           return false;
        }
        CellGroup grid = grids.get(getGridIndex(move.getIndex()));
        CellGroup row = rows.get(getRowIndex(move.getIndex()));
        CellGroup col = cols.get(getColumnIndex(move.getIndex()));
        return grid.isAdmissible(move.getValue()) && row.isAdmissible(move.getValue()) && col.isAdmissible(move.getValue());
    }

    private Move getLastMove(){
        return moves.isEmpty() ? null : moves.get(moves.size()-1);
    }

    public BoardInfo addMove(Move move){
        if(!isAdmissible(move)) return new BoardInfo(getId(), getLastMove(), getNumberOfRemainingMoves());
        cells[move.getIndex()].value = move.getValue();
        moves.add(move);
        return new BoardInfo(getId(), getLastMove(), getNumberOfRemainingMoves());
    }

    public List<Move> getMoves() {
        return Collections.unmodifiableList(moves);
    }

    /**
     * An internal class used to hold a reference to a specific board position.
     * Validation of board is computed based on the contents of all its cells.
     */
    class Cell {

        public Cell(int value, int index) {
            this.value = value;
            this.index = index;
        }

        int value;
        int index;

        boolean hasValue(){return value != -1;}
    }

    /**
     * Cell groups are the generic abstraction for rows, column and sub-grids.
     * A common abstraction was chosen because the same constraints for admissible/required cell values
     * applies to each group.
     */
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

        boolean isAdmissible(int value){
            if(value < 1 || value > size) return false;
            for(Cell member : members){
                // Each number used only once
                if(member.value == value)return false;
            }
            return true;
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
            return true;
        }

    }

}