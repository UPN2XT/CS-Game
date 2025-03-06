package model.player;

import model.Colour;
import model.card.Card;

import java.util.ArrayList;

/**
 * The Player class represents a player in the game.
 * It contains the player's name, colour, hand of cards, marbles, and selected card and marbles.
 */
public class Player {

    private final String name;
    private final Colour colour;
    private ArrayList<Card> hand;
    private ArrayList<Marble> marbles;
    private Card selectedCard;
    private final ArrayList<Marble> selectedMarbles;

    /**
     * Constructs a new Player with the specified name and colour.
     * Initializes the player's hand, marbles, and selected card and marbles.
     *
     * @param name   the name of the player
     * @param colour the colour associated with the player
     */
    public Player(String name, Colour colour) {
        this.name = name;
        this.colour = colour;
        this.hand = new ArrayList<>();
        this.marbles = new ArrayList<>();
        this.selectedCard = null;
        this.selectedMarbles = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            marbles.add(new Marble(colour));
    }

    /**
     * Gets the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the colour associated with the player.
     *
     * @return the colour of the player
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * Gets the hand of cards of the player.
     *
     * @return the hand of cards
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Gets the marbles of the player.
     *
     * @return the marbles
     */
    public ArrayList<Marble> getMarbles() {
        return marbles;
    }

    /**
     * Gets the selected card of the player.
     *
     * @return the selected card
     */
    public Card getSelectedCard() {
        return selectedCard;
    }

    /**
     * Sets the hand of cards of the player.
     *
     * @param hand the new hand of cards
     */
    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

}