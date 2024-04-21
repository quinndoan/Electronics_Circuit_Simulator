/**
 * 
 */
package application;
import java.util.ArrayList; // Import lớp ArrayList

/**
 * 
 */
public class EleController {
    public ArrayList<Resistor> resistors;
    public ArrayList<Capacitor> capacitors;
    public ArrayList<Inductor> inductors;
    public VoltageSource voltage;
	/**
	 * 
	 */
    
	public EleController(double uValue, String type, Resistor[] resistorArray, Capacitor[] capacitorArray, Inductor[] inductorArray) {
		// TODO Auto-generated constructor stub
		this.voltage = new VoltageSource(type, uValue);
		resistors = new ArrayList<>();
        capacitors = new ArrayList<>();
        inductors = new ArrayList<>();
        for (Resistor resistor : resistorArray) {
            resistors.add(resistor);
        }

        // Thêm các giá trị capacitor vào ArrayList
        for (Capacitor capacitor : capacitorArray) {
            capacitors.add(capacitor);
        }
        for (Inductor inductor : inductorArray) {
            inductors.add(inductor);
        }
        
	}
}
