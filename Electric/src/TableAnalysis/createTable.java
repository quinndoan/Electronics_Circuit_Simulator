package TableAnalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.DecimalFormat;

import Components.EleController;
import Components.element;
import Components.ComplexNum.Complex;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class createTable {
    public static class Table {
        private final Map<String, SimpleStringProperty> properties = new HashMap<>();

        public Table(String parameter, String unit, List<String> values) {
            properties.put("Parameter", new SimpleStringProperty(parameter));
            properties.put("Unit", new SimpleStringProperty(unit));

            for (int i = 0; i < values.size(); i++) {
                properties.put("R" + (i + 1), new SimpleStringProperty(values.get(i)));
            }
        }

        public SimpleStringProperty getProperty(String key) {
            return properties.get(key);
        }
    }

    public TableView<Table> analysisTableAC(EleController e) {
        TableView<Table> tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Tạo cột cho parameter
        TableColumn<Table, String> parameterColumn = new TableColumn<>("Parameter");
        parameterColumn.setCellValueFactory(cellData -> cellData.getValue().getProperty("Parameter"));
        tableView.getColumns().add(parameterColumn);

        // Tạo cột cho mỗi phần tử trong elementList từ EleController
        List<String> elementList = e.getElementList();
        for (int i = 0; i < elementList.size(); i++) {
            String columnName = "R" + (i + 1);
            TableColumn<Table, String> col = new TableColumn<>(elementList.get(i));
            col.setCellValueFactory(cellData -> cellData.getValue().getProperty(columnName));
            col.setPrefWidth(150);
            tableView.getColumns().add(col);
        }

        // Tạo cột cho unit
        TableColumn<Table, String> unitColumn = new TableColumn<>("Unit");
        unitColumn.setCellValueFactory(cellData -> cellData.getValue().getProperty("Unit"));
        unitColumn.setPrefWidth(50);
        tableView.getColumns().add(unitColumn);
        List<element> elements = e.getElements();
        double frequency = e.getFrequency();

        List<String> impedanceIntensityValues = new ArrayList<>();
        List<String> voltageIntensityValues = new ArrayList<>();
        List<String> currentIntensityValues = new ArrayList<>();

        DecimalFormat df = new DecimalFormat("#.##");
        for (element element : elements) {
            Complex impedance = element.getImpedance(frequency);
            String imString1 = df.format(impedance.getReal());
            String imString2 = df.format(impedance.getImaginary());
            imString1 = imString1 + " + " + imString2 + "i";

            Complex voltage = e.getVoltage(element, frequency);
            String cuString1 = df.format(voltage.getReal());
            String cuString2 = df.format(voltage.getImaginary());
            cuString1 = cuString1 + " + " + cuString2 + "i";

            Complex current = e.getCurrent(element, frequency);
            String donString1 = df.format(current.getReal());
            String donString2 = df.format(current.getImaginary());
            donString1 = donString1 + " + " + donString2 + "i";

            // Thêm giá trị vào bảng
            impedanceIntensityValues.add(imString1);
            voltageIntensityValues.add(cuString1);
            currentIntensityValues.add(donString1);
        }
        // Tạo dữ liệu mẫu
        ObservableList<Table> data = FXCollections.observableArrayList(
                new Table("U (Voltage)", "V", voltageIntensityValues),
                new Table("I (Current intensity)", "A", currentIntensityValues), // Thêm giá trị từ biến imString
                new Table("R (Resistance)", "Ω", impedanceIntensityValues));

        tableView.setItems(data);
        return tableView;
    }

    public TableView<Table> analysisTableDC(EleController e) {
        if (e.detectShortCircuit(0) == true) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Short Circuit Detected");
            alert.setHeaderText(null);
            alert.setContentText("A short circuit has been detected!");
            alert.showAndWait();
            return null;
        } else {
            TableView<Table> tableView = new TableView<>();
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            // Tạo cột cho parameter
            TableColumn<Table, String> parameterColumn = new TableColumn<>("Parameter");
            parameterColumn.setCellValueFactory(cellData -> cellData.getValue().getProperty("Parameter"));
            tableView.getColumns().add(parameterColumn);

            // Tạo cột cho mỗi phần tử trong elementList từ EleController
            List<String> elementList = e.getElementList();
            for (int i = 0; i < elementList.size(); i++) {
                String columnName = "R" + (i + 1);
                TableColumn<Table, String> col = new TableColumn<>(elementList.get(i));
                col.setCellValueFactory(cellData -> cellData.getValue().getProperty(columnName));
                col.setPrefWidth(50);
                tableView.getColumns().add(col);
            }
            TableColumn<Table, String> unitColumn = new TableColumn<>("Unit");
            unitColumn.setCellValueFactory(cellData -> cellData.getValue().getProperty("Unit"));
            unitColumn.setPrefWidth(50);
            tableView.getColumns().add(unitColumn);

            DecimalFormat df = new DecimalFormat("#.##");
            List<element> elements = e.getElements();
            double frequency = e.getFrequency();
            System.out.print(frequency);
            List<String> impedanceIntensityValues = new ArrayList<>();
            List<String> voltageIntensityValues = new ArrayList<>();
            List<String> currentIntensityValues = new ArrayList<>();
            for (element element : elements) {
                Complex impedance = element.getImpedance(frequency);
                String imString = df.format(impedance.getReal());
    
                Complex voltage = e.getVoltage(element, frequency);
                String cuString = df.format(voltage.getReal());
    
                Complex current = e.getCurrent(element, frequency);
                String donString = df.format(current.getReal());    

                impedanceIntensityValues.add(imString); // Thêm giá trị từ biến imString
                voltageIntensityValues.add(cuString);
                currentIntensityValues.add(donString);
            }

            // Tạo dữ liệu mẫu
            ObservableList<Table> data = FXCollections.observableArrayList(
                    new Table("U (Voltage)", "V", voltageIntensityValues),
                    new Table("I (Current intensity)", "A", currentIntensityValues), // Thêm giá trị từ biến imString
                    new Table("R (Resistance)", "Ω", impedanceIntensityValues));

            // Thêm dữ liệu vào TableView
            tableView.setItems(data);

            return tableView;
        }
    }
}