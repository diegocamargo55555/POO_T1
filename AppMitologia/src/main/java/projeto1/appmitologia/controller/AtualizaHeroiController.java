package projeto1.appmitologia.controller;

import javafx.scene.control.*;
import projeto1.appmitologia.model.Heroi;
import projeto1.appmitologia.dao.HeroiDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.sql.SQLException;
import java.util.Optional;

public class AtualizaHeroiController {

    @FXML
    private Button bAtualiza;

    @FXML
    private TextField nHeroi, nome, img;

    @FXML
    private TextArea desc;

    @FXML
    private void bAtualizaOnAction(ActionEvent event) {
        if (nHeroi.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, insira o nome do herói para atualizar.", ButtonType.OK);
            alert.setTitle("Nome do Herói Necessário");
            alert.setHeaderText("Atenção");
            alert.show();
            return;
        }

        String nomeHeroi = nHeroi.getText();
        HeroiDAO heroiDAO = new HeroiDAO();

        try {
            Heroi heroiExistente = heroiDAO.buscaPorNome(nomeHeroi);
            if (heroiExistente == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Herói com o nome " + nomeHeroi + " não encontrado.", ButtonType.OK);
                alert.setTitle("Herói não encontrado");
                alert.setHeaderText("Atenção");
                alert.show();
                return;
            }

            if (!nome.getText().isEmpty()) {
                heroiExistente.setNome(nome.getText());
            }
            if (!desc.getText().isEmpty()) {
                heroiExistente.setDescricao(desc.getText());
            }
            if (!img.getText().isEmpty()) {
                heroiExistente.setImagem(img.getText());
            }

            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Deseja atualizar o Herói?", ButtonType.CANCEL, ButtonType.OK);
            confirmAlert.setTitle("Confirmação de Atualização");
            confirmAlert.setHeaderText("Confirme a atualização");

            confirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        heroiDAO.atualiza(heroiExistente);
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Herói atualizado com sucesso!", ButtonType.OK);
                        successAlert.setTitle("Sucesso");
                        successAlert.setHeaderText("Informação");
                        successAlert.show();
                    } catch (SQLException e) {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Erro ao atualizar herói!", ButtonType.OK);
                        errorAlert.setTitle("Erro");
                        errorAlert.setHeaderText("Informação");
                        errorAlert.show();
                        e.printStackTrace();
                    }
                } else {
                    Alert cancelAlert = new Alert(Alert.AlertType.INFORMATION, "Atualização do herói cancelada.", ButtonType.OK);
                    cancelAlert.setTitle("Cancelado");
                    cancelAlert.setHeaderText("Informação");
                    cancelAlert.show();
                }
            });
        } catch (SQLException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Erro ao buscar herói!", ButtonType.OK);
            errorAlert.setTitle("Erro");
            errorAlert.setHeaderText("Informação");
            errorAlert.show();
            e.printStackTrace();
        }
    }


}