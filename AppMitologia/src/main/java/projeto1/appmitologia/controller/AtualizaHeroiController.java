package projeto1.appmitologia.controller;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import projeto1.appmitologia.dao.HeroiDAO;
import projeto1.appmitologia.model.Heroi;

public class AtualizaHeroiController extends GeralAvisos {

    @FXML
    private void bAtualizaHeroiOnAction(ActionEvent event) {
        int idHeroi = validarId(idHeroiField.getText(), "Herói");
        if (idHeroi == -1) return;

        HeroiDAO heroiDAO = new HeroiDAO();
        try {
            Heroi heroiExistente = heroiDAO.buscaPorId(idHeroi);
            if (heroiExistente == null) {
                criarAlerta(Alert.AlertType.WARNING, "Herói com ID " + idHeroi + " não encontrado.", "Herói não encontrado").show();
                return;
            }

            atualizarDadosHeroi(heroiExistente);

            Alert confirmAlert = criarAlerta(Alert.AlertType.CONFIRMATION, "Deseja atualizar o Herói?", "Confirmação de Atualização");
            confirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        heroiDAO.atualiza(heroiExistente);
                        criarAlerta(Alert.AlertType.INFORMATION, "Herói atualizado com sucesso!", "Sucesso").show();
                    } catch (SQLException e) {
                        mostrarErroAlert("Erro ao atualizar herói!");
                        e.printStackTrace();
                    }
                } else {
                    criarAlerta(Alert.AlertType.INFORMATION, "Atualização do herói cancelada.", "Cancelado").show();
                }
            });
        } catch (SQLException e) {
            mostrarErroAlert("Erro ao buscar herói!");
            e.printStackTrace();
        }
    }
    
    /*  2º Refatoração
        Autor: Giovana
        Separa as validadoções de campo do restante do código
        Objetivo: Deixa o codigo mais claro e mais compacto*/ 
    private void atualizarDadosHeroi(Heroi heroi) {
        if (!nome.getText().isEmpty()) heroi.setNome(nome.getText());
        if (!desc.getText().isEmpty()) heroi.setDescricao(desc.getText());
        if (!img.getText().isEmpty()) heroi.setUrl(img.getText());
    }
}
