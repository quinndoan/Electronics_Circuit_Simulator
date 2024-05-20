package demo.Components;

public class Inductor extends element {
    public Inductor(double inductance) {
        super(inductance);
    }

    @Override
    public Complex getImpedance(double frequency) {
        return new Complex(0, 2 * Math.PI * frequency * value);
    }
}