package projeto1.appmitologia.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import projeto1.appmitologia.dao.ContoDAO;
import projeto1.appmitologia.dao.HeroiDAO;
import projeto1.appmitologia.model.Conto;
import projeto1.appmitologia.model.Heroi;
import java.sql.SQLException;

public class AtualizaContoController {

    @FXML
    private TextField nomeConto, idConto, localizacao, nomeHeroi;

    @FXML
    private TextArea descricao;

    @FXML
    private Button bAtualiza;

    @FXML
    private void bAtualizaOnAction(ActionEvent event) {
        if (idConto.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, insira o ID do conto para atualizar.", ButtonType.OK);
            alert.setTitle("ID do Conto Necessário");
            alert.setHeaderText("Atenção");
            alert.show();
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idConto.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ID do Conto deve ser um número.", ButtonType.OK);
            alert.setTitle("Erro no ID");
            alert.setHeaderText("Atenção");
            alert.show();
            return;
        }

        ContoDAO contoDAO = new ContoDAO();

        try {
            Conto contoExistente = contoDAO.buscaPorId(id);
            if (contoExistente == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Conto com o ID " + id + " não encontrado.", ButtonType.OK);
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
            if (!nomeConto.getText().isEmpty()) {
                contoExistente.setNome(nomeConto.getText());
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