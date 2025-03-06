package engine;

import engine.board.Board;
import model.Colour;
import model.card.Deck;
import model.player.CPU;
import model.player.Player;
import java.util.ArrayList;
import model.card.Card;
import java.io.IOException;
import java.util.Collections;

/**
 * The Game class implements the GameManager interface and represents the main game logic.
 */
public class Game implements GameManager {
    private final Board board;
    private final ArrayList<Player> players;
    private final ArrayList<Card> firePit;
    private int currentPlayerIndex;
    private int turn;

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
}