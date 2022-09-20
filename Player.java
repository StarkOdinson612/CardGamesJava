package com.starkodinson;

import java.util.*;

public class Player extends Hand{
    protected String name;

    public Player(String name)
    {
        this.name = name;
    }

    public String getDeck()
    {
        return thisHand.toString();
    }
}
