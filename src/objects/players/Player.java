package objects.players;

import objects.cards.Card;
import objects.cards.Deck;

import java.util.Collections;

public class Player {
    private Brain brain;
    private Deck playerDeck;
    private Deck scoreDeck;

    //Init
    public Player(Deck d) {
        playerDeck = d;
        scoreDeck = new Deck();
    }
    public Player(Brain b, Deck d) {
        this(d);
        brain = b;
    }

    public void setStrategy(Brain b){
        brain=b;
    }

    //Pick a card, if no card to pick, lost.
    public Card think() {
        if (playerDeck.isEmpty()) {
            if (scoreDeck.isEmpty()) {
                return null;
            }
            playerDeck = scoreDeck;
            scoreDeck = new Deck();
            Collections.shuffle(playerDeck);
        }
        Card card = brain.think(playerDeck);
        return card;
    }

    //When wins a deck, add it to scoreDeck
    public void score(Deck d){
        scoreDeck.addAll(d);
    }
}
