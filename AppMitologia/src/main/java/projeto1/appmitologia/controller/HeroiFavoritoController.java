package projeto1.appmitologia.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Callback;
import projeto1.appmitologia.dao.HeroiDAO;
import projeto1.appmitologia.dao.UserDAO;
import projeto1.appmitologia.model.Heroi;
import java.sql.SQLException;
import javafx.scene.*;


public class HeroiFavoritoController {
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
    void initialize() throws SQLException {
        preencheColunas();
        tblHeroi.setItems(getHerois());
    }

    public ObservableList<Heroi> getHerois() throws SQLException {
        UserDAO user = new UserDAO();
        int heroID = user.getHeroiFav();
        HeroiDAO heroiDAO = new HeroiDAO();
        ObservableList<Heroi> heroi = FXCollections.observableArrayList(heroiDAO.buscaPorId(heroID));
        return heroi;
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
