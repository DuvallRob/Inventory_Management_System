module java {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens main to javafx.fxml;
    exports main;
    exports controller;
    opens controller to javafx.fxml;
    opens model to javafx.fxml;
    exports model;
}