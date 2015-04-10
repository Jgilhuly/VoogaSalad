package gae.backend;

import java.io.Serializable;

import gae.gridView.Pair;
import gae.listView.EditableImage;
import gae.openingView.UIObject;


public interface Editable extends Serializable {
    public void edit ();

    public String getName ();

    public String getType ();

    public String getImagePath ();

    public void setLocation (double x, double y);

    public Pair getLocation ();

    public void setEditableImage (EditableImage image);

    public EditableImage getEditableImage ();

    public void setID (int id);
}
