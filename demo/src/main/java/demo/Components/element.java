package demo.Components;

public abstract class element {
	protected double value;
	public element(double value) {
		 this.value = value;
	}
	public double getValue() {
    	return value;
    }
	public abstract Complex getImpedance(double frequency);

    public Complex getVoltage(Complex current, double frequency) {
        return current.multiply(getImpedance(frequency)); // xu ly Complex
		// cho truong hop AC 
    }

    public Complex getCurrent(Complex voltage, double frequency) {
        return voltage.divide(getImpedance(frequency));
    }
}
