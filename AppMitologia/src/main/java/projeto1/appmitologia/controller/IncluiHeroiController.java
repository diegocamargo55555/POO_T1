package projeto1.appmitologia.controller;

import javafx.scene.control.*;
import projeto1.appmitologia.model.Heroi;
import projeto1.appmitologia.dao.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.sql.SQLException;

public class IncluiHeroiController {

    @FXML
    private Button bCadastro;

    @FXML
    private TextField nome, img;

    @FXML
    private TextArea desc;


    @FXML
    private void bCadastroOnAction(ActionEvent event) {
        Heroi heroi = new Heroi();
        heroi.setNome(nome.getText());
        heroi.setDescricao(desc.getText());
        heroi.setImagem(img.getText());

        HeroiDAO heroiDAO = new HeroiDAO();
        try {
            heroiDAO.insere(heroi);
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION, "Deseja inserir o Herói?", ButtonType.OK);
            alert.setTitle("Herói cadastrado com sucesso!");
            alert.setHeaderText("Informação");
            alert.show();
        } catch (SQLException e1) {
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION, "Você clicou no botão Cancelar", ButtonType.OK);
            alert.setTitle("Fracasso ao cadastrar herói!");
            alert.setHeaderText("Informação");
            alert.show();
            e1.printStackTrace();
        }
    }

}