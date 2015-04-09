package gae.listView;

import gae.backend.TempTower;
import gae.gridView.PathView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;


public class LeftPaneView {

    private String[] gameObjects = { "Tower", "Enemy", "Path" };
    private List<PaneList> listOfListObjects;
    private Group root;
    private Group objectGroup;
    private Node nodeScene;
    private ObservableList<PathView> pathObservableList;
    private PathView pathView;
    private Scene myScene;
    private Accordion accordion;
    private TitledPane pathTitledPane;
    private PathList pathList;

    public Scene getScene () {
        root = new Group();
        root.getChildren().addAll(view(), tempButton());
        return new Scene(root);
    }

    public Group getGroup (Node pane, Scene scene, ObservableList<PathView> pathList, PathView view) {
        this.nodeScene = pane;
        this.pathObservableList = pathList;
        this.pathView = view;
        this.myScene = scene;
        root = new Group();
        objectGroup = new Group();
        root.getChildren().addAll(view(), tempButton(), objectGroup);
        root.setManaged(false);
        return root;
    }

    private Node view () {
        listOfListObjects = new ArrayList<>();
        accordion = new Accordion();
        for (int i = 0; i < gameObjects.length; i++) {
            try {
                Class<?> className = Class.forName("gae.listView." + gameObjects[i] + "PaneList");
                Object instance = className.getConstructor().newInstance();
                listOfListObjects.add((PaneList) instance);
                Method setUpList =
                        className.getMethod("initialize", Group.class, Node.class, Scene.class);
                accordion.getPanes().add((TitledPane) setUpList
                        .invoke(instance, objectGroup, nodeScene, myScene));
            }
            catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
                    | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                pathList = new PathList(pathView, (StackPane) nodeScene, myScene);
                pathTitledPane =
                        pathList.getTitledPane(pathObservableList, gameObjects[i]);
                accordion.getPanes().add(pathTitledPane);
            }
        }
        initialize();
        return accordion;
    }

    private void initialize () {
        pathTitledPane.setOnMousePressed(event -> {
            for (PaneList lists : listOfListObjects) {
                lists.removeRoot();
                pathList.setScreen();
            }
        });
        for (TitledPane panes : accordion.getPanes()) {
            panes.setOnMouseClicked(event -> {
                if (!panes.equals(pathTitledPane)) {
                    for (PaneList lists : listOfListObjects) {
                        lists.addRoot();
                        pathList.disableScreen();
                    }
                }
            });
        }
    }

    public void addToList (EditableNode node) {
        for (PaneList paneList : listOfListObjects) {
            if (paneList.getType().equals(node.getType())) {
                paneList.addToGenericList(node);
            }
        }
    }

    private Button tempButton () {
        Button temp = new Button("add to List");
        temp.setTranslateX(0);
        temp.setTranslateY(500);
        EditableNode node = new EditableNode(new TempTower());
        temp.setOnAction(e -> addToList(node));
        return temp;
    }

}
