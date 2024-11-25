package projeto1.appmitologia.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class GeralAvisos {
    @FXML
    protected TextField nomeConto, idConto, localizacao, nomeHeroi, idHeroiField, nome, img;
    @FXML
    protected TextArea descricao, desc;
    @FXML
    protected Button bAtualizaConto, bAtualizaHeroi;

    public void mostrarErroAlert(String mensagem) {
        criarAlerta(Alert.AlertType.ERROR, mensagem, "Erro").show();
    }

    public Alert criarAlerta(Alert.AlertType tipo, String mensagem, String titulo) {
        Alert alert = new Alert(tipo, mensagem, ButtonType.OK);
        alert.setTitle(titulo);
        alert.setHeaderText("Informação");
        return alert;
    }

    public int validarId(String idText, String tipo) {
        if (idText.isEmpty()) {
            criarAlerta(Alert.AlertType.WARNING, "Por favor, insira o ID do " + tipo + " para atualizar.", tipo + " Necessário").show();
            return -1;
        }
        try {
            return Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            criarAlerta(Alert.AlertType.ERROR, "ID do " + tipo + " deve ser um número.", "Erro no ID").show();
            return -1;
        }
    }
}
