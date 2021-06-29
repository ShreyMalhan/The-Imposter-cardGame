import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Model {
    private int numPlayers;
    private ArrayList<Card> ppl, places, weapon;
    private ArrayList<Card> allCards = new ArrayList<>();
    private ArrayList<Card> answer = new ArrayList<>();
    private ArrayList<IPlayer> players, cantWin = new ArrayList<>();
    private boolean wins = false;

    public Model(int numPlayers, ArrayList<Card> ppl, ArrayList<Card> place, ArrayList<Card> weapon, ArrayList<IPlayer> players){
        this.numPlayers = numPlayers;
        this.ppl = ppl;
        this.places = place;
        this.weapon = weapon;
        this.players = players;
        allCards.addAll(this.ppl);      // add all the cards to the allCards arrayList
        allCards.addAll(this.places);
        allCards.addAll(this.weapon);
    }

    // Runs the game
    public void run(){

        System.out.println("Setting up players..");
        setUp();                // set up the players
        chooseAnswer();         // choose the answer of the game

        System.out.println("Here are the names of all the Suspects:");
        printList(ppl);         // prints out the arrayList

        System.out.println("Here are all the Locations:");
        printList(places);      // prints out the arrayList

        System.out.println("Here are all the Weapons:");
        printList(weapon);      // prints out the arrayList

        System.out.println("Dealing cards..");
        dealCards();            // distributes the remaining cards to the players

        // starts the game
        System.out.println("Playing...");
        playGame();
    }

    // --------------------------------Private Methods--------------------------------//

    private void chooseAnswer(){
        Random rand = new Random();
        int i = rand.nextInt(ppl.size());
        answer.add(ppl.get(i));

        i = rand.nextInt(places.size());
        answer.add(places.get(i));

        i = rand.nextInt(weapon.size());
        answer.add(weapon.get(i));
    }

    private void dealCards(){
        for (int i = 0; i < answer.size(); i++) {
            allCards.remove(answer.get(i));
        }

        Collections.shuffle(allCards);

        for (int i = 0; i < allCards.size(); i++) {
            players.get(i%players.size()).setCard(allCards.get(i));
        }
    }

    private void setUp(){
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setUp(numPlayers, i, ppl, places, weapon);
        }
    }

    private Guess getGuess(int i){
        return players.get(i % players.size()).getGuess();
    }


    private void printList(ArrayList<Card> c){
        for (int i = 0; i < c.size()-1; i++) {
            System.out.print(c.get(i).getValue() + ", ");
        }
        System.out.println(c.get(c.size()-1).getValue());
        System.out.println();
    }

    private void playGame(){
        int i = 0, activePlayer, turn;
        Guess guess;
        Card c = null;

        do{
            if(!cantWin.contains(players.get(i%players.size()))){       //checks if the player has made any bad accusations in the past
                guess = getGuess(i);        // gets the guess from the active player
                guess.print();
                if(guess.isAccusation()){   // if the guess is an accusation
                    if(guess.getWho().equals(answer.get(0)) && guess.getWhere().equals(answer.get(1))  && guess.getWith().equals(answer.get(2))){
                        System.out.println("Player " + i%players.size()+" won the game!");
                        System.out.println("Game Over!");
                        wins = true;    // wins if the accusation is correct
                    }else {
                        cantWin.add(players.get(i % players.size()));   // if accusation is wrong then the player is removed from the game
                        System.out.println("Player " + i % players.size()+" made a bad accusation and was removed from the game!");
                    }
                }else{
                    activePlayer = i % players.size();
                    turn = i+1;
                    // go around the table and check if any player answered about the suggestion
                    while(activePlayer != turn % players.size() && c == null){ //
                        System.out.println("Asking player " + turn % players.size());
                        c = players.get(turn % players.size()).canAnswer(guess, players.get(activePlayer));
                        if(c != null){

                            System.out.println("Player " + turn % players.size() +" answered.");
                        }
                        players.get(activePlayer).receiveInfo(players.get(turn % players.size()), c);
                        turn++;     // goes to the next player to check if the the next player has a card from the current suggestion
                    }
                }
            }
            i++;        // goes to the next player to ask for a suggestion
            c = null;
        }while(!wins);  // repeat until someone wins
    }

}
