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
import java.util.regex.Pattern;


public class Deck {

    private static final String CARDS_FILE = "Cards.csv";
    private static ArrayList<Card> cardsPool = new ArrayList<>(); /* TODO verify this */


    public static void loadCardPool(BoardManager boardManager, GameManager gameManager)
            throws IOException {
        // open csv file
        try {
            cardsPool = new ArrayList<>();
            List<String> lines = Files.readAllLines(Paths.get(CARDS_FILE));
            for (String line : lines) {
                // Process each line
                Pattern pattern = Pattern.compile(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                String[] tokens = pattern.split(line);
                for (int i = 0; i < tokens.length; i++) {
                    tokens[i] = tokens[i].trim().replaceAll("^\"|\"$", "");
                }
                int id = Integer.parseInt(tokens[0]);
                int freq = Integer.parseInt(tokens[1]);
                for (int i = 0; i < freq; i++) {
                    Card temp = switch (id) {
                        case 0 ->
                                new Standard(tokens[2], tokens[3], Integer.parseInt(tokens[4]), Suit.valueOf(tokens[5]), boardManager, gameManager);
                        case 1 -> new Ace(tokens[2], tokens[3], Suit.valueOf(tokens[5]), boardManager, gameManager);
                        case 4 -> new Four(tokens[2], tokens[3], Suit.valueOf(tokens[5]), boardManager, gameManager);
                        case 5 -> new Five(tokens[2], tokens[3], Suit.valueOf(tokens[5]), boardManager, gameManager);
                        case 7 -> new Seven(tokens[2], tokens[3], Suit.valueOf(tokens[5]), boardManager, gameManager);
                        case 10 -> new Ten(tokens[2], tokens[3], Suit.valueOf(tokens[5]), boardManager, gameManager);
                        case 11 -> new Jack(tokens[2], tokens[3], Suit.valueOf(tokens[5]), boardManager, gameManager);
                        case 12 -> new Queen(tokens[2], tokens[3], Suit.valueOf(tokens[5]), boardManager, gameManager);
                        case 13 -> new King(tokens[2], tokens[3], Suit.valueOf(tokens[5]), boardManager, gameManager);
                        case 14 -> new Burner(tokens[2], tokens[3], boardManager, gameManager);
                        case 15 -> new Saver(tokens[2], tokens[3], boardManager, gameManager);
                        default -> throw  new IOException("Failed to load the card pool from the specified file.");
                    };
                    cardsPool.add(temp);
                }
            }
        } catch (IOException e) {
            throw new IOException("Failed to load the card pool from the specified file.", e);
        }
    }

    public static void printCardName(Card card) {
        System.out.println(card.getName());
        System.out.println(card.getDescription());
        String name = card.getName();
        if (!name.equals("MarbleSaver") && !name.equals("MarbleBurner")) {
            System.out.println(((Standard) card).getSuit());
        }
        System.out.println("-----");
    }

    public static ArrayList<Card> drawCards() {
        ArrayList<Card> drawnCards = new ArrayList<>();
        Collections.shuffle(cardsPool);
        for (int i = 0; i < 4 && !cardsPool.isEmpty(); i++)
            drawnCards.add(cardsPool.removeFirst());

        return drawnCards;
    }

}
