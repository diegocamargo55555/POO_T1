package projeto1.appmitologia.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projeto1.appmitologia.dao.UserDAO;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;



public class DeletUserController {

    @FXML
    private TextField UserName, senha;


    @FXML
    void DeletUserOnAction(ActionEvent event) throws SQLException {

        UserDAO userDAO = new UserDAO();
        String username = UserName.getText();
        String pass = senha.getText();

        if (userDAO.authenticateUser(username, pass)) {
            JOptionPane.showMessageDialog(null, "Login Successful");

            try {
                FileWriter myWriter = new FileWriter("session_cookie.txt");
                myWriter.write(username);
                myWriter.close();
                System.out.println("Successfully wrote to the file.");

            } catch (IOException e) {
                System.out.println("\n\n\n\n An error occurred. na hora de escrever os COOKIES \n\n\n\n");
                e.printStackTrace();
            }
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

}
