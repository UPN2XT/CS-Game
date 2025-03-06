package model.player;

import model.Colour;

/**
 * The Marble class represents a marble with a specific colour.
 */
public class Marble {
    private final Colour colour;

    /**
     * Constructs a new Marble with the specified colour.
     *
     * @param colour the colour of the marble
     */
    public Marble(Colour colour) {
        this.colour = colour;
    }

    /**
     * Gets the colour of the marble.
     *
     * @return the colour of the marble
     */
    public Colour getColour() {
        return colour;
    }
}