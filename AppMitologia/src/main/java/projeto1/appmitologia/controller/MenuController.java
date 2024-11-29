package projeto1.appmitologia.controller;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.SegmentedBar;
import projeto1.appmitologia.dao.UserDAO;
import projeto1.appmitologia.model.Session;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;


public class MenuController {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    @FXML
    private TextField UserName;
    private Button login;
    @FXML
    private PasswordField passfield;
    @FXML
    private MenuItem incluiHeroi, incluiConto, deletaHeroi, deletaConto,
            consultaHeroi, consultaConto, atualizaHeroi, atualizaConto;



    @FXML
    void loginOnAction(ActionEvent event) throws SQLException {
        UserDAO userDAO = new UserDAO();
        String username = UserName.getText();
        System.out.println("\npasssenha " + passfield.getText());

        String pass = passfield.getText();

        if (userDAO.authenticateUser(username, pass)) {
            JOptionPane.showMessageDialog(null, "Login Successful");
            Session.setCookie(username);
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto1/appmitologia/view/menuLogado.fxml"));
                Parent root = loader.load();
                Stage newStage = new Stage();
                Scene newScene = new Scene(root);
                newStage.setScene(newScene);
                newStage.setResizable(false);
                newStage.show();

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