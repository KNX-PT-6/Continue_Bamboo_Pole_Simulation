package objects.game;

import objects.cards.Card;
import objects.cards.Deck;
import objects.cards.DeckFactory;
import objects.players.Brain;
import objects.players.Player;

public class Gameboard {
    private Player[] players;
    private GameDeck gameDeck;

    private boolean runned;

    public Gameboard() {
        this(2);
    }

    public Gameboard(int num_of_players) {
        players = new Player[num_of_players];
        Deck[] decks = DeckFactory.getPlayerDeck(num_of_players);
        for (int i = 0; i < num_of_players; i++) {
            players[i] = new Player(decks[i]);
        }
        gameDeck = new GameDeck();
        runned = false;
    }

    public void AssignStrategy(int index, Brain Strategy) {
        players[index].setStrategy(Strategy);
    }

    public int gameRun() {
        if (runned) {
            System.out.println("This gameboard is already runned, please check code...");
            return -100;
        }
        runned = true;
        int current = 0;
        int playerNum = players.length;
//        int rounds = 0;
//        int cards = 0;

        while (playerNum > 1) {
//            rounds++;
            for (int i = 0; i < players.length; i++) {
                if (null == players[i]) {
                    continue;
                }
//                System.out.println("Now the " + (i + 1) + "st player is thinking...");
                Card c = players[i].think();
                if (null == c) {
                    players[i] = null;
//                    System.out.println("No card to play, " + (i + 1) + "st player lost...");
                    playerNum--;
                    continue;
                }
                current = i;
                Deck d = gameDeck.add(c);
//                cards++;
//                System.out.println("The " + (i + 1) + "st player used Card " + c.toString());
//                System.out.println("This is the " + cards + "st cards placed.");
                if (null != d) {
                    players[i].score(d);
//                    System.out.println("The " + (i + 1) + "st player won some extra cards!");
                    i--;
                }
//                System.out.println("Current game deck is:");
//                gameDeck.printDeck();
//                System.out.println("This is the " + rounds + " rounds played.\n");
            }
        }
//        System.out.println("The " + (current + 1) + "st player won the game!");
        return current;
    }
}
