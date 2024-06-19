package test;

import java.util.ArrayList;

import Components.*;

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
            EleController eleController = new EleController(5.0, Double.POSITIVE_INFINITY, "DC", resistors, capacitors,
                    null, elements, 1, elementList);

            // Thực hiện tính toán
            Complex equivalentImpedance = eleController.getEquivalentImpedance(Double.POSITIVE_INFINITY);

            // In ra giá trị điện trở tương đương
            if (equivalentImpedance.getReal() == Double.POSITIVE_INFINITY
                    || equivalentImpedance.getImaginary() == Double.POSITIVE_INFINITY) {
                System.out.println("Equivalent Impedance for DC: Infinity");
            } else {
                System.out.println("Equivalent Impedance for DC: " + equivalentImpedance);
            }

            // In ra bảng phân tích mạch
            eleController.printCircuitAnalysisTable(Double.POSITIVE_INFINITY);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
