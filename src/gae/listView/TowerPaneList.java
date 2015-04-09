package gae.listView;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;


public class TowerPaneList extends PaneList {
    private ObservableList<TitledPane> towerPaneList;
    private List<EditableNode> towerEditablesList;
    private Group root;
    private Node workspace;
    private Scene scene;

    public TowerPaneList () {
        towerEditablesList = new ArrayList<>();
    }

    /*
     * TODO: Make it so that when Tower is clicked, all the tower objects on the grid will show up,
     * and when Path is clicked, these will all disappear because we don't want conflicts
     */
    @Override
    public void addToGenericList (EditableNode editableNode) {
        towerEditablesList.add(editableNode);
        TitledPane newPane = setTitledPaneClick(editableNode, root, workspace, scene);
        towerPaneList.add(newPane);
    }

    @Override
    public TitledPane initialize (Group root, Node node, Scene scene) {
        this.root = root;
        this.workspace = node;
        this.scene = scene;
        TitledPane pane = getTitledPane("Tower");
        towerPaneList = setAccordion(pane);
        return pane;
    }

    @Override
    public String getType () {
        return "Tower";
    }

}
