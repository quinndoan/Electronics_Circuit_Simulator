package GUI_Components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public interface inputVoltage {
    Label sourceLabel = new Label();
    TextField inputsourcField = new TextField();
    Label giatri = new Label();
    ObservableList<String> list = FXCollections.observableArrayList("AC", "DC");

}
