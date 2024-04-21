import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GUI {
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JPanel parallelPanel;
    private JPanel serialPanel;
    private JButton addResistorButton;
    private JButton addCapacitorButton;
    private JButton addInductorButton;
    private JComboBox<String> sourceTypeComboBox;
    private JLabel sourceTypeLabel;
    private JTextField voltageTextField;
    private JLabel voltageLabel;
    private JTextField frequencyTextField;
    private JLabel frequencyLabel;
    private JButton submitButton;
    private List<Component> parallelComponents;
    private List<Component> serialComponents;
    private JPanel circuitPanel; // New JPanel to draw circuit

    // Representing a circuit element
    public class CircuitElement {
        private String type; // Circuit element type (R, L, C)
        private double value; // Value of the circuit element

        public CircuitElement(String type, double value) {
            this.type = type;
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public double getValue() {
            return value;
        }
    }

    // Representing a voltage source
    public class VoltageSource {
        private double voltage;
        private double frequency;

        public VoltageSource(double voltage) {
            this.voltage = voltage;
        }

        public VoltageSource(double voltage, double frequency) {
            this.voltage = voltage;
            this.frequency = frequency;
        }

        public double getVoltage() {
            return voltage;
        }

        public double getFrequency() {
            return frequency;
        }
    }

    // Representing an electrical circuit
    public class Circuit {
        private VoltageSource voltageSource;
        private List<CircuitElement> circuitElements;

        public Circuit(VoltageSource voltageSource, List<CircuitElement> circuitElements) {
            this.voltageSource = voltageSource;
            this.circuitElements = circuitElements;
        }

        private List<Point> calculateConnectionPoints() {
            List<Point> connectionPoints = new ArrayList<>();
            int x = 200;
            int y = 50;

            for (CircuitElement element : circuitElements) {
                connectionPoints.add(new Point(x, y));
                x += 100;
            }

            return connectionPoints;
        }

        public void drawCircuit(Graphics g) {
            if (g == null) {
                return;
            }

            g.setColor(Color.RED);
            g.drawString("Voltage Source", 50, 50);
            g.drawRect(40, 40, 80, 30);

            int x = 200;
            int y = 50;

            g.setColor(Color.BLACK);
            for (CircuitElement element : circuitElements) {
                g.drawString(element.getType() + ": " + element.getValue(), x, y);
                g.drawRect(x - 10, y - 20, 60, 40);
                x += 100;
            }

            List<Point> connectionPoints = calculateConnectionPoints();
            g.drawLine(120, 55, connectionPoints.get(0).x - 10, 55);
            for (int i = 0; i < connectionPoints.size() - 1; i++) {
                g.drawLine(connectionPoints.get(i).x + 50, 55, connectionPoints.get(i + 1).x - 10, 55);
            }
        }
    }

    public GUI() {
        frame = new JFrame("Electrical Circuit Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();

        parallelPanel = new JPanel(new FlowLayout());
        serialPanel = new JPanel(new FlowLayout());

        addResistorButton = new JButton("Add Resistor");
        addCapacitorButton = new JButton("Add Capacitor");
        addInductorButton = new JButton("Add Inductor");
        parallelPanel.add(addResistorButton);
        parallelPanel.add(addCapacitorButton);
        parallelPanel.add(addInductorButton);

        sourceTypeLabel = new JLabel("Source Type:");
        sourceTypeComboBox = new JComboBox<>(new String[]{"DC", "AC"});
        voltageLabel = new JLabel("Voltage (V):");
        voltageTextField = new JTextField(10);
        frequencyLabel = new JLabel("Frequency (Hz):");
        frequencyTextField = new JTextField(10);
        parallelPanel.add(sourceTypeLabel);
        parallelPanel.add(sourceTypeComboBox);
        parallelPanel.add(voltageLabel);
        parallelPanel.add(voltageTextField);
        parallelPanel.add(frequencyLabel);
        parallelPanel.add(frequencyTextField);

        submitButton = new JButton("Submit");
        parallelPanel.add(submitButton);

        tabbedPane.addTab("Parallel Circuit", parallelPanel);
        tabbedPane.addTab("Serial Circuit", serialPanel);

        frame.add(tabbedPane, BorderLayout.CENTER);

        parallelComponents = new ArrayList<>();
        serialComponents = new ArrayList<>();
        
        circuitPanel = new JPanel();
        circuitPanel.setBounds(0, 0, 600, 300);
        frame.add(circuitPanel, BorderLayout.NORTH);
    
        addResistorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addElement("Resistor", parallelPanel, parallelComponents);
            }
        });

        addCapacitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addElement("Capacitor", parallelPanel, parallelComponents);
            }
        });

        addInductorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addElement("Inductor", parallelPanel, parallelComponents);
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitCircuit();
            }
        });

        frame.setVisible(true);
    }

    private void addElement(String elementType, JPanel panel, List<Component> components) {
        JTextField textField = new JTextField(10);
        JLabel label = new JLabel(elementType);
        panel.add(label);
        panel.add(textField);
        components.add(label);
        components.add(textField);
    }
    
    // Phần 2: Phương thức submitCircuit và phương thức vẽ mạch

private void submitCircuit() {
    double voltage;
    double frequency;

    try {
        voltage = Double.parseDouble(voltageTextField.getText());
        frequency = Double.parseDouble(frequencyTextField.getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(frame, "Invalid voltage or frequency value. Please enter valid numbers.");
        return;
    }

    if (parallelComponents.size() % 2 != 0) {
        JOptionPane.showMessageDialog(frame, "Invalid circuit structure. Please add components in pairs.");
        return;
    }

    VoltageSource voltageSource = new VoltageSource(voltage, frequency);
    List<CircuitElement> circuitElements = new ArrayList<>();

    for (int i = 0; i < parallelComponents.size(); i += 2) {
        JLabel label = (JLabel) parallelComponents.get(i);
        JTextField textField = (JTextField) parallelComponents.get(i + 1);

        String elementType = label.getText();
        double value;
        try {
            value = Double.parseDouble(textField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid value for " + elementType + ". Please enter a valid number.");
            return;
        }

        circuitElements.add(new CircuitElement(elementType, value));
    }

    Circuit circuit = new Circuit(voltageSource, circuitElements);
    Graphics g = circuitPanel.getGraphics();
    drawCircuit(circuit, g);
}

private void drawCircuit(Circuit circuit, Graphics g) {
    if (g == null) {
        return;
    }

    g.setColor(Color.WHITE); // Clear previous drawings
    g.fillRect(0, 0, circuitPanel.getWidth(), circuitPanel.getHeight());
    g.setColor(Color.BLACK);

    circuit.drawCircuit(g);
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }
}
