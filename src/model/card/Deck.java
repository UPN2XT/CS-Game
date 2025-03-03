package model.card;

import model.card.standard.*;
import model.card.wild.*;
import java.util.ArrayList;
import java.io.IOException;
import engine.GameManager;
import engine.board.BoardManager;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import model.card.standard.Suit;
import java.util.Collections;

public class Deck {

    private static final String CARDS_FILE = "Cards.csv";
    public static ArrayList<Card> cardsPool = new ArrayList<>(); /* TODO verify this */

    public static void loadCardPool(BoardManager boardManager, GameManager gameManager)
            throws IOException {
        // open csv file
        try {
            List<String> lines = Files.readAllLines(Paths.get(CARDS_FILE));
            for (String line : lines) {
                // Process each line
                String[] tokens = line.split(",");
                for (int i = 0; i < tokens.length; i++)
                    tokens[i] = tokens[i].trim();
                int id = Integer.parseInt(tokens[0]);
                int freq = Integer.parseInt(tokens[1]);
                switch (id) {
                    case 0:
                        for (int i = 0; i < freq; i++)
                            cardsPool.add(new Standard(tokens[2], tokens[3], Integer.parseInt(tokens[4]),Suit.valueOf(tokens[5]), boardManager, gameManager));
                        break;
                    case 1:
                        for (int i = 0; i < freq; i++)
                            cardsPool.add(new Ace(tokens[2], tokens[3], Suit.valueOf(tokens[5]), boardManager, gameManager));
                        break;
                    case 4:
                        for (int i = 0; i < freq; i++)
                            cardsPool.add(new Four(tokens[2], tokens[3], Suit.valueOf(tokens[5]), boardManager, gameManager));
                        break;
                    case 5:
                        for (int i = 0; i < freq; i++)
                            cardsPool.add(new Five(tokens[2], tokens[3], Suit.valueOf(tokens[5]), boardManager, gameManager));
                        break;
                    case 7:
                        for (int i = 0; i < freq; i++)
                            cardsPool.add(new Seven(tokens[2], tokens[3], Suit.valueOf(tokens[5]), boardManager, gameManager));
                        break;
                    case 10:
                        for (int i = 0; i < freq; i++)
                            cardsPool.add(new Ten(tokens[2], tokens[3], Suit.valueOf(tokens[5]), boardManager, gameManager));
                        break;
                    case 11:
                        for (int i = 0; i < freq; i++)
                            cardsPool.add(new Jack(tokens[2], tokens[3], Suit.valueOf(tokens[5]), boardManager, gameManager));
                        break;
                    case 12:
                        for (int i = 0; i < freq; i++)
                            cardsPool.add(new Queen(tokens[2], tokens[3], Suit.valueOf(tokens[5]), boardManager, gameManager));
                        break;
                    case 13:
                        for (int i = 0; i < freq; i++)
                            cardsPool.add(new King(tokens[2], tokens[3], Suit.valueOf(tokens[5]), boardManager, gameManager));
                        break;
                    case 14:
                        for (int i = 0; i < freq; i++)
                            cardsPool.add(new Burner(tokens[2], tokens[3], boardManager, gameManager));
                        break;
                    case 15:
                        for (int i = 0; i < freq; i++)
                            cardsPool.add(new Saver(tokens[2], tokens[3], boardManager, gameManager));
                        break;
                }


            }
        } catch (IOException e) {
            throw new IOException("Failed to load the card pool from the specified file.", e);
        }
    }

    public static ArrayList<Card> drawCards() {
        ArrayList<Card> drawnCards = new ArrayList<>();
        Collections.shuffle(cardsPool);
        for (int i = 0; i < 4; i++)
            drawnCards.add(cardsPool.removeFirst());
        return drawnCards;
    }

}
