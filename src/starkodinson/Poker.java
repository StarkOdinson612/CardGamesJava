package starkodinson;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Poker {
    public static void playPoker(Scanner s) throws InterruptedException {
        boolean isPlaying = true;

        int count = 0;
        int winCount = 0;
        int drawCount = 0;

        System.out.print("Enter username here: ");
        String username = s.nextLine();
        Player p = new Player(username);
        System.out.println("Hello " + username + "! Welcome to PokerOnline™ (offline edition)!\n");

        Computer c = new Computer(Computer.GameType.POKER);

        while (isPlaying) {
            count++;

            System.out.printf("ROUND %d - Score (W/D/L): %d / %d / %d\n", count, winCount,drawCount,count == 1 ? 0 : count-winCount-drawCount);

            Deck d = new Deck();
            Deck.dealCards(p, c, d, 5);

            p.sortHand();
            c.sortHand();

            System.out.printf("Your Deck: %s\n", p.getDeck());
            System.out.printf("%s, your hand type is a %s !\n\n", p.name, p.getHandType());

            System.out.print("Enter ALL card indexes to replace: ");
            String inp_replace = s.nextLine();

            if (!inp_replace.isEmpty()) {
                List<Integer> replaceInd = Arrays.stream(inp_replace.split("")).mapToInt(i -> Integer.parseInt(i)).boxed().collect(Collectors.toList());
                HashMap<String, String> map = p.replaceAtInd(replaceInd, d);

                for (String str : map.keySet())
                {
                    System.out.printf("You discard %s and draw %s\n", str, map.get(str));
                }

                p.sortHand();
            }

            HandType playerHandType = p.getHandType();
            HandType computerHandType = c.getHandType();

            System.out.printf("%s's Deck: %s\n", p.name, p.getDeck());
            System.out.printf("%s's hand type is a %s!\n\n", p.name, playerHandType);
            System.out.printf("Computer's Deck: %s\n", c.getDeck());
            System.out.printf("Computer's hand type is a %s!\n\n", computerHandType);

            int comparisonHands = playerHandType.compareTo(computerHandType);

            if (comparisonHands == 0)
            {
                System.out.println("It's a tie!\n");
                drawCount++;
            }
            else if (comparisonHands < 0)
            {
                System.out.println("You Win!\n");
                winCount++;
            }
            else
            {
                System.out.println("Computer Wins!\n");
            }

            System.out.printf("Score (W/D/L): %d / %d / %d\n", winCount,drawCount,count-winCount-drawCount);

            System.out.print("Do you want to play another round? Enter Y/N: ");

            String choice = s.nextLine();

            while (!(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n")))
            {
                System.out.print("Invalid Choice. PLease try again.\nEnter choice here: ");
                choice = s.nextLine();
            }

            System.out.println();

            if (choice.equalsIgnoreCase("n"))
            {
                System.out.printf("Final Score(W/D/L): %d / %d / %d\nThanks for playing!", winCount, drawCount, count-winCount-drawCount);
                isPlaying = false;
                TimeUnit.SECONDS.sleep(5);
//                Runtime.getRuntime().exec("cls");
            }
        }
    }
}
