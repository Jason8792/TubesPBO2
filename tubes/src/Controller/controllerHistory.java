package Controller;

import Model.Leaderboard;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class controllerHistory {

    public TableView Tablehistory;
    public TableColumn tanggal;
    public TableColumn namaplayer;
    public TableColumn status;
    public Button kembali;
    ObservableList<Leaderboard> leaderboards = FXCollections.observableArrayList();
    User user;

    public void initialize(User user){
        this.user = user;
        load();
        namaplayer.setCellValueFactory(new PropertyValueFactory<>("name"));
        tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    public void load(){
        String path = "histori/save.txt";
        List<String> textList;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String text;
            while ((text = reader.readLine()) != null) {
                textList = Arrays.asList(text.split(";"));
                if (textList.get(0).equals(user.getUsername())) {
                    leaderboards.add(new Leaderboard(textList.get(0), textList.get(1), textList.get(2)));
                }
            }

            Tablehistory.setItems(leaderboards);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }

    public void kembali(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) kembali.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/beranda.fxml"));
        Parent root = loader.load();
        stage.setTitle("Beranda");
        stage.setScene(new Scene(root, 600, 400));
        controllerBeranda controllerBeranda = loader.getController();
        controllerBeranda.initialize(user);
        stage.show();
    }
}
