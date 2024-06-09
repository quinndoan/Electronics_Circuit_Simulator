package GUI_Components;

import java.util.ArrayList;

import Components.Capacitor;
import Components.Inductor;
import Components.Resistor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class input {
    protected ArrayList<TextField> textFieldsR;
    protected ArrayList<TextField> textFieldsL;
    protected ArrayList<TextField> textFieldsC;
    protected int R = 0;
    protected int C = 0;
    protected int L = 0;
    protected int RCount = 0;
    protected int CCount = 0;
    protected int LCount = 0;
    protected String AC_Voltage;
    protected String AC_Frequency;
    protected String DC_Voltage;
    protected ArrayList<Resistor> CircuitResistor = new ArrayList<>();
    protected ArrayList<Inductor> CircuitInductor = new ArrayList<>();
    protected ArrayList<Capacitor> CircuitCapacitor = new ArrayList<>();
    protected Label sourceLabel = new Label();
    protected TextField inputsourcField = new TextField();
    protected Label giatri = new Label();
    protected ObservableList<String> list = FXCollections.observableArrayList("AC", "DC");
}
