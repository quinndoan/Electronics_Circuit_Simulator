package demo.test;

import java.util.ArrayList;

import demo.Components.Capacitor;
import demo.Components.Complex;
import demo.Components.EleController;
import demo.Components.Resistor;
import demo.Components.element; 

public class TestDC_RC {
    public static void main(String[] args) {
        try {
            // Khởi tạo các phần tử trong mạch
            ArrayList<Resistor> resistors = new ArrayList<>();
            resistors.add(new Resistor(10)); 

            ArrayList<Capacitor> capacitors = new ArrayList<>();
            capacitors.add(new Capacitor(0.000001)); 

            // Tạo danh sách phần tử tổng hợp
            ArrayList<element> elements = new ArrayList<>();
            elements.addAll(resistors);
            elements.addAll(capacitors);

            // Khởi tạo danh sách tên phần tử
            ArrayList<String> elementList = new ArrayList<>();
            elementList.add("Resistor 1");
            elementList.add("Capacitor 1");

            // Khởi tạo EleController với các phần tử trong mạch
            EleController eleController = new EleController(5.0, "DC", resistors, capacitors, null, elements, 2, elementList);
            
            // Thực hiện tính toán
            Complex equivalentImpedance = eleController.getEquivalentImpedance();
            
            // In ra giá trị điện trở tương đương
            System.out.println("Equivalent Impedance for DC: " + equivalentImpedance);
            
            // In ra bảng phân tích mạch
            eleController.printCircuitAnalysisTable();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}