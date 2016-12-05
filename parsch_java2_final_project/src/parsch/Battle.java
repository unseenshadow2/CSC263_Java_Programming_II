package parsch;

/**
 * Created by unseenshadow2 on 9/29/2016.
 */
public class Battle
{
    private Player player;

    private Entity fighter;

    public VictoryReturn RunFight(PlayerActions actions)
    {
        if ((player.getHealth() > 0) && (fighter.getHealth() > 0))
        {
//            System.out.println("\nCurrent Status:");
//            System.out.println(player + "'s health is " + player.getHealth());
//            System.out.println(fighter + "'s health is " + fighter.getHealth());
            player.Run(this, actions);
            fighter.Run(this);
        }

        if ((player.getHealth() > 0) && (fighter.getHealth() <= 0))// Victory, returns true
        {
            //player.RunVictoryMenu(this);
            return VictoryReturn.Win;
        }
        else if ((player.getHealth() <= 0))  // Defeat, returns false
        {
            //player.RunDefeatMenu(this);
            return VictoryReturn.Lose;
        }
        else return VictoryReturn.Continue;
    }

    public Player GetPlayer() { return player; }
    public void SetPlayer(Player f1) { player = f1; }

    public Entity GetFighter() { return fighter; }
    public void SetFighter(Entity f2) { fighter = f2; }
}
