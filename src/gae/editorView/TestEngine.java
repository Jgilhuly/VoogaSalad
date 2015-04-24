package gae.editorView;

import View.EngineView;
import View.GameWriter;
import View.ViewConcrete2;
import engine.game.Game;
import engine.game.Player;
import gae.gameView.Main;
import gameworld.GameWorld;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import xml.DataManager;


public class TestEngine extends Application {
    private static final String FILE_SOURCE = "src/xml/Game.xml";

    @Override
    public void start (Stage primaryStage) throws Exception {
        Group root = new Group();
        primaryStage.setHeight(600); // needs to account for scaling; add constants
        primaryStage.setWidth(600);// needs to account for scaling; add constants

        Scene scene = new Scene(root);
        Game game = loadGame();
        System.out.println("Read");
        EngineView view = new ViewConcrete2(game, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        // pane.setCenter(view.initializeView());

        // view.addButton(addWavesButtonTest(event,myPlayer), 0, 0);

        root.getChildren().add(view.initializeView());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Game loadGame () {
        GameWriter gw = new GameWriter();
        GameWorld world = gw.makeWorld();
        Player player = gw.makePlayer();
        return gw.makeGame(player, world, gw.makeShop(player, world));
        //return DataManager.readFromXML(Game.class, FILE_SOURCE);
//        return DataManager.readFromXML(Game.class, FILE_SOURCE);


    }

    public static void main (String[] args) {
        launch(args);
    }

}
