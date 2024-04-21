import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Panel extends JPanel {
    private List<Model.CircuitElement> circuitElements;
    private Model.VoltageSource voltageSource;

    public Panel(List<Model.CircuitElement> circuitElements, Model.VoltageSource voltageSource) {
        this.circuitElements = circuitElements;
        this.voltageSource = voltageSource;
        setPreferredSize(new Dimension(600, 300));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCircuit(g);
    }

    private void drawCircuit(Graphics g) {
        if (g == null) {
            return;
        }

        g.setColor(Color.WHITE); // Clear previous drawings
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);

        // Draw voltage source
        g.drawString("Voltage Source", 50, 50);
        g.drawRect(40, 40, 80, 30);

        int x = 200;
        int y = 50;

        // Draw circuit elements
        for (Model.CircuitElement element : circuitElements) {
            g.drawString(element.getType() + ": " + element.getValue(), x, y);
            g.drawRect(x - 10, y - 20, 60, 40);
            x += 100;
        }

        // Draw connections
        List<Point> connectionPoints = calculateConnectionPoints();
        g.drawLine(120, 55, connectionPoints.get(0).x - 10, 55);
        for (int i = 0; i < connectionPoints.size() - 1; i++) {
            g.drawLine(connectionPoints.get(i).x + 50, 55, connectionPoints.get(i + 1).x - 10, 55);
        }
    }

    private List<Point> calculateConnectionPoints() {
        List<Point> connectionPoints = new ArrayList<>();
        int x = 200;
        int y = 50;

        for (Model.CircuitElement element : circuitElements) {
            connectionPoints.add(new Point(x, y));
            x += 100;
        }

        return connectionPoints;
    }
}
