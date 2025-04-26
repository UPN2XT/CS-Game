package model.card.standard;

import engine.Game;
import exception.ActionException;
import exception.IllegalDestroyException;
import exception.IllegalMovementException;
import exception.InvalidMarbleException;
import model.card.Card;
import engine.GameManager;
import engine.board.BoardManager;
import model.player.Marble;
import model.player.Player;

import java.util.ArrayList;

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

    protected void move(ArrayList<Marble> marbles, boolean des) throws ActionException {
        boardManager.moveBy(marbles.get(0), rank == 4? -4: rank, des);
    }

    protected void customActA(ArrayList<Marble> marbles, boolean des) throws ActionException,
            InvalidMarbleException {
        ArrayList<Player> players = (((Game)gameManager).getPlayers());
        if (marbles.isEmpty()) gameManager.fieldMarble();
        else move(marbles, des);
    }

    protected boolean validateMarbleSizeMultiAction(ArrayList<Marble> marbles, int a0, int a1) {
        return marbles.size() == a1 || marbles.size() == a0;
    }

    public void act(ArrayList<Marble> marbles) throws ActionException,
            InvalidMarbleException {
       move(marbles, false);
    }
}