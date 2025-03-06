package model.card;

import engine.board.BoardManager;
import engine.GameManager;

/**
 * The Card class represents an abstract playing card.
 * It contains common properties and methods for all types of cards.
 */
public abstract class Card {
    private final String name;
    private final String description;
    protected BoardManager boardManager;
    protected GameManager gameManager;

    /**
     * Constructs a new Card with the specified attributes.
     *
     * @param name         the name of the card
     * @param description  the description of the card
     * @param boardManager the board manager used by this card
     * @param gameManager  the game manager used by this card
     */
    public Card(String name, String description, BoardManager boardManager, GameManager gameManager) {
        this.name = name;
        this.description = description;
        this.boardManager = boardManager;
        this.gameManager = gameManager;
    }

    /**
     * Gets the name of the card.
     *
     * @return the name of the card
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the card.
     *
     * @return the description of the card
     */
    public String getDescription() {
        return description;
    }
}