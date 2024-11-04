package projeto1.appmitologia.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projeto1.appmitologia.dao.UserDAO;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;



public class DeletUserController {

    @FXML
    private TextField UserName;
    @FXML
    private PasswordField senha;


    @FXML
    void DeletUserOnAction(ActionEvent event) throws SQLException {

        UserDAO userDAO = new UserDAO();
        String username = UserName.getText();
        String pass = senha.getText();

        if (userDAO.authenticateUser(username, pass)) {
            JOptionPane.showMessageDialog(null, "Usuario encontrado");

            UserDAO.remove(username);
            Alert alertConfirmacao = new Alert(Alert.AlertType.INFORMATION, "Usuario deletado com sucesso!", ButtonType.OK);
            alertConfirmacao.show();

        } else {
            JOptionPane.showMessageDialog(null, "Invalid Credentials");
        }
    }

}
