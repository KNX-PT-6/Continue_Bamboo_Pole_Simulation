package objects.players;

import objects.cards.Card;
import objects.cards.Deck;

public class Str_LazyRandom extends Brain{

    @Override
    protected Card think(Deck deck) {
        return deck.remove((int)(Math.random()*deck.size()));
    }
}
