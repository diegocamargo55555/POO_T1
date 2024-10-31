package projeto1.appmitologia.controller;

import javafx.scene.control.*;
import projeto1.appmitologia.model.Conto;
import projeto1.appmitologia.dao.ContoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.sql.SQLException;

public class IncluiContoController {

    @FXML
    private Button bCadastro;

    @FXML
    private TextField nome, localizacao, nomeHeroi;

    @FXML
    private TextArea descricao;

    @FXML
    private void bCadastroOnAction(ActionEvent event) {
        Conto conto = new Conto();
        conto.setNome(nome.getText());
        conto.setDescricao(descricao.getText());
        conto.setLocalizacao(localizacao.getText());
        conto.setNomeHeroi(nomeHeroi.getText());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deseja inserir o Conto?", ButtonType.CANCEL, ButtonType.OK);
        alert.setTitle("Conto pode ser cadastrado!");
        alert.setHeaderText("Informação");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                ContoDAO contoDAO = new ContoDAO();
                try {
                    contoDAO.insere(conto);
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Conto cadastrado com sucesso!", ButtonType.OK);
                    successAlert.setTitle("Sucesso");
                    successAlert.setHeaderText("Informação");
                    successAlert.show();
                } catch (SQLException e1) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Erro ao cadastrar conto!", ButtonType.OK);
                    errorAlert.setTitle("Erro");
                    errorAlert.setHeaderText("Informação");
                    errorAlert.show();
                    e1.printStackTrace();
                }
            } else {
                Alert cancelAlert = new Alert(Alert.AlertType.INFORMATION, "Cadastro de conto cancelado.", ButtonType.OK);
                cancelAlert.setTitle("Cancelado");
                cancelAlert.setHeaderText("Informação");
                cancelAlert.show();
            }
        });
    }
}