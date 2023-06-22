module com.example.project_bd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports com.example.project_bd;
    opens com.example.project_bd to javafx.fxml;
    exports com.example.project_bd.login;
    opens com.example.project_bd.login to javafx.fxml;
    exports com.example.project_bd.member;
    opens com.example.project_bd.member to javafx.fxml;
}