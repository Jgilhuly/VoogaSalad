package gae.gridView;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;


public class Anchor extends Circle {
    private static final int PATH_OFFSET = 20;
    private PathLabel label;

    public Anchor (Color color, double x, double y, PathLabel label) {
        super(x, y, 15);
        init(color);
        label.setLayoutX(x + PATH_OFFSET);
        label.setLayoutY(y + PATH_OFFSET);
        this.label = label;

    }

    public Anchor (Color color, double x, double y) {
        super(x, y, 15);
        init(color);
    }

    private void init (Color color) {
        setFill(color.deriveColor(1, 1, 1, 0.5));
        setStroke(color);
        setStrokeWidth(2);
        setStrokeType(StrokeType.OUTSIDE);
        enableDrag();
    }

    public void bind (DoubleProperty x, DoubleProperty y) {
        x.bind(centerXProperty());
        y.bind(centerYProperty());
    }

    private void enableDrag () {
        final Pair dragDelta = new Pair(0, 0);
        // setOnMousePressed(e -> {
        // dragDelta.x = getCenterX() - e.getX();
        // dragDelta.y = getCenterY() - e.getY();
        // getScene().setCursor(Cursor.MOVE);
        // });
        //
        // setOnMouseReleased(e -> {
        // getScene().setCursor(Cursor.HAND);
        // });
        
        // FIX: dragDelta doesn't really matter anymore
        setOnMouseDragged(e -> {
            double newX = e.getX() + dragDelta.getX();
            try {
                if (newX > 0 && newX < getScene().getWidth()) {
                    setCenterX(newX);
                    label.setLayoutX(newX + PATH_OFFSET);
                }
                double newY = e.getY() + dragDelta.getY();
                if (newY > 0 && newY < getScene().getHeight()) {
                    setCenterY(newY);
                    label.setLayoutY(newY + PATH_OFFSET);
                }
            }
            catch (NullPointerException nullPointer) {
                if (newX > 0 && newX < getScene().getWidth()) {
                    setCenterX(newX);
                }
                double newY = e.getY() + dragDelta.getY();
                if (newY > 0 && newY < getScene().getHeight()) {
                    setCenterY(newY);
                }
            }
        });
        setOnMouseEntered(e -> {
            if (!e.isPrimaryButtonDown()) {
                getScene().setCursor(Cursor.HAND);
            }
        });
        setOnMouseExited(e -> {
            if (!e.isPrimaryButtonDown()) {
                getScene().setCursor(Cursor.DEFAULT);
            }
        });
    }

    public boolean checkIntersect (Node object) {
        return this.getBoundsInParent().intersects(object.getBoundsInParent());
    }
}
