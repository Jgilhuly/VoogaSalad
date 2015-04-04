package gae.gameView;

import gae.backend.GameManager;
import gae.openingView.OpeningView;
import gae.tabView.CentralTabView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TabPane;


// import gameobject.Editable;

public class GameView {

    // public EditorView createEditorViewInTab(Editable e);
    // public EditorView createEditorViewinPopup(Editable e);
    // public void createLevel();

    private static final String GAMEVIEW_CSS = "css/GameViewCSS.css";

    private BorderPane myUI;
    private CentralTabView myTabs; // replace with main editor
    private Scene myScene;
    private LibraryView myLibrary;
    private GenericObjectsPane myGenericObjects;
    private GameManager myGameManager;

    public GameView (GameManager gameManager) {
        initialize(gameManager);
    }

    public Scene getScene () {
        return myScene;
    }

    private void initialize (GameManager gameManager) {
        myGameManager = gameManager;
        
        myUI = new BorderPane();
        myScene = new Scene(myUI);
        myTabs = new CentralTabView(myScene);
        myUI.setPrefWidth(Main.SCREEN_WIDTH);
        myUI.setPrefHeight(Main.SCREEN_HEIGHT);

        myUI.setCenter(myTabs.getBaseNode());

        myGenericObjects = new GenericObjectsPane();
        myUI.setRight(myGenericObjects.getBaseNode());

        myScene.getStylesheets().add(GAMEVIEW_CSS);
        myLibrary = new LibraryView();

    }
}
