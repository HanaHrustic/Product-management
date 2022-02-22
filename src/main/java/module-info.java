module com.example.hrustic7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires java.sql;

    exports hr.java.production;
    opens hr.java.production to javafx.fxml;
    opens hr.java.production.model to javafx.base;
}