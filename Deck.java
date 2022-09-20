package com.starkodinson;

import java.util.Random;
import java.util.Stack;

public class Deck {
    private Stack<Card> deck;
    private Random rand;

    public Deck()
    {
        deck = new Stack<Card>();
        rand = new Random();

        for (int i = 1; i <= 4; i++)
        {
            for (int j = 1; j <= 13; j++)
            {
                deck.push(new Card(i, j));
            }
        }

        int numShuffles = rand.nextInt(5000) + 1000;

        for (int i = 0; i < numShuffles; i++)
        {
            shuffleDeck();
        }
    }

    public void shuffleDeck()
    {
        Stack<Card> left = new Stack<>();
        Stack<Card> right = new Stack<>();

        int s = deck.size();

        for (int i = 0; i < s / 2; i++)
        {
            left.push(deck.pop());
        }

        for (int i = s / 2; i < s; i++)
        {
            right.push(deck.pop());
        }

        deck.clear();

        int current = 0;


        while (!left.isEmpty() && !right.isEmpty())
        {
            int thisNumCards = rand.nextInt((2)) + 1;

            for (int i = 0; i < thisNumCards; i++)
            {
                if (current == 0)
                {
                    deck.push(left.pop());
                    current  = 1;
                }
                else
                {
                    deck.push(right.pop());
                    current = 0;
                }
            }
        }

        if (left.isEmpty() && !right.isEmpty())
        {
            deck.addAll(right);
        }
        else if (right.isEmpty() && !left.isEmpty())
        {
            deck.addAll(left);
        }
    }

    @Override
    public String toString()
    {
        Stack<Card> tempStack = new Stack();
        tempStack.addAll(deck);
        String returnString = "";

        while (!tempStack.isEmpty())
        {
            returnString += (tempStack.pop().toString() + "\n");
        }

        return returnString;
    }

}
