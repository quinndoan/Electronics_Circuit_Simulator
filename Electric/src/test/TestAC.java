package test;


import demo.Components.Capacitor; // Import các lớp cần thiết
import demo.Components.Complex;
import demo.Components.EleController;
import demo.Components.Inductor;
import demo.Components.Resistor;

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
