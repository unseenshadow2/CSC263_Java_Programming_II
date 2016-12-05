package parsch.LevelUpScreen;

/**
 * Controller class for the Game Screen
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import parsch.Main;
import parsch.Player;

public class Controller
{
    int pointsToSpend;

    @FXML private Button btnDone, btnMaxHealthLvl, btnAttackLvl, btnDefenseLvl, btnSpeedLvl;
    @FXML private Label lblMaxHealth, lblAttack, lblDefense, lblSpeed;

    void CheckBtnDone()
    {
        --pointsToSpend;

        if (pointsToSpend <= 0)
        {
            btnDone.setDisable(false);
            btnAttackLvl.setDisable(true);
            btnMaxHealthLvl.setDisable(true);
            btnDefenseLvl.setDisable(true);
            btnSpeedLvl.setDisable(true);
        }

        CheckLvlBtn();
    }

    // Check if the level buttons should be enabled or not
    void CheckLvlBtn()
    {
        if (Player.GetPlayer().getBaseAttack() > 5) btnAttackLvl.setDisable(true);
        if (Player.GetPlayer().getBaseDefense() > 5) btnDefenseLvl.setDisable(true);
        if (Player.GetPlayer().getSpeed() > 5) btnSpeedLvl.setDisable(true);
        if (Player.GetPlayer().getMaxHealth() > 75) btnMaxHealthLvl.setDisable(true);

        if (lblMaxHealth != null) lblMaxHealth.setText(Integer.toString(Player.GetPlayer().getMaxHealth()));
        if (lblAttack != null) lblAttack.setText(Integer.toString(Player.GetPlayer().getBaseAttack()));
        if (lblDefense != null) lblDefense.setText(Integer.toString(Player.GetPlayer().getBaseDefense()));
        if (lblSpeed != null) lblSpeed.setText(Integer.toString(Player.GetPlayer().getSpeed()));
    }

    @FXML
    void AttackLvlClick(ActionEvent event)
    {
        Player.GetPlayer().setBaseAttack(Player.GetPlayer().getBaseAttack()+1);
        CheckBtnDone();
    }

    @FXML
    void DefenseLvlClick(ActionEvent event)
    {
        Player.GetPlayer().setBaseDefense(Player.GetPlayer().getBaseDefense()+1);
        CheckBtnDone();
    }

    @FXML
    void MaxHealthLvlClick(ActionEvent event)
    {
        Player.GetPlayer().setMaxHealth(Player.GetPlayer().getMaxHealth()+5);
        CheckBtnDone();
    }

    @FXML
    void SpeedLvlClick(ActionEvent event)
    {
        Player.GetPlayer().setSpeed(Player.GetPlayer().getSpeed()+1);
        CheckBtnDone();
    }

    @FXML // Go back to the game
    void DoneButtonClick(ActionEvent event)
    {
        try
        {
            Main.currentStage.getScene().setRoot(Main.getGameScreen());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

            // get a handle to the stage
            Stage stage = (Stage) btnDone.getScene().getWindow();
            // Shut down the program
            stage.close();
        }
    }

    @FXML
    void initialize()
    {
        assert btnDone != null : "fx:id=\"btnDone\" was not injected: check your FXML file 'screen.fxml'.";
        assert btnMaxHealthLvl != null : "fx:id=\"btnMaxHealthLvl\" was not injected: check your FXML file 'screen.fxml'.";
        assert btnAttackLvl != null : "fx:id=\"btnAttackLvl\" was not injected: check your FXML file 'screen.fxml'.";
        assert btnDefenseLvl != null : "fx:id=\"btnDefenseLvl\" was not injected: check your FXML file 'screen.fxml'.";
        assert btnSpeedLvl != null : "fx:id=\"btnSpeedLvl\" was not injected: check your FXML file 'screen.fxml'.";
        assert lblMaxHealth != null : "fx:id=\"lblMaxHealth\" was not injected: check your FXML file 'screen.fxml'.";
        assert lblAttack != null : "fx:id=\"lblAttack\" was not injected: check your FXML file 'screen.fxml'.";
        assert lblDefense != null : "fx:id=\"lblDefense\" was not injected: check your FXML file 'screen.fxml'.";
        assert lblSpeed != null : "fx:id=\"lblSpeed\" was not injected: check your FXML file 'screen.fxml'.";

        pointsToSpend = 3;
        btnDone.setDisable(true);

        CheckLvlBtn();
    }
}