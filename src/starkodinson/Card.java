package starkodinson;

import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Card {
    private int suit;
    private int value;
    private static final HashMap<Integer, String> suitStringMap = (HashMap<Integer, String>) Stream.of(new String[][] {
            {"1", "♠"},
            {"2", "♣"},
            {"3", "♥"},
            {"4", "♦"}
    }).collect(Collectors.toMap(data -> Integer.parseInt(data[0]), data -> data[1]));

    private static final HashMap<Integer, String> pictureStringMap = (HashMap<Integer, String>) Stream.of(new String[][] {
        {"1", "A"},
        {"11", "J"},
        {"12", "Q"},
        {"13", "K"}
    }).collect(Collectors.toMap(data -> Integer.parseInt(data[0]), data -> data[1]));

    public Card(int s, int v)
    {
        this.suit = s;
        this.value = v;
    }

    @Override
    public String toString()
    {
        return suitConverter(suit) + " " + valConverter(value);
    }

    public int[] getDetails() { return new int[]{suit, value}; }

    public static String suitConverter(int s)
    {
        return suitStringMap.get(s);
    }

    public static String valConverter(int v)
    {
        if (v == 1 || v > 10)
        {
            return pictureStringMap.get(v);
        }
        else
        {
            return String.valueOf(v);
        }
    }
}
