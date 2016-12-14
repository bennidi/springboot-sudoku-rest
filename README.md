# Sudoku service

This is a service for playing soduko in a RESTful way.
You can find a very detailed explanation of the Sudoku rules [here](https://en.wikipedia.org/wiki/Sudoku)

## Requirements

+ can validate successive moves on a Sudoku board. 
+ be able to recognise and indicate if the Sudoku is finished with the current move.

## Routes

GET /boards/{id}
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



