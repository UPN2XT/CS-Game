import engine.Game;
import engine.GameManager;
import model.card.Card;
import java.io.IOException;
import model.card.Deck;
import model.card.standard.Standard;

public class Main {
    public static void main(String[] args) throws IOException {
       Deck.loadCardPool(null, null);
        for (Card c: Deck.cardsPool)
            printCardName(c);
    }

    public static void printCardName(Card card) {
        System.out.println(card.getName());
        System.out.println(card.getDescription());
        System.out.println("-----");
        String name = card.getName();
        if (!name.equals("MarbleSaver") && !name.equals("MarbleBurner")) {
            System.out.println(((Standard) card).getSuit());
        }
    }
}