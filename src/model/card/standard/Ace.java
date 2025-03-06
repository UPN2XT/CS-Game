package model.card.standard;

import engine.GameManager;
import engine.board.BoardManager;

/**
 * The Ace class represents a standard playing card with rank 1.
 * It extends the Standard card class and inherits its properties and behaviors.
 */
public class Ace extends Standard {
    /**
     * Constructs a new Ace card with the specified attributes.
     *
     * @param name         the name of the card
     * @param description  the description of the card
     * @param suit         the suit of the card (HEART, DIAMOND, SPADE, CLUB)
     * @param boardManager the board manager used by this card
     * @param gameManager  the game manager used by this card
     */
    public Ace(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 1, suit, boardManager, gameManager);
    }
}