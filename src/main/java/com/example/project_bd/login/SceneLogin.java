package com.example.project_bd.login;

import com.example.project_bd.MainApplication;
import com.example.project_bd.member.SceneMember;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;

public class SceneLogin {
    @FXML
    private Label welcomeText;
    @FXML
    private Text invalid;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private Button login;
    @FXML
    private Button mbsign;
    @FXML
    private Button stsign;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void SwitchkeSceneMember() {
        MainApplication app = MainApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene_member = app.getSceneMember();
        primaryStage.setScene(scene_member);
    }



    @FXML
    protected void CekValiditasLogin(){
        try {
            Connection con = MainApplication.createDatabaseConnection();
            String query = "select * from member where email = '" + email.getText()
                    + "' and password = '"+password.getText()+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int column_count = rs.getMetaData().getColumnCount();
            if (column_count > 0){
                while (rs.next()){
                    SceneMember.member_id = rs.getInt(1);
                }
                SwitchSceneMember();
            } else {
                query = "select * from staff where email = '" + email.getText()
                        + "' and password ='"+password.getText()+"'";
                rs = st.executeQuery(query);
                column_count = rs.getMetaData().getColumnCount();
                if (column_count > 0){
                    //pindah ke staff
                }
            }

            con.close();
        } catch (ClassNotFoundException e) {
            invalid.setText("invalid, email atau pass salah");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



//    @FXML
//    protected void SwitchSceneStaff(){
//        MainApplication app = MainApplication.getApplicationInstance();
//        Stage primaryStage = app.getPrimaryStage();
//        Scene scene_staf = app.getSceneStaff();
//        primaryStage.setScene(scene_staf);
//    }



    @FXML
    protected void SwitchSceneMember(){
        MainApplication app = MainApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene = app.getSceneMember();
        SceneMember controller = app.getSceneMemberController();
        controller.initializeScene();
        primaryStage.setScene(scene);
    }

}