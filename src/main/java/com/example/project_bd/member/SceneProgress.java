package com.example.project_bd.member;

import com.example.project_bd.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SceneProgress implements Initializable {
    @FXML
    private TableColumn<getPesanan, String> colNama;
    @FXML
    private TableColumn <getPesanan, String>colStatus;
    @FXML
    private TableView<getPesanan> pesananTableView;

    @FXML
    private TableColumn <getHistoryMember, String>colDeskripsi;

    @FXML
    private TableColumn <getHistoryMember, String>colWaktu;

    @FXML
    private TableView<getHistoryMember> historyTableView;

    ObservableList<getHistoryMember> dataHistory = FXCollections.observableArrayList();


    public ObservableList<getPesanan> dataPesan (){
        String SQL = "SELECT  j.nama_jasa, CASE WHEN d.status = 1 THEN 'SELESAI' WHEN d.status = 0 THEN" +
                " 'BELUM SELESAI' END AS status_pengerjaan, \n" +
                "d.nota_Id, d.jasa_id FROM detail_pesanan d  INNER JOIN jasa j ON d.jasa_id = j.id where d.nota_id = '"
                + SceneMember.nota_id + "'";

        ObservableList<getPesanan> dataPesanan = FXCollections.observableArrayList();
        try {

            Connection con = MainApplication.createDatabaseConnection();
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(SQL);
            getPesanan getP;
            int column_count = result.getMetaData().getColumnCount();
            if(column_count > 0) // ada data
            {
                while (result.next()){
                    getP = new getPesanan(result.getString("nama_jasa"),
                            result.getString("status_pengerjaan"),
                            result.getInt("nota_id"),
                            result.getInt("jasa_id"));

                    dataPesanan.add(getP); }
                con.close();
            }

        }catch (Exception e){e.printStackTrace();}
        return dataPesanan;
    }

    private ObservableList<getPesanan> pesananListData;
    //UNTUK MENUNJUKKAN DATA DI TABLEVIEW
    public void PesananShowData(){
        pesananListData =  dataPesan();

        colNama.setCellValueFactory(new PropertyValueFactory<>("nama_jasa"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));


        pesananTableView.setItems(pesananListData);

    }

    public void fillDetailTable( int js)
    {
        try {
            Connection con = MainApplication.createDatabaseConnection();
            String query ="SELECT waktu, deskripsi FROM  history_detail_pesanan WHERE nota_id  = '" + SceneMember.nota_id
                    + "' and jasa_id ='"+js+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);


            int column_count = rs.getMetaData().getColumnCount();
            dataHistory.clear();
            if(column_count > 0) // ada data
            {
                while (rs.next())
                {
                    getHistoryMember getH = new getHistoryMember(rs.getTimestamp("waktu"),
                            rs.getString("deskripsi"));

                    dataHistory.add(getH);
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        historyTableView.setItems(dataHistory);
    }



    public void Initialize(){
        PesananShowData();


        pesananTableView.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<getPesanan>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends getPesanan> change) {
                getPesanan selected = pesananTableView.getSelectionModel().getSelectedItem();

                if (selected != null) {
                    fillDetailTable(selected.getJasa_id());
                }

            }
        });
        historyTableView.setItems(dataHistory);
        colWaktu.setCellValueFactory(new PropertyValueFactory<>("waktu"));
        colDeskripsi.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));



    }

    @FXML
    protected void onBackClick () {
        MainApplication app = MainApplication.getApplicationInstance();
        Stage primStage = app.getPrimaryStage();
        Scene memberScene = app.getSceneMember();
        primStage.setTitle("Member View");
        primStage.setScene(memberScene);

//        historyTableView.getItems().clear();
//        pesananTableView.getItems().clear();
        pesananListData = FXCollections.observableArrayList();
        dataHistory = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}