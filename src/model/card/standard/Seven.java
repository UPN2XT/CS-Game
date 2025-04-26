package model.card.standard;

import engine.GameManager;
import engine.board.Board;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.player.Marble;

import java.util.ArrayList;

/**
 * The Seven class represents a standard playing card with rank 7.
 * It extends the Standard card class and inherits its properties and behaviors.
 */
public class Seven extends Standard {
    /**
     * Constructs a new Seven card with the specified attributes.
     *
     * @param name         the name of the card
     * @param description  the description of the card
     * @param suit         the suit of the card (HEART, DIAMOND, SPADE, CLUB)
     * @param boardManager the board manager used by this card
     * @param gameManager  the game manager used by this card
     */
    public Seven(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 7, suit, boardManager, gameManager);
    }


    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
        return validateMarbleSizeMultiAction(marbles, 1, 2);
    }
    public void act(ArrayList<Marble> marbles) throws ActionException,
            InvalidMarbleException {
        if (marbles.size() == 1) {
            move(marbles, false);
            return;
        }
        boardManager.moveBy(marbles.get(0), boardManager.getSplitDistance(), false);
        boardManager.moveBy(marbles.get(1), 7 - boardManager.getSplitDistance(), false);
    }
}