package objects.players;

import objects.cards.Card;
import objects.cards.Deck;
import objects.cards.Pairs;
import objects.Enums.*;

import java.util.ArrayList;

public class Str_Pairs extends Brain {

    private ArrayList<Pairs> pairs = new ArrayList<>();
    private ArrayList<Card> singles = new ArrayList<>();
    private PAIRPROCESS status = PAIRPROCESS.DOWN_OUT;

    @Override
    protected Card think(Deck deck) {
        if (pairs.isEmpty()) {
            if (singles.isEmpty()) {
                //Both are empty, the deck is fresh
                //Initialize pairs and singles object
                for (Card c :
                        deck) {
                    singles.add(c);
                }
                int k = singles.size();
                for (int i = 0; i < k - 1; i++) {
                    for (int j = i + 1; j < k; j++) {
                        if (singles.get(i).equals(singles.get(j))) {
                            Card up = singles.remove(j);
                            Card down = singles.remove(i);
                            k -= 2;
                            pairs.add(new Pairs(up, down));
                            break;
                        }
                    }
                }
                return think(deck);
            } else {
                //Only pairs is empty, we ran out of pairs
                return deck.remove(deck.indexOf(singles.remove(0)));
            }
        }
        else {
            //Pairs are not empty, play with pairs
            if (status == PAIRPROCESS.DOWN_OUT) {
                //The last card is the last card from a pair.
                Card c = pairs.get(0).getUp();
                status = PAIRPROCESS.UP_OUT;
                return deck.remove(deck.indexOf(c));
            } else if (status == PAIRPROCESS.UP_OUT) {
                //The last card is the first card from a pair.
                //Give the last card from a pair, remove it from pairs.
                Card c = pairs.get(0).getDown();
                pairs.remove(0);
                status = PAIRPROCESS.DOWN_OUT;
                return deck.remove(deck.indexOf(c));
            }
        }
        return null;
    }
}
