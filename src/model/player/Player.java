package model.player;

import model.Colour;
import model.card.Card;

import java.util.ArrayList;

public class Player {

    private final String name;
    private final Colour colour;
    private ArrayList<Card> hand;
    private ArrayList<Marble> marbles;
    private Card selectedCard;
    private final ArrayList<Marble> selectedMarbles;

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

    // setters and getters:
    public String getName() {
        return name;
    }
    public Colour getColour() {
        return colour;
    }
    public ArrayList<Card> getHand() {
        return hand;
    }
    public ArrayList<Marble> getMarbles() {
        return marbles;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    // setter functions:
    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

}
