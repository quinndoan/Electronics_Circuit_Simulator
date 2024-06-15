module demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires shadow.deeplearning4j.core;
    requires shadow.nd4j.nativeplatform;

    opens demo to javafx.fxml;
    exports demo;
}
