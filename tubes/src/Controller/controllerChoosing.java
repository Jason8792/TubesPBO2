package Controller;

import DAO.OwnedDAO;
import Model.Owned;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class controllerChoosing {
    public ComboBox<Owned> monster1;
    public ComboBox<Owned> monster2;
    public ComboBox<Owned> monster3;
    public Button btn;
    public Button kembali;

    User user;
    OwnedDAO ownedDAO = new OwnedDAO();
    ObservableList<Owned> oList= FXCollections.observableArrayList();
    ObservableList<Owned> picked = FXCollections.observableArrayList();

    public void initialize(User user) {
        oList = ownedDAO.showData(user);
        this.user = user;
        monster1.setItems(oList);
        monster2.setItems(oList);
        monster3.setItems(oList);
    }

    public void choose(ActionEvent actionEvent) throws IOException {
        picked.add(monster1.getValue());
        picked.add(monster2.getValue());
        picked.add(monster3.getValue());
        callStage(picked);
    }

    public void back(ActionEvent actionEvent) throws IOException {
        callStage(picked);
    }

    public void Choose1(MouseEvent mouseEvent) {
        monster1.setItems(Filter(monster2, monster3));
    }

    public void Choose2(MouseEvent mouseEvent){
        monster2.setItems(Filter(monster1, monster3));
    }

    public void Choose3(MouseEvent mouseEvent){
        monster3.setItems(Filter(monster1, monster2));
    }

    public ObservableList<Owned> Filter(ComboBox<Owned> monster1, ComboBox<Owned> monster2) {
        oList = ownedDAO.showData(user);
        oList.remove(monster1.getValue());
        oList.remove(monster2.getValue());
        return oList;
    }
    public void callStage(ObservableList<Owned> picked) throws IOException {
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/beranda.fxml"));
        Parent root = loader.load();
        controllerBeranda controllerBeranda = loader.getController();
        controllerBeranda.initialize(user);
        controllerBeranda.oList.addAll(picked);
        stage.setTitle("Beranda");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
