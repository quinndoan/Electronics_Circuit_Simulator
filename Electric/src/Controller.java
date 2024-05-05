
import java.net.URL;
import java.util.ResourceBundle;
import Components.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class Controller implements Initializable {
    private int soluong = 0;
    private int R = 0;
    private int C = 0;
    private int L = 0;
    private EleController eleController;

    @FXML
    private GridPane grid1;
    @FXML
    public GridPane grid2;
    @FXML
    public GridPane grid3;

    @FXML
    public Label label;

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
    
}