package com.github.bennidi.rest.sudoku;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * The Sudoku repository provides convenient access to the underlying storage layer.
 * It aims to provide technology agnostic methods for storing and retrieving instances
 * of @see Sudoku
 */
@Component
public class SudokuRepository {

    private static final Map<String, Sudoku> games = new HashMap<>();
    static{
        games.put("game-one", new Sudoku());
        games.put("game-two", new Sudoku());
    }

    public Sudoku byId(String id){
        return games.get(id);
    }


}
