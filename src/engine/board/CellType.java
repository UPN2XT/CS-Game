package engine.board;

/**
 * The CellType enum represents the different types of cells on the game board.
 */
public enum CellType {
    NORMAL, // A normal cell on the board
    SAFE,   // A safe zone cell where marbles are protected
    BASE,   // A base cell where marbles start
    ENTRY   // An entry cell where marbles enter the track
}