package drawcircuit;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class drawParallelCircuit extends drawCircuit {
    
    @Override
    public void drawCircuitDiagram(GraphicsContext gc,  String voltage, String frequency, ArrayList<String> ElementList) {
        int x = 30;
        int y = 50;
        int Distance = 80;
       
        drawVoltageSource(gc, x, y, frequency);
        gc.fillText(voltage, x + 25, y + 55);
        if(frequency!=null) gc.fillText(frequency, x + 25, y + 70);
        drawLine(gc, x, y);

        for (int i = 0; i < ElementList.size(); i++) {
            String c = ElementList.get(i);

            if (c.charAt(0) == 'R') {
                drawResistor(gc, x += Distance, y);
                gc.fillText(c, x + 20, y + 65);
            }

            if (c.charAt(0) == 'L') {
                drawInductor(gc, x += Distance, y);
                gc.fillText(c, x + 25, y + 65);
            }

            if (c.charAt(0) == 'C') {
                drawCapacitor(gc, x += Distance, y);
                gc.fillText(c, x + 20, y + 65);
            }

            if (i + 1 < ElementList.size())
                drawLine(gc, x, y);
        }

    }

    public void drawResistor(GraphicsContext gc, double x, double y) {

        // resitor
        gc.strokeLine(x, y + 40, x + 10, y + 46);
        gc.strokeLine(x + 10, y + 45, x - 10, y + 53);
        gc.strokeLine(x - 10, y + 53, x + 10, y + 60);
        gc.strokeLine(x + 10, y + 60, x - 10, y + 67);
        gc.strokeLine(x - 10, y + 67, x + 10, y + 74);
        gc.strokeLine(x + 10, y + 74, x, y + 80);

        // duong day tren
        gc.strokeLine(x, y, x, y + 40);

        // duong day duoi
        gc.strokeLine(x, y + 80, x, y + 120);

    }

    public void drawCapacitor(GraphicsContext gc, double x, double y) {

        // capacitor
        gc.strokeLine(x, y + 40, x, y + 55);
        gc.strokeLine(x - 10, y + 55, x + 10, y + 55);
        gc.strokeLine(x - 10, y + 65, x + 10, y + 65);
        gc.strokeLine(x, y + 65, x, y + 80);

        // duong day tren
        gc.strokeLine(x, y, x, y + 40);

        // duong day duoi
        gc.strokeLine(x, y + 80, x, y + 120);
    }

    public void drawInductor(GraphicsContext gc, double x, double y) {

        // inductor
        gc.strokeArc(x - 20, y + 40, 38, 14, 180 + 40, 180 + 50, ArcType.OPEN);
        gc.strokeArc(x - 20, y + 49, 38, 14, 180 + 40, 180 + 100, ArcType.OPEN);
        gc.strokeArc(x - 20, y + 58, 38, 14, 180 + 40, 180 + 100, ArcType.OPEN);
        gc.strokeArc(x - 20, y + 67, 38, 12, 180 + 90, 180 + 50, ArcType.OPEN);

        // duong day tren
        gc.strokeLine(x, y, x, y + 40);

        // duong day duoi
        gc.strokeLine(x, y + 80, x, y + 120);
    }

    public void drawVoltageSource(GraphicsContext gc, double x, double y, String frequency) {
        // hinh tron
        gc.strokeOval(x - 20, y + 40, 40, 40);

        if(frequency != null)
        {
            gc.strokeArc(x - 13, y + 50, 13, 18, 0, 180, ArcType.OPEN);
            gc.strokeArc(x , y + 50, 13, 18, 180, 180, ArcType.OPEN);
        }
        else
        {
            // dau cong
            gc.strokeLine(x, y + 50, x, y + 60);
            gc.strokeLine(x - 5, y + 55, x + 5, y + 55);

            // dau tru
            gc.strokeLine(x - 5, y + 70, x + 5, y + 70);
        }

        // duong day tren
        gc.strokeLine(x, y, x, y + 40);

        // duong day duoi
        gc.strokeLine(x, y + 80, x, y + 120);

    }

    public void drawLine(GraphicsContext gc, double x, double y) {
        gc.strokeLine(x, y, x + 80, y);
        gc.strokeLine(x, y + 120, x + 80, y + 120);
    }
}
