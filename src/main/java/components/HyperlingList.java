package components;

import javafx.beans.DefaultProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;

/**
 * Created by Adam on 2015-12-01.
 */
@DefaultProperty(value = "children")
public class HyperlingList extends Parent {
    @Override
    public ObservableList<Node> getChildren() {
        return super.getChildren();
    }

    public String setSpecificAttribute(String str) {
        return "asd";
    }
}
