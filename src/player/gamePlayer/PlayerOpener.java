package player.gamePlayer;

import engine.game.Game;
import gae.gameView.Main;
import java.io.File;
import java.util.Arrays;
import xml.DataManager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * Opens the game player. It will ideally have a few pre-authored games for the user to play but
 * also allow the user to upload new games he or she created as well.
 *
 * @author Brandon Choi
 *
 */
public class PlayerOpener implements GameScene {

    private static final String headerText = "Select a Game";

    private Stage myStage;
    private Scene playerScene;
    private BorderPane view;
    private HBox options;
    private HBox headerBox;
    private Text header;
    private Button loadB, playB;
    private GameSelector gameSelector;
    private Game myGame;

    public PlayerOpener (Stage s) {
        myStage = s;
        view = new BorderPane();
        playerScene = new Scene(view);
        options = new HBox(50);
        headerBox = new HBox();
        header = new Text(headerText);
        headerBox.getChildren().add(header);
        gameSelector = new GameSelector(playerScene);

        /* CSS */
        playerScene.getStylesheets().add("/css/GamePlayerCSS.css");
        options.setId("optionBox");
        header.setId("playerHeader");
        headerBox.setId("headerBox");

        setUpBorderPane();
    }

    @Override
    public Scene getScene () {
        return playerScene;
    }

    /**
     * sets up functionalities of buttons on the UI
     */
    private void setUpButtons () {
        loadB = new Button("LOAD GAME");
        loadB.setOnMousePressed(e -> {
            File file=openFileChooser();
            myGame=DataManager.readFromXML(Game.class, file.getAbsolutePath());
            moveToNextScreen(myGame);
        });

        playB = new Button("PLAY");
        playB.setOnMousePressed(e -> {
            myGame=DataManager.readFromXML(Game.class, "./src/xml/Game.xml");
            moveToNextScreen(myGame);
            // PauseScene pause = new PauseScene(null, myStage, playerScene);
            // myStage.setScene(pause.getScene());

        });

        Arrays.asList(loadB, playB).forEach(e -> {
            e.setId("playerButton");
            // e.setFocusTraversable(false);
        });

        createOptions();
    }

    private void moveToNextScreen(Game game) {
        GamePlayerScreen screen = new GamePlayerScreen(myStage, playerScene,game);
        screen.setImage(gameSelector.getCurrentImage());
        myStage.setScene(screen.makeScene());
    }
    
    private File openFileChooser () {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter(
                                                "XML files (*.xml)", "*.xml");
        fc.getExtensionFilters().add(extFilter);
        Stage fileStage = new Stage();
        
        
        File file=fc.showOpenDialog(fileStage);
        return file;
        
    }

    /**
     * adds buttons to options box
     */
    private void createOptions () {
        options.getChildren().addAll(loadB, playB);
    }

    /**
     * sets up different border areas of the border pane
     */
    private void setUpBorderPane () {
        view.setCenter(gameSelector.getChooser());
        headerBox.setAlignment(Pos.CENTER);
        view.setTop(headerBox);
        setUpButtons();
        view.setBottom(options);
        options.setAlignment(Pos.CENTER);
    }
}
