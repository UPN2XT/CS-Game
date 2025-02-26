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

public class Game implements GameManager {
    private final Board board;
    private final ArrayList<Player> players;
    private final ArrayList<Card> firePit;
    private int currentPlayerIndex;
    private int turn;

    public Game(String playerName) throws IOException {
        ArrayList<Colour> colours = createColourList();
        firePit = new ArrayList<>();
        players = new ArrayList<>();
        currentPlayerIndex = 0;
        turn = 0;
        board = new Board(colours, this);
        Deck.loadCardPool(board, this);
        players.add(new Player(playerName, colours.getFirst()));
        for (int i = 1; i < 4; i++)
            players.add(new CPU("CPU " + i, colours.get(i), board));
        for (Player player : players)
            player.setHand(Deck.drawCards());
    }

    private ArrayList<Colour> createColourList() {
        ArrayList<Colour> colours = new ArrayList<>();
        colours.add(Colour.RED);
        colours.add(Colour.BLUE);
        colours.add(Colour.GREEN);
        colours.add(Colour.YELLOW);
        Collections.shuffle(colours);
        return colours;
    }

    public Board getBoard() {
        return board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Card> getFirePit() {
        return firePit;
    }

}
