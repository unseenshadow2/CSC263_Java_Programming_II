package parsch;

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

    private static Player player;

    public Player()
    {
        setHealth(maxHealth);
        setWeapon(Weapon.SwordAndShield);
        setName("Player");
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
                Game.eventsPrintln(this + " negated " + (getBaseDefense() + getWeapon().getDefense()) +
                        " damage, taking " + actualDamage + " damage from " + attacker);
                attacker.TakeDamage(damage / 2, defendRoll, this);
            }
            else
            {
                // Failed and taking full damage
                setHealth(getHealth() - damage);
                Game.eventsPrintln(this + " fialed to defend and " + attacker + " dealt " + damage + " damage.");
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
                Game.eventsPrintln(this + " escaped temporarily, recovering " + (getHealth() - startHealth) + " health.");
            }
            else
            {
                Game.eventsPrintln(this + " failed to escape, taking " + damage + " damage.");
                setHealth(getHealth() - damage);
            }
        }
        else // Normal
        {
            setHealth(getHealth() - damage);
            Game.eventsPrintln(attacker + " dealt " + damage + " to " + this);
        }
    }

    // Run the players actions
    public void Run(Battle battle, PlayerActions actions)
    {
        setIsDefending(false);

        if (isFleeing)
        {
            isFleeing = false;
            int startHealth = getHealth();

            // Heal double speed or 6, whichever is higher
            if (speed < 3) setHealth(getHealth() + 6);
            else setHealth(getHealth() + (speed * 2));
            //Game.eventsPrintln(this + " escaped temporarily, recovering " + (getHealth() - startHealth) + " health.");
        }

        switch (actions)
        {
            case Attack:
                Attack(battle);
                break;
            case Defend:
                Defend(battle);
                break;
            case Flee:
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
        System.out.print(" (y or n): ");

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
    }

    public void ResetHealth() { setHealth(maxHealth); }

    public int getSpeed() { return speed; }
    public void setSpeed(int newSpeed) { speed = newSpeed; }

    public int getMaxHealth() { return maxHealth; }
    public void setMaxHealth(int newMax) { maxHealth = newMax; }

    public static Player GetPlayer()
    {
        if (player == null)
        {
            player = new Player();
        }

        return player;
    }
}
