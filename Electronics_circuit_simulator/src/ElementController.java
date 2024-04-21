import java.util.ArrayList;
import java.util.List;

public class ElementController {
    private Model model;

    public ElementController() {
        model = new Model();
    }

    public void addResistor(double resistance) {
        Resistor resistor = new Resistor(resistance);
        model.addElement(resistor);
    }

    public void addCapacitor(double capacitance) {
        Capacitor capacitor = new Capacitor(capacitance);
        model.addElement(capacitor);
    }

    public void addInductor(double inductance) {
        Inductor inductor = new Inductor(inductance);
        model.addElement(inductor);
    }

    public void setVoltageSource(double voltage) {
        VoltageSource voltageSource = new VoltageSource(voltage);
        model.setVoltageSource(voltageSource);
    }  


    // Nested classes for elements
    public abstract static class Element {
        protected double value;

        public Element(double value) {
            this.value = value;
        }

        public abstract double getValue();
    }

    private static class Resistor extends Element {
        public Resistor(double resistance) {
            super(resistance);
        }

        @Override
        public double getValue() {
            return value;
        }
    }

    private static class Capacitor extends Element {
        public Capacitor(double capacitance) {
            super(capacitance);
        }

        @Override
        public double getValue() {
            return value;
        }
    }

    private static class Inductor extends Element {
        public Inductor(double inductance) {
            super(inductance);
        }

        @Override
        public double getValue() {
            return value;
        }
    }

    public static class VoltageSource {
        private double voltage;

        public VoltageSource(double voltage) {
            this.voltage = voltage;
        }

        public double getVoltage() {
            return voltage;
        }
    }
}
