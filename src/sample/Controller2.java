package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller2 {
    public void changeScreen(MouseEvent event) throws IOException {
        Parent game= FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene s=new Scene(game);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }
}
