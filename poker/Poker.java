package poker;
import java.util.*;

public interface Poker {
    // Stores each cards with it associate values.
    HashMap<String, Integer> cards = new HashMap<>();
    // Stores how many cards are remaining.
    HashMap<String, Integer> cardsCount = new HashMap<>();
    // Cards.
    String[] letterCards = new String[]{"2", "3", "4", "5", "6", "7", "8", "9",
                                    "10", "J", "Q", "K", "A"};

    public void initCard();

    public void play();



}
