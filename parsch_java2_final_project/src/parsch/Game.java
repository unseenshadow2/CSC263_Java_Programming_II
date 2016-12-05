package parsch;

import parsch.enemies.gnolls.Gnoll;
import parsch.enemies.gnolls.GnollHunter;
import parsch.enemies.kobolds.Kobold;
import parsch.enemies.kobolds.KoboldSorcerer;

import java.util.Random;
import java.util.Stack;

/**
 * Created by unseenshadow2 on 9/29/2016.
 */
public class Game
{
    static Game _game;
    static String events;

    private Battle currentBattle = new Battle();
    private Stack<Entity> enemies = new Stack<Entity>();
    public static Random random = new Random();

//    public static void main(String[] args)
//    {
//        Game currentGame = new Game();

//        while (currentGame.currentBattle.RunFight() && !currentGame.enemies.isEmpty())
//        {
//            currentGame.currentBattle.GetPlayer().ResetHealth();
//            currentGame.currentBattle.SetFighter(currentGame.enemies.pop());
//        }
//    }

    // Control our events string
    public static void eventsPrintln(String toPrint) { events += "\n" + toPrint; }
    public static void addToEvents(String toAdd) { events += toAdd; }
    public static String getEvents() { return events; }
    public static void clearEvents() { events = ""; }

    public static int RollD20()
    {
        return random.nextInt(20) + 1;
    }

    public static int RollD6()
    {
        return random.nextInt(6) + 1;
    }

    public static Game GetGame()
    {
        if (_game==null) _game = new Game();

        return _game;
    }

    public Game()
    {
        enemies.push(new GnollHunter());
        enemies.push(new KoboldSorcerer());
        enemies.push(new Gnoll());
        enemies.push(new Kobold());

        currentBattle.SetPlayer(Player.GetPlayer());
        currentBattle.SetFighter(enemies.pop());
    }

    public Battle getCurrentBattle() {return currentBattle;}

    // Check if our enemy is good and continue on if they are not.
    // Returns whether game has ended
    public boolean CheckEnemy()
    {
        if (enemies.isEmpty())
        {
            return true;
        }
        else
        {
            if (currentBattle.GetFighter().getHealth() <= 0) currentBattle.SetFighter(enemies.pop());
            return false;
        }
    }
}
