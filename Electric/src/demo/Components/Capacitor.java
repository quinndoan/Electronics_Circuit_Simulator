package demo.Components;

import demo.Components.complexNum.Complex;

public class Capacitor extends element {
    public Capacitor(double capacitance) {
        super(capacitance);
    }

    @Override
    public Complex getImpedance(double frequency) {
        return new Complex(0, -1 / (2 * Math.PI * frequency * value));
    }
}
