package gae.gridView;

import gae.listView.ContainerWrapper;
import gae.listView.LeftPaneView;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


public class WorldView {
    private StackPane stack;
    private PathView pathView;
    private Scene scene;
    private BorderPane border;
    private ObjectProperty<Image> backgroundProperty;
    private ContainerWrapper wrapper;
    private ObservableList<PathView> paths =
            FXCollections.observableArrayList();

    /*
     * Used by CentralTabView to create a new Level. Could be changed
     */
    public StackPane getStack (Scene scene) {
        stack = new StackPane();
        this.scene = scene;
        ImageView background = new ImageView(new Image("/images/Park_Path.png"));
        backgroundProperty = background.imageProperty();
        Group root = new Group();
        TileContainer container = new TileContainer(20, border);
        root.getChildren().addAll(background, container);

        stack.getChildren().addAll(root);

        background.fitWidthProperty().bind(container.widthProperty());
        background.fitHeightProperty().bind(container.heightProperty());

        wrapper = (ContainerWrapper) container;
        return stack;
    }

    public BorderPane getBorder (Scene scene) {
        border = new BorderPane();
        border.setCenter(getStack(scene));
        border.setLeft(getLeftView());
        return border;
    }

    private Group getLeftView () {
        StackPane stackPane = new StackPane();
        LeftPaneView lpview = new LeftPaneView();
        Group leftview =
                lpview.getGroup(stack, scene, paths, pathView, backgroundProperty, wrapper);
        stackPane.getChildren().add(leftview);
        return leftview;
    }
}
