package com.parsch;

import java.util.Scanner;

/**
 * Created by unseenshadow2 on 10/3/2016.
 */
public class Player extends Entity
{
    private int speed = 0;
    private int maxHealth = 50;
    private Scanner input = new Scanner(System.in);
    private boolean isFleeing = false;

    public Player()
    {
        setHealth(maxHealth);
        setWeapon(Weapon.SwordAndShield);
        setName("Player");
    }

    public void Run(Battle battle)
    {
        setIsDefending(false);

        if (isFleeing)
        {
            isFleeing = false;
            int startHealth = getHealth();

            // Heal double speed or 6, whichever is higher
            if (speed < 3) setHealth(getHealth() + 6);
            else setHealth(getHealth() + (speed * 2));
            System.out.println(this + " escaped temporarily, recovering " + (getHealth() - startHealth) + " health.");
        }

        RunBattleMenu(battle);
    }

    public void TakeDamage(int damage, int toHit, Entity attacker)
    {
        if (getIsDefending()) // Defending
        {
            // Handle defending
            int defendRoll = Game.RollD20() + getBaseDefense() + getWeapon().getDefense();

            if (defendRoll >= toHit)
            {
                // Succeeded and negating damage
                int actualDamage = (damage - (getBaseDefense() + getWeapon().getDefense()));
                actualDamage = (actualDamage < 0) ? 0 : actualDamage;
                setHealth(getHealth() - actualDamage);
                System.out.println(this + " negated " + (getBaseDefense() + getWeapon().getDefense()) +
                        " damage, taking " + actualDamage + " damage from " + attacker);
                attacker.TakeDamage(damage / 2, defendRoll, this);
            }
            else
            {
                // Failed and taking full damage
                setHealth(getHealth() - damage);
                System.out.println(this + " fialed to defend and " + attacker + " dealt " + damage + " damage.");
            }
        }
        else if (isFleeing) // Fleeing
        {
            int escapeRoll = speed + getWeapon().getDefense() + Game.RollD20();
            isFleeing = false;

            if (escapeRoll > toHit) // We escaped
            {
                int startHealth = getHealth();

                // Heal double speed or 6, whichever is higher
                if (speed < 3) setHealth(getHealth() + 6);
                else setHealth(getHealth() + (speed * 2));
                System.out.println(this + " escaped temporarily, recovering " + (getHealth() - startHealth) + " health.");
            }
            else
            {
                System.out.println(this + " failed to escape, taking " + damage + " damage.");
                setHealth(getHealth() - damage);
            }
        }
        else // Normal
        {
            setHealth(getHealth() - damage);
            System.out.println(attacker + " dealt " + damage + " to " + this);
        }
    }

    public void RunBattleMenu(Battle battle)
    {
        System.out.print("\nWhat would you like to do:\n" +
        "1) Attack\n" +
        "2) Defend\n" +
        "3) Flee\n\n" +
        "Please select an option: ");

        int inValue = input.nextInt();

        switch (inValue)
        {
            case 1:
                Attack(battle);
                break;
            case 2:
                Defend(battle);
                break;
            case 3:
                isFleeing = true;
                break;
        }
    }

    public void RunVictoryMenu(Battle battle)
    {
        // Do you want to take the enemy's weapon
        // What are your stats
        // 3 stat increases

        // Take the enemy's weapon?
        System.out.print("\nThe " + battle.GetFighter() + " had a " + battle.GetFighter().getWeapon() +
            "\nWould you like to take the weapon (y or n): ");

        char takeWeapon = 'e';
        while ((takeWeapon != 'y') && (takeWeapon != 'n'))
        {
            takeWeapon = input.next().charAt(0);
            if ((takeWeapon != 'y') && (takeWeapon != 'n'))
            {
                System.out.print("Invalid input. Please input y or n: ");
            }
        }

        if (takeWeapon == 'y')
        {
            battle.GetPlayer().setWeapon(battle.GetFighter().getWeapon());
        }

        // Which stats would you like to upgrade?
        System.out.println("\nCurrent Stats:" +
            "\n1) Attack (Max 6): " + getBaseAttack() +
            "\n2) Defense (Max 6): " + getBaseDefense() +
            "\n3) Speed (Max 6): " + getSpeed() +
            "\n4) Max Health (Max 80): " + getMaxHealth() + "\n");

        for (int i = 0; i < 3; i++)
        {
            System.out.print("What stat would you like to upgrade? ");
            int stat = input.nextInt();

            switch (stat)
            {
                case 1:
                    if (getBaseAttack() < 6) setBaseAttack(getBaseAttack()+1);
                    else
                    {
                        --i;
                        System.out.println("Stat at max.");
                    }
                    break;

                case 2:
                    if (getBaseDefense() < 6) setBaseDefense(getBaseDefense()+1);
                    else
                    {
                        --i;
                        System.out.println("Stat at max.");
                    }
                    break;

                case 3:
                    if (getSpeed() < 6) setSpeed(getSpeed()+1);
                    else
                    {
                        --i;
                        System.out.println("Stat at max.");
                    }
                    break;

                case 4:
                    if (getMaxHealth() < 80) setMaxHealth(getMaxHealth()+5);
                    else
                    {
                        --i;
                        System.out.println("Stat at max.");
                    }
                    break;

                default:
                    System.out.println("Invalid input. Please input a value from 1 to 4.");
                    break;
            }
        }
    }

    public void RunDefeatMenu(Battle battle)
    {
        System.out.println(battle.GetFighter() + " has beaten you.");
    }

    public void ResetHealth() { setHealth(maxHealth); }

    public int getSpeed() { return speed; }
    public void setSpeed(int newSpeed) { speed = newSpeed; }

    public int getMaxHealth() { return maxHealth; }
    public void setMaxHealth(int newMax) { maxHealth = newMax; }
}
