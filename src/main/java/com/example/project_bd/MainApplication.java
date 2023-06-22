package com.example.project_bd;

import com.example.project_bd.login.*;
import com.example.project_bd.member.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainApplication extends Application {
    //DATABASE CONNECTION
    final static String driver = "com.mysql.cj.jdbc.Driver";
    final static String databaseName = "laundry";
    final static String url = "jdbc:mysql://localhost/" + databaseName;
    final static String user = "root";
    final static String password = "";

    //ATTRIBUTE
    //IMPORTANT
    private static MainApplication applicationInstance;
    private Stage primaryStage;

    //ATTRIBUTE SCENE
    //LOGIN
    private Scene SceneLogin;
    private SceneLogin sceneLoginController;

    private Scene sceneSignInMember;
    private SceneSignInMember sceneSignInMemberController;

    private Scene sceneSignInStaff;
    private SceneSignInStaff sceneSignInStaffController;

    //MEMBER
    private Scene sceneMember;
    private SceneMember sceneMemberController;

    private Scene sceneOrder;
    private SceneOrder sceneOrderController;

    private Scene sceneProgress;
    private SceneProgress sceneProgressController;

    //CONSTRUCTOR
    public MainApplication(){
        applicationInstance = this;
    }

    //FUNCTIONS
    public static Connection createDatabaseConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, user, password);
        return con;
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;

        //Inisiasi Login Scene
        {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login/SceneLogin.fxml"));
            SceneLogin = new Scene(fxmlLoader.load(), 800, 600);
            sceneLoginController = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login/SceneSignInMember.fxml"));
            sceneSignInMember = new Scene(fxmlLoader.load(), 800, 600);
            sceneSignInMemberController = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login/SceneSignInStaff.fxml"));
            sceneSignInStaff = new Scene(fxmlLoader.load(), 800, 600);
            sceneSignInStaffController = fxmlLoader.getController();
        }

        //Inisiasi Member Scene
        {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("member/SceneMember.fxml"));
            sceneMember = new Scene(fxmlLoader.load(), 800, 600);
            sceneMemberController = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(MainApplication.class.getResource("member/SceneOrder.fxml"));
            sceneOrder = new Scene(fxmlLoader.load(), 800, 600);
            sceneOrderController = fxmlLoader.getController();

            fxmlLoader = new FXMLLoader(MainApplication.class.getResource("member/SceneProgress.fxml"));
            sceneProgress = new Scene(fxmlLoader.load(), 800, 600);
            sceneProgressController = fxmlLoader.getController();

        }

        stage.setTitle("Log In");
        stage.setScene(SceneLogin);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    //GETTER AND SETTER
    public static MainApplication getApplicationInstance(){
        return applicationInstance;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Scene getSceneLogin() {
        return SceneLogin;
    }

    public com.example.project_bd.login.SceneLogin getSceneLoginController() {
        return sceneLoginController;
    }

    public Scene getSceneSignInMember() {
        return sceneSignInMember;
    }

    public SceneSignInMember getSceneSignInMemberController() {
        return sceneSignInMemberController;
    }

    public Scene getSceneSignInStaff() {
        return sceneSignInStaff;
    }

    public SceneSignInStaff getSceneSignInStaffController() {
        return sceneSignInStaffController;
    }

    public Scene getSceneMember() {
        return sceneMember;
    }

    public SceneMember getSceneMemberController() {
        return sceneMemberController;
    }

    public Scene getSceneOrder() {
        return sceneOrder;
    }

    public SceneOrder getSceneOrderController() {
        return sceneOrderController;
    }

    public Scene getSceneProgress() {
        return sceneProgress;
    }

    public SceneProgress getSceneProgressController() {
        return sceneProgressController;
    }
}