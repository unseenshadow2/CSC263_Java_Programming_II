package com.parsch;

/**
 * Created by unseenshadow2 on 9/29/2016.
 */
public enum Weapon
{
    SwordAndShield(2,4,"Sword and Shield"),
    TwoHandedSword(3,3,"Two Handed Sword"),
    DualSwords(6,0,"Dual Swords"),
    DualAxes(4,2,"Dual Axes"),
    GreatAxe(2,4,"Great Axe"),
    AxeAndShield(1,5,"AxeAndShield"),
    Spear(2,4,"Spear"),
    Bow(0,6,"Bow"),
    Crossbow(1,5,"Crossbow"),
    Dragonstaff(3,3,"Dragonstaff");

    private int attack;
    private int defense;
    private String name;

    Weapon(int a, int d, String n)
    {
        attack = a;
        defense = d;
        name = n;
    }

    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public String getName() { return name; }
    public String toString() { return getName(); }
}
