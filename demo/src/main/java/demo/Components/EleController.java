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
        this.voltage = new VoltageSource(voltageType, uValue);
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

    // Các phương thức khác...

    // public Complex getEquivalentImpedance(double frequency) {
    //     Complex equivalentImpedance = new Complex(0, 0);
    // // Trường hợp mạch nối tiếp
    //     // Tính toán trở kháng tương đương cho các resistor
    //     for (Resistor resistor : resistors) {
    //         equivalentImpedance = equivalentImpedance.add(resistor.getImpedance(frequency));
    //     }
    
    //     // Tính toán trở kháng tương đương cho các capacitor
    //     for (Capacitor capacitor : capacitors) {
    //         equivalentImpedance = equivalentImpedance.add(capacitor.getImpedance(frequency));
    //     }
    
    //     // Tính toán trở kháng tương đương cho các inductor
    //     for (Inductor inductor : inductors) {
    //         equivalentImpedance = equivalentImpedance.add(inductor.getImpedance(frequency));
    //     }
    
    //     return equivalentImpedance;
    // }
    public Complex getEquivalentImpedance(double frequency) {
        Complex equivalentImpedance = new Complex(0, 0);
    
        if (circuitType == 1) { // Parallel circuit
            for (element element : elements) {
                equivalentImpedance = equivalentImpedance.add(element.getImpedance(frequency).inverse());
            }
            equivalentImpedance = equivalentImpedance.inverse();
        } else if (circuitType == 2) { // Serial circuit
            for (element element : elements) {
                equivalentImpedance = equivalentImpedance.add(element.getImpedance(frequency));
            }
        }
    
        return equivalentImpedance;
    }
    
    
}
