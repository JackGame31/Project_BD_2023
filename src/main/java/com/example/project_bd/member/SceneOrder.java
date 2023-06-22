package com.example.project_bd.member;

import com.example.project_bd.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SceneOrder implements Initializable {
    @FXML
    private Label saldo;
    @FXML
    private Label nominal;
    @FXML
    private TextField kuantitas;
    @FXML
    private TableView <String> table_pesanan;
    protected String jenisJasa;


    @FXML
    private ChoiceBox<String> listJasa;

    @FXML
    protected void getListJasa (MouseEvent event) {
        jenisJasa = listJasa.getSelectionModel().getSelectedItem();
        System.out.println(jenisJasa);
    }

    @FXML
    protected void onCancelClick () {
        MainApplication app = MainApplication.getApplicationInstance();
        Stage primStage = app.getPrimaryStage();
        Scene memberScene = app.getSceneMember();
        primStage.setTitle("Member View");
        primStage.setScene(memberScene);
    }

    @FXML
    protected void onAddOrderClick () {
        int jumlah = Integer.parseInt(kuantitas.getText());
        System.out.println(jumlah);
    }

    @FXML
    protected void onMakeOrderClick (ActionEvent ae) {
        //ini belum ta buat  nyambung ke pembuatan nota sama pengurangan saldo nya.
        //if (saldo >= nominal) {}
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Order successfull!");
        alert.setContentText("Make this order? Click OK to confirm.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isEmpty()){
            System.out.println("Alert closed");
        } else if (result.get() == ButtonType.OK) {
            MainApplication app = MainApplication.getApplicationInstance();
            Stage primStage = app.getPrimaryStage();
            Scene memberScene = app.getSceneMember();
            primStage.setTitle("Member View");
            primStage.setScene(memberScene);

            System.out.println("OK. Order is made.");
            //saldo.setText(String.valueOf(Integer.parseInt(saldo.getText()) - Integer.parseInt(nominal.getText())));
        } else if (result.get() == ButtonType.CANCEL) {
            System.out.println("Order is cancelled.");
        }

        /*

        else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Saldo tidak mencukupi!");

         */
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listJasa.getItems().addAll("Cuci Setrika", "Cuci Lipat", "Laundry Sprei/Selimut",
                "Pengharum Baju", "Dry Clean Gaun Pesta", "Dry Clean Jas");
    }
}