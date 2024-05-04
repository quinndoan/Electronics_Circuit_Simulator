
import java.net.URL;
import java.util.ResourceBundle;
import Components.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Controller implements Initializable {
    private int soluong = 0;
    private int R = 0;
    private int C = 0;
    private int L = 0;
    private EleController eleController;
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
    public Label label;
    @FXML
    private StackPane circuit;
    ObservableList<String> list1 = FXCollections.observableArrayList("Ω", "kΩ", "MΩ");
    ObservableList<String> list2 = FXCollections.observableArrayList("H", "kH", "MH");
    ObservableList<String> list3 = FXCollections.observableArrayList("F", "kF", "MF");

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        // TODO Auto-generated method stub

    }

    @FXML
    public void addResistor(ActionEvent event) {
        TextField textField = new TextField();
        textField.setPromptText("Enter Resistor");
        ComboBox<String> comboBox = new ComboBox<String>();
        comboBox.setPromptText("Ω");
        comboBox.setItems(list1);
        Label label = new Label();
        soluong++;
        String slString = Integer.toString(soluong);
        label.textProperty().setValue("R" + slString);
        // int row = findEmptyRow(grid1);
        // int column = 0; // Cột luôn là 0 trong trường hợp này

        // // Nếu không tìm thấy hàng trống, thêm một hàng mới
        // if (row == -1) {
        // row = grid1.getRowConstraints().size();
        // grid1.getRowConstraints().add(new RowConstraints());
        // }

        // Thêm TextArea và Label vào GridPane
        grid1.add(label, 0, R);
        grid1.add(textField, 1, R);
        grid1.add(comboBox, 2, R); // Đặt Label vào hàng tiếp theo
        R++;
        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setPercentWidth(100);
        grid1.getColumnConstraints().add(colConstraints);

        // Cài đặt ràng buộc cột để TextArea và Label chiếm toàn bộ chiều rộng

    }

    public void addInductor(ActionEvent event) {
        TextField textField = new TextField();
        textField.setPromptText("Enter Inductor");
        ComboBox<String> comboBox = new ComboBox<String>();
        comboBox.setPromptText("H");
        comboBox.setItems(list2);
        Label label = new Label();
        soluong++;
        String slString = Integer.toString(soluong);
        label.textProperty().setValue("L" + slString);
        // int row = findEmptyRow(grid1);
        // int column = 0; // Cột luôn là 0 trong trường hợp này

        // // Nếu không tìm thấy hàng trống, thêm một hàng mới
        // if (row == -1) {
        // row = grid1.getRowConstraints().size();
        // grid1.getRowConstraints().add(new RowConstraints());
        // }

        // Thêm TextArea và Label vào GridPane
        grid2.add(label, 0, L);
        grid2.add(textField, 1, L);
        grid2.add(comboBox, 2, L); // Đặt Label vào hàng tiếp theo
        L++;
        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setPercentWidth(100);
        grid2.getColumnConstraints().add(colConstraints);

        // Cài đặt ràng buộc cột để TextArea và Label chiếm toàn bộ chiều rộng

    }

    public void addCapacitor(ActionEvent event) {
        TextField textField = new TextField();
        textField.setPromptText("Enter Capacitor");
        ComboBox<String> comboBox = new ComboBox<String>();
        comboBox.setPromptText("F");
        comboBox.setItems(list3);
        Label label = new Label();
        soluong++;
        String slString = Integer.toString(soluong);
        label.textProperty().setValue("C" + slString);
        // int row = findEmptyRow(grid1);
        // int column = 0; // Cột luôn là 0 trong trường hợp này

        // // Nếu không tìm thấy hàng trống, thêm một hàng mới
        // if (row == -1) {
        // row = grid1.getRowConstraints().size();
        // grid1.getRowConstraints().add(new RowConstraints());
        // }

        // Thêm TextArea và Label vào GridPane
        grid3.add(label, 0, C);
        grid3.add(textField, 1, C);
        grid3.add(comboBox, 2, C); // Đặt Label vào hàng tiếp theo
        C++;
        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setPercentWidth(100);
        grid3.getColumnConstraints().add(colConstraints);

        // Cài đặt ràng buộc cột để TextArea và Label chiếm toàn bộ chiều rộng

    }

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