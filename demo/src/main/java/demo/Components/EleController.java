package demo.Components;
import java.util.ArrayList; // Import lớp ArrayList
import java.util.List;

public class EleController {
    private List<element> elements; // them list cho element
    public ArrayList<Resistor> resistors;
    public ArrayList<Capacitor> capacitors;
    public ArrayList<Inductor> inductors;
    public VoltageSource voltage;
    private int circuitType; // Thêm trường type

    public EleController(double uValue, String voltageType, Resistor[] resistorArray, Capacitor[] capacitorArray, Inductor[] inductorArray, int circuitType) {
        this.voltage = new VoltageSource(voltageType, new Complex(uValue,0));
        resistors = new ArrayList<>();
        capacitors = new ArrayList<>();
        inductors = new ArrayList<>();
        for (Resistor resistor : resistorArray) {
            resistors.add(resistor);
        }

        for (Capacitor capacitor : capacitorArray) {
            capacitors.add(capacitor);
        }
        for (Inductor inductor : inductorArray) {
            inductors.add(inductor);
        }

        this.circuitType = circuitType; // Đặt giá trị cho trường type
    }

    
    public Complex getEquivalentImpedance(double frequency) throws Exception {
        Complex equivalentImpedance = new Complex(0, 0);
    
        if (circuitType == 1) { // Parallel circuit
            for (element element : elements) {
                if (element.getImpedance(frequency).getReal() == 0 && element.getImpedance(frequency).getImaginary() == 0) {
                    throw new Exception("Short circuit");
                }
                equivalentImpedance = equivalentImpedance.add(element.getImpedance(frequency).inverse());// mạch song song nên R phải lấy nghịch đảo của tổng nghịch đảo
            }
            equivalentImpedance = equivalentImpedance.inverse();
        } else if (circuitType == 2) { // Serial circuit
            for (element element : elements) {
                equivalentImpedance = equivalentImpedance.add(element.getImpedance(frequency));
            }
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
        System.out.println("Element\t\tImpedance\tVoltage\t\tCurrent");
        for (element element : elements) {
            Complex impedance = element.getImpedance(frequency);
            Complex voltage = getVoltage(element, frequency);
            Complex current = getCurrent(element, frequency);
            System.out.println(element.getClass().getSimpleName() + "\t\t" + impedance.getReal() + " + " + impedance.getImaginary() + "i\t" + voltage.getReal() + " + " + voltage.getImaginary() + "i\t" + current.getReal() + " + " + current.getImaginary() + "i");
        }
    }

}
    
    

