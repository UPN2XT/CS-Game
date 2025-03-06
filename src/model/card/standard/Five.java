package model.card.standard;

import engine.GameManager;
import engine.board.BoardManager;

/**
 * The Five class represents a standard playing card with rank 5.
 * It extends the Standard card class and inherits its properties and behaviors.
 */
public class Five extends Standard {
    /**
     * Constructs a new Five card with the specified attributes.
     *
     * @param name         the name of the card
     * @param description  the description of the card
     * @param suit         the suit of the card (HEART, DIAMOND, SPADE, CLUB)
     * @param boardManager the board manager used by this card
     * @param gameManager  the game manager used by this card
     */
    public Five(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 5, suit, boardManager, gameManager);
    }
}