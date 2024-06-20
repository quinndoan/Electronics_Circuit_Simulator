package Source_fxml;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class WelcomeController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private ImageView backgroundImageView;

    @FXML
    private VBox textContainer;

    @FXML
    private Label descriptionLabel;

    @FXML
    public void startToDes(ActionEvent event) {
        try {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Stage newStage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource(
                    "/source_fxml/des.fxml"));
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
