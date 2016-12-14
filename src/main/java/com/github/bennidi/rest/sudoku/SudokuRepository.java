package com.github.bennidi.rest.sudoku;

import com.github.bennidi.rest.sudoku.model.Board;
import com.github.bennidi.rest.sudoku.model.Move;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * The Board repository provides convenient access to the underlying storage layer.
 * It aims to provide technology agnostic methods for storing and retrieving instances
 * of @link Board
 *
 */
@Component
public class SudokuRepository {

    public static final Map<String, Board> games = new HashMap<>();
    static{
        games.put("board-one", createPredefinedBoard());
        games.put("board-two", createPredefinedBoard());
    }

    public static Board createPredefinedBoard(){
        return new Board(Arrays.asList(
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
    }

    public Board byId(String id){
        return games.get(id);
    }


}
