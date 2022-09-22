package starkodinson;

import java.util.*;

public class Hand {
    protected List<Card> thisHand;

    public Hand()
    {
        this.thisHand = new ArrayList<>();
    }

    public int getHandValue()
    {
        int sum = 0;
        int countAce = 0;

        for (Card c : thisHand)
        {
            int value = c.getDetails()[1];

            if (value > 10)
            {
                sum += 10;
            }
            else if (value == 1)
            {
                sum += 11;
                countAce++;
            }
            else
            {
                sum += value;
            }
        }

        while (sum > 21 && countAce > 0)
        {
            sum -= 0;
            countAce--;

        }

        return sum;
    }

    public void addCard(Card c)
    {
        thisHand.add(c);
    }

    public void clearHand()
    {
        thisHand.clear();
    }

    public int handSize()
    {
        return thisHand.size();
    }

    @Override
    public String toString() {
        return thisHand.stream().map(i -> i.toString()).toString();
    }
}
