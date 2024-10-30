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
import projeto1.appmitologia.model.Conto;
import projeto1.appmitologia.model.Heroi;

import java.sql.SQLException;

public class ConsultaContoController {
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
        Conto conto1 = new Conto();
        Conto conto2 = new Conto();
        Conto conto3 = new Conto();
        ContoDAO contoDAO = new ContoDAO();
        ObservableList<Conto> objeto = FXCollections.observableArrayList();
        preencherColunas();
        if(!txtNomeHeroi.getText().isEmpty() || !txtNomeConto.getText().isEmpty() || !txtId.getText().isEmpty()){
            if(!txtNomeHeroi.getText().isEmpty() && !txtNomeConto.getText().isEmpty() && !txtId.getText().isEmpty()) {
                conto1.setNomeHeroi(txtNomeHeroi.getText());
                conto2.setNome(txtNomeConto.getText());
                conto3.setId(Integer.parseInt(txtId.getText()));
                objeto.add(conto1);
                if(conto1.getId() != conto2.getId()){
                    objeto.add(conto2);
                    if(conto2.getId() != conto3.getId()){
                        objeto.add(conto3);
                    }else{
                        if(conto1.getId() != conto3.getId()){
                            objeto.add(conto3);
                        }
                    }
                }
            }
            tblConto.setItems(objeto);
        } else{
            objeto.addAll(contoDAO.listaTodos());

        }
        if(!txtNomeConto.getText().isEmpty()){

        } else{
            /*if(!txtId.getText().isEmpty()){
                conto.setId(Integer.parseInt(txtId.getText()));
                conto = contoDAO.buscaPorId(conto.getId());
                objeto.add(conto);
                tblConto.setItems(objeto);
            } else{
                if(!txtNomeHeroi.getText().isEmpty()){
                    conto.setNomeHeroi(txtNomeHeroi.getText());
                    conto = contoDAO.buscaPorNomeHeroi(conto.getNomeHeroi());
                    objeto.add(conto);
                    tblConto.setItems(objeto);
                } else{
                }
            }*/

        }
    }
}
