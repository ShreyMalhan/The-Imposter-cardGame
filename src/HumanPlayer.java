// CLASS: HumanPlayer
//
// Author: Shrey Malhan, 7824571
//
// REMARKS: Class for Human Player
//
//-----------------------------------------

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player {

    @Override
    public void setCard(Card c) {
        super.setCard(c);
        System.out.println("You received the card " + c.getValue() );
    }

    public Card canAnswer(Guess g, IPlayer ip){
        ArrayList<Card> cards = checkGuess(g);

        askedSuggestion(g,ip); // private method on the bottom of the class for the print statement.

        if(cards.size() == 1){
            System.out.println(", you have only one card, " + cards.get(0).getValue() + ", showed it to the player");
            return cards.get(0);
        }
        else if(cards.size() > 1){
            System.out.println(", which do you want to show?");
            printArrayList(cards);
            return getCardFromUser(cards); // private method for the user input of the cards
        }
        else{
            System.out.println(", but you cannot answer!");
        }
        return null;
    }

    public Guess getGuess(){
        Card who, where, with;
        boolean isAccusation = false, done = false;
        String line;
        Scanner input = new Scanner(System.in);
        System.out.println("It is your turn.");

        System.out.println("Which person do you want to suggest?");
        printArrayList(getPpl());               // private method for printing arrays
        who = getCardFromUser(getPpl());        // private method for getting user input

        System.out.println("Which location do you want to suggest?");
        printArrayList(getPlaces());            // private method for printing arrays
        where = getCardFromUser(getPlaces());   // private method for getting user input

        System.out.println("Which weapon do you want to suggest?");
        printArrayList(getWeapon());            // private method for printing arrays
        with = getCardFromUser(getWeapon());    // private method for getting user input

        // user input for accusation
        do {
            System.out.println("Is this an accusation (Y/N)?");
            line = input.nextLine();
            if (line.equals("n") || line.equals("N")) {
                System.out.print("Player " + getIndex() + ": Suggestion: ");
                done = true;
            } else if (line.equals("y") || line.equals("Y")) {
                isAccusation = true;
                System.out.print("Player " + getIndex() + ": Accusation: ");
                done = true;
            } else {
                System.out.println("It is not a valid response. Try Again!");
            }
        }while(!done);
        return new Guess(who, where, with, isAccusation);
    }


    // prints out if any player refuted current players suggestions
    public void receiveInfo(IPlayer ip, Card c){
        if(c != null){
            System.out.println("Player " + ip.getIndex() + " refuted your suggestion by showing you " + c.getValue() + ".");
        }else{
            System.out.println("No one could refute your suggestion.");
        }
    }


    //------------private helper methods----------------//


    private void askedSuggestion(Guess g, IPlayer ip){
        System.out.print("Player " + ip.getIndex() + " asked you about suggestion: "  + g.getWho().getValue() + " in " + g.getWhere().getValue()
                + " with " + g.getWith().getValue());
    }

    private Card getCardFromUser(ArrayList<Card> cards){
        int number;
        boolean done = false;
        Scanner input = new Scanner(System.in);
        do{
            try{
                number = input.nextInt();
                if(number >= 0 && number < cards.size()){
                    return new Card (cards.get(number).getType(), cards.get(number).getValue());
                }else{
                    System.out.println("That is not a valid option. Try Again!");
                }
            }catch (InputMismatchException e){
                System.out.println("That is not a valid input. Try again!");
                input.next();
            }

        }while(!done);

        return null;
    }

    private void printArrayList(ArrayList<Card> cards){
        for (int i = 0; i < cards.size(); i++) {
            System.out.println(i + ": " + cards.get(i).getValue());
        }
    }
}