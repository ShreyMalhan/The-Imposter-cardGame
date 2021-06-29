// CLASS: Player
//
// Author: Shrey Malhan, 7824571
//
// REMARKS: Abstract super class for Human and Computer player
//
//-----------------------------------------

import java.util.ArrayList;

public abstract class Player implements IPlayer{
    private int index, numPlayers;
    private ArrayList<Card> ppl, places, weapons, cardsOnHand;

    Player(){
        ppl = new ArrayList<>();
        places = new ArrayList<>();
        weapons = new ArrayList<>();
        cardsOnHand = new ArrayList<>();
    }

    public void setUp(int numPlayers, int index, ArrayList<Card> ppl,
                               ArrayList<Card> places, ArrayList<Card> weapons){
        this.numPlayers = numPlayers;
        this.index = index;
        this.ppl = ppl;
        this.places = places;
        this.weapons = weapons;
    }

    public void setCard (Card c){
        cardsOnHand.add(c);
    }

    public int getIndex(){
        return index;
    }

    public abstract Card canAnswer(Guess g, IPlayer ip);

    public abstract Guess getGuess();

    public abstract void receiveInfo(IPlayer ip, Card c);


    //-----------------Protected methods for subclasses to use-------------------

    protected ArrayList<Card> checkGuess(Guess g){ // returns the list of matching cards from cards on hand
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < cardsOnHand.size(); i++) {
            if(cardsOnHand.get(i).equals(g.getWho()) || cardsOnHand.get(i).equals(g.getWith()) ||
                    cardsOnHand.get(i).equals(g.getWhere())){
                cards.add(cardsOnHand.get(i));
            }
        }
        return cards;
    }

    protected ArrayList<Card> getPpl(){
        return ppl;
    }

    protected ArrayList<Card> getPlaces(){
        return places;
    }

    protected ArrayList<Card> getWeapon(){
        return weapons;
    }

    protected ArrayList<Card> getCardsOnHand(){
        return cardsOnHand;
    }

}
