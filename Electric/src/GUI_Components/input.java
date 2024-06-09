package GUI_Components;

import java.util.ArrayList;

import Components.Capacitor;
import Components.Inductor;
import Components.Resistor;
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
    public ArrayList<Resistor> CircuitResistor = new ArrayList<>();
    public ArrayList<Inductor> CircuitInductor = new ArrayList<>();
    public ArrayList<Capacitor> CircuitCapacitor = new ArrayList<>();
    public Label sourceLabel = new Label();
    public TextField inputsourcField = new TextField();
    public Label giatri = new Label();
    public ObservableList<String> list = FXCollections.observableArrayList("AC", "DC");
}
