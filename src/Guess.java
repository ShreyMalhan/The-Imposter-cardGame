// CLASS: Guess
//
// Author: Shrey Malhan, 7824571
//
// REMARKS: Class for Guesses object
//
//-----------------------------------------

public class Guess {
    private Card who, where, with;
    private boolean isAnAccusation;

    Guess(Card who, Card where, Card with, boolean isAnAccusation){
        this.who = who;
        this.where = where;
        this.with = with;
        this.isAnAccusation = isAnAccusation;
    }

    public Card getWho() {
        return who;
    }

    public Card getWhere() {
        return where;
    }

    public Card getWith() {
        return with;
    }

    public void print(){
        System.out.println(who.getValue() + " in " + where.getValue() + " with " + with.getValue());
    }

    public boolean isAccusation(){
        return isAnAccusation;
    }

}
