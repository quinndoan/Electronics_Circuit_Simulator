package demo.Components.tableAnalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import demo.Components.EleController;
import demo.Components.element;
import demo.Components.complexNum.Complex;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public TableView<Table> analysisTable(EleController e) {
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

        // Tạo cột cho unit
        TableColumn<Table, String> unitColumn = new TableColumn<>("Unit");
        unitColumn.setCellValueFactory(cellData -> cellData.getValue().getProperty("Unit"));
        unitColumn.setPrefWidth(50);
        tableView.getColumns().add(unitColumn);
        List<element> elements = e.getElements();
        double frequency = e.getFrequency();
        List<String> impedanceIntensityValues = new ArrayList<>();
        List<String> voltageIntensityValues = new ArrayList<>();
        for (element element : elements) {
            Complex impedance = element.getImpedance(frequency);
            String imString1 = Double.toString(impedance.getReal());
            String imString2 = Double.toString(impedance.getImaginary());
            String imString = imString1 + " + " + imString2 + "i";
            impedanceIntensityValues.add(imString); // Thêm giá trị từ biến imString
            Complex voltage = e.getVoltage(element, frequency);
            String cuString1 = Double.toString(voltage.getReal());
            String cuString2 = Double.toString(voltage.getImaginary());
            String cString = cuString1 + " + " + cuString2 + "i";
            Complex current = e.getCurrent(element, frequency);
            voltageIntensityValues.add(cString);

        }
        // Tạo dữ liệu mẫu
        ObservableList<Table> data = FXCollections.observableArrayList(
                new Table("U (Voltage)", "V", voltageIntensityValues),
                new Table("I (Current intensity)", "A", impedanceIntensityValues), // Thêm giá trị từ biến imString
                new Table("R (Resistance)", "Ω", impedanceIntensityValues));

        // Thêm dữ liệu vào TableView
        tableView.setItems(data);

        return tableView;
    }
}
