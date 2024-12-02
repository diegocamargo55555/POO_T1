package projeto1.appmitologia.controller;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import projeto1.appmitologia.dao.HeroiDAO;
import projeto1.appmitologia.model.Heroi;

public class IncluiHeroiController extends GeralAvisos {

    @FXML
    private void bCadastroOnAction(ActionEvent event) {
        Heroi heroi = criarHeroi();

        Alert confirmAlert = criarAlerta(Alert.AlertType.CONFIRMATION, "Deseja inserir o Herói?", "Cadastro de Herói");
        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                HeroiDAO heroiDAO = new HeroiDAO();
                try {
                    heroiDAO.insere(heroi);
                    criarAlerta(Alert.AlertType.INFORMATION, "Herói cadastrado com sucesso!", "Sucesso").show();
                } catch (SQLException e) {
                    mostrarErroAlert("Erro ao cadastrar herói!");
                    e.printStackTrace();
                }
            } else {
                criarAlerta(Alert.AlertType.INFORMATION, "Cadastro de herói cancelado.", "Cancelado").show();
            }
        });
    }

    /*  4º Refatoração
        Autor: Giovana
        Separa os preenchimentos de campo do restante do código
        Objetivo: Deixa o codigo mais claro e mais compacto*/
    private Heroi criarHeroi() {
        Heroi heroi = new Heroi();
        heroi.setNome(nome.getText());
        heroi.setDescricao(desc.getText());
        heroi.setUrl(img.getText());
        return heroi;
    }
}
