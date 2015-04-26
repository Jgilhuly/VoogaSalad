package gae.tabView;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import gae.editorView.GameObjectEditorView;
import javafx.scene.Scene;
import javafx.scene.control.Tab;

/**
 * Object Editor Tab
 * @author JohnGilhuly
 *
 */

// Potentially combine this with the ShopTab using an abstract class

public class GameObjectEditorTab {

    private Tab baseNode;
    private GameObjectEditorView view;

    public GameObjectEditorTab (Scene scene, Consumer<Object> consumer, BiConsumer<Class<?>, Object> biConsumer) {
        baseNode = new Tab();
        baseNode.setText("Game Object Editor");

        view = new GameObjectEditorView(scene, consumer, biConsumer);
        baseNode.setContent(view.getObject());
        baseNode.setClosable(false);
    }

    public Tab getBaseTabNode () {
        return baseNode;
    }
}
