package com.parsch.enemies.kobolds;

import com.parsch.Battle;
import com.parsch.Game;
import com.parsch.Weapon;

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
