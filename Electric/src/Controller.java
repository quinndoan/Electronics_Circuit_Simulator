import GUI_Components.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Components.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Controller extends input implements Initializable, inputVoltage {

    private int soluong = 0;
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
    private ComboBox<String> comboBoxVol;
    @FXML
    private StackPane circuit;
    ObservableList<String> list = FXCollections.observableArrayList("AC", "DC");

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        comboBoxVol.setItems(list);
        comboBoxVol.getStyleClass().addAll("combobox2");

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

        grid3.add(label, 0, C);
        grid3.add(textField, 1, C);
        grid3.add(comboBox, 2, C); // Đặt Label vào hàng tiếp theo
        C++;
        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setPercentWidth(100);
        grid3.getColumnConstraints().add(colConstraints);

    }

    @FXML

    public void circuitGenerate(ActionEvent event) {
        Canvas canvas = new Canvas(600, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawCircuitDiagram(gc);

        circuit.getChildren().add(canvas);

    }

    private void drawCircuitDiagram(GraphicsContext gc) {
        // Draw circuit components
        drawResistor(gc, 100, 150);
        drawResistor(gc, 250, 150);
        drawResistor(gc, 400, 150);

        // Draw connections
        drawConnection(gc, 50, 150, 550, 150);
        drawConnection(gc, 50, 250, 550, 250);

        // Draw voltage source
        drawVoltageSource(gc, 50, 200);
    }

    private void drawResistor(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeLine(x, y, x + 50, y);
        gc.strokeLine(x + 50, y, x + 60, y - 10);
        gc.strokeLine(x + 60, y - 10, x + 70, y + 10);
        gc.strokeLine(x + 70, y + 10, x + 80, y - 10);
        gc.strokeLine(x + 80, y - 10, x + 90, y + 10);
        gc.strokeLine(x + 90, y + 10, x + 100, y);
    }

    private void drawConnection(GraphicsContext gc, double startX, double startY, double endX, double endY) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeLine(startX, startY, endX, endY);
    }

    private void drawVoltageSource(GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.RED);
        gc.setLineWidth(2);
        gc.strokeRect(x, y - 20, 20, 40); // Draw rectangle representing the battery
        gc.strokeLine(x + 20, y, x + 30, y); // Draw positive terminal line
        gc.strokeLine(x, y, x - 10, y); // Draw negative terminal line
    }
}
