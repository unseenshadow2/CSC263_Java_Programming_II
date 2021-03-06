package parsch.enemies.gnolls;

import parsch.Battle;
import parsch.Game;
import parsch.Weapon;
import parsch.enemies.gnolls.Gnoll;

/**
 * Created by unseenshadow2 on 9/29/2016.
 */
public class GnollHunter extends Gnoll
{
    public GnollHunter()
    {
        setHealth(30);
        setBaseAttack(6);
        setBaseDefense(2);
        setName("Gnoll Hunter");
        setWeapon(Weapon.Bow);
        setDescription("A seemingly small gnoll, this creature carries a bow that seems to take more force than any man can "+
        "deliver just to draw it back. However, this creature doesn't seem to struggle at all.");
    }

    public void Run(Battle battle)
    {
        setIsDefending(false);

        int attChance = 3;
        int defChance = 3;

        if (getHealth() < 5) defChance += 3;
        if (battle.GetPlayer().getHealth() <= (battle.GetPlayer().getMaxHealth()/2)) attChance += 3;

        int totalChance = attChance + defChance;
        int choice = Game.random.nextInt(totalChance) + 1;

        if (choice <= attChance) Attack(battle);
        else Defend(battle);
    }
}
