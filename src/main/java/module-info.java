module com.example.profx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires com.dlsc.formsfx;
    requires org.json;
    requires java.desktop;
    requires java.sql;
    opens com.example.profx to javafx.fxml;
    exports com.example.profx;
}