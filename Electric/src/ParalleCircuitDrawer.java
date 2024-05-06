import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class ParalleCircuitDrawer {

    private Stage stage;

    @FXML
    StackPane root = new StackPane();
    @FXML
    public void GenerateCircuit(ActionEvent event) {
        // Create a canvas
        Canvas canvas = new Canvas(640, 240);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Draw circuit diagram
        drawCircuitDiagram(gc);

        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        stage.setScene(new Scene(root, 640, 240));
        stage.setTitle("Parallel Circuit");
        stage.show();
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
        drawVoltageSource(gc, x, y);
        drawParallelLine(gc, x, y);

        drawResistor(gc, x+80, y);
        drawParallelLine(gc, x+80, y);

        drawCapacitor(gc, x+160, y);
        drawParallelLine(gc, x+160, y);

        drawResistor(gc, x+240, y);
        drawParallelLine(gc, x+240, y);

        drawCapacitor(gc, x+320, y);
        drawParallelLine(gc, x+320, y);

        drawInductor(gc, x+400, y);
        
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

    private void drawCapacitor(GraphicsContext gc, double x, double y) {

        //capacitor
        gc.strokeLine(x, y+40, x, y+55);
        gc.strokeLine(x-10, y+55, x+10, y+55);
        gc.strokeLine(x-10, y+65, x+10, y+65);
        gc.strokeLine(x, y+65, x, y+80);

        //duong day tren
        gc.strokeLine(x, y, x, y+40);
        
        //duong day duoi
        gc.strokeLine(x, y+80, x, y+120);
    }

    private void drawInductor(GraphicsContext gc, double x, double y) {

        //inductor
        gc.strokeArc(x-20, y+40, 38, 14, 180+40, 180+50, ArcType.OPEN );
        gc.strokeArc(x-20, y+49, 38, 14, 180+40, 180+100, ArcType.OPEN );
        gc.strokeArc(x-20, y+58, 38, 14, 180+40, 180+100, ArcType.OPEN );
        gc.strokeArc(x-20, y+67, 38, 12, 180+90, 180+50, ArcType.OPEN );

        //duong day tren
        gc.strokeLine(x, y, x, y+40);
        
        //duong day duoi
        gc.strokeLine(x, y+80, x, y+120);
    }

    private void drawVoltageSource(GraphicsContext gc, double x, double y) {
        gc.strokeOval(x-20,y+40,40,40);

        //dau cong
        gc.strokeLine(x, y+50, x, y+60);
        gc.strokeLine(x-5, y+55, x+5, y+55);

        //dau tru
        gc.strokeLine(x-5, y+70, x+5, y+70);

        //duong day tren
        gc.strokeLine(x, y, x, y+40);
        
        //duong day duoi
        gc.strokeLine(x, y+80, x, y+120);
        
    }

    private void drawParallelLine(GraphicsContext gc, double x, double y) {
        gc.strokeLine(x, y, x+80, y);
        gc.strokeLine(x, y+120, x+80, y+120);
    }

}
