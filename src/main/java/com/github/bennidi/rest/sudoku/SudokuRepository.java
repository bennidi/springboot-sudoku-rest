package com.github.bennidi.rest.sudoku;

import com.github.bennidi.rest.sudoku.model.Board;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * The Board repository provides convenient access to the underlying storage layer.
 * It aims to provide technology agnostic methods for storing and retrieving instances
 * of @see Board
 */
@Component
public class SudokuRepository {

    private static final Map<String, Board> games = new HashMap<>();
    static{
        games.put("game-one", new Board());
        games.put("game-two", new Board());
    }

    public Board byId(String id){
        return games.get(id);
    }


}
