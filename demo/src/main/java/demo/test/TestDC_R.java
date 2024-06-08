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
            ArrayList<Resistor> resistorsSeries = new ArrayList<>();
            resistorsSeries.add(new Resistor(10)); 
            resistorsSeries.add(new Resistor(20)); 

            ArrayList<Resistor> resistorsParallel = new ArrayList<>();
            resistorsParallel.add(new Resistor(10)); 
            resistorsParallel.add(new Resistor(20)); 

            // Tạo danh sách phần tử tổng hợp cho mạch nối tiếp
            ArrayList<element> elementsSeries = new ArrayList<>();
            elementsSeries.addAll(resistorsSeries);

            // Tạo danh sách phần tử tổng hợp cho mạch song song
            ArrayList<element> elementsParallel = new ArrayList<>();
            elementsParallel.addAll(resistorsParallel);

            // Khởi tạo danh sách tên phần tử
            ArrayList<String> elementListSeries = new ArrayList<>();
            elementListSeries.add("Resistor 1");
            elementListSeries.add("Resistor 2");

            ArrayList<String> elementListParallel = new ArrayList<>();
            elementListParallel.add("Resistor 1");
            elementListParallel.add("Resistor 2");

            // Khởi tạo EleController với các phần tử trong mạch nối tiếp
            EleController eleControllerSeries = new EleController(5.0, Double.POSITIVE_INFINITY, "DC", resistorsSeries, null, null, elementsSeries, 2, elementListSeries);
            
            // Khởi tạo EleController với các phần tử trong mạch song song
            EleController eleControllerParallel = new EleController(5.0, Double.POSITIVE_INFINITY, "DC", resistorsParallel, null, null, elementsParallel, 1, elementListParallel);

            // Chạy hàm getEquivalentImpedance cho mạch nối tiếp
            Complex equivalentImpedanceSeries = eleControllerSeries.getEquivalentImpedance(Double.POSITIVE_INFINITY);
            
            // In ra giá trị điện trở tương đương cho mạch nối tiếp
            System.out.println("Equivalent Impedance for Series Circuit (DC): " + equivalentImpedanceSeries);
            
            // In ra bảng phân tích mạch nối tiếp
            System.out.println("Series Circuit Analysis:");
            eleControllerSeries.printCircuitAnalysisTable(Double.POSITIVE_INFINITY);

            // Chạy hàm getEquivalentImpedance cho mạch song song
            Complex equivalentImpedanceParallel = eleControllerParallel.getEquivalentImpedance(Double.POSITIVE_INFINITY);
            
            // In ra giá trị điện trở tương đương cho mạch song song
            System.out.println("Equivalent Impedance for Parallel Circuit (DC): " + equivalentImpedanceParallel);
            
            // In ra bảng phân tích mạch song song
            System.out.println("Parallel Circuit Analysis:");
            eleControllerParallel.printCircuitAnalysisTable(Double.POSITIVE_INFINITY);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
