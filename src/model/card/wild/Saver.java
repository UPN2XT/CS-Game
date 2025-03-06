package model.card.wild;

import engine.GameManager;
import engine.board.BoardManager;

/**
 * The Saver class represents a wild playing card.
 * It extends the Wild card class and inherits its properties and behaviors.
 */
public class Saver extends Wild {
    /**
     * Constructs a new Saver card with the specified attributes.
     *
     * @param name         the name of the card
     * @param description  the description of the card
     * @param boardManager the board manager used by this card
     * @param gameManager  the game manager used by this card
     */
    public Saver(String name, String description, BoardManager boardManager, GameManager gameManager) {
        super(name, description, boardManager, gameManager);
    }
}