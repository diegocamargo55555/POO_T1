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

public class MenuController {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    @FXML
    private TextField UserName, senha;
    private Button login;

    @FXML
    private MenuItem incluiHeroi, incluiConto, deletaHeroi, deletaConto,
            consultaHeroi, consultaConto, atualizaHeroi, atualizaConto;

    @FXML
    void loginOnAction(ActionEvent event) throws SQLException {

        UserDAO userDAO = new UserDAO();
        String username = UserName.getText();
        String pass = senha.getText();

        if (userDAO.authenticateUser(username, pass)) {
            JOptionPane.showMessageDialog(null, "Login Successful");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto1/appmitologia/view/menuLogado.fxml"));
                Parent root = loader.load();
                Stage newStage = new Stage();
                Scene newScene = new Scene(root);
                newStage.setScene(newScene);
                newStage.setResizable(false);
                newStage.show();
                Stage stage = (Stage) login.getScene().getWindow();
                stage.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Credentials");
        }
    }

    @FXML
    void incluiHeroiOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto1/appmitologia/view/incluiHeroi.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            Scene newScene = new Scene(root);
            newStage.setScene(newScene);
            newStage.setTitle("Inclui Herói");
            newStage.setResizable(false);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void atualizaHeroiOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto1/appmitologia/view/atualizaHeroi.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            Scene newScene = new Scene(root);
            newStage.setScene(newScene);
            newStage.setTitle("Atualiza Herói");
            newStage.setResizable(false);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void consultaHeroiOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto1/appmitologia/view/consultaHeroi.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            Scene newScene = new Scene(root);
            newStage.setScene(newScene);
            newStage.setTitle("Consulta Herói");
            newStage.setResizable(false);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deletaHeroiOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto1/appmitologia/view/deletaHeroi.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            Scene newScene = new Scene(root);
            newStage.setScene(newScene);
            newStage.setTitle("Deletar Herói");
            newStage.setResizable(false);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void incluiContoOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto1/appmitologia/view/incluiConto.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            Scene newScene = new Scene(root);
            newStage.setScene(newScene);
            newStage.setTitle("Inclui Conto");
            newStage.setResizable(false);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void atualizaContoOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto1/appmitologia/view/atualizaConto.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            Scene newScene = new Scene(root);
            newStage.setScene(newScene);
            newStage.setTitle("Atualiza Conto");
            newStage.setResizable(false);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void consultaContoOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto1/appmitologia/view/consultaConto.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            Scene newScene = new Scene(root);
            newStage.setScene(newScene);
            newStage.setTitle("Consulta Conto");
            newStage.setResizable(false);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deletaContoOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto1/appmitologia/view/deletaConto.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            Scene newScene = new Scene(root);
            newStage.setScene(newScene);
            newStage.setTitle("Deletar Conto");
            newStage.setResizable(false);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cadastroUserOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto1/appmitologia/view/cadastroUser.fxml"));
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
}