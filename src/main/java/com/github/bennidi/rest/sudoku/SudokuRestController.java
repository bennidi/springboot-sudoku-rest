package com.github.bennidi.rest.sudoku;

import com.github.bennidi.rest.sudoku.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * The Board REST controller exposes all public endpoints necessary to interact with
 * the Board game.
 *
 * The controller will handle JSON<->Class mapping, parameter extraction, authorization
 * and other common REST resource features
 */
@RestController
public class SudokuRestController {

    @Autowired private SudokuRepository repository;

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Board byId(@PathVariable("id") String id) {
        return repository.byId(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String index() {
        return "Welcome to the Board Board Game REST service";
    }
    
}
