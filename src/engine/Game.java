package engine;

import engine.board.Board;
import engine.board.SafeZone;
import exception.*;
import model.Colour;
import model.card.Deck;
import model.player.CPU;
import model.player.Marble;
import model.player.Player;
import java.util.ArrayList;
import model.card.Card;
import java.io.IOException;
import java.util.Collections;
import java.util.Random;

/**
 * The Game class implements the GameManager interface and represents the main game logic.
 */
public class Game implements GameManager {
    private final Board board;
    private final ArrayList<Player> players;
    private final ArrayList<Card> firePit;
    private int currentPlayerIndex;
    private int turn;
    private Random rand = new Random();

    /**
     * Constructs a new Game instance.
     *
     * @param playerName the name of the human player
     * @throws IOException if there is an error loading the card pool
     */
    public Game(String playerName) throws IOException {
        // init vars
        ArrayList<Colour> colours = createColourList();
        firePit = new ArrayList<>();
        players = new ArrayList<>();
        currentPlayerIndex = 0;
        turn = 0;
        board = new Board(colours, this);
        Deck.loadCardPool(board, this);
        // create players
        players.add(new Player(playerName, colours.get(0)));
        for (int i = 1; i < 4; i++)
            players.add(new CPU("CPU " + i, colours.get(i), board));
        for (int i = 0; i < 4; i++)
            players.get(i).setHand(Deck.drawCards());
    }

    /**
     * Shuffles the colours and returns them in an ArrayList.
     *
     * @return an ArrayList of shuffled colours
     */
    private ArrayList<Colour> createColourList() {
        ArrayList<Colour> colours = new ArrayList<>();
        colours.add(Colour.RED);
        colours.add(Colour.BLUE);
        colours.add(Colour.GREEN);
        colours.add(Colour.YELLOW);
        Collections.shuffle(colours);
        return colours;
    }

    // getter methods

    /**
     * Gets the game board.
     *
     * @return the game board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Gets the list of players.
     *
     * @return an ArrayList of players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Gets the fire pit cards.
     *
     * @return an ArrayList of cards in the fire pit
     */
    public ArrayList<Card> getFirePit() {
        return firePit;
    }

    public void selectCard(Card card) throws InvalidCardException {
        players.get(currentPlayerIndex).selectCard(card);
    }

    public void selectMarble(Marble marble) throws InvalidMarbleException {
        players.get(currentPlayerIndex).selectMarble(marble);
    }

    public void deselectAll() {
        players.get(currentPlayerIndex).deselectAll();
    }

    public void editSplitDistance(int splitDistance) throws SplitOutOfRangeException {
        if (splitDistance < 1 || splitDistance > 6) {
            throw new SplitOutOfRangeException("Split distance out of range");
        }
        board.setSplitDistance(splitDistance);
    }

    public void sendHome(Marble marble) {
        Player p = null;
        for (Player player : players)
            if (player.getColour() == marble.getColour()) {
                p = player;
                break;
            }
        if (p != null)
            p.regainMarble(marble);
    }

    public void fieldMarble() throws CannotFieldException, IllegalDestroyException {
        Marble marble = players.get(currentPlayerIndex).getOneMarble();
        if (marble == null) {
            throw new CannotFieldException("No marbles to field");
        }
        board.sendToBase(marble);
        players.get(currentPlayerIndex).getMarbles().remove(marble);
    }

    public void discardCard(Colour colour) throws CannotDiscardException {
        Player p = null;
        for (Player player : players)
            if (player.getColour() == colour) {
                p = player;
                break;
            }
        if (p == null) return;
        if (p.getHand().isEmpty())
            throw new CannotDiscardException("No cards to discard");
        Card card = p.getHand().get(0);
        p.getHand().remove(card);
        firePit.add(card);
    }

    public void discardCard() throws CannotDiscardException {
        ArrayList<Player> pls = new ArrayList<>();
        for (int i = 0; i < players.size(); i++)
            if (players.get(i).getHand().isEmpty() && i != currentPlayerIndex)
                pls.add(players.get(i));
        if (pls.isEmpty()) {
            throw new CannotDiscardException("No cards to discard");
        }
        int i = rand.nextInt(pls.size());
        discardCard(pls.get(i).getColour());
    }

    public boolean canPlayTurn() {
        return players.get(currentPlayerIndex).getHand().size() == (turn);
    }

    public void endPlayerTurn() {
        Player p = players.get(currentPlayerIndex);
        p.getHand().remove(p.getSelectedCard());
        firePit.add(p.getSelectedCard());
        p.deselectAll();
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        if (currentPlayerIndex == 0) {
            turn = (turn + 1) % 4;
            if (turn == 0)
                for (Player player : players) {
                    if (Deck.getPoolSize() < 4) {
                        Deck.refillPool(firePit);
                        firePit.clear();
                    }
                    player.getHand().addAll(Deck.drawCards());
                }

        }

    }

    public void playPlayerTurn() throws GameException {
        players.get(currentPlayerIndex).play();
    }

    public Colour checkWin() {
        ArrayList<SafeZone> safeZones = board.getSafeZones();
        for (SafeZone s: safeZones)
            if (s.isFull())
                return s.getColour();
        return null;
    }

    public Colour getActivePlayerColour() {
        return players.get(currentPlayerIndex).getColour();
    }

    public Colour getNextPlayerColour() {
        int nextPlayerIndex = (currentPlayerIndex + 1) % players.size();
        return players.get(nextPlayerIndex).getColour();
    }
}