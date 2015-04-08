package gae.listView;

import gae.backend.TempTower;
import gae.gridView.PathView;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


public class LeftPaneView {

    private String[] gameObjects = { "Tower", "Enemy", "Path" };
    private List<PaneList> listOfListObjects;
    private Group root;
    private Node nodeScene;
    private ObservableList pathObservableList;
    private PathView pathView;

    public Scene getScene () {
        root = new Group();
        root.getChildren().addAll(view(), tempButton());
        return new Scene(root);
    }

    public Group getGroup (Node pane, ObservableList pathList, PathView view) {
        this.nodeScene = pane;
        this.pathObservableList = pathList;
        this.pathView = view;

        root = new Group();
        root.getChildren().addAll(view(), tempButton());
        root.setManaged(false);
        return root;
    }

    private Node view () {
        listOfListObjects = new ArrayList<>();
        Accordion accordion = new Accordion();
        for (int i = 0; i < gameObjects.length; i++) {
            try {
                Class<?> className = Class.forName("gae.listView." + gameObjects[i] + "PaneList");
                Object instance = className.getConstructor().newInstance();
                listOfListObjects.add((PaneList) instance);
                Method setUpList = className.getMethod("initialize", Group.class, Node.class);
                accordion.getPanes().add((TitledPane) setUpList
                        .invoke(instance, root, nodeScene));
            }
            catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
                    | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                PathList pathList = new PathList(pathView);
                accordion.getPanes()
                        .add(pathList.getTitledPane(pathObservableList, gameObjects[i]));
            }
        }
        return accordion;
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
