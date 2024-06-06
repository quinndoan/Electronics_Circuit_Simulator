import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class createTable {

    public static class Table {
        private String parameter;
        private ArrayList<String> elementList;

        public Table(String parameter, ArrayList<String> elementList) {
            this.parameter = parameter;
            this.elementList = elementList;
        }

        public String getParameter() {
            return parameter;
        }

        public ArrayList<String> getElementList() {
            return elementList;
        }
    }

    @SuppressWarnings("unchecked")
    public TableView<Table> createTable() {
        TableView<Table> tableView = new TableView<>();

        // Tạo cột cho parameter
        TableColumn<Table, String> parameterColumn = new TableColumn<>("Parameter");
        parameterColumn.setCellValueFactory(new PropertyValueFactory<>("parameter"));

        // Tạo cột cho elementList
        TableColumn<Table, String> elementsColumn = new TableColumn<>("Elements");
        elementsColumn.setCellValueFactory(new PropertyValueFactory<>("elementList"));

        // Thêm cột vào TableView
        tableView.getColumns().addAll(parameterColumn, elementsColumn);

        // Tạo dữ liệu mẫu
        ArrayList<String> elements1 = new ArrayList<>();
        elements1.add("R1");
        elements1.add("R2");
        elements1.add("R3");

        ArrayList<String> elements2 = new ArrayList<>();
        elements2.add("C1");
        elements2.add("C2");

        ArrayList<String> elements3 = new ArrayList<>();
        elements3.add("L1");
        elements3.add("L2");
        elements3.add("L3");
        elements3.add("L4");

        tableView.getItems().addAll(
                new Table("Parameter 1", elements1),
                new Table("Parameter 2", elements2),
                new Table("Parameter 3", elements3)
        );

        return tableView;
    }
}
