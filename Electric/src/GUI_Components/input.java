package GUI_Components;

import java.util.ArrayList;

import demo.Components.Capacitor;
import demo.Components.Inductor;
import demo.Components.Resistor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public abstract class input {
    public ArrayList<TextField> textFieldsR;
    public ArrayList<TextField> textFieldsL;
    public ArrayList<TextField> textFieldsC;
    public int R = 0;
    public int C = 0;
    public int L = 0;
    public int RCount = 0;
    public int CCount = 0;
    public int LCount = 0;
    public String AC_Voltage;
    public String AC_Frequency;
    public String DC_Voltage;
    public Resistor[] CircuitResistor = new Resistor[10];
    public Inductor[] CircuitInductor = new Inductor[10];
    public Capacitor[] CircuitCapacitor = new Capacitor[10];
    public Label sourceLabel = new Label();
    public TextField inputsourcField = new TextField();
    public Label giatri = new Label();
    public ObservableList<String> list = FXCollections.observableArrayList("AC", "DC");
}
