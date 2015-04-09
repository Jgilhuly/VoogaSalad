package gae.listView;

import gae.backend.Editable;
import View.ViewUtilities;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;



public abstract class PaneList {
    public static final int THUMBNAIL_SIZE = 20;

    public abstract TitledPane initialize (Group root, Node node, Scene scene);

    public abstract void addToGenericList (EditableNode node);

    public abstract String getType ();

    protected TitledPane getTitledPane (String text) {
        TitledPane pane = new TitledPane();
        pane.setText(text);
        pane.setTextFill(Color.RED);
        return pane;
    }

    protected ObservableList<TitledPane> setAccordion (TitledPane pane) {
        Accordion accordion = new Accordion();
        pane.setContent(accordion);
        return accordion.getPanes();
    }

    protected TitledPane setTitledPaneClick (EditableNode node, Group root, Node pane, Scene scene) {
        TitledPane newPane = new TitledPane();
        newPane.setText(node.getName());
        root.setManaged(false);
        ObservableList<Editable> editableList = node.getChildrenList();
        newPane.setContent(ListViewUtilities.createList(editableList, scene));
        newPane.setOnMousePressed(me -> {
            if (me.isSecondaryButtonDown()) {
                ContextMenu contextmenu = new ContextMenu();
                MenuItem item = new MenuItem("New");
                item.setOnAction(ae -> {
                    ImageView transitionImage = node.getImageView();
                    // TODO: fix issue of not being able to use bindcursor if cursor is above
                    // already
                    // placed tower (group is above stack)
                    Node binder =
                            ViewUtilities.bindCursor(transitionImage,
                                                     pane,
                                                     ViewUtilities
                                                             .getMouseLocation(me, transitionImage),
                                                     KeyCode.ESCAPE);
                    makeNodePlaceable(binder, node, root);
                    root.getChildren().add(binder);

                });
                contextmenu.getItems().add(item);
                contextmenu.show(newPane, me.getSceneX(), me.getSceneY());

            }
        });
        return newPane;
    }

    private void makeNodePlaceable (Node binder, EditableNode node, Group root) {
        binder.setOnMouseClicked(ev -> {
            Point2D current =
                    binder.localToParent(new Point2D(binder.getTranslateX(), binder
                            .getTranslateY()));
            Double currentX = current.getX();
            Double currentY = current.getY();

            Editable newEditable = node.makeNewInstance();
            newEditable.setLocation(currentX, currentY);
            EditableImage edimage = new EditableImage(node.getImageView(), newEditable);
            newEditable.setEditableImage(edimage);
            edimage.relocate(currentX, currentY);
            root.getChildren().add(edimage);

            // for (Editable editables : editableList) {
            // System.out.println("X IS : " + editables.getLocation().getX());
            // System.out.println("Y IS : " + editables.getLocation().getY());
            // System.out.println();
            // }

        });
    }

}
