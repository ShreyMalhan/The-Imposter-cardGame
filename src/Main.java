import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        ArrayList<Card> ppl = new ArrayList<>();
        ArrayList<Card> place = new ArrayList<>();
        ArrayList<Card> weapon = new ArrayList<>();
        ArrayList<IPlayer> playerList = new ArrayList<>();


        Scanner input = new Scanner(System.in);
        int num = 0;
        boolean done = false;


        // Number of computer playes
        do{
            try {
                System.out.println("How many computer opponents would you like to play against?");
                num = input.nextInt();
                if(num > 0){
                    done = true;
                }else {
                    System.out.println("Number should be greater than 0");
                }
            }catch (InputMismatchException e){
                System.out.println("That is not a valid input. Try again!");
                input.next();
            }

        }while (!done);

        //creates desired number of computer player
        for (int i = 0; i < num; i++) {
            playerList.add(new ComputerPlayer());
        }

        playerList.add(new HumanPlayer()); // to add the Human player to the list

        // Creates Cards for the game
        ppl.add(new Card("Suspect", "Candace"));
        ppl.add(new Card("Suspect", "Hamilton"));
        ppl.add(new Card("Suspect", "Jack"));
        ppl.add(new Card("Suspect", "Spencer"));
        ppl.add(new Card("Suspect", "Wang"));

        place.add(new Card("Location", "Home"));
        place.add(new Card("Location", "Parking Lot"));
        place.add(new Card("Location", "Mall"));
        place.add(new Card("Location", "Office"));
        place.add(new Card("Location", "Theater"));

        weapon.add(new Card("Weapon", "Bomb"));
        weapon.add(new Card("Weapon", "Gun"));
        weapon.add(new Card("Weapon", "Knife"));
        weapon.add(new Card("Weapon", "Rocket"));
        weapon.add(new Card("Weapon", "Sword"));

        Model model = new Model(num, ppl, place, weapon, playerList);

        // Runs the game
        model.run();

    }

}
