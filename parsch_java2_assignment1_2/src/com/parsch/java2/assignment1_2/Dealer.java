package com.parsch.java2.assignment1_2;

import java.util.LinkedList;

/**
 * Created by unseenshadow2 on 9/8/2016.
 */
public class Dealer
{
    private LinkedList<Card> dealerCards = new LinkedList<Card>();

    /**
     * Run the dealer and draw his cards.
     */
    public void RunDealer(Deck deck, int playerScore)
    {
        int score = 0;

        // Draw the first two cards
        dealerCards.add(deck.drawCard());
        dealerCards.add(deck.drawCard());

        score = Game.GetScore(dealerCards);

        // Display initial cards
        System.out.print("\nThe dealer drew a " + dealerCards.get(0) + " and a " + dealerCards.get(1));
        System.out.println(" for a score of " + score);

        if (score == 21) return;

        while ((score < playerScore) && (score < 21))
        {
            dealerCards.add(deck.drawCard());

            score = Game.GetScore(dealerCards);

            System.out.println("\nThe dealer drew a " + dealerCards.getLast() + ", increasing his score to " + score);
            try { Thread.sleep(2000); } catch (Exception ex) {}
        }
    }

    /**
     * Get the dealer's cards.
     * @return The dealer's cards.
     */
    public LinkedList<Card> getDealerCards()
    {
        return dealerCards;
    }

    /**
     * Reset the dealer to prepare it for another game.
     */
    public void resetDealer()
    {
        dealerCards.clear();
    }
}
