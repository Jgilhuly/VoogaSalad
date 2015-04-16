package gae.listView;

import java.util.Iterator;
import View.ImageUtilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import gae.backend.Editable;
import gae.openingView.UIObject;


/**
 * A class that takes in an Editable and keeps a list of its copied instances
 * 
 * @author Kei
 *
 */
public class EditableNode implements UIObject {
    private String myName;
    private String myType;
    private Editable editable;
    private int myID = 0;
    private ObservableList<Editable> myChildren;

    public EditableNode (Editable editable) {
        myName = editable.getName();
        myType = editable.getType();
        myChildren = FXCollections.observableArrayList();
        this.editable = editable;
    }

    public void clearChildren () {
        myChildren = FXCollections.observableArrayList();
    }

    public Editable makeNewInstance () {
        Editable copy = (Editable) DeepCopy.copy(editable);
        copy.setID(myID);
        myID++;
//        myChildren.add(copy);
        return copy;
    }

    public void remove (Editable editable) {
        Iterator<Editable> iter = myChildren.iterator();
        while (iter.hasNext()) {
            if (iter.next().equals(editable)) {
                iter.remove();
            }
        }
    }

    public ImageView getImageView () {
        return ImageUtilities.changeImageSize(new ImageView(new Image(editable.getImagePath())),
                                              75, 75);
    }

    public String getName () {
        return myName;
    }

    public String getType () {
        return myType;
    }

    public ObservableList<Editable> getChildrenList () {
        return myChildren;
    }

    @Override
    public boolean equals (Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        return true;
    }

    @Override
    public Node getObject () {
        // TODO Auto-generated method stub
        return null;
    }

}
