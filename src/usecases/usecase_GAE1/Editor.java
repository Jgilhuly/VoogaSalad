package usecases.usecase_GAE1;

import gae.backend.Editable;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * This class is for taking in an Editable object and referencing the property
 * file to see which fields can be change by the author.
 * 
 */
public class Editor {
	private HashMap<String, String[]> fieldMap;
	private Editable editable;

	public Editor(Editable editable) {
		this.editable = editable;
		fieldMap = useResourceBundle();
	}

	private String[] getFields() {
		return fieldMap.get(editable.getClass().getSimpleName());
	}

	public void showFields() {
		Class<?> editableClass = editable.getClass();
		for (String fieldName : getFields()) {
			try {
				Field field = editableClass.getDeclaredField(fieldName);
				field.setAccessible(true);
				Object value = field.get(editable);
				// System.out.println("Field is: " + field);
				// System.out.println("Value is: " + value);
				// if (!fieldName.equals("Weapon")){
				// field.set(editable, 10);
				// Object newValue = field.get(editable);
				// System.out.println("New Value is: " + newValue);
				// }
			} catch (NoSuchFieldException | SecurityException
					| IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Key is the type of object, its values are the editable fields
	 */
	private HashMap<String, String[]> useResourceBundle() {
		ResourceBundle resources = ResourceBundle
				.getBundle("Example_Data/Reflection_Data");
		Enumeration<String> paramKeys = resources.getKeys();
		HashMap<String, String[]> newMap = new HashMap<>();

		while (paramKeys.hasMoreElements()) {
			String Key = paramKeys.nextElement();
			newMap.put(Key, resources.getString(Key).split(",\\s+"));
		}
		return newMap;
	}

	// public static void main(String[] args) {
	// ConcreteTower ct = new ConcreteTower();
	// Editor ee = new Editor(ct);
	// ee.showFields();
	// }
}
