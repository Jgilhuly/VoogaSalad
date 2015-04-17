package gae.backend;

import java.io.Serializable;
import engine.gameobject.PointSimple;
import gae.gridView.Path;
import gae.listView.MovableImage;


public interface Editable extends Serializable {
    public void edit ();

    public String getName ();

    public String getType ();

    public String getImagePath ();

    public PointSimple getLocation ();

    public int getID ();

    public Path getPath ();
    
    public int getWidth();
    
    public int getHeight();

    public void setLocation (PointSimple point);

    public void setMovableImage (MovableImage image);

    public MovableImage getMovableImage ();

    public void setID (int id);

    public void setPath (Path path);

    public void setWidth (int width);

    public void setHeight (int height);

    public Object clone ();

}
