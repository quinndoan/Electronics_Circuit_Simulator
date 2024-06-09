package Components;

import Components.complexNum.Complex;

public class VoltageSource {
	public String type;
	public Complex uValue;

	public String getType() {
		return type;
	}

	public VoltageSource(String type, Complex uValue) {
		this.type = type;
		this.uValue = uValue;
	}

	public Complex getVoltage() {
		return uValue;
	}
	// trường hợp là nguồn DC thì lấy phần real làm giá trị Voltage
	// và phần ảo để là 0i
}
