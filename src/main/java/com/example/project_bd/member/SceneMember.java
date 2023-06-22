package com.example.project_bd.member;

import com.example.project_bd.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SceneMember implements Initializable {
    @FXML
    private TableView<NotaProperty> list_nota;
    @FXML
    private TableColumn<NotaProperty, Integer> notaIdColumn;
    @FXML
    private TableColumn<NotaProperty, String> tanggalPemesananColumn;
    @FXML
    private TableColumn<NotaProperty, String> siapDiambilColumn;
    @FXML
    private TableColumn<NotaProperty, String> selesaiColumn;

    ObservableList<NotaProperty> nota;
    public static int member_id;
    public static int nota_id;

    @FXML
    protected void onOrderClick() {
        MainApplication app = MainApplication.getApplicationInstance();
        Stage primStage = app.getPrimaryStage();
        Scene orderScene = app.getSceneOrder();
        primStage.setTitle("Order");
        primStage.setScene(orderScene);
    }

    @FXML
    protected void onProgressClick() {
        MainApplication app = MainApplication.getApplicationInstance();
        Stage primStage = app.getPrimaryStage();
        Scene progressScene = app.getSceneProgress();
        primStage.setTitle("Progress");
        primStage.setScene(progressScene);
    }

    @FXML
    protected void onComplainClick() {
    }

    public void initializeScene(){
        refreshTable();
    }

    private void refreshTable(){
        nota = FXCollections.observableArrayList();
         try{
             Connection con = MainApplication.createDatabaseConnection();
             String query = "SELECT " +
                     "n.id, " +
                     "DATE_FORMAT(n.tanggal_pemesanan, '%Y-%m-%d') as tanggal_pemesanan, " +
                     "CASE " +
                     "   WHEN (SELECT COUNT(*) FROM detail_pesanan WHERE nota_id = n.id AND status = 0) = 0 THEN 'Selesai' " +
                     "   ELSE 'Diproses' " +
                     "END AS progress, " +
                     "CASE " +
                     "   WHEN tanggal_pengambilan IS NULL THEN 'Belum Diambil' " +
                     "   ELSE 'Sudah Diambil' " +
                     "END AS selesai " +
                     "FROM nota n " +
                     "WHERE member_id = " + member_id;
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query);
             int column_count = rs.getMetaData().getColumnCount();
             if (column_count > 0){
                 while (rs.next()){
                     int nota_id = rs.getInt(1);
                     String tanggalPemesanan = rs.getString(2);
                     String siapDiambil = rs.getString(3);
                     String selesai = rs.getString(4);
                     nota.add(new NotaProperty(nota_id, tanggalPemesanan, siapDiambil, selesai));
                 }
             }
         } catch (SQLException e) {
             throw new RuntimeException(e);
         } catch (ClassNotFoundException e) {
             throw new RuntimeException(e);
         }
         list_nota.setItems(nota);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        notaIdColumn.setCellValueFactory(new PropertyValueFactory("notaId"));
        tanggalPemesananColumn.setCellValueFactory(new PropertyValueFactory("tanggalPemesanan"));
        siapDiambilColumn.setCellValueFactory(new PropertyValueFactory("siapDiambil"));
        selesaiColumn.setCellValueFactory(new PropertyValueFactory("selesai"));

        list_nota.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 && !list_nota.getSelectionModel().isEmpty()) {
                // Mendapatkan baris yang terpilih
                NotaProperty selectedItem = list_nota.getSelectionModel().getSelectedItem();
                nota_id = selectedItem.getNotaId();
                System.out.println(nota_id);
            }
        });
    }
}