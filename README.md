# Sudoku service

This is a service for playing Sudoku in a RESTful way. It implements the rules of the game as describe [here](https://en.wikipedia.org/wiki/Sudoku)

The service does not (yet!) support board creation. It also does not yet have any connection to persistence storage. It is merely a demonstration how to implement an interactive RESTful API for Sudoku.

The REST controller is held quite simple. The core logic resides in the domain model. The boards stores a list of all its successive moves. Each move can be verified against the current state of the board. This model would allow to replay boards

## Requirements

+ can validate successive moves on a Sudoku board. 
+ be able to recognise and indicate if the Sudoku is finished with the current move.

## Routes

### GET /boards/{id}
Retrieves a JSON representation of the full board

### GET /boards
Not implemented. Would usually return a (pageable) list of existing boards.

### POST /boards
Not supported. Would usually create a new board and return a document with its identifier.

### PUT /boards/{id}
Not supported. Would update an existing board replacing its full content. For partial updates PATCH could be used. Would return the updated document or a filtered view (if supported). 
Returns 404 if board with id does not yet exist (this is strict REST which does not have the notion of createOrUpdate).
Would require to create diffs between boards because the existing sequence of moves is immutable. The delta between the two board versions could then be applied to the original.

### DELETE /boards/{id}?
Not supported. Deletion of boards is not allowed. Deletion of resources is generally worth discussing. Instead of physical deletion, items could transition into "status=deleted".

### POST /boards/{id}/moves
Adds another move to the board. Validates the move against the current state of the board,
if the move is valid it will be added. If the move is not allowed 420(Policy not fullfilled) will be returned. Response for valid moves contains number of remaining (open) cells.

DELETE | PUT | PATCH is not supported for board moves

## Future work

+ add HATEOS to response body to make the API traversable
+ add integration with Swagger or RAML for convenient API browsing for humans
+ add mongodb or other storage technology




