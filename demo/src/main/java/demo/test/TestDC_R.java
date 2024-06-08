package demo.test;

import java.util.ArrayList;

import demo.Components.Complex;
import demo.Components.EleController;
import demo.Components.Resistor;
import demo.Components.element;

public class TestDC_R {
    public static void main(String[] args) {
        try {
            // Khởi tạo các phần tử trong mạch
            ArrayList<Resistor> resistors = new ArrayList<>();
            resistors.add(new Resistor(10)); // 10 ohm resistor

            // Tạo danh sách phần tử tổng hợp
            ArrayList<element> elements = new ArrayList<>();
            elements.addAll(resistors);

            // Khởi tạo danh sách tên phần tử
            ArrayList<String> elementList = new ArrayList<>();
            elementList.add("Resistor");

            // Khởi tạo EleController với các phần tử trong mạch
            EleController eleController = new EleController(5.0, 0.0, "DC", resistors, null, null, elements, 1, elementList);
            
            // Chạy hàm getEquivalentImpedance với tần số cụ thể (ví dụ: 0 Hz cho DC)
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
