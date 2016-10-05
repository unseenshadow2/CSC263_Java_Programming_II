package com.parsch.java2.assignment1_2;

/**
 * Created by unseenshadow2 on 9/8/2016.
 */
public class Card
{
    public Suit suit;
    public CardType value;

    /**
     * Constructor. Sets the suit and the value of a card.
     * @param s The suit of the card.
     * @param v The value of the card.
     */
    public Card(Suit s, CardType v) { suit = s; value = v; }

    /**
     * Get the string representation of the card.
     * @return The string representation of the card.
     */
    public String toString()
    {
        return value.name() + " of " + suit.name();
    }
}
