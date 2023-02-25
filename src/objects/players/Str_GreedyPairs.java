package objects.players;

import objects.Enums.PAIRPROCESS;
import objects.cards.Card;
import objects.cards.Deck;
import objects.cards.Pairs;

import java.util.ArrayList;

public class Str_GreedyPairs extends Brain {

    private ArrayList<Pairs> pairs = new ArrayList<>();
    private ArrayList<Card> singles = new ArrayList<>();
    private PAIRPROCESS status = PAIRPROCESS.DOWN_OUT;
    private int cardCount = 0;
    private final int greedyGoal;

    public Str_GreedyPairs() {
        this(1);
    }

    public Str_GreedyPairs(int greedy) {
        greedyGoal = greedy;
    }

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
        } else {
            //Pairs are not empty, play with pairs
            if (status == PAIRPROCESS.DOWN_OUT) {
                //The last card is the last card from a pair.
                Card c = pairs.get(0).getUp();
                status = PAIRPROCESS.UP_OUT;
                cardCount = 0;
                return deck.remove(deck.indexOf(c));
            } else if (status == PAIRPROCESS.UP_OUT) {
                //The last card is the first card from a pair.
                if (cardCount < greedyGoal) {
                    //if we're in the "greedy period"
                    if (singles.isEmpty() && pairs.size() > 1) {
                        //if we run out of singles but still got pairs, we split one pair up.
                        Pairs p = pairs.remove(1);
                        singles.add(p.getUp());
                        singles.add(p.getDown());
                    }
                    //we draw a card from singles if we still have single cards
                    if (!singles.isEmpty()) {
                        cardCount++;
                        Card c = singles.remove(0);
                        return deck.remove(deck.indexOf(c));
                    }
                }
                //If the program ever runs to here, we either have no other cards to play,
                //or should put our last cards from a pair on.
                //Give the last card from a pair, remove it from pairs.
                Card c = pairs.get(0).getDown();
                pairs.remove(0);
                status = PAIRPROCESS.UP_OUT;
                return deck.remove(deck.indexOf(c));
            }
        }
        return null;
    }
}
