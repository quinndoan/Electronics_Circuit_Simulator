package demo.Components;

import java.util.ArrayList;
import java.util.List;

public class EleController {
    private List<element> elements;
    public ArrayList<Resistor> resistors;
    public ArrayList<Capacitor> capacitors;
    public ArrayList<Inductor> inductors;
    public VoltageSource voltage;
    public ArrayList<String> ElementList;
    private int circuitType;
    public double frequency;

    public List<element> getElements() {
        return elements;
    }

    public ArrayList<String> getElementList() {
        return ElementList;
    }

    public EleController(double uValue, double frequency, String voltageType, ArrayList<Resistor> resistors,
            ArrayList<Capacitor> capacitors, ArrayList<Inductor> inductors, ArrayList<element> Elements,
            int circuitType, ArrayList<String> ElementList) {
        this.voltage = new VoltageSource(voltageType, new Complex(uValue, 0));
        this.resistors = resistors;
        this.capacitors = capacitors;
        this.inductors = inductors;
        this.elements = Elements;
        this.circuitType = circuitType;
        this.ElementList = ElementList;
        this.frequency = frequency;
    }

    public double getFrequency() {
        return frequency;
    }

    public EleController(double uValue, String voltageType, ArrayList<Resistor> resistors,
            ArrayList<Capacitor> capacitors, ArrayList<Inductor> inductors, ArrayList<element> Elements,
            int circuitType, ArrayList<String> ElementList) {
        this.voltage = new VoltageSource(voltageType, new Complex(uValue, 0));
        this.resistors = resistors;
        this.capacitors = capacitors;
        this.inductors = inductors;
        this.elements = Elements;
        this.circuitType = circuitType;
        this.ElementList = ElementList;
    }

    public Complex getEquivalentImpedance() throws Exception {
        Complex equivalentImpedance;
        boolean hasInductor = false;

        if (circuitType == 1) { // Parallel circuit
            equivalentImpedance = new Complex(0, 0);
            for (element element : elements) {
                Complex impedance = element.getImpedance(0); // DC case, frequency is 0
                if (element instanceof Inductor) {
                    hasInductor = true;
                } else if (element instanceof Capacitor) {
                    impedance = new Complex(Double.POSITIVE_INFINITY, 0); // Infinite impedance for capacitors in DC
                }
                if (impedance.getReal() == 0 && impedance.getImaginary() == 0 && element instanceof Inductor) {
                    throw new Exception("Short circuit due to inductor");
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
                Complex impedance = element.getImpedance(0); // DC case, frequency is 0
                if (element instanceof Inductor) {
                    hasInductor = true;
                } else if (element instanceof Capacitor) {
                    impedance = new Complex(Double.POSITIVE_INFINITY, 0); // Infinite impedance for capacitors in DC
                }
                if (impedance.getReal() == 0 && impedance.getImaginary() == 0 && element instanceof Inductor) {
                    throw new Exception("Short circuit due to inductor");
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
        if (circuitType == 1) { // Parallel circuit
            return voltage.getVoltage(); // U = Vsource
        } else if (circuitType == 2) { // Serial circuit
            return getCurrent(element).multiply(element.getImpedance(0)); // U = I * R
        }
        return new Complex(0, 0); // Default case
    }

    public Complex getCurrent(element element) {
        if (circuitType == 1) { // Parallel circuit
            Complex impedance = element.getImpedance(0);
            if (impedance.getReal() == Double.POSITIVE_INFINITY || impedance.getImaginary() == Double.POSITIVE_INFINITY) {
                return new Complex(0, 0); // I = 0 for infinite impedance
            }
            return voltage.getVoltage().divide(impedance); 
        } else if (circuitType == 2) { // Serial circuit
            try {
                return voltage.getVoltage().divide(getEquivalentImpedance());
            } catch (Exception e) {
                e.printStackTrace();
                return new Complex(0, 0);
            }
        }
        return new Complex(0, 0); // Default case
    }

    public boolean detectShortCircuit() {
        for (element element : elements) {
            Complex impedance = element.getImpedance(0); // DC case, frequency is 0
            if (impedance.getReal() == 0 && impedance.getImaginary() == 0 && element instanceof Inductor) {
                return true;
            }
        }
        return false;
    }

    public void printCircuitAnalysisTable() {
        System.out.println("Element\t\t\t\tImpedance\t\t\tVoltage\t\t\tCurrent");
        for (element element : elements) {
            Complex impedance = element.getImpedance(0); // DC case, frequency is 0
            if (element instanceof Capacitor) {
                impedance = new Complex(Double.POSITIVE_INFINITY, 0); // Infinite impedance for capacitors in DC
            }
            Complex voltage = getVoltage(element);
            Complex current = getCurrent(element);
            System.out.println(element.getClass().getSimpleName() + "\t\t\t" + impedance.getReal() + " + "
                    + impedance.getImaginary() + "i\t\t" + voltage.getReal() + " + " + voltage.getImaginary() + "i\t\t"
                    + current.getReal() + " + " + current.getImaginary() + "i");
        }
    }
}
