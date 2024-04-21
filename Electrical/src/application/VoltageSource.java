package application;

public class VoltageSource {
	public String type;
	public double uValue;
	
	public VoltageSource(String type, double uValue) {
		this.type = type;
		this.uValue = uValue;
	}
	public double getVoltage() {
        return uValue;
    }
}
