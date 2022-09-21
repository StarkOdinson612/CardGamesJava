package starkodinson;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BlackJack {

    public static void main(String[] args) {
	// write your code here
        boolean isPlaying = true;
        Player player;
        Computer computer = new Computer();
        Scanner s = new Scanner(System.in);

        while (isPlaying)
        {
            // Create new deck and players
            Deck deck = new Deck();
            String username = s.nextLine();
            player = new Player(username);

            // Deal cards
            player.addCard(deck.nextCard());
            computer.addCard(deck.nextCard());
            player.addCard(deck.nextCard());
            computer.addCard(deck.nextCard());


        }
    }
}
