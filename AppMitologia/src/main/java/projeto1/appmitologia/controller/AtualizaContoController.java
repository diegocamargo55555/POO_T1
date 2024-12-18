package projeto1.appmitologia.controller;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import projeto1.appmitologia.dao.ContoDAO;
import projeto1.appmitologia.model.Conto;

public class AtualizaContoController extends GeralAvisos {
     /* 4º Refatoração
        Autor: Giovana
        Utiliza a valição de id
        Objetivo: Deixa o codigo mais claro e mais compacto, já que não é preciso repetir os métodos*/ 
    @FXML
    private void bAtualizaContoOnAction(ActionEvent event) {
        int id = validarId(idConto.getText(), "Conto");
        if (id == -1) return;

        ContoDAO contoDAO = new ContoDAO();

        try {
            Conto contoExistente = contoDAO.buscaPorId(id);
            if (contoExistente == null) {
                criarAlerta(Alert.AlertType.WARNING, "Conto com ID " + id + " não encontrado.", "Conto não encontrado").show();
                return;
            }

            atualizarDadosConto(contoExistente);

            Alert confirmAlert = criarAlerta(Alert.AlertType.CONFIRMATION, "Deseja atualizar o Conto?", "Confirmação de Atualização");
            confirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        contoDAO.atualiza(contoExistente);
                        criarAlerta(Alert.AlertType.INFORMATION, "Conto atualizado com sucesso!", "Sucesso").show();
                    } catch (SQLException e) {
                        mostrarErroAlert("Erro ao atualizar conto!");
                        e.printStackTrace();
                    }
                } else {
                    criarAlerta(Alert.AlertType.INFORMATION, "Atualização do conto cancelada.", "Cancelado").show();
                }
            });
        } catch (SQLException e) {
            mostrarErroAlert("Erro ao buscar conto!");
            e.printStackTrace();
        }
    }
    
    /*  2º Refatoração
        Autor: Giovana
        Separa as validadoções de campo do restante do código
        Objetivo: Deixa o codigo mais claro e mais compacto*/
    void atualizarDadosConto(Conto conto) {
        if (!descricao.getText().isEmpty()) conto.setDescricao(descricao.getText());
        if (!localizacao.getText().isEmpty()) conto.setLocalizacao(localizacao.getText());
        if (!nomeConto.getText().isEmpty()) conto.setNome(nomeConto.getText());
        if (!nomeHeroi.getText().isEmpty()) conto.setNomeHeroi(nomeHeroi.getText());
    }
}
