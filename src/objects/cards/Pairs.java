package objects.cards;

public class Pairs {
    private Card up;
    private Card down;

    public Pairs(Card c1, Card c2) {
        up = c1;
        down = c2;
    }

    public Card getUp(){
        return up;
    }

    public Card getDown(){
        return down;
    }
}
