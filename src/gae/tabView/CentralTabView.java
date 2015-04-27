package gae.tabView;

import engine.fieldsetting.Settable;
import engine.game.Game;
import engine.game.Level;
import engine.game.StoryBoard;
import gae.editor.EditingParser;
import gae.gameWorld.FixedGameWorldFactory;
import gae.gameWorld.FreeGameWorldFactory;
import gae.gameWorld.GameWorldFactory;
import gae.gridView.LevelView;
import gae.levelPreferences.LevelPreferencesEditor;
import gae.listView.LibraryData;
import gae.openingView.UIObject;
import gae.waveeditor.WaveEditor;
import gameworld.FreeWorld;
import gameworld.GameWorld;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


/**
 * Central container for the central tab view in the gae editor
 * 
 * @author JohnGilhuly
 *
 */

public class CentralTabView implements UIObject {
    private VBox baseNode;
    private TabPane tabView;
    private int levelCount;
    private Scene scene;
    private HudEditorTab hudTab;
    private LevelView levelView;
    private LibraryData libraryData;
    private Game game;
    private GameWorldFactory gameWorldFactory;
    private boolean editorInstantiated;
    private BooleanProperty freeWorld;

    public CentralTabView (Scene sceneIn, Game gameIn, String gameTypeIn) {
        freeWorld = new SimpleBooleanProperty();
        scene = sceneIn;
        game = gameIn;
        initialize(gameTypeIn);
    }

    private void initialize (String gameTypeIn) {
        libraryData = LibraryData.getInstance();
        levelCount = 1;

        baseNode = new VBox();
        tabView = new TabPane();
        // refactor this code
        ShopTab shopTab = new ShopTab();
        hudTab = new HudEditorTab(null);

        tabView.getTabs().addAll(shopTab.getBaseTabNode(), hudTab.getBaseTabNode());

        Button newLevel = new Button("Add Level");
        newLevel.setOnAction(e -> {
            if (!editorInstantiated) {
                GameObjectEditorTab gameObjectTab =
                        new GameObjectEditorTab(scene, getConsumer(), getBiconsumer());
                tabView.getTabs().add(gameObjectTab.getBaseTabNode());
                editorInstantiated = true;
            }
            createNewLevel();
        });
        baseNode.getChildren().addAll(newLevel, tabView);
        gameWorldFactory = createGameWorldFactory(gameTypeIn);
    }

    private GameWorldFactory createGameWorldFactory (String gameTypeIn) {

        if (gameTypeIn != null && gameTypeIn.equals("Free Path")) {
            return new FreeGameWorldFactory();
        }
        else {
            return new FixedGameWorldFactory();
        }
    }

    private void createNewLevel () {
        levelView = new LevelView(freeWorld);
        Pane levelViewPane = levelView.getBorder(scene);
        gameWorldFactory.bindGridSize(levelView.getGridDimensionProperty());
        GameWorld nextWorld = gameWorldFactory.createGameWorld();
        if (nextWorld instanceof FreeWorld) {
            FreeWorld game = (FreeWorld) nextWorld;
            LibraryData.getInstance().addFreeWorldPath(game.getPath());
            freeWorld.setValue(true);
        }
        Level levelData = null;
        StoryBoard sb = new StoryBoard();
        List<Method> levelMethods;

        try {
            levelData =
                    (Level) Class.forName(EditingParser
                            .getInterfaceClasses("engine.fieldsetting.implementing_classes")
                            .get("Level").get(0)).newInstance();

            levelMethods =
                    EditingParser.getMethodsWithAnnotation(Class.forName(levelData.getClass()
                            .getName()), Settable.class);

            for (Method m : levelMethods) {
                if (m.getName().equals("setStoryBoard")) {
                    m.invoke(levelData, sb);
                }
                if (m.getName().equals("setGameWorld")) {
                    m.invoke(levelData, nextWorld);
                }
                if (m.getName().equals("setImagePath")) {
                    m.invoke(levelData, levelView.getBackgroundImagePath());
                }
            }
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        game.getLevelBoard().addLevel(levelData);
        WaveEditor waves = new WaveEditor(sb, gameWorldFactory.createGameWorld());
        LevelPreferencesEditor prefs = new LevelPreferencesEditor();
        LevelTabSet newLevel =
                new LevelTabSet(levelViewPane,
                                waves.getObject(), prefs.getObject());
        Tab newTab = new Tab("Level:" + levelCount++);
        newTab.setContent(newLevel.getBaseNode());
        newTab.setClosable(false);
        tabView.getTabs().add(newTab);
        hudTab.setBackgroundImage(levelView.getBackgroundImage());
    }

    @Override
    public Node getObject () {
        return baseNode;
    }

    public Consumer<Object> getConsumer () {
        return e -> libraryData.addGameObjectToList(e);
    }

    public BiConsumer<Class<?>, Object> getBiconsumer () {
        BiConsumer<Class<?>, Object> biConsumer = (klass, o) -> {
            libraryData.addCreatedObjectToList(klass, o);
        };
        return biConsumer;
    }
}
