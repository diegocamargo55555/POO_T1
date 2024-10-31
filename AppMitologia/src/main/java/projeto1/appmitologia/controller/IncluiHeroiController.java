package projeto1.appmitologia.controller;

import javafx.scene.control.*;
import projeto1.appmitologia.model.Heroi;
import projeto1.appmitologia.dao.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.sql.SQLException;

public class IncluiHeroiController {

    @FXML
    private Button bCadastro;

    @FXML
    private TextField nome, img;

    @FXML
    private TextArea desc;


    @FXML
    private void bCadastroOnAction(ActionEvent event) {
        Heroi heroi = new Heroi();
        heroi.setNome(nome.getText());
        heroi.setDescricao(desc.getText());
        heroi.setUrl(img.getText());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deseja inserir o Herói?", ButtonType.CANCEL, ButtonType.OK);
        alert.setTitle("Herói pode ser cadastrado!");
        alert.setHeaderText("Informação");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                HeroiDAO heroiDAO = new HeroiDAO();
                try {
                    heroiDAO.insere(heroi);
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Herói cadastrado com sucesso!", ButtonType.OK);
                    successAlert.setTitle("Sucesso");
                    successAlert.setHeaderText("Informação");
                    successAlert.show();
                } catch (SQLException e1) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Erro ao cadastrar herói!", ButtonType.OK);
                    errorAlert.setTitle("Erro");
                    errorAlert.setHeaderText("Informação");
                    errorAlert.show();
                    e1.printStackTrace();
                }
            } else {
                Alert cancelAlert = new Alert(Alert.AlertType.INFORMATION, "Cadastro de herói cancelado.", ButtonType.OK);
                cancelAlert.setTitle("Cancelado");
                cancelAlert.setHeaderText("Informação");
                cancelAlert.show();
            }
        });
    }
}