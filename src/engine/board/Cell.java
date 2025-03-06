package engine.board;

import model.player.Marble;

/**
 * The Cell class represents a cell on the game board.
 */
public class Cell {
    private Marble marble;
    private CellType cellType;
    private boolean trap;

    /**
     * Constructs a new Cell instance with the specified cell type.
     *
     * @param cellType the type of the cell
     */
    public Cell(CellType cellType) {
        this.cellType = cellType;
        this.trap = false;
        this.marble = null;
    }

    /**
     * Gets the marble on this cell.
     *
     * @return the marble on this cell, or null if there is no marble
     */
    public Marble getMarble() {
        return marble;
    }

    /**
     * Gets the type of this cell.
     *
     * @return the cell type
     */
    public CellType getCellType() {
        return cellType;
    }

    /**
     * Checks if this cell is a trap.
     *
     * @return true if this cell is a trap, false otherwise
     */
    public boolean isTrap() {
        return trap;
    }

    /**
     * Sets the marble on this cell.
     *
     * @param marble the marble to set on this cell
     */
    public void setMarble(Marble marble) {
        this.marble = marble;
    }

    /**
     * Sets whether this cell is a trap.
     *
     * @param trap true to set this cell as a trap, false otherwise
     */
    public void setTrap(boolean trap) {
        this.trap = trap;
    }

    /**
     * Sets the type of this cell.
     *
     * @param cellType the cell type to set
     */
    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }
}