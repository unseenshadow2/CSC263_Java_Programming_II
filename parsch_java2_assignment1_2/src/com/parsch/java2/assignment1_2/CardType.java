package com.parsch.java2.assignment1_2;

/**
 * Created by unseenshadow2 on 9/8/2016.
 */
public enum CardType
{
    Ace(1),
    Two(2),
    Three(3),
    Four(4),
    Five(5),
    Six(6),
    Seven(7),
    Eight(8),
    Nine(9),
    Ten(10),
    Jack(10),
    Queen(10),
    King(10);

    private final int value; // Score value

    /**
     * Constructor. Assigns a card's numeric value.
     * @param value The card's numeric value.
     */
    private CardType(int value)
    {
        this.value = value;
    }

    /**
     * Get the numeric value of a card.
     * @return The numeric value of the card.
     */
    public int getValue() { return value; }
}
