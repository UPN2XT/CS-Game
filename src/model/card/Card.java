package model.card;

import engine.board.BoardManager;
import engine.GameManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.card.standard.Seven;
import model.player.Marble;

import java.util.ArrayList;

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

    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
        return marbles.size() == 1;
    }

    public boolean validateMarbleColours(ArrayList<Marble> marbles) {
        int s = marbles.size();
        if (s == 0)
            return true;
        else if (s == 1)
            return marbles.get(0).getColour() == gameManager.getActivePlayerColour();
        if (this instanceof Seven) {
            return marbles.get(0).getColour() == gameManager.getActivePlayerColour()
                    && marbles.get(1).getColour() == gameManager.getActivePlayerColour();
        }
        if (marbles.get(0).getColour() == gameManager.getActivePlayerColour())
            return marbles.get(1).getColour() != gameManager.getActivePlayerColour();
        return marbles.get(1).getColour() == gameManager.getActivePlayerColour();
    }

    public void basicValidate(ArrayList<Marble> marbles) throws ActionException,
            InvalidMarbleException {
        if (!validateMarbleSize(marbles)) {
            throw new InvalidMarbleException("Invalid marble size");
        }

        if (!validateMarbleColours(marbles)) {
            throw new InvalidMarbleException("Invalid marble colour");
        }
    }

    public abstract void act(ArrayList<Marble> marbles) throws ActionException,
            InvalidMarbleException;

}