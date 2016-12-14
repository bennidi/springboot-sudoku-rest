package com.github.bennidi.rest.sudoku;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * The Sudoku REST controller exposes all public endpoints necessary to interact with
 * the Sudoku game.
 *
 * The controller will handle JSON<->Class mapping, parameter extraction, authorization
 * and other common REST resource features
 */
@RestController
public class SudokuRestController {

    @Autowired private SudokuRepository repository;

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Sudoku byId(@PathVariable("id") String id) {
        return repository.byId(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String index() {
        return "Welcome to the Sudoku Board Game REST service";
    }
    
}
