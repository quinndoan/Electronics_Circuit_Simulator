package Components;

import Components.complexNum.Complex;

public class Capacitor extends element {
    private double capacitance;

    public Capacitor(double capacitance) {
        super(capacitance);
    }

    public double getCapacitance() {
        return capacitance;
    }

    @Override
    public Complex getImpedance(double frequency) {
        if (frequency == Double.POSITIVE_INFINITY) {
            return new Complex(Double.POSITIVE_INFINITY, 0); // Rc = Infinity for capacitors in DC
        }
        double impedance = 1 / (2 * Math.PI * frequency * capacitance);
        return new Complex(0, -1 / (2 * Math.PI * frequency * capacitance));
    }
}