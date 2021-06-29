import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player{

    private ArrayList<Card> pplLeft;
    private ArrayList<Card> placesLeft;
    private ArrayList<Card> weaponLeft;
    private boolean removed = false;

    public ComputerPlayer(){
        pplLeft = new ArrayList<>();
        placesLeft = new ArrayList<>();
        weaponLeft = new ArrayList<>();
    }

    public void setUp(int numPlayers, int index, ArrayList<Card> ppl, ArrayList<Card> places, ArrayList<Card> weapons){
        super.setUp(numPlayers, index, ppl, places, weapons);
        pplLeft.addAll(ppl);
        placesLeft.addAll(places);
        weaponLeft.addAll(weapons);
    }

    // return a card which matches the current suggestion from a player
    public Card canAnswer(Guess g, IPlayer ip){
        ArrayList<Card> cards = checkGuess(g);
        if(cards.size() > 0){
            return cards.get(0);
        }

        return null;
    }

    public Guess getGuess() {
        if(!removed){
            removeCardsFromGuessing();      // removes cards which are dealt to the player from the guessing options
        }
        Card who, where, with;
        boolean isAccusation = false;
        Random rand = new Random();
        int i = rand.nextInt(pplLeft.size());
        who = pplLeft.get(i);               // returns a random card from the remaining cards
        i = rand.nextInt(placesLeft.size());
        where = placesLeft.get(i);          // returns a random card from the remaining cards
        i = rand.nextInt(weaponLeft.size());
        with = weaponLeft.get(i);           // returns a random card from the remaining cards

        // if there are only 3 cards left to guess from, then change the suggestion to an accusation
        if(pplLeft.size() == 1 && placesLeft.size() == 1 && weaponLeft.size() == 1){
            isAccusation = true;
            System.out.print("Player " + getIndex() + ": Accusation: ");
        }
        else{
            System.out.print("Player " + getIndex() + ": Suggestion: ");
        }
        return new Guess(who, where, with, isAccusation);
    }


    // if a player rejects current players suggestions by showing a card, then remove that card from the guessing options
    public void receiveInfo(IPlayer ip, Card c){
        if(c != null){
            if(pplLeft.contains(c)){
                pplLeft.remove(c);
            }else if (placesLeft.contains(c)){
                placesLeft.remove(c);
            }else{
                weaponLeft.remove(c);
            }
        }
    }


    // removes the cards which are dealt to the player from the guessing options
    private void removeCardsFromGuessing(){
        for (int i = 0; i < getCardsOnHand().size(); i++) {
            if(pplLeft.contains(getCardsOnHand().get(i))){
                pplLeft.remove(getCardsOnHand().get(i));
            }else if(placesLeft.contains(getCardsOnHand().get(i))){
                placesLeft.remove(getCardsOnHand().get(i));
            }else{
                weaponLeft.remove(getCardsOnHand().get(i));
            }
        }
        removed = true;
    }
}
