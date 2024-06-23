package Test;
import Components.Capacitor;
import Components.EleController;
import Components.Inductor;
import Components.Resistor;
import Components.element;
import Components.ComplexNum.Complex;

import java.util.ArrayList;

public class TestAC {
    public static void main(String[] args) {
        try {
            // Define elements
            Resistor resistor = new Resistor(100); // 100 ohms
            Capacitor capacitor = new Capacitor(1e-6); // 1 uF
            Inductor inductor = new Inductor(1e-3); // 1 mH

            // Create lists of elements
            ArrayList<Resistor> resistors = new ArrayList<>();
            resistors.add(resistor);

            ArrayList<Capacitor> capacitors = new ArrayList<>();
            capacitors.add(capacitor);

            ArrayList<Inductor> inductors = new ArrayList<>();
            inductors.add(inductor);

            ArrayList<element> elements = new ArrayList<>();
            elements.add(resistor);
            elements.add(capacitor);
            elements.add(inductor);

            ArrayList<String> elementList = new ArrayList<>();
            elementList.add("Resistor");
            elementList.add("Capacitor");
            elementList.add("Inductor");

            // Set circuit parameters
            double voltageValue = 10; // 10V
            double frequency = 1000; // 1kHz
            int circuitType = 1; // Parallel circuit

            // Create EleController instance
            EleController controller = new EleController(voltageValue, frequency, "AC", resistors, capacitors, inductors, elements, circuitType, elementList);

            // Print equivalent impedance
            Complex equivalentImpedance = controller.getEquivalentImpedance(frequency);
            System.out.println("Equivalent Impedance: " + equivalentImpedance.getReal() + " + " + equivalentImpedance.getImaginary() + "i");

            // Print circuit analysis table
            controller.printCircuitAnalysisTable(frequency);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}