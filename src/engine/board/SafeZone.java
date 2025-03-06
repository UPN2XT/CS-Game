package engine.board;

import model.Colour;
import java.util.ArrayList;

/**
 * The SafeZone class represents a safe zone on the game board.
 */
public class SafeZone {
    private final Colour colour;
    private final ArrayList<Cell> cells;

    /**
     * Constructs a new SafeZone instance with the specified colour.
     *
     * @param colour the colour of the safe zone
     */
    public SafeZone(Colour colour) {
        this.colour = colour;
        this.cells = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            cells.add(new Cell(CellType.SAFE));
    }

    /**
     * Gets the colour of the safe zone.
     *
     * @return the colour of the safe zone
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * Gets the cells in the safe zone.
     *
     * @return an ArrayList of Cell representing the cells in the safe zone
     */
    public ArrayList<Cell> getCells() {
        return cells;
    }
}