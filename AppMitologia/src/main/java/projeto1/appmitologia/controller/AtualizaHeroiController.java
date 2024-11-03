package projeto1.appmitologia.controller;

import javafx.scene.control.*;
import projeto1.appmitologia.model.Heroi;
import projeto1.appmitologia.dao.HeroiDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.sql.SQLException;

public class AtualizaHeroiController {

    @FXML
    private Button bAtualiza;

    @FXML
    private TextField idHeroiField, nome, img;

    @FXML
    private TextArea desc;

    @FXML
    private void bAtualizaOnAction(ActionEvent event) {
        if (idHeroiField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, insira o ID do herói para atualizar.", ButtonType.OK);
            alert.setTitle("ID do Herói Necessário");
            alert.setHeaderText("Atenção");
            alert.show();
            return;
        }

        int idHeroi;
        try {
            idHeroi = Integer.parseInt(idHeroiField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ID do herói deve ser um número.", ButtonType.OK);
            alert.setTitle("Erro no ID");
            alert.setHeaderText("Atenção");
            alert.show();
            return;
        }

        HeroiDAO heroiDAO = new HeroiDAO();
        try {
            Heroi heroiExistente = heroiDAO.buscaPorId(idHeroi);
            if (heroiExistente == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Herói com o ID " + idHeroi + " não encontrado.", ButtonType.OK);
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
                heroiExistente.setUrl(img.getText());
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