package parsch.enemies.kobolds;

import parsch.Battle;
import parsch.Entity;
import parsch.Game;
import parsch.Weapon;

/**
 * Created by unseenshadow2 on 9/29/2016.
 */
public class Kobold extends Entity
{
    public Kobold()
    {
        setHealth(10);
        setBaseAttack(1);
        setBaseDefense(4);
        setWeapon(Weapon.Spear);
        setName("Kobold");
        setDescription("Small lizard people who claim to be related to dragons. Much like the great beasts, their hearts are"+
        " filled with only hate.");
    }

    public void Run(Battle battle)
    {
        setIsDefending(false);

        int attChance = 1;
        int defChance = 3;

        if (getHealth() < 5) defChance += 3;
        if (battle.GetPlayer().getHealth() <= (battle.GetPlayer().getMaxHealth()/2)) attChance += 3;

        int totalChance = attChance + defChance;
        int choice = Game.random.nextInt(totalChance) + 1;

        if (choice <= attChance) Attack(battle);
        else Defend(battle);
    }
}
