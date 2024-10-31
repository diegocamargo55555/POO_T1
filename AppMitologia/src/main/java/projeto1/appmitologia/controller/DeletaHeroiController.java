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

public class DeletaHeroiController {
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
    private Button btnDeletar;

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

    public void btnDeletarOnAction(ActionEvent actionEvent) throws SQLException {
        HeroiDAO heroiDAO = new HeroiDAO();
        if(!txtId.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deseja deletar o Herói?", ButtonType.CANCEL, ButtonType.OK);
            alert.setHeaderText("Informação");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        Heroi heroi = heroiDAO.buscaPorId(Integer.parseInt(txtId.getText()));
                        if(heroi != null){
                            heroiDAO.remove(Integer.parseInt(txtId.getText()));
                            Alert alertConfirmacao = new Alert(Alert.AlertType.INFORMATION, "Heroi deletado com sucesso!", ButtonType.OK);
                            alertConfirmacao.show();
                        } else{
                            Alert alertHeroiNExtiste = new Alert(Alert.AlertType.ERROR, "Erro! Insira um heroi válido", ButtonType.OK);
                            alertHeroiNExtiste.setTitle("Este heroi não existe!");
                            alertHeroiNExtiste.show();
                        }
                    }catch (SQLException e1){
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Erro ao deletar heroi!", ButtonType.OK);
                        errorAlert.setTitle("Erro");
                        errorAlert.show();
                        e1.printStackTrace();
                    } catch (NumberFormatException e) {
                        Alert alertId;
                        alertId = new Alert(Alert.AlertType.INFORMATION, "Não é possivel realizar a remoção, pois o id inserido não é um número!", ButtonType.OK);
                        alertId.setTitle("Insira um número para o Id!");
                        alertId.show();
                    }
                } else{
                    Alert cancelAlert = new Alert(Alert.AlertType.INFORMATION, "Reemoção cancelada.", ButtonType.OK);
                    cancelAlert.setTitle("Cancelado");
                    cancelAlert.setHeaderText("Informação");
                    cancelAlert.show();
                }
            });
        }  else {
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION, "Insira um id para deletar o heroi!", ButtonType.OK);
            alert.setTitle("Insira um Id!");
            alert.setHeaderText("Informação");
            alert.show();
        }
    }
}
