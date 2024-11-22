package projeto1.appmitologia.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projeto1.appmitologia.dao.UserDAO;
import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class MenuLogadoController extends MenuController {

    @FXML
    void ContoFavOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto1/appmitologia/view/contoFavorito.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            Scene newScene = new Scene(root);
            newStage.setScene(newScene);
            newStage.setResizable(false);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void HeroiFavOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto1/appmitologia/view/heroiFavorito.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            Scene newScene = new Scene(root);
            newStage.setScene(newScene);
            newStage.setResizable(false);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void SelectHeroiFavOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto1/appmitologia/view/favHeroiIncluir.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            Scene newScene = new Scene(root);
            newStage.setScene(newScene);
            newStage.setResizable(false);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void SelectContoFavOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto1/appmitologia/view/favContoIncluir.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            Scene newScene = new Scene(root);
            newStage.setScene(newScene);
            newStage.setResizable(false);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void DeletUserOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto1/appmitologia/view/deletUser.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            Scene newScene = new Scene(root);
            newStage.setScene(newScene);
            newStage.setResizable(false);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void LogoutOnAction(ActionEvent event) throws IOException {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        Stage stage = new Stage();
        FXMLLoader principal = new FXMLLoader(getClass().getResource("/projeto1/appmitologia/view/menu.fxml"));
        Parent root = principal.load();
        Scene scene = new Scene(root);
        stage.setTitle("Herois");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}












