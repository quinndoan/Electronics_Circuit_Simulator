module demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires deeplearning4j.core;
    requires nd4j.nativeplatform;

    opens demo to javafx.fxml;
    exports demo;
}
