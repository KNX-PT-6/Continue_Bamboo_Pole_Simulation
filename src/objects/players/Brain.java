package objects.players;

import objects.cards.Card;
import objects.cards.Deck;

public abstract class Brain {
    protected abstract Card think(Deck deck);
}
