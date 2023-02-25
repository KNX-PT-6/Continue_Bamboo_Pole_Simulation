package objects.game;

import objects.cards.Card;
import objects.cards.Deck;

public class GameDeck {
    private Deck gameDeck;
    public GameDeck(){
        gameDeck = new Deck();
    }

    public Deck add(Card card){
        int index_1 = -1;
        //check if there exist a card with same number
        for (Card c:
                gameDeck) {
            if(c.equals(card)){
                index_1= gameDeck.indexOf(c);
            }
        }
        gameDeck.add(card);
        //review result from previous check
        if(index_1!=-1){
            //if wins a deck, remove deck from gameDeck and return them
            Deck d = new Deck();
            while (gameDeck.size()>index_1){
                d.add(gameDeck.remove(index_1));
            }
            return d;
        }
        return null;
    }

    public Deck getGameDeck(){
        return gameDeck;
    }

    public void printDeck(){
        for (Card c:
             gameDeck) {
            System.out.print(c.toString()+"    ");
        }
        System.out.println();
    }
}
