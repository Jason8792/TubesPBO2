package Controller;

import Model.Owned;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class controllerBeranda {
    public Button btn;

    User user;
    ObservableList<Owned> oList = FXCollections.observableArrayList();

    public void initialize(User user) {
        this.user = user;
    }

    public void battle(ActionEvent actionEvent) throws IOException {
        if(!oList.isEmpty()) {
            Stage stage = (Stage) btn.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/battle.fxml"));
            Parent root = loader.load();
            controllerGame game = loader.getController();
            game.initialize(user, oList);
            stage.setTitle("Game");
            stage.setScene(new Scene(root,600,400));
            stage.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Monster tidak ada");
            alert.setContentText("Pilih monstermu di monster list !");
            alert.showAndWait();
        }
    }

    public void monster_list(ActionEvent actionEvent) throws IOException{
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/choose_monster.fxml"));
        Parent root = loader.load();
        controllerChoosing choosing = loader.getController();
        choosing.initialize(user);
        oList.addAll(choosing.picked);
        stage.setTitle("Pilih Monster");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void add_monster(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/add_monster.fxml"));
        Parent root = loader.load();
        controllerAdd add = loader.getController();
        add.initialize(user);
        stage.setTitle("Add monster");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
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

    public void history(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/history.fxml"));
        Parent root = loader.load();
        controllerHistory history = loader.getController();
        history.initialize(user);
        stage.setTitle("Leaderboard");
        stage.setScene(new Scene(root,600,400));
        stage.show();

    }
}
