package model.card.standard;

import engine.GameManager;
import engine.board.BoardManager;

/**
 * The Four class represents a standard playing card with rank 4.
 * It extends the Standard card class and inherits its properties and behaviors.
 */
public class Four extends Standard {
    /**
     * Constructs a new Four card with the specified attributes.
     *
     * @param name         the name of the card
     * @param description  the description of the card
     * @param suit         the suit of the card (HEART, DIAMOND, SPADE, CLUB)
     * @param boardManager the board manager used by this card
     * @param gameManager  the game manager used by this card
     */
    public Four(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 4, suit, boardManager, gameManager);
    }

}