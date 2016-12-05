package parsch.enemies.kobolds;

import parsch.Battle;
import parsch.Game;
import parsch.Weapon;

/**
 * Created by unseenshadow2 on 9/29/2016.
 */
public class KoboldSorcerer extends Kobold
{
    public KoboldSorcerer()
    {
        setHealth(20);
        setBaseAttack(3);
        setBaseDefense(6);
        setWeapon(Weapon.Dragonstaff);
        setName("Kobold Sorcerer");
        setDescription("Carrying around a staff with what appears to be a carving of a dragon's head at the end, this "+
        "magic wielding kobold begins firing balls of fire and bolts of lighting at you almost as soon as it sees you, "+
        "with what appears to be no regard for its own life.");
    }

    public void Run(Battle battle)
    {
        setIsDefending(false);

        int attChance = 3;
        int defChance = 5;

        if (getHealth() < 5) defChance += 3;
        if (battle.GetPlayer().getHealth() <= (battle.GetPlayer().getMaxHealth()/2)) attChance += 3;

        int totalChance = attChance + defChance;
        int choice = Game.random.nextInt(totalChance) + 1;

        if (choice <= attChance) Attack(battle);
        else Defend(battle);
    }
}
