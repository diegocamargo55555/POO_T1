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


public class DeletaContoController extends Tabelas{
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
     //  4º Refatoração
    //  Autor: Ana Beatrz
    //  alteração de nome da variavel
    //  Objetivo: melhor entedimento do código/
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
        if(!txtId.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deseja deletar o Herói?", ButtonType.CANCEL, ButtonType.OK);
            alert.setHeaderText("Informação");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                            Conto conto = contoDAO.buscaPorId(Integer.parseInt(txtId.getText()));
                            if(conto != null){
                                contoDAO.remove(Integer.parseInt(txtId.getText()));
                                Alert alertConfirmacao = new Alert(Alert.AlertType.CONFIRMATION, "Conto deletado com sucesso!", ButtonType.OK);
                                alert.show();
                            } else{
                                Alert alertContoNExistente = new Alert(Alert.AlertType.WARNING, "Conto não existe!", ButtonType.OK);
                                alertContoNExistente.show();
                            }
                    }catch (SQLException e1){
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Erro ao deletar conto!", ButtonType.OK);
                            errorAlert.setTitle("Erro");
                            errorAlert.setHeaderText("Informação");
                            errorAlert.show();
                            e1.printStackTrace();
                    } catch (NumberFormatException e) {
                        Alert alertId = new Alert(Alert.AlertType.INFORMATION, "Não é possivel realizar a remoção, pois o id inserido não é um número!", ButtonType.OK);
                        alertId.setTitle("Insira um número para o Id!");
                        alertId.setHeaderText("Informação");
                        alertId.show();
                    }
                }
            });
        } else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Insira um id para deletar o conto!", ButtonType.OK);
            alert.setTitle("Insira um Id!");
            alert.setHeaderText("Informação");
            alert.show();
        }
    }

}
