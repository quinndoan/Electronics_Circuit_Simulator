import GUI_Components.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.text.html.parser.Element;

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

    private int soluong = 0;
    private int R = 0;
    private int C = 0;
    private int L = 0;
    private EleController eleController;

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
        circuit.getChildren().clear(); // Xóa hết các thành phần trong StackPane
        ;
        comboBoxVol.getSelectionModel().clearSelection(); // Xóa lựa chọn trong ComboBox
        comboBoxVol.setPromptText("Voltage Source"); // Đặt lại prompt text cho ComboBox
        // Đặt prompt text cho ComboBox
        R = 0;
        L = 0;
        C = 0;
        soluong = 0;
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
}