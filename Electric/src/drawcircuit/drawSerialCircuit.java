package DrawCircuit;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class drawSerialCircuit extends drawCircuit {
    int ElementDistance = 50;
    int ResitorLength = 40;
    int InductorLength = 40;
    int VoltageLength = 40;
    int CapacitorLength = 10;

    public void drawCircuitDiagram(GraphicsContext gc,  String voltage, String frequency, ArrayList<String> ElementList) {

        int x = 50;
        int y = 100;
        int x_start = x;

        drawLine(gc, x, y);
        x += ElementDistance;

        for (int i = 0; i < ElementList.size(); i++) {
            String c = ElementList.get(i);
            if (c.charAt(0) == 'R') {
                drawResistor(gc, x, y);
                gc.fillText(c, x + 12, y - 20);
                x += ResitorLength;
            }

            if (c.charAt(0) == 'L') {
                drawInductor(gc, x, y);
                gc.fillText(c, x + 12, y - 30);
                x += InductorLength;
            }

            if (c.charAt(0) == 'C') {
                drawCapacitor(gc, x, y);
                gc.fillText(c, x - 2, y - 20);
                x += CapacitorLength;
            }

            drawLine(gc, x, y);
            x += ElementDistance;
        }

        int x_voltage = (x - x_start - VoltageLength) / 2 + x_start;
        int gap = 50;

        drawVoltageSource(gc, x_voltage, y + gap, frequency);

        gc.strokeLine(x_start, y + gap, x_voltage, y + gap);
        gc.strokeLine(x_voltage + VoltageLength, y + gap, x, y + gap);
        gc.strokeLine(x_start, y, x_start, y + gap);
        gc.strokeLine(x, y, x, y + gap);
    }

    public void drawResistor(GraphicsContext gc, double x, double y) {

        // resitor
        gc.strokeLine(x, y, x + 6, y - 10);
        gc.strokeLine(x + 6, y - 10, x + 13, y + 10);
        gc.strokeLine(x + 13, y + 10, x + 20, y - 10);
        gc.strokeLine(x + 20, y - 10, x + 27, y + 10);
        gc.strokeLine(x + 27, y + 10, x + 34, y - 10);
        gc.strokeLine(x + 34, y - 10, x + 40, y);

    }

    public void drawCapacitor(GraphicsContext gc, double x, double y) {

        gc.strokeLine(x, y - 10, x, y + 10);
        gc.strokeLine(x + 10, y - 10, x + 10, y + 10);

    }

    public void drawInductor(GraphicsContext gc, double x, double y) {

        // inductor
        gc.strokeArc(x, y - 20, 14, 38, 180 + 40 + 90, 180 + 50, ArcType.OPEN);
        gc.strokeArc(x + 9, y - 20, 14, 38, 180 + 40 + 90, 180 + 100, ArcType.OPEN);
        gc.strokeArc(x + 18, y - 20, 14, 38, 180 + 40 + 90, 180 + 100, ArcType.OPEN);
        gc.strokeArc(x + 27, y - 20, 12, 38, 180 + 90 + 90, 180 + 50, ArcType.OPEN);
    }

    public void drawVoltageSource(GraphicsContext gc, double x, double y, String frequency) {

        gc.strokeOval(x, y - 20, 40, 40);

        if(frequency!=null)
        {
            gc.strokeArc(x + 7, y - 10, 13, 18, 0, 180, ArcType.OPEN);
            gc.strokeArc(x + 20, y - 10, 13, 18, 180, 180, ArcType.OPEN);
        }
        else
        {
            // dau cong
            gc.strokeLine(x + 5, y, x + 15, y);
            gc.strokeLine(x + 10, y - 5, x + 10, y + 5);

            // dau tru
            gc.strokeLine(x + 25, y, x + 35, y);


        }
    }

    public void drawLine(GraphicsContext gc, double x, double y) {
        gc.strokeLine(x, y, x + ElementDistance, y);
    }

}
