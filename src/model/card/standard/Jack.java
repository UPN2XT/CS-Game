package model.card.standard;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.player.Marble;

import java.util.ArrayList;

/**
 * The Jack class represents a standard playing card with rank 11.
 * It extends the Standard card class and inherits its properties and behaviors.
 */
public class Jack extends Standard {
    /**
     * Constructs a new Jack card with the specified attributes.
     *
     * @param name         the name of the card
     * @param description  the description of the card
     * @param suit         the suit of the card (HEART, DIAMOND, SPADE, CLUB)
     * @param boardManager the board manager used by this card
     * @param gameManager  the game manager used by this card
     */
    public Jack(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 11, suit, boardManager, gameManager);
    }

    public boolean validateMarbleColours(ArrayList<Marble> marbles) {
        if (marbles.size() == 1)
            return marbles.get(0).getColour() == gameManager.getActivePlayerColour();
        if (marbles.get(0).getColour() == gameManager.getActivePlayerColour())
            return marbles.get(1).getColour() != gameManager.getActivePlayerColour();
        return marbles.get(1).getColour() == gameManager.getActivePlayerColour();
    }

    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
        return marbles.size() == 2 || marbles.size() == 1;
    }
    public void act(ArrayList<Marble> marbles) throws ActionException,
            InvalidMarbleException {
        basicValidate(marbles);
        if (marbles.size() == 2)
            boardManager.swap(marbles.get(0), marbles.get(1));
        else
            move(marbles, false);
    }
}