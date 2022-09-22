package starkodinson;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.stream.Collectors;

public class BlackJack {

    public static void main(String[] args) throws InterruptedException, IOException {
        new BlackJackGUI();

        boolean isPlaying = true;
        Computer computer = new Computer();
        Scanner s = new Scanner(System.in);
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
                printHands(p, c);
                return false;
            }
            else
            {
                System.out.println("You both bust! Dealer wins!");
                printHands(p, c);
                return false;
            }

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

    static class BlackJackGUI
    {
        JButton startButton;
        JTextField usernameField;

        public BlackJackGUI()
        {
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Test");
                frame.add(new MenuPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            });
        }


        // WIP GUI

        public static class MenuPane extends JPanel
        {
            public MenuPane() {
                setBorder(new EmptyBorder(10, 10, 10, 10));
                setLayout(new GridLayout(1, 1, 0, 20));

                JPanel fullPanel = new JPanel(new GridLayout(7, 1, 0, 20));
                fullPanel.setMaximumSize(200, 200);

                fullPanel.add(new JLabel("<html><h1><strong><i>BlackJack Online™ (Offline Edition)</i></strong></h1><hr></html>"));


                JTextField usernameEntry = new JTextField();

//                JPanel textArea = new JPanel();
//                textArea.setLayout(new GridLayout(1,1,0,0));
//                textArea.setBounds(0,0,100,50);
                fullPanel.add(new JLabel("Username: "));
                fullPanel.add(usernameEntry);
//                fullPanel.add(textArea);


//                JPanel buttons = new JPanel();
//                buttons.setLa/yout(new GridLayout(4,1,0,5));
                fullPanel.add(new JButton("Start"));
                fullPanel.add(new JButton("Show scores"));
                fullPanel.add(new JButton("Help"));
                fullPanel.add(new JButton("Exit"));
//                fullPanel.add(buttons);

                add(fullPanel);
            }
        }
    }
}

