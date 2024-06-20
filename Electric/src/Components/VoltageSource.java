package Components;

import Components.complexNum.Complex;

public class VoltageSource {
	public String type;
	public Complex uValue;

	public VoltageSource(String type, Complex uValue) {
		this.type = type;
		this.uValue = uValue;
	}

	public Complex getVoltage() {
		return uValue;
	}

}