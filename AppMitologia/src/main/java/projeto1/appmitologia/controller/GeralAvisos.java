package projeto1.appmitologia.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GeralAvisos {
    @FXML
    protected TextField nomeConto, idConto, localizacao, nomeHeroi, idHeroiField, nome, img;
    @FXML
    protected TextArea descricao, desc;
    @FXML
    protected Button bAtualizaConto, bAtualizaHeroi;

    /*  3º Refatoração
        Autor: Giovana
        Reune a lógica de alertas em uma única classe. Utilizado em Atualiza* e Inclui*
        Objetivo: Utilizando essa classe, não é necessário repetir essas linhas em todas as classes que a utilizam*/
    public void mostrarErroAlert(String mensagem) {
        criarAlerta(Alert.AlertType.ERROR, mensagem, "Erro").show();
    }

    /*  3º Refatoração
        Autor: Giovana
        Reune a lógica de alertas em uma única classe. Utilizado em Atualiza* e Inclui*
        Objetivo: Utilizando essa classe, não é necessário repetir essas linhas em todas as classes que a utilizam*/
    public Alert criarAlerta(Alert.AlertType tipo, String mensagem, String titulo) {
        Alert alert = new Alert(tipo, mensagem, ButtonType.OK);
        alert.setTitle(titulo);
        alert.setHeaderText("Informação");
        return alert;
    }

    /*  4º Refatoração
        Autor: Giovana
        Previne erros no preenchimento do ID 
        Objetivo: Reunir toda a validação em uma única função em uma classe geral*/
    public int validarId(String idText, String tipo) {
        if (idText.isEmpty()) {
            //criarAlerta(Alert.AlertType.WARNING, "Por favor, insira o ID do " + tipo + " para atualizar.", tipo + " Necessário").show();
            return -1;
        }
        try {
            return Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            //criarAlerta(Alert.AlertType.ERROR, "ID do " + tipo + " deve ser um número.", "Erro no ID").show();
            return -1;
        }
    }
}
