package projeto1.appmitologia.controller;

import javafx.scene.control.*;
import projeto1.appmitologia.dao.ContoDAO;
import projeto1.appmitologia.model.Conto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.sql.SQLException;

public class IncluiContoController extends GeralAvisos {

    @FXML
    private void bCadastroOnAction(ActionEvent event) {
        Conto conto = criarConto();

        Alert confirmAlert = criarAlerta(Alert.AlertType.CONFIRMATION, "Deseja inserir o Conto?", "Cadastro de Conto");
        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                ContoDAO contoDAO = new ContoDAO();
                try {
                    contoDAO.insere(conto);
                    criarAlerta(Alert.AlertType.INFORMATION, "Conto cadastrado com sucesso!", "Sucesso").show();
                } catch (SQLException e) {
                    mostrarErroAlert("Erro ao cadastrar conto!");
                    e.printStackTrace();
                }
            } else {
                criarAlerta(Alert.AlertType.INFORMATION, "Cadastro de conto cancelado.", "Cancelado").show();
            }
        });
    }

    private Conto criarConto() {
        Conto conto = new Conto();
        conto.setNome(nome.getText());
        conto.setDescricao(descricao.getText());
        conto.setLocalizacao(localizacao.getText());
        conto.setNomeHeroi(nomeHeroi.getText());
        return conto;
    }
}
