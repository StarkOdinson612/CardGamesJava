package starkodinson;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameManager {
    public static void main(String[] args) throws InterruptedException, IOException {
        boolean gameGoing = true;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username here: ");
        String username = scanner.nextLine();

        System.out.printf("Hello %s! Welcome to CardGamesOnline™! (offline edition)\n", username);

        while (gameGoing)
        {
            System.out.print("What game would you like to play?\n[1] Poker\n[2] BlackJack\n[3] Exit\nEnter input here: ");
            String choice = scanner.nextLine();

            while (!(choice.equalsIgnoreCase("1") || choice.equalsIgnoreCase("2") || choice.equalsIgnoreCase("3"))) {
                System.out.print("Invalid Choice. PLease try again.\nEnter choice here: ");
                choice = scanner.nextLine();
            }

            switch (choice.toLowerCase()) {
                case "1":
                    System.out.println("Loading PokerOnline™ (offline edition)...\n");
                    TimeUnit.SECONDS.sleep(2);
                    Poker.playPoker(scanner, username);
                    break;
                case "2":
                    System.out.println("Loading BlackJackOnline™ (offline edition)...\n");
                    TimeUnit.SECONDS.sleep(2);
                    BlackJack.playBlackJack(scanner, username);
                    break;
                case "3":
                    System.out.print("Goodbye!\n");
                    gameGoing = false;
                    break;
            }
        }
        scanner.close();
    }


}
