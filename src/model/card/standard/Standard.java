package model.card.standard;

import model.card.Card;
import engine.GameManager;
import engine.board.BoardManager;

/**
 * The Standard class represents a standard playing card with a specific rank and suit.
 * It extends the Card class and inherits its properties and behaviors.
 */
public class Standard extends Card {
    private final int rank;
    private final Suit suit;

    /**
     * Constructs a new Standard card with the specified attributes.
     *
     * @param name         the name of the card
     * @param description  the description of the card
     * @param rank         the rank of the card
     * @param suit         the suit of the card (HEART, DIAMOND, SPADE, CLUB)
     * @param boardManager the board manager used by this card
     * @param gameManager  the game manager used by this card
     */
    public Standard(String name, String description, int rank, Suit suit, BoardManager boardManager,
                    GameManager gameManager) {
        super(name, description, boardManager, gameManager);
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Gets the rank of the card.
     *
     * @return the rank of the card
     */
    public int getRank() {
        return rank;
    }

    /**
     * Gets the suit of the card.
     *
     * @return the suit of the card
     */
    public Suit getSuit() {
        return suit;
    }
}