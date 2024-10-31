package projeto1.appmitologia.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import projeto1.appmitologia.dao.ContoDAO;
import projeto1.appmitologia.model.Conto;
import java.sql.SQLException;

public class AtualizaContoController {

    @FXML
    private TextField nomeConto, nomeHeroi, localizacao;

    @FXML
    private TextArea descricao;

    @FXML
    private Button bAtualiza;

    @FXML
    private void bAtualizaOnAction(ActionEvent event) {
        if (nomeConto.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, insira o nome do conto para atualizar.", ButtonType.OK);
            alert.setTitle("Nome do Conto Necessário");
            alert.setHeaderText("Atenção");
            alert.show();
            return;
        }

        String nomeContoText = nomeConto.getText();
        ContoDAO contoDAO = new ContoDAO();

        try {
            Conto contoExistente = contoDAO.buscaPorNome(nomeContoText);
            if (contoExistente == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Conto com o nome " + nomeContoText + " não encontrado.", ButtonType.OK);
                alert.setTitle("Conto não encontrado");
                alert.setHeaderText("Atenção");
                alert.show();
                return;
            }

            if (!descricao.getText().isEmpty()) {
                contoExistente.setDescricao(descricao.getText());
            }
            if (!localizacao.getText().isEmpty()) {
                contoExistente.setLocalizacao(localizacao.getText());
            }
            if (!nomeHeroi.getText().isEmpty()) {
                contoExistente.setNomeHeroi(nomeHeroi.getText());
            }

            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Deseja atualizar o Conto?", ButtonType.CANCEL, ButtonType.OK);
            confirmAlert.setTitle("Confirmação de Atualização");
            confirmAlert.setHeaderText("Confirme a atualização");

            confirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        contoDAO.atualiza(contoExistente);
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Conto atualizado com sucesso!", ButtonType.OK);
                        successAlert.setTitle("Sucesso");
                        successAlert.setHeaderText("Informação");
                        successAlert.show();
                    } catch (SQLException e) {
                        mostrarErroAlert("Erro ao atualizar conto!");
                        e.printStackTrace();
                    }
                } else {
                    Alert cancelAlert = new Alert(Alert.AlertType.INFORMATION, "Atualização do conto cancelada.", ButtonType.OK);
                    cancelAlert.setTitle("Cancelado");
                    cancelAlert.setHeaderText("Informação");
                    cancelAlert.show();
                }
            });
        } catch (SQLException e) {
            mostrarErroAlert("Erro ao buscar conto!");
            e.printStackTrace();
        }
    }

    private void mostrarErroAlert(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        errorAlert.setTitle("Erro");
        errorAlert.setHeaderText("Informação");
        errorAlert.show();
    }
}