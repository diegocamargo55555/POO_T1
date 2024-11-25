package projeto1.appmitologia.controller;
import javafx.scene.control.TableCell;
import javafx.scene.text.Text;
import projeto1.appmitologia.model.Conto;
import projeto1.appmitologia.model.Heroi;

public class Tabelas {
    public TableCell<Heroi, String> wrapHeroi(){
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
    public TableCell<Conto, String> wrapConto(){
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


}
