package usecases.usecase_GAE2;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import gameobject.Editable;

public class TileData implements Editable {
	// bound to TileView's coordinates
	ObjectProperty<Integer> x_coor = new SimpleObjectProperty<>();
	ObjectProperty<Integer> y_coor = new SimpleObjectProperty<>();

	@Override
	public void edit() {
	}

	public void setEditable(Editable editable) {

	}

	public Editable getEditable() {
		return null;
	}
}
