package parsch.EndScreen;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import parsch.Game;

/**
 * Controller class for the End Screen
 */
public class Controller
{
    @FXML
    private Label lblLostWon, lblTurnCount;

    @FXML
    void EndGame(ActionEvent event)
    {
        Platform.exit();
    }

    @FXML
    void initialize()
    {
        if (Game.GetGame().CheckEnemy() && (Game.GetGame().getCurrentBattle().GetPlayer().getHealth() > 0))
        {
            lblLostWon.setText("Congratulations! You won!!!");
            lblTurnCount.setText("In " + Integer.toString(parsch.GameScreen.Controller.turnCount) + " turns!!!");
        }
        else
        {
            lblLostWon.setText("Oh no, you lost! Maybe if you try again.");
            lblTurnCount.setText("Over " + Integer.toString(parsch.GameScreen.Controller.turnCount) + " turns.");
        }
    }
}
