package com.parsch;

/**
 * Created by unseenshadow2 on 9/29/2016.
 */
public abstract class Entity
{
    private int health = 25;
    private int baseAttack = 0;
    private int baseDefense = 0;
    private Weapon weapon;
    private boolean isDefending = false;
    private String name;

    public void Run(Battle battle)
    {
        isDefending = false;
    }

    public void Attack(Battle battle)
    {
        Entity other = (battle.GetPlayer() != this) ? battle.GetPlayer() : battle.GetFighter();

        System.out.println(this + " attempts to attack " + other);

        ToHit toHit = RollToHit(other);

        if (toHit.didHit)
        {
            other.TakeDamage((baseAttack + weapon.getAttack() + Game.RollD6()), toHit.hitRoll, this);
        }
        else
        {
            System.out.println(this + " has failed to hit " + other);
        }
    }

    public void Defend(Battle battle)
    {
        System.out.println(this + " takes a defensive stance.");
        isDefending = true;
    }

    public void TakeDamage(int damage, int toHit, Entity attacker)
    {
        if (!isDefending)
        {
            health -= damage;
            System.out.println(attacker + " dealt " + damage + " to " + this);
        }
        else
        {
            // Handle defending
            int defendRoll = Game.RollD20() + baseDefense + weapon.getDefense();

            if (defendRoll >= toHit)
            {
                int actualDamage = (damage - (baseDefense + weapon.getDefense()));
                actualDamage = (actualDamage < 0) ? 0 : actualDamage;
                health -= actualDamage;
                System.out.println(this + " negated " + (baseDefense + weapon.getDefense()) +
                        " damage, taking " + actualDamage + " damage from " + attacker);
                attacker.TakeDamage(damage / 2, defendRoll, this);
            }
            else
            {
                health -= damage;
                System.out.println(this + " fialed to defend and " + attacker + " dealt " + damage + " damage.");
            }
        }
    }

    public int getHealth() { return health; }
    public void setHealth(int newHealth) { health = newHealth; }

    public int getBaseAttack() { return baseAttack; }
    public void setBaseAttack(int newBaseAttack) { baseAttack = newBaseAttack; }

    public int getBaseDefense() { return baseDefense; }
    public void setBaseDefense(int newBaseDefense) { baseDefense = newBaseDefense; }

    public Weapon getWeapon() { return weapon; }
    public void setWeapon(Weapon newWeapon) { weapon = newWeapon; }

    public String getName() { return name; }
    public void setName(String newName) { name = newName; }

    public boolean getIsDefending() { return isDefending; }
    public void setIsDefending(boolean newDefending) { isDefending = newDefending; }

    public String toString()
    {
        return name;
    }

    private ToHit RollToHit(Entity enemy)
    {
        ToHit toReturn = new ToHit();

        // Roll to hit and determine if a hit was successful
        toReturn.hitRoll = baseAttack + Game.RollD20() + weapon.getAttack();
        toReturn.didHit = toReturn.hitRoll >= (enemy.getBaseDefense() + 10 + enemy.weapon.getDefense());

        return toReturn;
    }
}

class ToHit
{
    boolean didHit;
    int hitRoll;
}