package com.parsch.java2.assignment1_2;

import java.util.LinkedList;
import java.util.Scanner;

import static java.awt.SystemColor.menu;

/**
 * Created by unseenshadow2 on 9/8/2016.
 */
public class Game
{
    private LinkedList<Card> playerCards = new LinkedList<Card>();
    private Dealer dealer = new Dealer();
    private Deck deck = new Deck();
    private Scanner scanner = new Scanner(System.in);

    // Menu Strings
    private final String mainMenu = "\n" +
            "***************\n" +
            "** Main Menu **\n" +
            "***************\n\n" +
            "1) Play Game\n" +
            "2) Exit Game\n\n" +
            "Please select an option: ";

    private final String playMenuString = "\n" +
            "***************\n" +
            "** Game Menu **\n" +
            "***************\n\n" +
            "1) Hit\n" +
            "2) Stand\n" +
            "3) Check Cards\n" +
            "Please select an option: ";

    /**
     * Constructor. Ensures that the deck is shuffled.
     */
    public Game() {deck.shuffleDeck();}

    /**
     * All hail the main function.
     * @param args Command line arguments
     */
    public static void main(String[] args)
    {
        Game currentGame = new Game();

        while (currentGame.menu()) // Our main menu
        {
            while (currentGame.playMenu()) // Game menu
            { System.out.println("\nYour current score is " + Game.GetScore(currentGame.playerCards));}

            currentGame.dealer.RunDealer(currentGame.deck, Game.GetScore(currentGame.playerCards)); // Dealer's turn

            // Check and display victory or loss
            if (currentGame.checkWinner()) { System.out.println("\nYou have won!"); }
            else { System.out.println("\nThe dealer has won!"); }

            // Display scores
            System.out.println("You ended with a score of " + Game.GetScore(currentGame.playerCards));
            System.out.println("The dealer ended with a score of " + Game.GetScore(currentGame.dealer.getDealerCards()));

            // Clear and reassign currentGame
            currentGame = new Game();
        }
    }

    /**
     * Run the main menu.
     * @return True if to start game, or false to quit.
     */
    private boolean menu()
    {
        System.out.print(mainMenu);
        int response = scanner.nextInt();

        switch (response)
        {
            case 1:
                return true;

            case 2:
                return false;
        }

        return false;
    }

    /**
     * Run the menu for playing the game.
     * @return True if continue running, false is stopping.
     */
    private boolean playMenu()
    {
        System.out.print(playMenuString);
        int response = scanner.nextInt();

        switch (response)
        {
            case 1: // Hit
                Card drawnCard = deck.drawCard();
                playerCards.add(drawnCard);
                System.out.println("\nDrew " + drawnCard);
                return (GetScore(playerCards) <= 21) ? true : false;

            case 2: // Stand
                return false;

            case 3: // Check
                System.out.println("\nCurrent Cards:");
                for (Card x : playerCards) System.out.println(x);
                break;
        }

        return true;
    }

    /**
     * Checks if the dealer or the player wins.
     * @return True if the player wins, false if the dealer wins
     */
    private boolean checkWinner()
    {
        int playerScore, dealerScore;

        playerScore = Game.GetScore(playerCards);
        dealerScore = Game.GetScore(dealer.getDealerCards());

        if (playerScore > 21) return false;
        else if (dealerScore > 21) return true;
        else if (playerScore > dealerScore) return true;
        else return false;
    }

    /**
     * Calculates the score of an individual player.
     * @param toCount The series of cards whose score is to be evaluated.
     * @return The score that the series of cards calculates out to.
     */
    public static int GetScore(LinkedList<Card> toCount)
    {
        // Return if we have no values
        if (toCount.size() <= 0) return 0;

        int score = 0;
        int aces = 0;

        // Check the actual score
        for (Card x : toCount)
        {
            if (x.value != CardType.Ace)
            {
                score += x.value.getValue();
            }
            else
            {
                // Aces are complicated and should be dealt with after
                ++aces;
            }
        }

        // Aces are complicated
        for (int i = 0; i < aces; i++)
        {
            if (score <= 11) score += 10;
            else score += 1;
        }

        return score;
    }

    /**
     * Resets the game.
     */
    private void resetGame()
    {
        dealer.resetDealer();
        playerCards.clear();
        deck.shuffleDeck();
    }
}
