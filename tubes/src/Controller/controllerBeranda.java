package Controller;

import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class controllerBeranda {
    public Button btn;

    String username;

    public void initialize(User user) {
        username = user.getUsername();
    }

    public void battle(ActionEvent actionEvent) throws IOException {
    }

    public void monster_list(ActionEvent actionEvent) throws IOException{
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/choose_monster.fxml"));
        Parent root = loader.load();
        stage.setTitle("Pilih Monster");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void add_monster(ActionEvent actionEvent) throws IOException {
    }

    public void switch_user(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/login.fxml"));
        Parent root = loader.load();
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void exit(ActionEvent actionEvent) throws IOException {
        System.exit(0);
    }
}
