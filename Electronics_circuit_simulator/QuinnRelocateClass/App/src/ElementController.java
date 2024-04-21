import java.util.ArrayList;
import java.util.List;

public class ElementController {
    private List<Model.Element> elements;

    public ElementController() {
        elements = new ArrayList<>();
    }

    public void addResistor(double resistance) {
        Model.Resistor resistor = new Model.Resistor(resistance);
        addElement(resistor);
    }

    public void addCapacitor(double capacitance) {
        Model.Capacitor capacitor = new Model.Capacitor(capacitance);
        addElement(capacitor);
    }

    public void addInductor(double inductance) {
        Model.Inductor inductor = new Model.Inductor(inductance);
        addElement(inductor);
    }

    public void addElement(Model.Element element) {
        elements.add(element);
    }

    public List<Model.Element> getElements() {
        return elements;
    }
}
