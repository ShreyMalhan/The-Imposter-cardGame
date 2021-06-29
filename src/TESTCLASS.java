import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TESTCLASS {
    ArrayList<Card> ppl = new ArrayList<>();
    ArrayList<Card> place = new ArrayList<>();
    ArrayList<Card> weapon = new ArrayList<>();
    ArrayList<IPlayer> player = new ArrayList<>();

    @BeforeEach
    public void setUp() {           // Set up the cards for the tests
        ppl.add(new Card("Suspect", "Prof. Boyer"));
        ppl.add(new Card("Suspect", "Prof. Miller"));
        ppl.add(new Card("Suspect", "Prof. Wang"));

        place.add(new Card("Location", "Comp 2150"));
        place.add(new Card("Location", "Comp 2160"));
        place.add(new Card("Location", "Comp 2140"));

        weapon.add(new Card("Weapon", "Mid Term"));
        weapon.add(new Card("Weapon", "Final Exam"));
        weapon.add(new Card("Weapon", "Lab"));

        player.add(new ComputerPlayer());
        player.add(new ComputerPlayer());
        player.add(new HumanPlayer());
        for (int i = 0; i < player.size(); i++) {
            player.get(i).setUp(3, i, ppl, place, weapon);
        }
    }

    // If a computer player has no cards, then canAnswer should return null.
    @Test
    public void test1() {
        //setUp();
        Guess guess = new Guess(ppl.get(0), place.get(0), weapon.get(0), false);

        assertNull(player.get(0).canAnswer(guess, player.get(1)));
    }


    // If a computer player has exactly one card from a guess, canAnswer should return that card
    @Test
    public void test2() {
        //setUp();
        Guess guess = new Guess(ppl.get(0), place.get(0), weapon.get(0), false);
        player.get(1).setCard(ppl.get(0));

        assertEquals(guess.getWho(), player.get(1).canAnswer(guess, player.get(0)));
    }


    // If a computer player has more than one card from a guess, canAnswer should return one of the cards.
    @Test
    public void test3() {
        //setUp();
        Guess guess = new Guess(ppl.get(0), place.get(0), weapon.get(0), false);
        player.get(1).setCard(ppl.get(0));
        player.get(1).setCard(place.get(0));

        assertEquals(guess.getWho(), player.get(1).canAnswer(guess, player.get(0)));
    }

    // An initial guess from a computer player must consist of cards it does not have.
    @Test
    public void test4(){
        //setUp();
        player.get(0).setCard(ppl.get(0));
        player.get(0).setCard(place.get(0));
        player.get(0).setCard(weapon.get(0));

        Guess guess = player.get(0).getGuess();
        System.out.println();
        assertNotEquals(guess.getWho(), ppl.get(0));
        assertNotEquals(guess.getWhere(), place.get(0));
        assertNotEquals(guess.getWith(), weapon.get(0));
    }


    // If a computer player is given all but three cards from the set of cards,
    // a call to getGuess should return the correct accusation (not a suggestion).
    @Test
    public void test5(){
        //setUp();
        player.get(0).setCard(ppl.get(0));
        player.get(0).setCard(place.get(0));
        player.get(0).setCard(weapon.get(0));
        player.get(0).setCard(ppl.get(1));
        player.get(0).setCard(place.get(1));
        player.get(0).setCard(weapon.get(1));

        Guess guess = player.get(0).getGuess();
        System.out.println();
        assertTrue(guess.isAccusation());
    }


    // If a computer player is given all but four cards from the set of cards, a call to getGuess should not return an accusation. However,
    // if receiveInfo is called with one of the four cards, then after that, a second call to getGuess should return the correct accusation.
    @Test
    public void test6(){
        //setUp();
        player.get(0).setCard(ppl.get(0));
        player.get(0).setCard(place.get(0));
        player.get(0).setCard(weapon.get(0));
        player.get(0).setCard(ppl.get(1));
        player.get(0).setCard(place.get(1));

        Guess guess = player.get(0).getGuess();

        assertFalse(guess.isAccusation());

        System.out.println();
        player.get(0).receiveInfo(player.get(1), weapon.get(1));

        guess = player.get(0).getGuess();

        assertTrue(guess.isAccusation());
    }


    // If a human player is given some cards, and then canAnswer is called with a guess that includes one (or more)
    // of the cards the player has, the method must return one of those cards (that is, the human player cannot give
    // a card that they do not have in their hand – this will be achieved through input validation in your implementation).
    @Test
    public void test7(){
        //setUp();
        System.out.println();
        System.out.println("-------------------------TEST7---------------------------");
        player.get(2).setCard(ppl.get(0));
        player.get(2).setCard(place.get(1));
        player.get(2).setCard(weapon.get(1));

        Guess guess = new Guess(ppl.get(0), place.get(0), weapon.get(0), false);

        Card card = player.get(2).canAnswer(guess, player.get(0));

        assertEquals(card, ppl.get(0));
    }

}
