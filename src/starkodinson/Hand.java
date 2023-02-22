package starkodinson;

import java.util.*;
import java.util.stream.Collectors;

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
            int value = c.getValue();

            if (value > 10)
            {
                sum += 10;
            }
            else if (value == 14)
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

    public void sortHand()
    {
        Collections.sort(thisHand);
    }

    public void clearHand()
    {
        thisHand.clear();
    }

    public int handSize()
    {
        return thisHand.size();
    }

    private boolean isSameSuit(List<Card> list)
    {
        int thisSuit = list.get(0).getSuit();

        for (Card c : list)
        {
            if (c.getSuit() != thisSuit)
            {
                return false;
            }
        }
        return true;
    }

    private HashMap<Integer, Integer> matchingCards(List<Card> list)
    {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (Card c : list)
        {
            if (!map.containsKey(c.getValue()))
            {
                map.put(c.getValue(),1);
            }
            else
            {
                map.put(c.getValue(), map.get(c.getValue()) + 1);
            }
        }

        return map;
    }

    public HashMap<String, String> replaceAtInd(List<Integer> l, Deck d)
    {
        HashMap<String, String> map = new HashMap<>();

        for (int i : l)
        {
            Card n = d.nextCard();
            map.put(thisHand.get(i).toString(), n.toString());
            thisHand.set(i,n);
        }

        return map;
    }

    private boolean[] isStraight(List<Card> list)
    {
        List<Integer> vals = list.stream().mapToInt(i -> i.getValue()).boxed().collect(Collectors.toList());

        if (vals.contains(14))
        {
            List<Integer> t = new ArrayList<>();
            t.addAll(vals);
            t.replaceAll(i -> i == 14 ? 1 : i);
            Collections.sort(t);

            boolean aceLow = true;

            for (int i = 1; i < t.size(); i++)
            {
                if (t.get(i) != t.get(i - 1) + 1)
                {
                    aceLow = false;
                    break;
                }
            }

            if (aceLow)
            {
                return new boolean[]{true,false};
            }
        }

        for (int i = 1; i < vals.size(); i++)
        {
            if (vals.get(i) != vals.get(i - 1) + 1)
            {
                return new boolean[]{false, false};
            }
        }

        return new boolean[]{true, list.get(0).getValue() == 10};
    }

    public HandType getHandType()
    {
        List<Card> temp = new ArrayList<>(thisHand).stream().sorted().collect(Collectors.toList());
        boolean[] isStraight = isStraight(temp);
        boolean sameSuit = isSameSuit(temp);

        HashMap<Integer, Integer> matching = matchingCards(temp);

        if (isStraight[1] && sameSuit)
        {
            return new HandType(HandType.Type.ROYAL_FLUSH, HandType.Value.ACE);
        }
        else if (isStraight[0] && sameSuit)
        {
            return new HandType(HandType.Type.STRAIGHT_FLUSH, HandType.getValueWithInt(temp.get(4).getValue() == 14 ? temp.get(3).getValue() == 5 ? 1 : 14 : temp.get(4).getValue()));
        }
        else if (matching.values().contains(4))
        {
            return new HandType(HandType.Type.FOUR_OF_A_KIND, HandType.getValueWithInt(matching.keySet().stream().filter(i -> matching.get(i) == 4).collect(Collectors.toList()).get(0)));
        }
        else if (matching.values().contains(3) && matching.values().contains(2))
        {
            return new HandType(HandType.Type.FULL_HOUSE, HandType.getValueWithInt(matching.keySet().stream().filter(i -> matching.get(i) == 3).collect(Collectors.toList()).get(0)));
        }
        else if (sameSuit)
        {
            return new HandType(HandType.Type.FLUSH, HandType.getValueWithInt(temp.get(4).getValue()));
        }
        else if (isStraight[0])
        {
            return new HandType(HandType.Type.STRAIGHT, HandType.getValueWithInt(temp.get(4).getValue() == 14 ? temp.get(3).getValue() == 5 ? 1 : 14 : temp.get(4).getValue()));
        }
        else if (matching.values().contains(3))
        {
            return new HandType(HandType.Type.THREE_OF_A_KIND, HandType.getValueWithInt(matching.keySet().stream().filter(i -> matching.get(i) == 3).collect(Collectors.toList()).get(0)));
        }
        else if (matching.values().stream().filter(i -> i == 2).collect(Collectors.toList()).size() == 2)
        {
            return new HandType(HandType.Type.TWO_PAIR, HandType.getValueWithInt(matching.keySet().stream().filter(i -> matching.get(i) == 2).sorted().collect(Collectors.toList()).get(1)));
        }
        else if (matching.values().contains(2))
        {
            return new HandType(HandType.Type.PAIR, HandType.getValueWithInt(matching.keySet().stream().filter(i -> matching.get(i) == 2).collect(Collectors.toList()).get(0)));
        }
        else
        {
            return new HandType(HandType.Type.HIGH_CARD, HandType.getValueWithInt(temp.get(4).getValue()));
        }
    }

    @Override
    public String toString() {
        return thisHand.stream().map(i -> i.toString()).toString();
    }
}
