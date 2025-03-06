package model.card.standard;

import engine.GameManager;
import engine.board.BoardManager;

/**
 * The Ten class represents a standard playing card with rank 10.
 * It extends the Standard card class and inherits its properties and behaviors.
 */
public class Ten extends Standard {
    /**
     * Constructs a new Ten card with the specified attributes.
     *
     * @param name         the name of the card
     * @param description  the description of the card
     * @param suit         the suit of the card (HEART, DIAMOND, SPADE, CLUB)
     * @param boardManager the board manager used by this card
     * @param gameManager  the game manager used by this card
     */
    public Ten(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 10, suit, boardManager, gameManager);
    }
}