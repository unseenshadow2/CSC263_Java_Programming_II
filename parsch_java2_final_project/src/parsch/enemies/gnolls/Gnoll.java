package parsch.enemies.gnolls;

import parsch.Battle;
import parsch.Entity;
import parsch.Game;
import parsch.Weapon;

/**
 * Created by unseenshadow2 on 9/29/2016.
 */
public class Gnoll extends Entity
{
    public Gnoll()
    {
        setHealth(15);
        setBaseAttack(3);
        setBaseDefense(0);
        setWeapon(Weapon.AxeAndShield);
        setName("Gnoll");
        setDescription("Large, mangy hyena men, the creature stands nearly 2 feet taller than you. Gnolls are known for "+
        "there love of the meat of intelligent creatures. This one is drooling as it raises its axe.");
    }

    public void Run(Battle battle)
    {
        setIsDefending(false);

        int attChance = 3;
        int defChance = 2;

        if (getHealth() < 5) defChance += 3;
        if (battle.GetPlayer().getHealth() <= (battle.GetPlayer().getMaxHealth()/2)) attChance += 5;

        int totalChance = attChance + defChance;
        int choice = Game.random.nextInt(totalChance) + 1;

        if (choice <= attChance) Attack(battle);
        else Defend(battle);
    }
}
