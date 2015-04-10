package gae.tabView;

import gae.gridView.WorldView;
import gae.openingView.UIObject;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;


public class CentralTabView implements UIObject{
    private VBox baseNode;
    private TabPane tabView;
    private int levelCount;
    private Scene scene;

    public CentralTabView (Scene sceneIn) {
        scene = sceneIn;
        initialize();
    }

    private void initialize () {
        levelCount = 1;

        baseNode = new VBox();
        tabView = new TabPane();
        ShopTab shopTab = new ShopTab();
        HudEditorTab hudTab = new HudEditorTab(null);
        tabView.getTabs().addAll(shopTab.getBaseTabNode(), hudTab.getBaseTabNode());

        Button newLevel = new Button("Add Level");
        newLevel.setOnAction(e -> createNewLevel());

        baseNode.getChildren().addAll(newLevel, tabView);
    }

    private void createNewLevel () {
        WorldView worldView = new WorldView();
        LevelPreferencesTab levelPrefs = new LevelPreferencesTab();
        LevelTabSet newLevel =
                new LevelTabSet(worldView.getBorder(scene), levelPrefs.getStack());
        // temporarily --> real code is :
        // LevelTabSet newLevel = new LevelTabSet(worldView.getStack(scene),levelPrefs.getStack());
        Tab newTab = new Tab("Level:" + levelCount++);
        newTab.setContent(newLevel.getBaseNode());
        newTab.setClosable(false);
        tabView.getTabs().add(newTab);
    }

    @Override
    public Node getObject () {
        return baseNode;
    }
}
