package test;


import Components.Capacitor;
import Components.EleController;
import Components.Inductor;
import Components.Resistor;
import Components.complexNum.Complex;

public class TestAC {
    public static void main(String[] args) {
        try {
            // Khởi tạo các phần tử trong mạch
            Resistor[] resistors = {
                new Resistor(100), // 100 ohm resistor
                new Resistor(200)  // 200 ohm resistor
            };
            Capacitor[] capacitors = {
                new Capacitor(0.000001) // 1 µF capacitor
            };
            Inductor[] inductors = {
                new Inductor(0.001) // 1 mH inductor
            };

            // Khởi tạo EleController với các phần tử trong mạch
            EleController eleController = new EleController(5.0, "DC", resistors, capacitors, inductors, 2); // 2 là mạch nối tiếp
            
            // Chạy hàm getEquivalentImpedance với tần số cụ thể (ví dụ: 60 Hz)
            double frequency = 60.0;
            Complex equivalentImpedance = eleController.getEquivalentImpedance(frequency);
            
            // In ra giá trị điện trở tương đương
            System.out.println("Equivalent Impedance: " + equivalentImpedance);
            
            // In ra bảng phân tích mạch
            eleController.printCircuitAnalysisTable(frequency);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
