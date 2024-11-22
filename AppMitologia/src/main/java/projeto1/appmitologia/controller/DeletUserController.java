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
import projeto1.appmitologia.model.Session;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
public class DeletUserController {
    @FXML
    private PasswordField senha;
    //Refatoração 2 - não precisar inserir o nome de usuario para deletar um usuario
    @FXML
    void DeletUserOnAction(ActionEvent event) throws SQLException {

        UserDAO userDAO = new UserDAO();
        String pass = senha.getText();

        if (userDAO.authenticateUser(Session.getCookie(), pass)) {
            UserDAO.remove(Session.getCookie());
            Alert alertConfirmacao = new Alert(Alert.AlertType.INFORMATION, "Usuario deletado com sucesso!", ButtonType.OK);
            alertConfirmacao.show();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Credentials");
        }
    }
}