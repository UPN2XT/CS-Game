package model.card.wild;

import engine.GameManager;
import engine.board.BoardManager;
import model.card.Card;

/**
 * The Wild class represents an abstract wild playing card.
 * It extends the Card class and inherits its properties and behaviors.
 */
public abstract class Wild extends Card {
    /**
     * Constructs a new Wild card with the specified attributes.
     *
     * @param name         the name of the card
     * @param description  the description of the card
     * @param boardManager the board manager used by this card
     * @param gameManager  the game manager used by this card
     */
    public Wild(String name, String description, BoardManager boardManager, GameManager gameManager) {
        super(name, description, boardManager, gameManager);
    }
}