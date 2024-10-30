package projeto1.appmitologia.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;
import projeto1.appmitologia.dao.ContoDAO;
import projeto1.appmitologia.dao.HeroiDAO;
import projeto1.appmitologia.model.Conto;

import java.sql.SQLException;
import java.util.ArrayList;


public class DeletaContoController {
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
    public void btnDeletarOnAction(ActionEvent actionEvent) throws SQLException{
        ContoDAO contoDAO = new ContoDAO();
        Alert alert;
        if(!txtId.getText().isEmpty()){
            try {
                contoDAO.remove(Integer.parseInt(txtId.getText()));
            }catch (NumberFormatException e){
                alert = new Alert(Alert.AlertType.INFORMATION, "Não é possivel realizar a remoção, pois o id inserido não é um número!", ButtonType.OK);
                alert.setTitle("Insira um número para o Id!");
                alert.setHeaderText("Informação");
                alert.show();
            }
        } else{
            alert = new Alert(Alert.AlertType.INFORMATION, "Insira um id para deletar o conto!", ButtonType.OK);
            alert.setTitle("Insira um Id!");
            alert.setHeaderText("Informação");
            alert.show();
        }
    }

}
