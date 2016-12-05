package parsch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The program's main controlling class...
 */
public class Main extends Application
{
    static public Stage currentStage;
    static Main _main;

    public static Parent getStartScreen() throws Exception
    {
        return FXMLLoader.load(Main._main.getClass().getResource("StartScreen/screen.fxml"));
    }

    public static Parent getEndScreen() throws Exception
    {
        return FXMLLoader.load(Main._main.getClass().getResource("EndScreen/screen.fxml"));
    }

    public static Parent getLvlUpScreen() throws Exception
    {
        return FXMLLoader.load(Main._main.getClass().getResource("LevelUpScreen/screen.fxml"));
    }
    public static Parent getGameScreen() throws Exception
    {
        return FXMLLoader.load(Main._main.getClass().getResource("GameScreen/screen.fxml"));
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        _main = this;

        primaryStage.setTitle("Java Final Project");
        primaryStage.setScene(new Scene(getStartScreen(), 1000, 800));
        primaryStage.show();
        currentStage = primaryStage;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
