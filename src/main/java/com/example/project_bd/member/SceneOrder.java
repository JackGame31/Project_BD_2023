package com.example.project_bd.member;

import com.example.project_bd.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;

public class SceneOrder implements Initializable {
    @FXML
    private Label labelJenisJasa;
    @FXML
    private Label nominal;
    @FXML
    private TextField kuantitas;
    @FXML
    private MenuItem cuci_setrika;
    @FXML
    private MenuItem cuci_lipat;
    @FXML
    private MenuItem laundry_sprei_selimut;
    @FXML
    private MenuItem pengharum_laundry;
    @FXML
    private MenuItem dryClean_gaunPesta;
    @FXML
    private MenuItem dryClean_jas;
    @FXML
    private TableView <OrderProperty> table_pesanan;
    @FXML
    TableColumn<OrderProperty, String> kolom_jenisJasa;
    @FXML
    TableColumn<OrderProperty, Integer> kolom_kuantitas;
    @FXML
    TableColumn<OrderProperty, Integer> kolom_harga;
    @FXML
    TableColumn<OrderProperty, Integer> kolom_subtotal;
    ObservableList<OrderProperty> listNamaOrder = FXCollections.observableArrayList();
    DecimalFormat df = new DecimalFormat("###,##0.00");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        kolom_jenisJasa.setCellValueFactory(new PropertyValueFactory<OrderProperty, String>("kolom_jenisJasa"));
        kolom_kuantitas.setCellValueFactory(new PropertyValueFactory<OrderProperty, Integer>("kolom_kuantitas"));
        kolom_harga.setCellValueFactory(new PropertyValueFactory<OrderProperty, Integer>("kolom_harga"));
        kolom_subtotal.setCellValueFactory(new PropertyValueFactory<OrderProperty, Integer>("kolom_subtotal"));
    }

    @FXML
    protected void onCancelClick () {
        MainApplication app = MainApplication.getApplicationInstance();
        Stage primStage = app.getPrimaryStage();
        Scene memberScene = app.getSceneMember();
        primStage.setTitle("Member View");
        primStage.setScene(memberScene);

        listNamaOrder = FXCollections.observableArrayList();

        cuci_setrika.setDisable(false);
        cuci_lipat.setDisable(false);
        laundry_sprei_selimut.setDisable(false);
        pengharum_laundry.setDisable(false);
        dryClean_gaunPesta.setDisable(false);
        dryClean_jas.setDisable(false);

        table_pesanan.getItems().clear();
        labelJenisJasa.setText("");
        kuantitas.setText("");
        nominal.setText("");
    }

    @FXML
    protected void onAddOrderClick () {
        int jumlah = Integer.parseInt(kuantitas.getText());
        System.out.println(labelJenisJasa.getText());
        System.out.println(jumlah);

        switch (labelJenisJasa.getText()){
            case "Cuci Setrika":
                listNamaOrder.add(new OrderProperty(labelJenisJasa.getText(), 1,
                        jumlah,
                        50000,
                        jumlah * 50000));
                cuci_setrika.setDisable(true);
                break;
            case "Cuci Lipat":
                listNamaOrder.add(new OrderProperty(labelJenisJasa.getText(), 2,
                        jumlah,
                        30000,
                        jumlah * 30000));
                cuci_lipat.setDisable(true);
                break;
            case "Laundry Sprei / Selimut":
                listNamaOrder.add(new OrderProperty(labelJenisJasa.getText(), 3,
                        jumlah,
                        20000,
                        jumlah * 20000));
                laundry_sprei_selimut.setDisable(true);
                break;
            case "Dry Clean Gaun Pesta":
                listNamaOrder.add(new OrderProperty(labelJenisJasa.getText(), 4,
                        jumlah,
                        20000,
                        jumlah * 20000));
                dryClean_gaunPesta.setDisable(true);
                break;
            case "Pengharum Laundry":
                listNamaOrder.add(new OrderProperty(labelJenisJasa.getText(), 5,
                        jumlah,
                        1000,
                        jumlah * 1000));
                pengharum_laundry.setDisable(true);
                break;
            case "Dry Clean Jas":
                listNamaOrder.add(new OrderProperty(labelJenisJasa.getText(), 6,
                        jumlah,
                        25000,
                        jumlah * 25000));
                dryClean_jas.setDisable(true);
                break;
        }

        labelJenisJasa.setText("");
        kuantitas.setText("");

        table_pesanan.setItems(listNamaOrder);

        int total = 0;
        for (OrderProperty order:
                listNamaOrder) {
            total += order.getKolom_subtotal();
        }
        nominal.setText("Rp " + df.format(total));
    }

    @FXML
    protected void onMakeOrderClick (ActionEvent ae) {
        //ini belum ta buat  nyambung ke pembuatan nota sama pengurangan saldo nya.
        Alert alert = new Alert(Alert.AlertType.NONE, "Make this order? Click OK to confrim.", ButtonType.OK, ButtonType.CANCEL);
        alert.setTitle("Order confirmation");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isEmpty()){
            System.out.println("Alert closed");
        } else if (result.get() == ButtonType.OK) {
            try{
                Connection con = MainApplication.createDatabaseConnection();
                String query = "insert into nota (member_id) value (" + SceneMember.member_id +");";
                PreparedStatement ps = con.prepareStatement(query);
                ps.executeUpdate();

                for (OrderProperty order:
                        listNamaOrder) {
                    query = String.format("INSERT INTO detail_pesanan (nota_id, jasa_id, jumlah, status) VALUES ((SELECT MAX(id) FROM nota), %d, %d, 0);", order.getKolom_jasaID(), order.getKolom_kuantitas());
                    ps = con.prepareStatement(query);
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            MainApplication.getApplicationInstance().getSceneMemberController().initializeScene();
            listNamaOrder = FXCollections.observableArrayList();

            Alert alertConfirm = new Alert(Alert.AlertType.NONE, "Order made!", ButtonType.OK);
            alertConfirm.setTitle("Order made");
            Optional<ButtonType> resultConfirm = alertConfirm.showAndWait();

            if (resultConfirm.isEmpty()) {
                table_pesanan.getItems().clear();
                MainApplication app = MainApplication.getApplicationInstance();
                Stage primStage = app.getPrimaryStage();
                Scene memberScene = app.getSceneMember();
                primStage.setTitle("Member View");
                primStage.setScene(memberScene);

                System.out.println("OK. Order is made.");

            } else if (resultConfirm.get() == ButtonType.OK) {
                table_pesanan.getItems().clear();
                MainApplication app = MainApplication.getApplicationInstance();
                Stage primStage = app.getPrimaryStage();
                Scene memberScene = app.getSceneMember();
                primStage.setTitle("Member View");
                primStage.setScene(memberScene);
                System.out.println("OK. Order is made.");
            }

        } else if (result.get() == ButtonType.CANCEL) {
            System.out.println("Order is cancelled.");
        }

        nominal.setText("");
    }
    @FXML
    public void clickCuciSetrika(){
        labelJenisJasa.setText("Cuci Setrika");
    }

    @FXML
    public void clickCuciLipat(){
        labelJenisJasa.setText("Cuci Lipat");
    }

    @FXML
    public void clickLaundrySpreiSelimut(){
        labelJenisJasa.setText("Laundry Sprei / Selimut");
    }

    @FXML
    public void clickPengharumLaundry(){
        labelJenisJasa.setText("Pengharum Laundry");
    }

    @FXML
    public void clickDryCleanGaunPesta(){
        labelJenisJasa.setText("Dry Clean Gaun Pesta");
    }

    @FXML
    public void clickDryCleanJas(){
        labelJenisJasa.setText("Dry Clean Jas");
    }
}