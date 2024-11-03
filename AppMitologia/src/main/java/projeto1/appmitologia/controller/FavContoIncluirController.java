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
import projeto1.appmitologia.model.Conto;

import java.sql.SQLException;
import java.util.ArrayList;

public class FavContoIncluirController {

    @FXML
    private Button btnConsulta;
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

    public void btnDeletarOnAction(ActionEvent actionEvent) throws SQLException{
        Conto conto = new Conto();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deseja inserir o Conto?", ButtonType.CANCEL, ButtonType.OK);
        alert.setTitle("Conto pode ser cadastrado!");
        alert.setHeaderText("Informação");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                ContoDAO contoDAO = new ContoDAO();
                try {
                    contoDAO.insere(conto);
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Conto cadastrado com sucesso!", ButtonType.OK);
                    successAlert.setTitle("Sucesso");
                    successAlert.setHeaderText("Informação");
                    successAlert.show();
                } catch (SQLException e1) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Erro ao cadastrar conto!", ButtonType.OK);
                    errorAlert.setTitle("Erro");
                    errorAlert.setHeaderText("Informação");
                    errorAlert.show();
                    e1.printStackTrace();
                }
            } else {
                Alert cancelAlert = new Alert(Alert.AlertType.INFORMATION, "Cadastro de conto cancelado.", ButtonType.OK);
                cancelAlert.setTitle("Cancelado");
                cancelAlert.setHeaderText("Informação");
                cancelAlert.show();
            }
        });
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
