package Source_fxml;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.text.html.parser.Element;

import Components.Capacitor;
import Components.EleController;
import Components.Inductor;
import Components.Resistor;
import Components.element;
import Components.TableAnalysis.createTable;
import DrawCircuit.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller implements Initializable {
    private int R = 0;
    private int C = 0;
    private int L = 0;
    private String AC_Voltage;
    private String AC_Frequency;
    private String DC_Voltage;
    private ArrayList<Resistor> CircuitResistor = new ArrayList<>();
    private ArrayList<Inductor> CircuitInductor = new ArrayList<>();
    private ArrayList<Capacitor> CircuitCapacitor = new ArrayList<>();
    private ObservableList<String> list = FXCollections.observableArrayList("AC", "DC");
    private createTable table = new createTable();
    private drawParallelCircuit parallelCircuit = new drawParallelCircuit();
    private drawSerialCircuit serialCircuit = new drawSerialCircuit();
    private int soluong = 0;
    private ArrayList<element> elements = new ArrayList<>();
    private ArrayList<String> ElementList = new ArrayList<String>(); // lưu tên thành phần cộng với số thứ tự
    @FXML
    Canvas canvas = new Canvas(600, 400);
    @FXML
    GraphicsContext gc = canvas.getGraphicsContext2D();
    @FXML
    private GridPane grid1;
    @FXML
    private GridPane grid2;
    @FXML
    private GridPane grid3;
    @FXML
    private Label label;
    @FXML
    private StackPane circuit;
    @FXML
    ComboBox<String> comboBoxVol = new ComboBox<>();
    private int circuitType = 0;
    private int voltageType = 0;

    @FXML
    private TextField textField1;
    @FXML
    private Label name1;
    @FXML
    private Label name2;
    @FXML
    private Label unit1;
    @FXML
    private Label unit2;
    @FXML
    private Button resetButton;
    @FXML
    private TextField textField2;
    @FXML
    private VBox vBox;
    @FXML
    private Text text1;
    @FXML
    private Text text2;

    // Hàm được gọi khi sự kiện chọn mục xảy ra trong ComboBox
    @FXML
    public void setVoltageType(ActionEvent event) {
        String selectedItem = comboBoxVol.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Ẩn tất cả các TextField trước khi hiển thị lại
            textField1.setVisible(false);
            textField2.setVisible(false);
            name1.setVisible(false);
            name2.setVisible(false);
            unit1.setVisible(false);
            unit2.setVisible(false);

            // Kiểm tra giá trị được chọn và hiển thị các TextField tương ứng
            if ("AC".equals(selectedItem)) {
                voltageType = 1;
                textField1.setVisible(true);
                textField2.setVisible(true);
                name1.setVisible(true);
                name2.setVisible(true);
                unit1.setVisible(true);
                unit2.setVisible(true);
            } else if ("DC".equals(selectedItem)) {
                voltageType = 2;
                textField1.setVisible(true);
                name1.setVisible(true);
                unit1.setVisible(true);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        comboBoxVol.setItems(list);
        comboBoxVol.getStyleClass().addAll("combobox2");
        textField1.setVisible(false);
        textField2.setVisible(false);
        name1.setVisible(false);
        name2.setVisible(false);
        unit1.setVisible(false);
        unit2.setVisible(false);
        text1.setVisible(false);
        text2.setVisible(false);
        table = new createTable();

    }

    @FXML
    public void resettoInitialize(ActionEvent event) {
        vBox.getChildren().clear();
        grid1.getChildren().clear();
        grid2.getChildren().clear();
        grid3.getChildren().clear();
        textField1.setVisible(false);
        textField2.setVisible(false);
        textField1.clear();
        textField2.clear();
        name1.setVisible(false);
        name2.setVisible(false);
        unit1.setVisible(false);
        unit2.setVisible(false);
        text1.setVisible(false);
        text2.setVisible(false);
        circuit.getChildren().clear(); // Xóa hết các thành phần trong StackPane
        ;
        comboBoxVol.getSelectionModel().clearSelection(); // Xóa lựa chọn trong ComboBox
        comboBoxVol.setPromptText("Voltage Source"); // Đặt lại prompt text cho ComboBox
        // Đặt prompt text cho ComboBox
        R = 0;
        L = 0;
        C = 0;
        soluong = 0;
        ElementList.clear();
        circuitType = 0;
        voltageType = 0;
        elements.clear();
    }

    @FXML
    public void circuitGenerate(ActionEvent event) {
        Canvas canvas = new Canvas(600, 300);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        circuit.getChildren().clear();
        if (voltageType == 1) {
            AC_Voltage = textField1.getText();
            double voltage = Double.parseDouble(AC_Voltage);
            AC_Frequency = textField2.getText();
            double frequency = Double.parseDouble(AC_Frequency);
            if (ElementList.size() > 0) {
                if (circuitType == 1)
                    parallelCircuit.drawCircuitDiagram(gc, AC_Voltage, AC_Frequency, ElementList);
                if (circuitType == 2)
                    serialCircuit.drawCircuitDiagram(gc, AC_Voltage, AC_Frequency, ElementList);
            }

            System.out.print(elements.get(0).getValue());
            EleController handleEleController = new EleController(voltage, frequency,
                    "AC", CircuitResistor,
                    CircuitCapacitor, CircuitInductor, elements, circuitType, ElementList);
            vBox.getChildren().clear();
            vBox.getChildren().add(table.analysisTableAC(handleEleController));

        }
        if (voltageType == 2) {
            DC_Voltage = textField1.getText();
            double voltage = Double.parseDouble(DC_Voltage);
            if (ElementList.size() > 0) {
                if (circuitType == 1)
                    parallelCircuit.drawCircuitDiagram(gc, DC_Voltage, null, ElementList);
                if (circuitType == 2)
                    serialCircuit.drawCircuitDiagram(gc, DC_Voltage, null, ElementList);
            }
            EleController hanController = new EleController(voltage, Double.POSITIVE_INFINITY, "DC", CircuitResistor,
                    CircuitCapacitor, CircuitInductor, elements, circuitType, ElementList);
            vBox.getChildren().clear();
            vBox.getChildren().add(table.analysisTableDC(hanController));
        }

        circuit.getChildren().add(canvas);

    }

    @FXML
    public void openChatbot(ActionEvent event) {
        try {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Stage newStage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource(
                    "/source_fxml/chatbot.fxml"));
            Scene scene = new Scene(root);
            newStage.setTitle("OpenAI Chatbot");
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            // Đặt Scene cho Stage mới
            newStage.setScene(scene);

            // Hiển thị cửa sổ mới
            newStage.show();
            currentStage.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void addResistor(ActionEvent event) {
        if (soluong == 5) {
            // Show an alert when the number of resistors reaches 5
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Limit Reached");
            alert.setHeaderText(null);
            alert.setContentText("You have reached the maximum number of elements.");
            alert.showAndWait();
        } else {
            Label unit = new Label("Ω");
            TextField textField = new TextField();
            textField.setPromptText("Enter Resistor");
            Label label = new Label();
            soluong++;
            String slString = Integer.toString(soluong);
            label.textProperty().setValue("R" + slString);
            ElementList.add("R" + slString);

            grid1.add(label, 0, R);
            grid1.add(textField, 1, R);
            grid1.add(unit, 2, R); // Đặt Label vào hàng tiếp theo

            R++;
            // Add a listener to handle the input after the user enters it
            textField.setOnAction(e -> {
                String s = textField.getText();
                if (s != null && !s.isEmpty()) {
                    try {
                        double value = Double.parseDouble(s);
                        CircuitResistor.add(new Resistor(value));
                        elements.add(new Resistor(value));
                        System.out.println("Added resistor with value: " + value);
                    } catch (NumberFormatException ex) {
                        // Handle the case where the input is not a valid number
                        System.out.println("Invalid input: " + s);
                    }
                }
            });

            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setPercentWidth(100);
            grid1.getColumnConstraints().add(colConstraints);
        }

    }

    public void addInductor(ActionEvent event) {
        Label unit = new Label("H");

        if (soluong == 5) {
            // Show an alert when the number of resistors reaches 5
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Limit Reached");
            alert.setHeaderText(null);
            alert.setContentText("You have reached the maximum number of elements.");
            alert.showAndWait();
        } else {
            TextField textField = new TextField();
            textField.setPromptText("Enter Inductor");
            Label label = new Label();
            soluong++;
            String slString = Integer.toString(soluong);
            label.textProperty().setValue("L" + slString);
            ElementList.add("L" + slString);

            grid2.add(label, 0, L);
            grid2.add(textField, 1, L);
            grid2.add(unit, 2, L); // Đặt Label vào hàng tiếp theo
            L++;

            textField.setOnAction(e -> {
                String s = textField.getText();
                if (s != null && !s.isEmpty()) {
                    try {
                        double value = Double.parseDouble(s);
                        CircuitInductor.add(new Inductor(value));
                        elements.add(new Inductor(value));
                        System.out.println("Added inductor with value: " + value);
                    } catch (NumberFormatException ex) {
                        // Handle the case where the input is not a valid number
                        System.out.println("Invalid input: " + s);
                    }
                }
            });

            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setPercentWidth(100);
            grid2.getColumnConstraints().add(colConstraints);
        }

    }

    public void addCapacitor(ActionEvent event) {
        Label unit = new Label("F");
        if (soluong == 5) {
            // Show an alert when the number of resistors reaches 5
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Limit Reached");
            alert.setHeaderText(null);
            alert.setContentText("You have reached the maximum number of elements.");
            alert.showAndWait();
        } else {
            TextField textField = new TextField();
            textField.setPromptText("Enter Capacitor");
            Label label = new Label();
            soluong++;
            String slString = Integer.toString(soluong);
            label.textProperty().setValue("C" + slString);
            ElementList.add("C" + slString);

            grid3.add(label, 0, C);
            grid3.add(textField, 1, C);
            grid3.add(unit, 2, C); // Đặt Label vào hàng tiếp theo
            C++;

            textField.setOnAction(e -> {
                String s = textField.getText();
                if (s != null && !s.isEmpty()) {
                    try {
                        double value = Double.parseDouble(s);
                        CircuitCapacitor.add(new Capacitor(value));
                        elements.add(new Capacitor(value));
                        System.out.println("Added capacitor with value: " + value);
                    } catch (NumberFormatException ex) {
                        // Handle the case where the input is not a valid number
                        System.out.println("Invalid input: " + s);
                    }
                }
            });
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setPercentWidth(100);
            grid3.getColumnConstraints().add(colConstraints);
        }
    }

    @FXML
    public void ChooseParallelCircuitType(ActionEvent event) {
        circuitType = 1;
        text2.setVisible(false);
        text1.setVisible(true);
    }

    @FXML
    public void ChooseSerialCircuitType(ActionEvent event) {
        circuitType = 2;
        text1.setVisible(false);
        text2.setVisible(true);

    }

}