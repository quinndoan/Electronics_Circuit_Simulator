package demo.Components;

import java.util.ArrayList; // Import lớp ArrayList
import java.util.List;


public class EleController {
    private List<element> elements; // them list cho element
    public ArrayList<Resistor> resistors;
    public ArrayList<Capacitor> capacitors;
    public ArrayList<Inductor> inductors;
    public VoltageSource voltage;
    public ArrayList<String> ElementList;
    private int circuitType; // Thêm trường type
    private double frequency; // Change to private and remove public access

    public List<element> getElements() {
        return elements;
    }

    public ArrayList<String> getElementList() {
        return ElementList;
    }

    public EleController(double uValue, String voltageType, ArrayList<Resistor> resistors,
                         ArrayList<Capacitor> capacitors, ArrayList<Inductor> inductors, ArrayList<element> elements,
                         int circuitType, ArrayList<String> ElementList) {
        this.voltage = new VoltageSource(voltageType, new Complex(uValue, 0));
        this.resistors = resistors;
        this.capacitors = capacitors;
        this.inductors = inductors;
        this.elements = elements;
        this.circuitType = circuitType;
        this.ElementList = ElementList;
        this.frequency = voltageType.equals("DC") ? Double.POSITIVE_INFINITY : 0; // Set frequency to infinity if DC
    }

    private double getFrequency() {
        return frequency;
    }

    public Complex getEquivalentImpedance() throws Exception {
        double frequency = getFrequency();
        Complex equivalentImpedance;

        if (circuitType == 1) { // Parallel circuit
            equivalentImpedance = new Complex(0, 0);
            for (element element : elements) {
                Complex impedance = element.getImpedance(frequency);
                if (impedance.getReal() == 0 && impedance.getImaginary() == 0) {
                    throw new Exception("Short circuit");
                }
                if (impedance.getReal() == Double.POSITIVE_INFINITY || impedance.getImaginary() == Double.POSITIVE_INFINITY) {
                    continue; // Skip adding infinity impedances
                }
                equivalentImpedance = equivalentImpedance.add(impedance.inverse());
            }
            equivalentImpedance = equivalentImpedance.inverse();
        } else if (circuitType == 2) { // Serial circuit
            equivalentImpedance = new Complex(0, 0);
            for (element element : elements) {
                Complex impedance = element.getImpedance(frequency);
                if (impedance.getReal() == 0 && impedance.getImaginary() == 0) {
                    throw new Exception("Short circuit");
                }
                equivalentImpedance = equivalentImpedance.add(impedance);
            }
        } else {
            throw new IllegalArgumentException("Invalid circuit type");
        }

        if (equivalentImpedance.getReal() == Double.POSITIVE_INFINITY || equivalentImpedance.getImaginary() == Double.POSITIVE_INFINITY) {
            return new Complex(0, 0); // Assume result is 0 for divide by infinity
        }

        return equivalentImpedance;
    }

    public Complex getVoltage(element element) {
        double frequency = getFrequency();

        if (circuitType == 1) { // Parallel circuit
            return voltage.getVoltage(); // U = Vsource
        } else if (circuitType == 2) { // Serial circuit
            return getCurrent(element).multiply(element.getImpedance(frequency)); // U = I * R
        }
        return new Complex(0, 0); // Default case
    }

    public Complex getCurrent(element element) {
        double frequency = getFrequency();

        if (circuitType == 1) { // Parallel circuit
            return voltage.getVoltage().divide(element.getImpedance(frequency)); // I = Vsource / R
        } else if (circuitType == 2) { // Serial circuit
            try {
                return voltage.getVoltage().divide(getEquivalentImpedance());
            } catch (Exception e) {
                e.printStackTrace();
                return new Complex(0, 0); // Trả về một giá trị mặc định khi có lỗi
            }
        }
        return new Complex(0, 0); // Default case
    }

    public boolean detectShortCircuit() {
        double frequency = getFrequency();
        for (element element : elements) {
            if (element.getImpedance(frequency).getReal() == 0 && element.getImpedance(frequency).getImaginary() == 0) {
                return true;
            }
        }
        return false;
    }

    public void printCircuitAnalysisTable() {
        double frequency = getFrequency();

        System.out.println("Element\t\t\t\tImpedance\t\t\tVoltage\t\t\tCurrent");
        for (element element : elements) {
            Complex impedance = element.getImpedance(frequency);
            Complex voltage = getVoltage(element);
            Complex current = getCurrent(element);
            System.out.println(element.getClass().getSimpleName() + "\t\t\t" + impedance.getReal() + " + "
                    + impedance.getImaginary() + "i\t\t" + voltage.getReal() + " + " + voltage.getImaginary() + "i\t\t"
                    + current.getReal() + " + " + current.getImaginary() + "i");
        }
    }
}
