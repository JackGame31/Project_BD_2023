package com.example.project_bd.login;

import com.example.project_bd.MainApplication;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.sql.*;

public class SceneSignInMember {
    @FXML
    private Text keterangan;
    @FXML
    private TextField email;
    @FXML
    private TextField namadepan;
    @FXML
    private TextField namabelakang;
    @FXML
    private TextField nomorhp;
    @FXML
    private TextField alamat;
    @FXML
    private TextField password;
    @FXML
    private Button submitdata;
    @FXML
    private Button back;


    @FXML
    protected void uploadData(){
        try{
            Connection con = MainApplication.createDatabaseConnection();

            String query = "INSERT INTO member (nama_depan,nama_belakang,alamat,no_hp,email,password) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, namadepan.getText());
            preparedStatement.setString(2, namabelakang.getText());
            preparedStatement.setString(3, alamat.getText());
            preparedStatement.setString(4, nomorhp.getText());
            preparedStatement.setString(5, email.getText());
            preparedStatement.setString(6, password.getText());

            preparedStatement.executeUpdate();
            con.close();

            Window owner = submitdata.getScene().getWindow();
            showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful",
                    "Welcome " + namadepan.getText());

            SwitchkeSceneLogin();


        } catch (ClassNotFoundException e) {
            keterangan.setText("invalid, email atau pass salah");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            keterangan.setText("invalid, email atau pass salah");
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void SwitchkeSceneLogin(){
        MainApplication app = MainApplication.getApplicationInstance();
        Stage primaryStage = app.getPrimaryStage();
        Scene scene_login = app.getSceneLogin();
        primaryStage.setScene(scene_login);
    }

    public static void showAlert(Alert.AlertType _alertType, Window _owner,
                                 String _title, String _message){
        Alert alert = new Alert(_alertType);
        alert.setTitle(_title);
        alert.setHeaderText(null);
        alert.setContentText(_message);
        alert.initOwner(_owner);
        alert.show();
    }



}