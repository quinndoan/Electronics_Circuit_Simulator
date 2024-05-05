import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SpringSymbol extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a canvas
        Canvas canvas = new Canvas(640, 240);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Draw circuit diagram
        drawCircuitDiagram(gc);

        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        primaryStage.setScene(new Scene(root, 640, 240));
        primaryStage.setTitle("Parallel Circuit");
        primaryStage.show();
    }

    private void drawCircuitDiagram(GraphicsContext gc) {
        // Draw circuit components
        //drawResistor(gc, 100, 150);
        //drawResistor(gc, 250, 150);
        //drawResistor(gc, 400, 150);

        // Draw connections
        //drawConnection(gc, 50, 150, 550, 150);
        //drawConnection(gc, 50, 250, 550, 250);

        // Draw voltage source
        int x = 120;
        int y = 60;
        

        drawResistor(gc, x, y);
        drawInductor(gc, x, y);
        
        //gc.strokeLine(0, 0, 640, 240);
    }

    private void drawResistor(GraphicsContext gc, double x, double y) {

        //gc.setStroke(Color.BLACK);
        //gc.setLineWidth(2);
        //gc.strokeLine(x, y, x + 50, y);
        //gc.strokeLine(x + 50, y, x + 60, y - 10);
        //gc.strokeLine(x + 60, y - 10, x + 70, y + 10);
        //gc.strokeLine(x + 70, y + 10, x + 80, y - 10);
        //gc.strokeLine(x + 80, y - 10, x + 90, y + 10);
        //gc.strokeLine(x + 90, y + 10, x + 100, y);

        //resitor
        gc.strokeLine(x, y+40, x+10, y+46);
        gc.strokeLine(x+10, y+45, x-10, y+53);
        gc.strokeLine(x-10, y+53, x+10, y+60);
        gc.strokeLine(x+10, y+60, x-10, y+67);
        gc.strokeLine(x-10, y+67, x+10, y+74);
        gc.strokeLine(x+10, y+74, x, y+80);

        //duong day tren
        gc.strokeLine(x, y, x, y+40);
        
        //duong day duoi
        gc.strokeLine(x, y+80, x, y+120);
        
    }
    private void drawInductor(GraphicsContext gc, double x, double y) {
        // Draw coil shape
        double startX = x;
        double startY = y + 40;
        double endY = y + 80;
        double radius = 20;
        double step = 10;
    
        gc.beginPath();
        gc.moveTo(startX, startY);
    
        // Draw coil
        for (double yPos = startY; yPos <= endY; yPos += step) {
            gc.arcTo(startX + radius, yPos, startX + 2 * radius, yPos + step, radius);
        }
    
        gc.lineTo(startX, endY);
        gc.stroke();
    
        // Draw connection lines
        gc.strokeLine(x, y, x, startY);
        gc.strokeLine(x, endY, x, endY + 40);
    }
}
