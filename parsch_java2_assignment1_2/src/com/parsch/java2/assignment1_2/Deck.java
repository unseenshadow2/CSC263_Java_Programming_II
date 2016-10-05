package com.parsch.java2.assignment1_2;

import java.util.Random;
import java.util.Vector;
import java.util.Stack;

/**
 * Created by unseenshadow2 on 9/12/2016.
 */
public class Deck
{
    private final Card[] baseDeck = new Card[]
    {
        new Card(Suit.Clubs, CardType.Ace), new Card(Suit.Diamonds, CardType.Ace),
        new Card(Suit.Hearts, CardType.Ace), new Card(Suit.Spades, CardType.Ace),

        new Card(Suit.Clubs, CardType.Two), new Card(Suit.Diamonds, CardType.Two),
        new Card(Suit.Hearts, CardType.Two), new Card(Suit.Spades, CardType.Two),

        new Card(Suit.Clubs, CardType.Three), new Card(Suit.Diamonds, CardType.Three),
        new Card(Suit.Hearts, CardType.Three), new Card(Suit.Spades, CardType.Three),

        new Card(Suit.Clubs, CardType.Four), new Card(Suit.Diamonds, CardType.Four),
        new Card(Suit.Hearts, CardType.Four), new Card(Suit.Spades, CardType.Four),

        new Card(Suit.Clubs, CardType.Five), new Card(Suit.Diamonds, CardType.Five),
        new Card(Suit.Hearts, CardType.Five), new Card(Suit.Spades, CardType.Five),

        new Card(Suit.Clubs, CardType.Six), new Card(Suit.Diamonds, CardType.Six),
        new Card(Suit.Hearts, CardType.Six), new Card(Suit.Spades, CardType.Six),

        new Card(Suit.Clubs, CardType.Seven), new Card(Suit.Diamonds, CardType.Seven),
        new Card(Suit.Hearts, CardType.Seven), new Card(Suit.Spades, CardType.Seven),

        new Card(Suit.Clubs, CardType.Eight), new Card(Suit.Diamonds, CardType.Eight),
        new Card(Suit.Hearts, CardType.Eight), new Card(Suit.Spades, CardType.Eight),

        new Card(Suit.Clubs, CardType.Nine), new Card(Suit.Diamonds, CardType.Nine),
        new Card(Suit.Hearts, CardType.Nine), new Card(Suit.Spades, CardType.Nine),

        new Card(Suit.Clubs, CardType.Ten), new Card(Suit.Diamonds, CardType.Ten),
        new Card(Suit.Hearts, CardType.Ten), new Card(Suit.Spades, CardType.Ten),

        new Card(Suit.Clubs, CardType.Jack), new Card(Suit.Diamonds, CardType.Jack),
        new Card(Suit.Hearts, CardType.Jack), new Card(Suit.Spades, CardType.Jack),

        new Card(Suit.Clubs, CardType.Queen), new Card(Suit.Diamonds, CardType.Queen),
        new Card(Suit.Hearts, CardType.Queen), new Card(Suit.Spades, CardType.Queen),

        new Card(Suit.Clubs, CardType.King), new Card(Suit.Diamonds, CardType.King),
        new Card(Suit.Hearts, CardType.King), new Card(Suit.Spades, CardType.King)
    };

    private Stack<Card> shuffledDeck;

    /**
     * Draw a card from the active shuffled deck.
     * @return The drawn card.
     */
    public Card drawCard()
    {
        return shuffledDeck.pop();
    }

    /**
     * Shuffle the deck.
     */
    public void shuffleDeck()
    {
        Vector<Card> drawFrom = new Vector<Card>(52);
        shuffledDeck = new Stack<Card>();
        Random rand = new Random();

        for (Card x : baseDeck)
        {
            drawFrom.add(x);
        }

        for (int i = 0; i < 52; i++)
        {
            shuffledDeck.push(drawFrom.remove(rand.nextInt(drawFrom.size())));
        }
    }
}
