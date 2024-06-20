package Components;

import Components.complexNum.Complex; 
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
    this.elements = Elements != null ? Elements : new ArrayList<>();
    this.circuitType = circuitType;
    this.ElementList = ElementList;
    this.frequency = frequency;
}

    public double getFrequency() {
        return frequency;
    }

    public Complex getEquivalentImpedance(double frequency) throws Exception {
        Complex equivalentImpedance;
    
        if (circuitType == 1) { // Parallel circuit
            equivalentImpedance = new Complex(0, 0);
            for (element element : elements) {
                if (element instanceof Inductor && frequency == Double.POSITIVE_INFINITY) {
                    throw new Exception("Short circuit due to inductor");
                }
                Complex impedance = element.getImpedance(frequency);
                if (element instanceof Capacitor && frequency == Double.POSITIVE_INFINITY) {
                    impedance = new Complex(Double.POSITIVE_INFINITY, 0); // Infinite impedance for capacitors in DC
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
                if (element instanceof Inductor && frequency == Double.POSITIVE_INFINITY) {
                    throw new Exception("Short circuit due to inductor");
                }
                Complex impedance = element.getImpedance(frequency);
                if (element instanceof Capacitor && frequency == Double.POSITIVE_INFINITY) {
                    impedance = new Complex(Double.POSITIVE_INFINITY, 0); // Infinite impedance for capacitors in DC
                }
                equivalentImpedance = equivalentImpedance.add(impedance);
            }
        } else {
            throw new IllegalArgumentException("Invalid circuit type");
        }
    
        return equivalentImpedance;
    }
    
    public Complex getVoltage(element element, double frequency) {
        if (circuitType == 1) { // Parallel circuit
            if (element instanceof Capacitor && frequency == Double.POSITIVE_INFINITY) {
                return new Complex(0, 0); // Uc = 0 for capacitors in DC
            }
            return voltage.getVoltage(); // U = Vsource
        } else if (circuitType == 2) { // Serial circuit
            if (element instanceof Capacitor && frequency == Double.POSITIVE_INFINITY) {
                return new Complex(0, 0); // Uc = 0 for capacitors in DC
            }
            return getCurrent(element, frequency).multiply(element.getImpedance(frequency)); // U = I * R
        }
        return new Complex(0, 0); // Default case
    }
    
    public Complex getCurrent(element element, double frequency) {
        if (circuitType == 1) { // Parallel circuit
            if (element instanceof Capacitor && frequency == Double.POSITIVE_INFINITY) {
                return new Complex(0, 0); // Ic = 0 for capacitors in DC
            }
            return voltage.getVoltage().divide(element.getImpedance(frequency)); // I = Vsource / R
        } else if (circuitType == 2) { // Serial circuit
            if (element instanceof Capacitor && frequency == Double.POSITIVE_INFINITY) {
                return new Complex(0, 0); // Ic = 0 for capacitors in DC
            }
            try {
                return voltage.getVoltage().divide(getEquivalentImpedance(frequency));
            } catch (Exception e) {
                e.printStackTrace();
                return new Complex(0, 0);
            }
        }
        return new Complex(0, 0); // Default case
    }

    public void printCircuitAnalysisTable(double frequency) {
        System.out.println("Element\t\t\t\tImpedance\t\t\tVoltage\t\t\tCurrent");
        for (element element : elements) {
            Complex impedance = element.getImpedance(frequency);
            Complex voltage = getVoltage(element, frequency);
            Complex current = getCurrent(element, frequency);
            System.out.println(element.getClass().getSimpleName() + "\t\t\t" + impedance.getReal() + " + "
                    + impedance.getImaginary() + "i\t\t" + voltage.getReal() + " + " + voltage.getImaginary() + "i\t\t"
                    + current.getReal() + " + " + current.getImaginary() + "i");
        }
    }

}