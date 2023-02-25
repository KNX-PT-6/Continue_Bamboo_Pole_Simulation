package objects.cards;

import static objects.Enums.*;

public class Card {
    private CARDTYPE cardtype;
    private CARDVALUE cardvalue;

    public Card(CARDTYPE type, CARDVALUE value){
        cardtype=type;
        cardvalue=value;
    }

    public boolean equals(Card obj) {
        return obj.getValue().equals(cardvalue);
    }

    public CARDVALUE getValue(){
        return cardvalue;
    }

    public String toString(){
        return cardtype.toString()+cardvalue.toString();
    }
}
