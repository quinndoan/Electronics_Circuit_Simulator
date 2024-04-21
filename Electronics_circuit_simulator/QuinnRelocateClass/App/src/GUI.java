import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUI {
    private JFrame frame;
    private JPanel parallelPanel;
    private JButton submitButton;
    private List<Component> parallelComponents;
    private Panel circuitPanel;
    private ElementController elementController;

    public GUI() {
        frame = new JFrame("Electrical Circuit Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        parallelPanel = new JPanel(new FlowLayout());
        submitButton = new JButton("Submit");
        parallelPanel.add(submitButton);

        frame.add(parallelPanel, BorderLayout.CENTER);

        parallelComponents = new ArrayList<>();
        elementController = new ElementController();

        circuitPanel = new Panel(new ArrayList<>(), null); // Initialize with empty elements and null voltage source
        circuitPanel.setPreferredSize(new Dimension(600, 300));
        frame.add(circuitPanel, BorderLayout.NORTH);

        submitButton.addActionListener(e -> submitCircuit());

        frame.setVisible(true);
    }

    private void submitCircuit() {
        double voltage;
        double frequency;

        try {
            voltage = Double.parseDouble(JOptionPane.showInputDialog("Enter Voltage (V):"));
            frequency = Double.parseDouble(JOptionPane.showInputDialog("Enter Frequency (Hz):"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid voltage or frequency value. Please enter valid numbers.");
            return;
        }

        Model.VoltageSource voltageSource = new Model.VoltageSource(voltage, frequency);
        List<Model.CircuitElement> circuitElements = new ArrayList<>();

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

            circuitElements.add(new Model.CircuitElement(elementType, value));
        }

        circuitPanel = new Panel(circuitElements, voltageSource);
        frame.add(circuitPanel, BorderLayout.NORTH);
        frame.revalidate();
        frame.repaint();
    }

    private void addElement(String elementType, JPanel panel, List<Component> components) {
        JTextField textField = new JTextField(10);
        JLabel label = new JLabel(elementType);
        panel.add(label);
        panel.add(textField);
        components.add(label);
        components.add(textField);

        switch (elementType) {
            case "Resistor":
                textField.addActionListener(e -> {
                    double resistance = Double.parseDouble(textField.getText());
                    elementController.addResistor(resistance);
                });
                break;
            case "Capacitor":
                textField.addActionListener(e -> {
                    double capacitance = Double.parseDouble(textField.getText());
                    elementController.addCapacitor(capacitance);
                });
                break;
            case "Inductor":
                textField.addActionListener(e -> {
                    double inductance = Double.parseDouble(textField.getText());
                    elementController.addInductor(inductance);
                });
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}
