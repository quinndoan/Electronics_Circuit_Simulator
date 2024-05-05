package GUI_Components;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

public abstract class input {
    public ArrayList<TextField> textFieldsR;
    public ArrayList<TextField> textFieldsL;
    public ArrayList<TextField> textFieldsC;
    public ObservableList<String> list3 = FXCollections.observableArrayList("F", "kF", "MF");
    public ObservableList<String> list1 = FXCollections.observableArrayList("Ω", "kΩ", "MΩ");
    public ObservableList<String> list2 = FXCollections.observableArrayList("H", "kH", "MH");
    public int R = 0;
    public int C = 0;
    public int L = 0;

}
