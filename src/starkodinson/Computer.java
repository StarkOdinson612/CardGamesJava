package starkodinson;

public class Computer extends Player {
    public Computer() {
        super("Computer");
    }

    public String play()
    {
        if (getHandValue() < 16)
        {
            return "Hit";
        }
        else
        {
            return "Stay";
        }
    }
}
