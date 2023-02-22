package starkodinson;

public class HandType implements Comparable {
    public enum Type {
        ROYAL_FLUSH(1,"Royal Flush"),
        STRAIGHT_FLUSH(2, "Straight Flush"),
        FOUR_OF_A_KIND(3, "Four of a Kind"),
        FULL_HOUSE(4, "Full House"),
        FLUSH(5, "Flush"),
        STRAIGHT(6, "Straight"),
        THREE_OF_A_KIND(7, "Three of a Kind"),
        TWO_PAIR(8, "Two Pair"),
        PAIR(9, "Pair"),
        HIGH_CARD(10, "High Card");

        private int type;
        private String name;

        private Type(int t, String s) {
            type = t;
            name = s;
        }

        @Override
        public String toString()
        {
            return name;
        }

        public int typeInt()
        {
            return type;
        }
    }

    public enum Value {
        ACE(14),
        KING(13),
        QUEEN(12),
        JACK(11),
        TEN(10),
        NINE(9),
        EIGHT(8),
        SEVEN(8),
        SIX(6),
        FIVE(5),
        FOUR(4),
        THREE(3),
        TWO(2),
        ACE_LOW(1);

        private int value;

        private Value(int v) {
            value = v;
        }

        @Override
        public String toString()
        {
            return String.valueOf(value);
        }

        public int valInt()
        {
            return value;
        }
    }

    public static Value getValueWithInt(int n) {
        switch (n) {
            case 1:
                return Value.ACE_LOW;
            case 2:
                return Value.TWO;
            case 3:
                return Value.THREE;
            case 4:
                return Value.FOUR;
            case 5:
                return Value.FIVE;
            case 6:
                return Value.SIX;
            case 7:
                return Value.SEVEN;
            case 8:
                return Value.EIGHT;
            case 9:
                return Value.NINE;
            case 10:
                return Value.TEN;
            case 11:
                return Value.JACK;
            case 12:
                return Value.QUEEN;
            case 13:
                return Value.KING;
            case 14:
                return Value.ACE;
            default:
                return Value.ACE_LOW;
        }
    }

    private Type type;
    private Value value;
    public HandType(Type t, Value v)
    {
        this.type = t;
        this.value = v;
    }

    public Type getType()
    {
        return type;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return type.toString() + " with a value of " + value.toString();
    }

    @Override
    public int compareTo(Object o) {
        HandType other = (HandType) o;
        int thisType = type.typeInt();
        int thisValue = value.valInt();
        int otherType = other.getType().typeInt();
        int otherVal = other.getValue().valInt();

        return thisType - otherType != 0 ? thisType - otherType : otherVal - thisValue;
    }
}
