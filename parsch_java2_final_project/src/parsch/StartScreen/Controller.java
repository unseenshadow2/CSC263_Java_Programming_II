package parsch.StartScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import parsch.Main;


/**
 * Controller class for the Start Screen
 */
public class Controller
{
    @FXML
    private Button btnPlayButton;

    @FXML
    public void PlayButtonClick(ActionEvent event)
    {
        try
        {
            Main.currentStage.getScene().setRoot(Main.getGameScreen());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

            // get a handle to the stage
            Stage stage = (Stage) btnPlayButton.getScene().getWindow();
            // Shut down the program
            stage.close();
        }
    }
}
