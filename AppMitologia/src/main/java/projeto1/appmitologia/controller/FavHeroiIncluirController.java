package projeto1.appmitologia.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Callback;
import projeto1.appmitologia.dao.HeroiDAO;
import projeto1.appmitologia.dao.UserDAO;
import projeto1.appmitologia.model.Heroi;

import java.sql.SQLException;
import java.util.ArrayList;

public class FavHeroiIncluirController {

    @FXML
    private Button btnConsultar;
    @FXML
    private TableView<Heroi> tblHeroi;
    @FXML
    private TableColumn<Heroi, String> nomeColuna;
    @FXML
    private TableColumn<Heroi, Integer> idColuna;
    @FXML
    private TableColumn<Heroi, String> descColuna;
    @FXML
    private TableColumn<Heroi, ImageView> imgColuna;
    @FXML
    private TextField txtNome, idHeroiField;
    @FXML
    private Button btnFAVORITAR;

    public void btnfavoritarOnAction(ActionEvent actionEvent) throws SQLException {
        if (idHeroiField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, insira o ID do herói para favoritar.", ButtonType.OK);
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
        UserDAO userDAO = new UserDAO();
        try {
            Heroi heroiExistente = heroiDAO.buscaPorId(idHeroi);
            if (heroiExistente == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Herói com o ID " + idHeroi + " não encontrado.", ButtonType.OK);
                alert.setTitle("Herói não encontrado");
                alert.setHeaderText("Atenção");
                alert.show();
                return;
            }


            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Deseja selecionar o Herói?", ButtonType.CANCEL, ButtonType.OK);
            confirmAlert.setTitle("Confirmação de Atualização");
            confirmAlert.setHeaderText("Confirme a atualização");

            confirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        userDAO.favoritarHeroi(idHeroi);
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










    public ObservableList<Heroi> getHerois() throws SQLException {
        HeroiDAO heroiDAO = new HeroiDAO();
        ObservableList<Heroi> heroi = FXCollections.observableArrayList(heroiDAO.listaTodos());
        return heroi;
    }
    public void btnConsultarOnAction(ActionEvent actionEvent) throws SQLException {
        HeroiDAO heroiDAO = new HeroiDAO();
        ObservableList<Heroi> objeto = FXCollections.observableArrayList();
        preencheColunas();
        if(!txtNome.getText().isEmpty()){
            ArrayList<Heroi> herois = new ArrayList<>();
            herois = heroiDAO.listaTodosNomes(txtNome.getText());
            objeto.addAll(herois);
            tblHeroi.setItems(objeto);
        }
    }
    public TableCell<Heroi, String> wrap(){
        return new TableCell<Heroi, String>() {
            Text text = new Text();
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                text.setWrappingWidth(getTableColumn().getWidth() - 10);
                text.setText(item);
                setGraphic(text);
            }
        };
    }
    private void preencheColunas() {
        nomeColuna.setCellValueFactory(
                new PropertyValueFactory<Heroi,String>("nome")
        );
        nomeColuna.setCellFactory(new Callback<TableColumn<Heroi, String>, TableCell<Heroi, String>>() {
            public TableCell call(TableColumn param) {
                return wrap();
            }
        });
        idColuna.setCellValueFactory(
                new PropertyValueFactory<Heroi, Integer>("id")
        );
        descColuna.setCellValueFactory(
                new PropertyValueFactory<Heroi, String>("descricao")
        );
        descColuna.setCellFactory(new Callback<TableColumn<Heroi, String>, TableCell<Heroi, String>>() {
            public TableCell call(TableColumn param) {
                return wrap();
            }
        });
        imgColuna.setCellValueFactory(
                new PropertyValueFactory<Heroi, ImageView>("imagem")
        );
    }


}