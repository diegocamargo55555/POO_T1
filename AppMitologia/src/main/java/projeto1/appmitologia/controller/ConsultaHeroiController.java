package projeto1.appmitologia.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;
import projeto1.appmitologia.dao.HeroiDAO;
import projeto1.appmitologia.model.Heroi;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        HeroiDAO heroiDAO = new HeroiDAO();
        ObservableList<Heroi> objeto = FXCollections.observableArrayList();
        preencheColunas();
        if(!txtNome.getText().isEmpty()){
            ArrayList<Heroi> herois = new ArrayList<>();
            herois = heroiDAO.listaTodosNomes(txtNome.getText());
            objeto.addAll(herois);
            tblHeroi.setItems(objeto);
        } else {
            if (!txtId.getText().isEmpty()) {
                try {
                    Heroi heroi1 = new Heroi();
                    heroi1 = heroiDAO.buscaPorId(Integer.parseInt(txtId.getText()));
                    objeto.add(heroi1);
                    tblHeroi.setItems(objeto);
                } catch (NumberFormatException e) {
                    Alert alert;
                    alert = new Alert(Alert.AlertType.INFORMATION, "Não é possivel realizar a pesquisa, pois o id inserido não é um número!", ButtonType.OK);
                    alert.setTitle("Insira um número para o Id!");
                    alert.setHeaderText("Informação");
                    alert.show();
                    tblHeroi.setItems(getHerois());
                }
            } else {
                tblHeroi.setItems(getHerois());
            }
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

   public void txtNomeOnClick(MouseEvent mouseEvent) {
        txtId.clear();
   }
   public void txtIdOnCLick(MouseEvent mouseEvent) {
        txtNome.clear();
   }

}
