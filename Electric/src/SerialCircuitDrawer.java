import Components.element;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class SerialCircuitDrawer extends Application {

    int ElementDistance = 50;
    int ResitorLength=40;
    int inductorLength=40;
    int CapacitorLength=10;
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
        primaryStage.setTitle("Serial Circuit");
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
        int x = 60;
        int y = 180;
        drawVoltageSource(gc, x, y);

        x=60;y=60;
        drawResistor(gc, x, y);
        drawLine(gc, x+=ResitorLength, y);

        drawCapacitor(gc, x+=ElementDistance, y);
        drawLine(gc, x+=CapacitorLength, y);

        drawInductor(gc, x+=ElementDistance, y);
        
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
        gc.strokeLine(x, y, x+6, y-10);
        gc.strokeLine(x+6, y-10, x+13, y+10);
        gc.strokeLine(x+13, y+10, x+20, y-10);
        gc.strokeLine(x+20, y-10, x+27, y+10);
        gc.strokeLine(x+27, y+10, x+34, y-10);
        gc.strokeLine(x+34, y-10, x+40, y);

        //duong day tren
        
        //duong day duoi
        
    }

    private void drawCapacitor(GraphicsContext gc, double x, double y) {

        //capacitor
        gc.strokeLine(x, y-10, x, y+10);
        gc.strokeLine(x+10, y-10, x+10, y+10);
   

        //duong day tren
        
        //duong day duoi
    }

    private void drawInductor(GraphicsContext gc, double x, double y) {

        //inductor
        gc.strokeArc(x, y-20, 14, 38, 180+40+90, 180+50, ArcType.OPEN );
        gc.strokeArc(x+9, y-20, 14, 38, 180+40+90, 180+100, ArcType.OPEN );
        gc.strokeArc(x+18, y-20, 14, 38, 180+40+90, 180+100, ArcType.OPEN );
        gc.strokeArc(x+27, y-20, 12, 38, 180+90+90, 180+50, ArcType.OPEN );

        //duong day tren
        
        //duong day duoi
    }

    private void drawVoltageSource(GraphicsContext gc, double x, double y) {
        gc.strokeOval(x,y-20,40,40);

        //dau cong
        gc.strokeLine(x+5, y, x+15, y);
        gc.strokeLine(x+10, y-5, x+10, y+5);

        //dau tru
        gc.strokeLine(x+25, y, x+35, y);

        //duong day tren
        
        
        //duong day duoi
        
        
    }

    private void drawLine(GraphicsContext gc, double x, double y) {
        gc.strokeLine(x, y, x+ElementDistance, y);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
