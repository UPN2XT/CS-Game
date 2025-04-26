package model.card.standard;

import engine.Game;
import engine.GameManager;
import engine.board.Board;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.player.Marble;
import model.player.Player;

import java.util.ArrayList;

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

    public void act(ArrayList<Marble> marbles) throws ActionException,
            InvalidMarbleException {
        customActA(marbles, false);
    }

    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
        return validateMarbleSizeMultiAction(marbles,0,1);
    }



}