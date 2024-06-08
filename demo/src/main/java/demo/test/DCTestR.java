package demo.test;
import demo.Components.Complex;
import demo.Components.EleController;
import demo.Components.Resistor;

public class DCTestR {
    public static void main(String[] args) {
        try {
            // Khởi tạo các phần tử trong mạch
            Resistor[] resistors = {
                new Resistor(10) // 10 ohm resistor
            };

            // Khởi tạo EleController với các phần tử trong mạch
            EleController eleController = new EleController(5.0, "DC", resistors, null, null, 1); // 1 là mạch nối tiếp
        
            double frequency = 0.0;
            Complex equivalentImpedance = eleController.getEquivalentImpedance(frequency);
            
            // In ra giá trị điện trở tương đương
            System.out.println("Equivalent Impedance for DC: " + equivalentImpedance);
            
            // In ra bảng phân tích mạch
            eleController.printCircuitAnalysisTable(frequency);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
