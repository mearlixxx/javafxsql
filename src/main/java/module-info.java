module com.example.javafxsql {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.javafxsql to javafx.fxml;
    exports com.example.javafxsql;
}