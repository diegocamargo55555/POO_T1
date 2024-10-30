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
import projeto1.appmitologia.model.Conto;
import projeto1.appmitologia.model.Heroi;

import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class ConsultaHeroiController {
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
    private TextField txtNome;
    @FXML
    private TextField txtId;
    @FXML
    void initialize() throws SQLException {
        preencheColunas();
        tblHeroi.setItems(getHerois());
    }

    public ObservableList<Heroi> getHerois() throws SQLException {
        HeroiDAO heroiDAO = new HeroiDAO();
        ObservableList<Heroi> heroi = FXCollections.observableArrayList(heroiDAO.listaTodos());
        return heroi;
    }
    public void btnConsultarOnAction(ActionEvent actionEvent) throws SQLException {
        Heroi heroi1 = new Heroi();
        Heroi heroi2 = new Heroi();
        HeroiDAO heroiDAO = new HeroiDAO();
        ObservableList<Heroi> objeto = FXCollections.observableArrayList();
        preencheColunas();
        if(!txtNome.getText().isEmpty() || !txtId.getText().isEmpty()){
            if(!txtNome.getText().isEmpty()){
                heroi1.setNome(txtNome.getText());
                heroi1 = heroiDAO.buscaPorNome(heroi1.getNome());
                objeto.add(heroi1);
                if(!txtId.getText().isEmpty()){
                    try {
                        heroi2.setId(Integer.parseInt(txtId.getText()));
                        heroi2 = heroiDAO.buscaPorId(heroi2.getId());
                        if (heroi2.getId() != heroi1.getId()) {
                            objeto.add(heroi2);
                        }
                    }catch(NumberFormatException e) {
                        Alert alert;
                        alert = new Alert(Alert.AlertType.INFORMATION, "Não é possivel realizar a pesquisa, pois o id inerido não foi um número!", ButtonType.OK);
                        alert.setTitle("Insira um número para o Id!");
                        alert.setHeaderText("Informação");
                        alert.show();
                    }
                }
            }else {
                if (!txtId.getText().isEmpty()) {
                    try {
                        heroi2.setId(Integer.parseInt(txtId.getText()));
                        heroi2 = heroiDAO.buscaPorId(heroi2.getId());
                        objeto.add(heroi2);
                    } catch (NumberFormatException e) {
                        Alert alert;
                        alert = new Alert(Alert.AlertType.INFORMATION, "Não é possivel realizar a pesquisa, pois o id inserido não foi um número!", ButtonType.OK);
                        alert.setTitle("Insira um número para o Id!");
                        alert.setHeaderText("Informação");
                        alert.show();
                        tblHeroi.setItems(getHerois());
                    }
                }
            }
            tblHeroi.setItems(objeto);
        } else {
                tblHeroi.setItems(getHerois());
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
                new PropertyValueFactory<Heroi, ImageView>("imagemFisica")
        );
    }
    private void txtNomeOnMouseDragged(MouseEvent mouseEvent) {
        txtId.clear();
    }
}
