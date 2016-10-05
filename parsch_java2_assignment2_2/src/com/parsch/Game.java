package com.parsch;

import com.parsch.enemies.gnolls.Gnoll;
import com.parsch.enemies.gnolls.GnollHunter;
import com.parsch.enemies.kobolds.Kobold;
import com.parsch.enemies.kobolds.KoboldSorcerer;

import java.util.Random;
import java.util.Stack;

/**
 * Created by unseenshadow2 on 9/29/2016.
 */
public class Game
{
    private Battle currentBattle = new Battle();
    private Stack<Entity> enemies = new Stack<Entity>();
    public static Random random = new Random();

    public static void main(String[] args)
    {
        Game currentGame = new Game();

        while (currentGame.currentBattle.RunFight() && !currentGame.enemies.isEmpty())
        {
            currentGame.currentBattle.GetPlayer().ResetHealth();
            currentGame.currentBattle.SetFighter(currentGame.enemies.pop());
        }
    }

    public static int RollD20()
    {
        return random.nextInt(20) + 1;
    }

    public static int RollD6()
    {
        return random.nextInt(6) + 1;
    }

    public Game()
    {
        enemies.push(new GnollHunter());
        enemies.push(new KoboldSorcerer());
        enemies.push(new Gnoll());
        enemies.push(new Kobold());

        currentBattle.SetPlayer(new Player());
        currentBattle.SetFighter(enemies.pop());
    }
}
