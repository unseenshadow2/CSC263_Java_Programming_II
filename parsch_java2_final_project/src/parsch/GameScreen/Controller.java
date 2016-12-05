package parsch.GameScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import parsch.*;
import javafx.scene.control.Alert.AlertType;

import java.util.Optional;

/**
 * Controller class for the Game Screen
 */
public class Controller
{
    public static int turnCount = 0;

    // Player Labels
    @FXML private Label txtHealthValue, txtAttackValue, txtDefenseValue, txtSpeedValue, txtCurrentWeapon;
    @FXML private Label txtHealthWB, txtAttackWB, txtDefenseWB, txtSpeedWB;

    // Enemy Labels
    @FXML private Label txtEnemyTitle, txtEnemyHealthValue, txtEnemyAttackValue, txtEnemyDefenseValue;
    @FXML private Label txtEnemyHealthWB, txtEnemyAttackWB, txtEnemyDefenseWB;
    @FXML private Label txtEnemyCurrentWeapon, txtEnemyDescription;

    // Buttons
    @FXML private Button btnAttack, btnDefend, btnFlee;

    @FXML
    void PlayerAttack(ActionEvent event) { RunRound(PlayerActions.Attack); }

    @FXML
    void PlayerDefend(ActionEvent event) { RunRound(PlayerActions.Defend); }

    @FXML
    void PlayerFlee(ActionEvent event) { RunRound(PlayerActions.Flee); }

    // Run the round with the action given
    void RunRound(PlayerActions actions)
    {
        ++turnCount;
        SetButtonsEnabled(false); // Pause the buttons until processing complete

        // Run the round
        VictoryReturn didWin; // Did we win
        didWin = Game.GetGame().getCurrentBattle().RunFight(actions);

        // Display what happened
        DisplayAndClearEventsLog();

        // Check if we won or lost and act appropriately
        if (didWin==VictoryReturn.Win)
        {
            DisplayWeaponPickupMenu();

            if (Game.GetGame().CheckEnemy())
            {
                DisplayGameVictory();
            }
            else
            {
                DisplayLvlMenu();
            }
        }
        else if (didWin==VictoryReturn.Lose) DisplayLoseMenu();

        // Update the display and release the buttons
        UpdateTextDisplay();
        SetButtonsEnabled(true);
    }

    void DisplayAndClearEventsLog()
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Round " + Integer.toString(turnCount));
        alert.setHeaderText(null);
        alert.setContentText(Game.getEvents());

        alert.showAndWait();
        Game.clearEvents();
    }

    // Enable or disable the buttons
    void SetButtonsEnabled(boolean set)
    {
        if ((btnAttack != null) && (btnDefend != null) && (btnFlee != null))
        {
            btnAttack.setDisable(!set);
            btnDefend.setDisable(!set);
            btnFlee.setDisable(!set);
        }
    }

    void DisplayLvlMenu()
    {
        try
        {
            Main.currentStage.getScene().setRoot(Main.getLvlUpScreen());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

            // get a handle to the stage
            Stage stage = (Stage) btnAttack.getScene().getWindow();
            // Shut down the program
            stage.close();
        }
    }

    void DisplayLoseMenu()
    {
        try
        {
            Main.currentStage.getScene().setRoot(Main.getEndScreen());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

            // get a handle to the stage
            Stage stage = (Stage) btnAttack.getScene().getWindow();
            // Shut down the program
            stage.close();
        }
    }

    void DisplayGameVictory()
    {
        try
        {
            Main.currentStage.getScene().setRoot(Main.getEndScreen());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

            // get a handle to the stage
            Stage stage = (Stage) btnAttack.getScene().getWindow();
            // Shut down the program
            stage.close();
        }
    }

    void DisplayWeaponPickupMenu()
    {
        Battle battle = Game.GetGame().getCurrentBattle();

        // Setup Dialog
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Claim the Enemy's Weapon?");
        alert.setHeaderText(null);
        alert.setContentText("\nThe " + battle.GetFighter() + " had a " + battle.GetFighter().getWeapon() +
                ". Would you like to take the weapon?");

        // Setup Buttons
        ButtonType btpYes = new ButtonType("Yes");
        ButtonType btpNo = new ButtonType("No");
        alert.getButtonTypes().setAll(btpYes, btpNo);

        // Run Dialog
        Optional<ButtonType> result = alert.showAndWait();

        // React
        if (result.get() == btpYes) { battle.GetPlayer().setWeapon(battle.GetFighter().getWeapon()); }
    }

    // Update the text displays
    public void UpdateTextDisplay()
    {
        // Player
        txtHealthValue.setText(Integer.toString(Player.GetPlayer().getHealth()));
        txtHealthWB.setText("0");
        txtAttackValue.setText(Integer.toString(Player.GetPlayer().getBaseAttack()));
        txtAttackWB.setText(Integer.toString(Player.GetPlayer().getWeapon().getAttack()));
        txtDefenseValue.setText(Integer.toString(Player.GetPlayer().getBaseDefense()));
        txtDefenseWB.setText(Integer.toString(Player.GetPlayer().getWeapon().getDefense()));
        txtSpeedValue.setText(Integer.toString(Player.GetPlayer().getSpeed()));
        txtSpeedWB.setText("0");
        txtCurrentWeapon.setText(Player.GetPlayer().getWeapon().getName());

        // Enemy
        Entity enemy = Game.GetGame().getCurrentBattle().GetFighter();
        txtEnemyTitle.setText(enemy.getName());
        txtEnemyDescription.setText(enemy.getDescription());
        txtEnemyHealthValue.setText(Integer.toString(enemy.getHealth()));
        txtEnemyHealthWB.setText("0");
        txtEnemyAttackValue.setText(Integer.toString(enemy.getBaseAttack()));
        txtEnemyAttackWB.setText(Integer.toString(enemy.getWeapon().getAttack()));
        txtEnemyDefenseValue.setText(Integer.toString(enemy.getBaseDefense()));
        txtEnemyDefenseWB.setText(Integer.toString(enemy.getWeapon().getDefense()));
        txtEnemyCurrentWeapon.setText(enemy.getWeapon().getName());
    }

    @FXML
    void initialize()
    {
        SetButtonsEnabled(true);
        Player.GetPlayer().ResetHealth();
        UpdateTextDisplay();
    }
}
