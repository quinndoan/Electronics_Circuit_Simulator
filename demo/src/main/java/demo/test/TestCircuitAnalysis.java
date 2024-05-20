package demo.test;
import demo.Components.Capacitor;
import demo.Components.Complex;
import demo.Components.EleController;
import demo.Components.Inductor;
import demo.Components.Resistor;
public class TestCircuitAnalysis {
    public static void main(String[] args) {
        // Tạo một mạch để kiểm tra
        Resistor[] resistors = {new Resistor(10), new Resistor(20)};
        Capacitor[] capacitors = {new Capacitor(0.01)};
        Inductor[] inductors = {new Inductor(0.001)};
        EleController circuit = new EleController(5, "DC", resistors, capacitors, inductors, 2);

        // Tính toán giá trị mong đợi
        Complex expectedImpedance = new Complex(30, 0); // Thay đổi giá trị này theo mạch của bạn
        Complex expectedVoltage = new Complex(5, 0); // Thay đổi giá trị này theo mạch của bạn
        Complex expectedCurrent = expectedVoltage.divide(expectedImpedance);

        // Kiểm tra giá trị tính toán được
        try {
            Complex calculatedImpedance = circuit.getEquivalentImpedance(0);
            Complex calculatedVoltage = circuit.getVoltage(resistors[0], 0);
            Complex calculatedCurrent = circuit.getCurrent(resistors[0], 0);

            System.out.println("Calculated equivalent R: " + calculatedImpedance);
            System.out.println("Calculated U: " + calculatedVoltage);
            System.out.println("Calculated I: " + calculatedCurrent);

            System.out.println("Check equivalent R: " + (calculatedImpedance.equals(expectedImpedance) ? "Đúng" : "Sai"));
            System.out.println("Check U: " + (calculatedVoltage.equals(expectedVoltage) ? "Đúng" : "Sai"));
            System.out.println("Check I: " + (calculatedCurrent.equals(expectedCurrent) ? "Đúng" : "Sai"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
