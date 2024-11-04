package projeto1.appmitologia.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;
import projeto1.appmitologia.dao.ContoDAO;
import projeto1.appmitologia.dao.HeroiDAO;
import projeto1.appmitologia.dao.UserDAO;
import projeto1.appmitologia.model.Conto;
import projeto1.appmitologia.model.Heroi;

import java.sql.SQLException;
import java.util.ArrayList;

public class FavContoIncluirController {

    @FXML
    private Button favoritar;
    @FXML
    private TableView<Conto> tblConto;
    @FXML
    private TableColumn<Conto, Integer> idColuna;
    @FXML
    private TableColumn<Conto, String> descColuna;
    @FXML
    private TableColumn<Conto, String> nomeContoColuna;
    @FXML
    private TableColumn<Conto, String> nomeHeroiColuna;
    @FXML
    private TableColumn<Conto, String> localColuna;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtId;

    public void favoritarOnAction(ActionEvent actionEvent) throws SQLException{
        int idConto;
        try {
            idConto = Integer.parseInt(txtId.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ID do herói deve ser um número.", ButtonType.OK);
            alert.setTitle("Erro no ID");
            alert.setHeaderText("Atenção");
            alert.show();
            return;
        }
        ContoDAO contoDAO = new ContoDAO();
        UserDAO userDAO = new UserDAO();
        try {
            Conto contoExistente =contoDAO.buscaPorId(idConto);
            if (contoExistente == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Conto com o ID " + idConto + " não encontrado.", ButtonType.OK);
                alert.setTitle("Conto não encontrado");
                alert.setHeaderText("Atenção");
                alert.show();
                return;
            }


            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Deseja selecionar o Conto?", ButtonType.CANCEL, ButtonType.OK);
            confirmAlert.setTitle("Confirmação de Atualização");
            confirmAlert.setHeaderText("Confirme a atualização");

            confirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        userDAO.favoritarConto(idConto);
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Conto atualizado com sucesso!", ButtonType.OK);
                        successAlert.setTitle("Sucesso");
                        successAlert.setHeaderText("Informação");
                        successAlert.show();
                    } catch (SQLException e) {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Erro ao atualizar Conto!", ButtonType.OK);
                        errorAlert.setTitle("Erro");
                        errorAlert.setHeaderText("Informação");
                        errorAlert.show();
                        e.printStackTrace();
                    }
                } else {
                    Alert cancelAlert = new Alert(Alert.AlertType.INFORMATION, "Atualização do Conto cancelada.", ButtonType.OK);
                    cancelAlert.setTitle("Cancelado");
                    cancelAlert.setHeaderText("Informação");
                    cancelAlert.show();
                }
            });
        } catch (SQLException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Erro ao buscar Conto!", ButtonType.OK);
            errorAlert.setTitle("Erro");
            errorAlert.setHeaderText("Informação");
            errorAlert.show();
            e.printStackTrace();
        }
    }


















    public void preencherColunas(){
        idColuna.setCellValueFactory(
                new PropertyValueFactory<Conto, Integer>("id")
        );
        nomeContoColuna.setCellValueFactory(
                new PropertyValueFactory<Conto,String>("nome")
        );
        nomeContoColuna.setCellFactory(new Callback<TableColumn<Conto, String>, TableCell<Conto, String>>() {
            public TableCell call(TableColumn param) {
                return wrap();
            }
        });
        nomeHeroiColuna.setCellValueFactory(
                new PropertyValueFactory<Conto, String>("nomeHeroi")
        );
        nomeHeroiColuna.setCellFactory(new Callback<TableColumn<Conto, String>, TableCell<Conto, String>>() {
            public TableCell call(TableColumn param) {
                return wrap();
            }
        });
        localColuna.setCellValueFactory(
                new PropertyValueFactory<Conto, String>("localizacao")
        );
        localColuna.setCellFactory(new Callback<TableColumn<Conto, String>, TableCell<Conto, String>>() {
            public TableCell call(TableColumn param) {
                return wrap();
            }
        });
        descColuna.setCellValueFactory(
                new PropertyValueFactory<Conto, String>("descricao")

        );
        descColuna.setCellFactory(new Callback<TableColumn<Conto, String>, TableCell<Conto, String>>() {
            public TableCell call(TableColumn param) {
                return wrap();
            }
        });
    }
    public TableCell<Conto, String> wrap(){
        return new TableCell<Conto, String>() {
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
    public void btnConsultarOnAction(ActionEvent actionEvent) throws SQLException {
        ArrayList<Conto> contos = new ArrayList<>();
        ContoDAO contoDAO = new ContoDAO();
        ObservableList<Conto> objeto = FXCollections.observableArrayList();
        preencherColunas();
        if(!txtNome.getText().isEmpty()){
            contos = contoDAO.listaTodosNomeConto(txtNome.getText());
            objeto.addAll(contos);
            tblConto.setItems(objeto);
        }
    }



}
