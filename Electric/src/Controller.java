import GUI_Components.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Components.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;

public class Controller extends input implements Initializable, inputVoltage {

    private int soluong = 0;
    private ArrayList<String> ElementList = new ArrayList<String>();
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

    @FXML
    private VBox vbox2;
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
    public int type = 0;
    @FXML
    private Text text1;
    @FXML
    private Text text2;

    // Hàm được gọi khi sự kiện chọn mục xảy ra trong ComboBox
    @FXML
    private void handleComboBoxAction(ActionEvent event) {
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
                textField1.setVisible(true);
                textField2.setVisible(true);
                name1.setVisible(true);
                name2.setVisible(true);
                unit1.setVisible(true);
                unit2.setVisible(true);
            } else if ("DC".equals(selectedItem)) {
                textField1.setVisible(true);
                DC_Voltage = textField1.getText();
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
    }

    @FXML
    public void resettoInitialize(ActionEvent event) {
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
        type = 0;
    }

    @FXML
    public void addResistor(ActionEvent event) {
        TextField textField = new TextField();
        textField.setPromptText("Enter Resistor");
        ComboBox<String> comboBox = new ComboBox<String>();
        comboBox.getStyleClass().add("my-combobox");
        comboBox.setPromptText("Ω");
        comboBox.setItems(list1);
        Label label = new Label();
        soluong++;
        String slString = Integer.toString(soluong);
        label.textProperty().setValue("R" + slString);
        ElementList.add("R" + slString);

        grid1.add(label, 0, R);
        grid1.add(textField, 1, R);
        grid1.add(comboBox, 2, R); // Đặt Label vào hàng tiếp theo
        R++;
        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setPercentWidth(100);
        grid1.getColumnConstraints().add(colConstraints);

    }

    public void addInductor(ActionEvent event) {
        TextField textField = new TextField();
        textField.setPromptText("Enter Inductor");
        ComboBox<String> comboBox = new ComboBox<String>();
        comboBox.getStyleClass().add("my-combobox");

        comboBox.setPromptText("H");
        comboBox.setItems(list2);
        Label label = new Label();
        soluong++;
        String slString = Integer.toString(soluong);
        label.textProperty().setValue("L" + slString);
        ElementList.add("L" + slString);

        grid2.add(label, 0, L);
        grid2.add(textField, 1, L);
        grid2.add(comboBox, 2, L); // Đặt Label vào hàng tiếp theo
        L++;
        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setPercentWidth(100);
        grid2.getColumnConstraints().add(colConstraints);

    }

    public void addCapacitor(ActionEvent event) {
        TextField textField = new TextField();
        textField.setPromptText("Enter Capacitor");
        ComboBox<String> comboBox = new ComboBox<String>();
        comboBox.getStyleClass().add("my-combobox");

        comboBox.setPromptText("F");
        comboBox.setItems(list3);
        Label label = new Label();
        soluong++;
        String slString = Integer.toString(soluong);
        label.textProperty().setValue("C" + slString);
        ElementList.add("C" + slString);

        grid3.add(label, 0, C);
        grid3.add(textField, 1, C);
        grid3.add(comboBox, 2, C); // Đặt Label vào hàng tiếp theo
        C++;
        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setPercentWidth(100);
        grid3.getColumnConstraints().add(colConstraints);

    }

    @FXML
    public void ChooseParallelCircuitType(ActionEvent event) {
        type = 1;
        text2.setVisible(false);
        text1.setVisible(true);
    }

    @FXML
    public void ChooseSerialCircuitType(ActionEvent event) {
        type = 2;
        text1.setVisible(false);
        text2.setVisible(true);

    }

    public void circuitGenerate(ActionEvent event) {
        Canvas canvas = new Canvas(600, 300);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        circuit.getChildren().clear();
        if (ElementList.size() > 0) {
            if (type == 1)
                drawParallelCircuitDiagram(gc);
            if (type == 2)
                drawSerialCircuitDiagram(gc);
        }

        circuit.getChildren().add(canvas);

    }

    private void drawParallelCircuitDiagram(GraphicsContext gc) {
        int x = 30;
        int y = 50;
        int Distance = 80;
        AC_Voltage = textField1.getText() + "V";
        AC_Frequency = textField2.getText() + "Hz";

        drawParallelVoltageSource(gc, x, y);
        gc.fillText(AC_Voltage, x + 25, y + 55);
        gc.fillText(AC_Frequency, x + 25, y + 70);
        drawParallelLine(gc, x, y);

        for (int i = 0; i < ElementList.size(); i++) {
            String c = ElementList.get(i);

            if (c.charAt(0) == 'R') {
                drawParallelResistor(gc, x += Distance, y);
                gc.fillText(c, x + 20, y + 65);
            }

            if (c.charAt(0) == 'L') {
                drawParallelInductor(gc, x += Distance, y);
                gc.fillText(c, x + 25, y + 65);
            }

            if (c.charAt(0) == 'C') {
                drawParallelCapacitor(gc, x += Distance, y);
                gc.fillText(c, x + 20, y + 65);
            }

            if (i + 1 < ElementList.size())
                drawParallelLine(gc, x, y);
        }

    }

    private void drawParallelResistor(GraphicsContext gc, double x, double y) {

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

    private void drawParallelCapacitor(GraphicsContext gc, double x, double y) {

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

    private void drawParallelInductor(GraphicsContext gc, double x, double y) {

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

    private void drawParallelVoltageSource(GraphicsContext gc, double x, double y) {
        gc.strokeOval(x - 20, y + 40, 40, 40);

        // dau cong
        gc.strokeLine(x, y + 50, x, y + 60);
        gc.strokeLine(x - 5, y + 55, x + 5, y + 55);

        // dau tru
        gc.strokeLine(x - 5, y + 70, x + 5, y + 70);

        // duong day tren
        gc.strokeLine(x, y, x, y + 40);

        // duong day duoi
        gc.strokeLine(x, y + 80, x, y + 120);

    }

    private void drawParallelLine(GraphicsContext gc, double x, double y) {
        gc.strokeLine(x, y, x + 80, y);
        gc.strokeLine(x, y + 120, x + 80, y + 120);
    }

    int ElementDistance = 50;
    int ResitorLength = 40;
    int InductorLength = 40;
    int VoltageLength = 40;
    int CapacitorLength = 10;

    private void drawSerialCircuitDiagram(GraphicsContext gc) {

        int x = 50;
        int y = 100;
        int x_start = x;

        drawSerialLine(gc, x, y);
        x += ElementDistance;

        for (int i = 0; i < ElementList.size(); i++) {
            String c = ElementList.get(i);
            if (c.charAt(0) == 'R') {
                drawSerialResistor(gc, x, y);
                gc.fillText(c, x + 12, y - 20);
                x += ResitorLength;
            }

            if (c.charAt(0) == 'L') {
                drawSerialInductor(gc, x, y);
                gc.fillText(c, x + 12, y - 30);
                x += InductorLength;
            }

            if (c.charAt(0) == 'C') {
                drawSerialCapacitor(gc, x, y);
                gc.fillText(c, x - 2, y - 20);
                x += CapacitorLength;
            }

            drawSerialLine(gc, x, y);
            x += ElementDistance;
        }

        int x_voltage = (x - x_start - VoltageLength) / 2 + x_start;
        int gap = 50;

        drawSerialVoltageSource(gc, x_voltage, y + gap);

        gc.strokeLine(x_start, y + gap, x_voltage, y + gap);
        gc.strokeLine(x_voltage + VoltageLength, y + gap, x, y + gap);
        gc.strokeLine(x_start, y, x_start, y + gap);
        gc.strokeLine(x, y, x, y + gap);
    }

    private void drawSerialResistor(GraphicsContext gc, double x, double y) {

        // resitor
        gc.strokeLine(x, y, x + 6, y - 10);
        gc.strokeLine(x + 6, y - 10, x + 13, y + 10);
        gc.strokeLine(x + 13, y + 10, x + 20, y - 10);
        gc.strokeLine(x + 20, y - 10, x + 27, y + 10);
        gc.strokeLine(x + 27, y + 10, x + 34, y - 10);
        gc.strokeLine(x + 34, y - 10, x + 40, y);

    }

    private void drawSerialCapacitor(GraphicsContext gc, double x, double y) {

        gc.strokeLine(x, y - 10, x, y + 10);
        gc.strokeLine(x + 10, y - 10, x + 10, y + 10);

    }

    private void drawSerialInductor(GraphicsContext gc, double x, double y) {

        // inductor
        gc.strokeArc(x, y - 20, 14, 38, 180 + 40 + 90, 180 + 50, ArcType.OPEN);
        gc.strokeArc(x + 9, y - 20, 14, 38, 180 + 40 + 90, 180 + 100, ArcType.OPEN);
        gc.strokeArc(x + 18, y - 20, 14, 38, 180 + 40 + 90, 180 + 100, ArcType.OPEN);
        gc.strokeArc(x + 27, y - 20, 12, 38, 180 + 90 + 90, 180 + 50, ArcType.OPEN);
    }

    private void drawSerialVoltageSource(GraphicsContext gc, double x, double y) {
        gc.strokeOval(x, y - 20, 40, 40);

        // dau cong
        gc.strokeLine(x + 5, y, x + 15, y);
        gc.strokeLine(x + 10, y - 5, x + 10, y + 5);

        // dau tru
        gc.strokeLine(x + 25, y, x + 35, y);

    }

    private void drawSerialLine(GraphicsContext gc, double x, double y) {
        gc.strokeLine(x, y, x + ElementDistance, y);
    }

}