package demo.Components;

public class Resistor extends element {
    public Resistor(double resistance) {
        super(resistance);
    }
    @Override
    public Complex getImpedance(double frequency) {
        return new Complex(value, 0);
    }
}

