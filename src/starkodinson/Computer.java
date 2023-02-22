package starkodinson;

public class Computer extends Player {
    GameType h;
    public Computer(GameType g) {
        super("Computer");
        h = g;
    }

    public String play()
    {
        if (h == GameType.BLACKJACK) {
            if (getHandValue() < 16) {
                return "Hit";
            } else {
                return "Stay";
            }
        }
        else {
            return "";
        }
    }

    public String getLastCard()
    {
        return thisHand.get(thisHand.size() - 1).toString();
    }

    enum GameType
    {
        POKER, BLACKJACK
    }
}
