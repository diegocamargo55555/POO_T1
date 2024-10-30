package projeto1.appmitologia.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader principal = new FXMLLoader(getClass().getResource("/projeto1/appmitologia/view/menu.fxml"));
        Parent root = principal.load();
        Scene scene = new Scene(root);
        stage.setTitle("Herois");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args)  {
        launch(args);
    }
}
