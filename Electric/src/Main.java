import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
			Parent root = FXMLLoader.load(getClass().getResource(
					"/source_fxml/welcome.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("source_fxml/style.css").toExternalForm());
			primaryStage.setTitle("Electronic Circuit Simulator");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}