package com.example.project_bd.member;

import com.example.project_bd.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SceneProgress implements Initializable {
    @FXML
    private TableView<NotaProperty> notaTableView;

    @FXML
    private TableColumn<NotaProperty, Integer> kolom_nomerNota;
    @FXML
    private TableColumn<NotaProperty, String> kolom_kasir;
    @FXML
    private TableColumn<NotaProperty, Integer> kolom_grandTotal;
    @FXML
    private TableColumn<NotaProperty, String> kolom_pembayaran;
    @FXML
    private TableColumn<NotaProperty, String> kolom_tanggalAmbil;
    @FXML
    private TextField nomorNota;
    @FXML
    private Button backButton;

    ObservableList<NotaProperty> notaModelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        //DatabaseConnection connectNow  = new DatabaseConnection();
        //Connection connectDB  = connectNow.getDBConnection();

        //SQL Query
        String notaViewQuery = "SELECT * FROM nota WHERE nota_id  = " + SceneMember.nota_id; //Tolong disesuaikan nama tablenya

        /*
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(notaViewQuery);

            while (queryOutput.next()) {
                int queryNotaID = queryOutput.getInt("Nomer_Nota");
                String queryKasir = queryOutput.getString("Kasir");
                int queryGrandTotal = queryOutput.getInt("Grand_Total");
                String queryPembayaran = queryOutput.getString("Pembayaran");
                String queryTanggalAmbil = queryOutput.getString("Tanggal_pengambilan");

                //Populate the ObservableList
                notaModelObservableList.add(new NotaModel(queryNotaID, queryKasir, queryGrandTotal, queryPembayaran, queryTanggalAmbil));
            }

            kolom_nomerNota.setCellValueFactory(new PropertyValueFactory<>("nomer_nota"));
            kolom_kasir.setCellValueFactory(new PropertyValueFactory<>("nama_kasir"));
            kolom_grandTotal.setCellValueFactory(new PropertyValueFactory<>("grand_total"));
            kolom_pembayaran.setCellValueFactory(new PropertyValueFactory<>("pembayaran"));
            kolom_tanggalAmbil.setCellValueFactory(new PropertyValueFactory<>("tanggal_ambil"));

            notaTableView.setItems(notaModelObservableList);


            //Initial filtered list
            FilteredList<NotaModel> filteredData = new FilteredList<>(notaModelObservableList, b -> true);

            nomorNota.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(notaModel -> {

                    //If no search value then display all records (semua nota dicetak)
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (notaModel.getNomer_nota().toString().indexOf(searchKeyword) > -1) {
                        return true; //means we found a match in nomer nota
                    } else if (notaModel.getNama_kasir().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (notaModel.getGrand_total().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (notaModel.getPembayaran().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (notaModel.getTanggal_ambil().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else {
                        return false; // no match found
                    }


                });
            });

            SortedList<NotaModel> sortedData  = new SortedList<>(filteredData);

            //Bind sorted result with Table View
            sortedData. comparatorProperty().bind(notaTableView.comparatorProperty());

            //Apply filtered and sorted data to the Table View
            notaTableView.setItems(sortedData);

        } catch (SQLException e) {
            Logger.getLogger(SceneProgress.class.getSimpleName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

         */

    }

    @FXML
    protected void onBackClick () {
        MainApplication app = MainApplication.getApplicationInstance();
        Stage primStage = app.getPrimaryStage();
        Scene memberScene = app.getSceneMember();
        primStage.setTitle("Member View");
        primStage.setScene(memberScene);
    }
}