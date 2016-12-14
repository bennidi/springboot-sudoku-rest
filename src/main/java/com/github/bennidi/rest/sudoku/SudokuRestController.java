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
 *
 *
 * GET /boards/{id}
 Retrieves a JSON representation of the full board with respective id.

 POST /boards/
 Not supported. Would usually create a new board and return a document with its resource identifier

 PUT /boards/{id}
 Not supported. Would usually update an existing board replacing its full content. For partial updates PATCH would be used. Would return the updated document or a filtered view (if supported)
 Returns 404 if board with id does not yet exist (this is strict REST which does not have the notion of createOrUpdate)

 DELETE /boards/{id}?
 Not supported. Deletion of boards is not allowed. Deletion of resources is generally worth discussing. Instead of physical deletion, items could transition into "status=deleted".

 POST /boards/{id}/moves
 Adds another move to the board. Validates the move against the current state of the board,
 if the move is valid it will be added. If the move is not allowed 420(Policy not fullfilled) will be returned. Response for valid moves contains number of remaining (open) cells.

 DELETE | PUT | PATCH is not supported for board moves
 *
 *
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

    /**
     * Adds another move to the board. Validates the move against the current state of the board
     * if the move is valid it will be added. If the move is not allowed 420(Policy not fullfilled) will be returned.
     * Response for valid moves contains number of remaining (open) cells.
     */
    @RequestMapping(method = RequestMethod.POST, path = "/boards/{id}/moves")
    @ResponseBody
    public BoardInfo addMove(@RequestBody Move move, @PathVariable("id") String id, HttpServletResponse response){
        Board board = repository.byId(id);
        if(board==null) throw new BoardNotFoundException();
        BoardInfo info = board.addMove(move);
        if(!info.getLastSuccessfulMove().equals(move)) response.setStatus(420);
        return info;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String index() {
        return "Welcome to the Board Board Game REST service";
    }
    
}
