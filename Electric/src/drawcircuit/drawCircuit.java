package DrawCircuit;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public abstract class drawCircuit {

    public void drawCircuitDiagram(GraphicsContext gc, String AC_Voltage, String AC_Frequency,
            ArrayList<String> ElementList) {
    }

    public void drawCircuitDiagram(GraphicsContext gc, String DC_Voltage, ArrayList<String> ElementList) {
    }

    public void drawResistor(GraphicsContext gc, double x, double y) {
    }

    public void drawCapacitor(GraphicsContext gc, double x, double y) {
    }

    public void drawInductor(GraphicsContext gc, double x, double y) {
    }

    public void drawVoltageSource(GraphicsContext gc, double x, double y) {
    }

    public void drawLine(GraphicsContext gc, double x, double y) {
    }

}