package demo.Components;

import java.util.ArrayList; // Import lớp ArrayList
import java.util.List;

import demo.Components.complexNum.Complex;

public class EleController {
    private List<element> elements; // them list cho element
    public ArrayList<Resistor> resistors;
    public ArrayList<Capacitor> capacitors;
    public ArrayList<Inductor> inductors;
    public VoltageSource voltage;
    public ArrayList<String> ElementList;
    private int circuitType; // Thêm trường type
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
        this.circuitType = circuitType; // Đặt giá trị cho trường type
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
        this.circuitType = circuitType; // Đặt giá trị cho trường type
        this.ElementList = ElementList;
    }

    public Complex getEquivalentImpedance(double frequency) throws Exception {
        Complex equivalentImpedance;

        if (circuitType == 1) { // Parallel circuit
            equivalentImpedance = new Complex(0, 0);
            for (element element : elements) {
                Complex impedance = element.getImpedance(frequency);
                if (impedance.getReal() == 0 && impedance.getImaginary() == 0) {
                    throw new Exception("Short circuit");
                }
                equivalentImpedance = equivalentImpedance.add(impedance.inverse());
            }
            equivalentImpedance = equivalentImpedance.inverse();
        } else if (circuitType == 2) { // Serial circuit
            equivalentImpedance = new Complex(0, 0);
            for (element element : elements) {
                Complex impedance = element.getImpedance(frequency);
                equivalentImpedance = equivalentImpedance.add(impedance);
            }
        } else {
            throw new IllegalArgumentException("Invalid circuit type");
        }

        if (equivalentImpedance.getReal() == 0 && equivalentImpedance.getImaginary() == 0) {
            throw new Exception("Short circuit");
        }

        return equivalentImpedance;
    }

    public Complex getVoltage(element element, double frequency) {
        if (circuitType == 1) { // Parallel circuit
            return voltage.getVoltage(); // U = Vsource
        } else if (circuitType == 2) { // Serial circuit
            return getCurrent(element, frequency).multiply(element.getImpedance(frequency)); // U = I * R
        }
        return new Complex(0, 0); // Default case
    }

    public Complex getCurrent(element element, double frequency) {
        if (circuitType == 1) { // Parallel circuit
            return voltage.getVoltage().divide(element.getImpedance(frequency)); // I = Vsource / R
        } else if (circuitType == 2) { // Serial circuit
            try {
                return voltage.getVoltage().divide(getEquivalentImpedance(frequency));
            } catch (Exception e) {
                e.printStackTrace();
                return new Complex(0, 0); // Trả về một giá trị mặc định khi có lỗi
            }

        }
        return new Complex(0, 0); // Default case
    }

    public boolean detectShortCircuit(double frequency) {
        for (element element : elements) {
            if (element.getImpedance(frequency).getReal() == 0 && element.getImpedance(frequency).getImaginary() == 0) {
                return true;
            }
        }
        return false;
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
