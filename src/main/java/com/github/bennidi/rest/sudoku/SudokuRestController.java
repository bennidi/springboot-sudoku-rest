package com.github.bennidi.rest.sudoku;

import com.github.bennidi.rest.sudoku.model.Board;
import com.github.bennidi.rest.sudoku.model.BoardInfo;
import com.github.bennidi.rest.sudoku.model.Move;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


/**
 * The Board REST controller exposes all public endpoints necessary to interact with
 * the Board game.
 *
 * The controller will handle JSON<->Class mapping, parameter extraction, authorization
 * and other common REST resource features
 */
@RestController
@RequestMapping("/sudoku")
public class SudokuRestController {

    @Autowired private SudokuRepository repository;

    @RequestMapping(method = RequestMethod.GET, path = "/boards/{id}")
    @ResponseBody
    public ResponseEntity<Board> byId(@PathVariable("id") String id) {
        Board board = repository.byId(id);
        if(board != null) return ResponseEntity.ok(board);
        else throw new BoardNotFoundException();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/boards/{id}/moves")
    @ResponseBody
    public BoardInfo addMove(@RequestBody Move move, @PathVariable("id") String id, HttpServletResponse response){
        return null;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String index() {
        return "Welcome to the Board Board Game REST service";
    }
    
}
