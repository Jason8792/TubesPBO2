package Controller;

import DAO.MonsterDAO;
import DAO.OwnedDAO;
import DAO.SkillDAO;
import Model.Owned;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class controllerAdd {

    public Button addmonster;
    public Button cancel;
    User user;

    public void initialize(User user) {
        this.user = user;
    }

    public void addmonster(ActionEvent actionEvent) {
        Random rand = new Random();
        OwnedDAO owned = new OwnedDAO();
        Owned data = new Owned();



        MonsterDAO monster = new MonsterDAO();
        SkillDAO skill = new SkillDAO();
        int countm = monster.count().intValue();
        int sk1 = skill.countSkill().intValue();
        int sk2 = skill.countSkill().intValue();

        int gatcha = rand.nextInt(countm) + 1;
        int skill1 = rand.nextInt(sk1)+1;
        int skill2 = rand.nextInt(sk2)+1;
        while (skill2==skill1){
            skill2 = rand.nextInt(sk2)+1;
        }

        data.setUsername(user);
        data.setMonsterskill1(skill.addskill(skill1));
        data.setMonsterskill2(skill.addskill(skill2));
        data.setMonsterByMonsterId1(monster.Gatcha(gatcha));
        owned.addData(data);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Pembelian monstermu berhasil !");
        alert.setContentText("Tutup layar ini");
        alert.showAndWait();

    }

    public void cancelmonster(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) cancel.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/beranda.fxml"));
        Parent root = loader.load();
        controllerBeranda controllerBeranda = loader.getController();
        controllerBeranda.initialize(user);
        stage.setTitle("Beranda");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
