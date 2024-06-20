package Source_fxml;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class DescriptionController {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    void pressedButton(ActionEvent event) {
        try {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Stage newStage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource(
                    "/source_fxml/sample.fxml"));
            Scene scene = new Scene(root);
            newStage.setTitle("Electronic Circuit Simulator");
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

}
