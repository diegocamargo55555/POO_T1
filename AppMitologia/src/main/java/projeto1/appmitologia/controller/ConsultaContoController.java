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
import projeto1.appmitologia.model.Conto;

import java.sql.SQLException;
import java.util.ArrayList;


public class ConsultaContoController extends Tabelas{
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
    private TextField txtNomeConto;
    @FXML
    private TextField txtNomeHeroi;
    @FXML
    private TextField txtId;
    @FXML
    void initialize() throws SQLException {
        preencherColunas();
        tblConto.setItems(getContos());
    }
    public ObservableList<Conto> getContos() throws SQLException {
        ContoDAO contoDAO = new ContoDAO();
        ObservableList<Conto> conto = FXCollections.observableArrayList(contoDAO.listaTodos());
        return conto;
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
                return wrapConto();
            }
        });
        nomeHeroiColuna.setCellValueFactory(
                new PropertyValueFactory<Conto, String>("nomeHeroi")
        );
        nomeHeroiColuna.setCellFactory(new Callback<TableColumn<Conto, String>, TableCell<Conto, String>>() {
            public TableCell call(TableColumn param) {
                return wrapConto();
            }
        });
        localColuna.setCellValueFactory(
                new PropertyValueFactory<Conto, String>("localizacao")
        );
        localColuna.setCellFactory(new Callback<TableColumn<Conto, String>, TableCell<Conto, String>>() {
            public TableCell call(TableColumn param) {
                return wrapConto();
            }
        });
        descColuna.setCellValueFactory(
                new PropertyValueFactory<Conto, String>("descricao")

        );
        descColuna.setCellFactory(new Callback<TableColumn<Conto, String>, TableCell<Conto, String>>() {
            public TableCell call(TableColumn param) {
                return wrapConto();
            }
        });
    }

    public void btnConsultarOnAction(ActionEvent actionEvent) throws SQLException {
        Conto conto1 = new Conto();
        ArrayList<Conto> contos = new ArrayList<>();
        ContoDAO contoDAO = new ContoDAO();
        ObservableList<Conto> objeto = FXCollections.observableArrayList();
        preencherColunas();
        if(!txtNomeHeroi.getText().isEmpty()){
            contos = contoDAO.listaTodosNomesHeroi(txtNomeHeroi.getText());
            objeto.addAll(contos);
            tblConto.setItems(objeto);
        } else{
            if(!txtNomeConto.getText().isEmpty()){
                contos = contoDAO.listaTodosNomeConto(txtNomeConto.getText());
                objeto.addAll(contos);
                tblConto.setItems(objeto);
            } else{
                if(!txtId.getText().isEmpty()){
                    try {
                        conto1 = contoDAO.buscaPorId(Integer.parseInt(txtId.getText()));
                        objeto.add(conto1);
                        tblConto.setItems(objeto);
                    }catch(NumberFormatException e){
                        Alert alert;
                        alert = new Alert(Alert.AlertType.INFORMATION, "Não é possivel realizar a pesquisa, pois o id inserido não é um número!", ButtonType.OK);
                        alert.setTitle("Insira um número para o Id!");
                        alert.setHeaderText("Informação");
                        alert.show();
                        tblConto.setItems(getContos());
                    }
                } else{
                    tblConto.setItems(getContos());
                }
            }
        }
    }

    public void txtNomeHeroiOnClick(MouseEvent mouseEvent) {
       txtNomeConto.clear();
       txtId.clear();
    }
    public void txtNomeContoOnClick(MouseEvent mouseEvent) {
       txtNomeHeroi.clear();
       txtId.clear();
    }
    public void txtIdOnClick(MouseEvent mouseEvent) {
        txtNomeHeroi.clear();
        txtNomeConto.clear();
    }
}
