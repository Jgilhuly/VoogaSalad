package gae.listView;

import engine.gameobject.PointSimple;
import exception.ObjectOutOfBoundsException;
import gae.backend.Editable;
import gae.editorView.DragIntoRectangle;
import gae.gridView.ContainerWrapper;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import View.ViewUtil;


public class DraggableUtilities {
    /**
     * places the node on the location clicked. When the location is clicked, a new instance of the
     * object is added to a list and a MovableImage is placed on the location.
     *
     * @param me: MouseEvent to bind the cursor to the EditableNode
     * @param editablenode: The EditableNode
     * @param node: Node that will be clicked on
     * @param instanceList: List of Editables
     * @param wrapper: The object which determines the bounds of where the object can be placed
     * @param root: where the Image will be placed
     */
    public static void makeEditablePlaceable (MouseEvent me,
                                              Editable editable,
                                              Node node,
                                              ObservableList<Editable> instanceList,
                                              ContainerWrapper wrapper,
                                              Group root) {
        ImageView transitionImage = editable.getImageView();
        // TODO: implement popup error when overlapping - collision detection
        Node binder =
                ViewUtil.bindCursor(transitionImage,
                                    node,
                                    ViewUtil
                                    .getMouseLocation(me, transitionImage),
                                    KeyCode.ESCAPE, false);
        binder.setOnMouseClicked(ev -> {
            Point2D current =
                    binder.localToParent(new Point2D(binder.getTranslateX(), binder
                                                     .getTranslateY()));
            Double currentX = current.getX();
            Double currentY = current.getY();
            if (wrapper.checkBounds(currentX, currentY)) {
                throw new ObjectOutOfBoundsException();
            }

            Editable newEditable = editable.makeNewInstance();
            instanceList.add(newEditable);
            PointSimple relativeLocation = wrapper.convertCoordinates(currentX, currentY);
            newEditable.setLocation(relativeLocation);
            MovableImage edimage =
                    new MovableImage(editable.getImageView(), newEditable, wrapper);
            newEditable.setMovableImage(edimage);
            edimage.relocate(currentX, currentY);
            root.getChildren().add(edimage);
        });
        root.getChildren().add(binder);
    }

    public static void makeImagePlaceable (MouseEvent me,
                                           ImageView image,
                                           Node node, DragIntoRectangle correct,
                                           Group root) {
        ImageView clone = new ImageView(image.getImage());
        ImageView imageView = (ImageView) ListViewUtilities.createCellContentWithIcon(clone);
        Node binder =
                ViewUtil.bindCursor(imageView,
                                    node,
                                    ViewUtil
                                    .getMouseLocation(me, imageView),
                                    KeyCode.ESCAPE, false);
        binder.setOnMouseClicked(ev -> {
            if (image.getBoundsInLocal().intersects(correct.getBoundsInLocal())) {
                // root.getChildren().add(imageView);
                // root.setTranslateX(correct.getTranslateX());
                // root.setTranslateY(correct.getTranslateY() + size/2);
                correct.setCorrect(imageView);
            }

        });
        root.getChildren().add(binder);
    }
}
