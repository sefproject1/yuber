module com.example.yuber {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.example.yuber to javafx.fxml;
    exports com.example.yuber;
    exports com.example.yuber.controllers;
    opens com.example.yuber.controllers to javafx.fxml;
}