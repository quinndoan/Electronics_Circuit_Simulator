import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SpringSymbol extends Application {

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Canvas canvas = new Canvas(400, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        drawSpring(gc);

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }

    private void drawSpring(GraphicsContext gc) {
        double startX = 50;
        double startY = 200;
        double amplitude = 50;
        double frequency = 0.1;
        double angularFrequency = 2 * Math.PI * frequency;
        double x = startX;
        double y;

        gc.setStroke(Color.BLACK);

        for (double t = 0; t <= 2 * Math.PI; t += 0.1) {
            y = startY + amplitude * Math.sin(angularFrequency * t);
            gc.strokeLine(x, y, x + 1, y); // Draw a small line segment
            x += 1; // Increment x-coordinate
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
