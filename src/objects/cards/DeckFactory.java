package objects.cards;

import objects.Enums.*;

import java.util.Collections;

public class DeckFactory {
    public static Deck genFreshDeck() {
        Deck deck = new Deck();
        for (CARDTYPE ct :
                CARDTYPE.values()) {
            for (CARDVALUE cv :
                    CARDVALUE.values()) {
                Card card = new Card(ct, cv);
                deck.add(card);
            }
        }
        return deck;
    }

    public static Deck[] getPlayerDeck(int players, int numOfDecks) {
        Deck freshDeck = new Deck();
        for(int i=0;i<numOfDecks;i++){
            freshDeck.addAll(genFreshDeck());
        }
        Deck[] decks = new Deck[players];
        Collections.shuffle(freshDeck);
        for(int i=0;i<players;i++){
            decks[i]=new Deck();
        }
        int i = 0;
        while (!freshDeck.isEmpty()) {
            decks[i].add(freshDeck.remove(0));
            i++;
            if (i >= players) {
                i = 0;
            }
        }

        return decks;
    }
}
