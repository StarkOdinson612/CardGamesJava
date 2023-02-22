package starkodinson;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class BlackJack {

    public static void playBlackJack(Scanner s) throws InterruptedException, IOException {

        boolean isPlaying = true;
        Computer computer = new Computer(Computer.GameType.BLACKJACK);
        System.out.print("Enter username here: ");
        String username = s.nextLine();
        Player player = new Player(username);
        System.out.println("Hello " + username + "! Welcome to BlackJackOnline™ (offline edition)!\n");
        int count = 0;
        int winCount = 0;

        while (isPlaying)
        {
            count++;
            // Create new deck and players
            Deck deck = new Deck();


            player.clearHand();
            computer.clearHand();

            // Deal cards
            player.addCard(deck.nextCard());
            computer.addCard(deck.nextCard());
            player.addCard(deck.nextCard());
            computer.addCard(deck.nextCard());

            if (player.getHandValue() == 21)
            {
                System.out.println("Blackjack! You Win!");
                printHands(player, computer);
                System.out.println();
                System.out.println("Wins/Losses: " + winCount + "/" + count);

                System.out.print("Would you like to play again? (Y/N)\nEnter choice here: ");
                String choice = s.nextLine();

                while (!(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n")))
                {
                    System.out.print("Invalid Choice. PLease try again.\nEnter choice here: ");
                    choice = s.nextLine();
                }

                System.out.println();

                if (choice.equalsIgnoreCase("n"))
                {
                    System.out.println("Thanks for playing!");
                    TimeUnit.SECONDS.sleep(5);
                    Runtime.getRuntime().exec("cls");
                    isPlaying = false;
                }

                continue;
            }

            System.out.println("Computer's Second Card: " + computer.getLastCard());

            boolean turnsGoing = true;
            boolean isPlayerTurn = true;

            while (turnsGoing)
            {

                if (isPlayerTurn)
                {
                    String move;
                    System.out.println("Player Hand: " + player.getDeck() + "     Value: " + player.getHandValue());
                    System.out.print("Hit or Stay?  ");
                    move = s.nextLine();

                    while (!(move.equalsIgnoreCase("hit") || move.equalsIgnoreCase("stay")))
                    {
                        System.out.print("Invalid choice, please try again.\nHit or Stay?  ");
                        move = s.nextLine();
                    }

                    System.out.println();

                    if (move.equalsIgnoreCase("hit"))
                    {
                        player.addCard(deck.nextCard());

                        if (player.getHandValue() > 21)
                        {
                            System.out.println("You bust! Computer wins!");
                            printHands(player, computer);
                            turnsGoing = false;
                        }

                        isPlayerTurn =  false;
                    }
                    else
                    {
                        boolean playerWins = checkPlayerWin(player, computer);
                        System.out.println();
                        if (playerWins) { winCount++; }
                        turnsGoing = false;
                    }
                }
                else
                {
                    String move = computer.play();

                    if (move.equals("Hit"))
                    {
                        computer.addCard(deck.nextCard());
                    }

                    isPlayerTurn = true;

                }
            }

            System.out.println("Wins/Losses: " + winCount + "/" + count);
            System.out.print("Would you like to play again? (Y/N)\nEnter choice here: ");
            String choice = s.nextLine();

            while (!(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n")))
            {
                System.out.print("Invalid Choice. PLease try again.\nEnter choice here: ");
                choice = s.nextLine();
            }

            System.out.println();

            if (choice.equalsIgnoreCase("n"))
            {
                System.out.println("Thanks for playing!");
                isPlaying = false;
                TimeUnit.SECONDS.sleep(5);
//                Runtime.getRuntime().exec("cls");
            }
        }
    }

    public static void printHands(Player p, Computer c)
    {
        String pAdd = p.getHandValue() > 21 ? "Bust!" : "";
        String cAdd = c.getHandValue() > 21 ? "Bust!" : "";
        System.out.println("Player Hand: " + p.getDeck() + "     Value: " + p.getHandValue() + "   " + pAdd);
        System.out.println("Computer Hand: " + c.getDeck() + "     Value: " + c.getHandValue() + "   " + cAdd);
    }

    public static boolean checkPlayerWin(Player p, Computer c)
    {
        int pVal = p.getHandValue();
        int cVal = c.getHandValue();

        if (pVal > 21)
        {
            if (cVal <= 21)
            {
                System.out.println("You bust! Computer wins!");
            }
            else
            {
                System.out.println("You both bust! Dealer wins!");
            }
            printHands(p, c);
            return false;

        }
        else if (pVal == 21)
        {
            if (cVal == 21)
            {
                System.out.println("Tie! Dealer wins!");
                printHands(p, c);
                return false;
            }
            else
            {
                System.out.println("Blackjack! You Win!");
                printHands(p, c);
                return true;
            }
        }
        else
        {
            if (cVal == 21)
            {
                System.out.println("Blackjack! Computer wins!");
                printHands(p, c);
                return false;
            }
            else if (cVal > 21)
            {
                System.out.println("Computer busts! You win!");
                printHands(p, c);
                return true;
            }
            else if (21 - pVal < 21 - cVal)
            {
                System.out.println("You win!");
                printHands(p, c);
                return true;
            }
            else
            {
                System.out.println("Tie! Dealer wins!");
                printHands(p, c);
                return false;
            }
        }
    }

//    static class BlackJackGUI
//    {
//        JButton startButton;
//        JTextField usernameField;
//
//        public BlackJackGUI()
//        {
//            SwingUtilities.invokeLater(() -> {
//                JFrame frame = new JFrame("Test");
//                frame.add(new MenuPane());
//                frame.pack();
//                frame.setLocationRelativeTo(null);
//                frame.setVisible(true);
//            });
//        }
//
//        public static class MenuPane extends JPanel
//        {
//            public MenuPane() {
//                setBorder(new EmptyBorder(10, 10, 10, 10));
//                setLayout(new GridBagLayout());
//
//                GridBagConstraints gbc = new GridBagConstraints();
//                gbc.gridwidth = GridBagConstraints.REMAINDER;
//                gbc.anchor = GridBagConstraints.NORTH;
//
//                add(new JLabel("<html><h1><strong><i>BlackJack Online™ (Offline Edition)</i></strong></h1><hr></html>"), gbc);
//
//                gbc.anchor = GridBagConstraints.CENTER;
//                gbc.fill = GridBagConstraints.HORIZONTAL;
//
//
//                JPanel buttons = new JPanel(new GridBagLayout());
//                buttons.add(new JLabel("Username: "));
//                buttons.add(new JTextField(), gbc);
//                buttons.add(new JButton("Start"), gbc);
//                buttons.add(new JButton("Show scores"), gbc);
//                buttons.add(new JButton("Help"), gbc);
//                buttons.add(new JButton("Exit"), gbc);
//
//                gbc.weighty = 1;
//                add(buttons, gbc);
//            }
//        }
//    }
}


