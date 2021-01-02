package Controller;

import DAO.MonsterDAO;
import DAO.SkillDAO;
import Model.Element;
import Model.Owned;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class controllerGame {
    public Label currenthelathplayer;
    public Label hptotalplayer;
    public Label hptotalpc;
    public Label currenthealthpc;
    public Label attackplayer;
    public Label attackpc;
    public Label playerusername;
    public ComboBox<Owned> mymonster;
    public Button skill1;
    public Button skill2;
    public Button leavebattle;
    public Label Computer;
    public TextArea historyArea;
    User user;
    Random rand = new Random();
    ObservableList<Owned> npclist = FXCollections.observableArrayList();
    float damage;
    float curHpPc;

    public ObservableList<Owned> monsterpc() {
        for (int i = 0; i < 3; i++) {
            npclist.add(random());
        }
        return npclist;
    }

    public void initialize(User user, ObservableList<Owned> olist) {
        //buat player
        this.user = user;
        mymonster.setItems(olist);
        mymonster.getSelectionModel().select(0);
        hptotalplayer.setText(mymonster.getValue().getMonsterByMonsterId1().getMaxHp().toString());
        currenthelathplayer.setText(mymonster.getValue().getMonsterByMonsterId1().getMaxHp().toString());
        attackplayer.setText(mymonster.getValue().getMonsterByMonsterId1().getAtk().toString());
        playerusername.setText(this.user.getUsername());
        // buat Monster
        monsterpc();
        setMonsterPc();
    }

    public void setMonsterPc(){
        Computer.setText(npclist.get(0).getMonsterByMonsterId1().getName());
        currenthealthpc.setText(npclist.get(0).getMonsterByMonsterId1().getMaxHp().toString());
        hptotalpc.setText(npclist.get(0).getMonsterByMonsterId1().getMaxHp().toString());
        attackpc.setText(npclist.get(0).getMonsterByMonsterId1().getAtk().toString());
    }

    public void useskill1(ActionEvent actionEvent) {
        actionnpc();
        Element eplayer = mymonster.getValue().getMonsterByMonsterId1().getElementmonster();
        Element eenemy = npclist.get(0).getMonsterByMonsterId1().getElementmonster();
        float multiplier = (float) elementCheck(eplayer,eenemy);
        String comment = komentar(multiplier);
        float curAtkPlayer = Float.parseFloat(attackplayer.getText());
        curHpPc = Float.parseFloat(currenthealthpc.getText());
        float curDefPc = Float.valueOf(npclist.get(0).getMonsterByMonsterId1().getDef());
        float pure = curAtkPlayer*(mymonster.getValue().getMonsterskill1().getEffect()/100);
        damage = ((pure - curDefPc)*multiplier);
        if (multiplier == 0){
            damage = pure - curDefPc;
        }
        if (damage<0){
            damage = 1;
            //komen-nya panjang2 dan gda pembatas
            comment = comment + "Enemy's def has nullified your damage.";
        }
        if(curHpPc-damage<0){
            curHpPc = (float) 0.0;
        }
        else {
            curHpPc = curHpPc - damage;
        }
        currenthealthpc.setText(String.valueOf(curHpPc));
        historyArea.appendText("Player "+playerusername.getText()+" commanded "+ mymonster.getValue().getMonsterByMonsterId1().getName()+
                "\nto use Skill "+mymonster.getValue().getMonsterskill1().getName()+"\n");
        historyArea.appendText(mymonster.getValue().getMonsterByMonsterId1().getName() + " dealt " + damage + " " + eplayer.getElement()
                + " damage to \n"+ npclist.get(0).getMonsterByMonsterId1().getName()+"! " + comment+"\n");
    }

    public void useskill2(ActionEvent actionEvent) {
        actionnpc();
        Element eplayer = mymonster.getValue().getMonsterByMonsterId1().getElementmonster();
        Element eenemy = npclist.get(0).getMonsterByMonsterId1().getElementmonster();
        float multiplier = (float) elementCheck(eplayer,eenemy);
        String comment = komentar(multiplier);
        float curAtkPlayer = Float.parseFloat(attackplayer.getText());
        curHpPc = Float.parseFloat(currenthealthpc.getText());
        float curDefPc = Float.valueOf(npclist.get(0).getMonsterByMonsterId1().getDef());
        float pure = curAtkPlayer*(mymonster.getValue().getMonsterskill2().getEffect()/100);
        damage = ((pure - curDefPc)*multiplier);
        if (multiplier == 0){
            damage = pure - curDefPc;
        }
        if (damage<0){
            damage = 1;
            //komen-nya panjang2 dan gda pembatas
            comment = comment + "Enemy's def has nullified your damage.";
        }
        if(curHpPc-damage<0){
            curHpPc = (float) 0.0;
        }
        else {
            curHpPc = curHpPc - damage;
        }
        currenthealthpc.setText(String.valueOf(curHpPc));
        historyArea.appendText("Player "+playerusername.getText()+" commanded "+ mymonster.getValue().getMonsterByMonsterId1().getName()+
                "\nto use Skill "+mymonster.getValue().getMonsterskill2().getName()+"\n");
        historyArea.appendText(mymonster.getValue().getMonsterByMonsterId1().getName() + " dealt " + damage + " " + eplayer.getElement()
                + " damage to \n"+ npclist.get(0).getMonsterByMonsterId1().getName()+"! " + comment+"\n");
    }

    public void retreat(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Retreat");
        alert.setHeaderText("You chose to leave");
        alert.setContentText("Choose your monster to battle again");
        alert.showAndWait();
        Stage stage = (Stage) leavebattle.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/beranda.fxml"));
        Parent root = loader.load();
        stage.setTitle("Beranda");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void actionnpc() {
        curHpPc = Float.parseFloat(currenthealthpc.getText());
        if (curHpPc == 0.0) {
            historyArea.appendText(npclist.get(0).getMonsterByMonsterId1().getName()+" has reached 0 hp!\n");
            npclist.remove(0);
            //kalau npc list habis berarti menang?
            if(npclist!=null) {
                setMonsterPc();
            } else {

            }
        }
    }

    public Owned random() {
        Owned npc = new Owned();
        int gatcha, skll1, skll2;
        MonsterDAO monster = new MonsterDAO();
        SkillDAO skill = new SkillDAO();

        int countm = monster.count().intValue();
        int sk1 = skill.countSkill().intValue();
        int sk2 = skill.countSkill().intValue();

        gatcha = rand.nextInt(countm) + 1;
        skll1 = rand.nextInt(sk1) + 1;
        skll2 = rand.nextInt(sk2) + 1;
        while (skill2 == skill1) {
            skll2 = rand.nextInt(sk2) + 1;
        }
        npc.setIdOwned(0);
        npc.setMonsterByMonsterId1(monster.Gatcha(gatcha));
        npc.setMonsterskill1(skill.addskill(skll1));
        npc.setMonsterskill2(skill.addskill(skll2));
        return npc;
    }


    public void switchmonster(ActionEvent actionEvent) {
        hptotalplayer.setText(mymonster.getValue().getMonsterByMonsterId1().getMaxHp().toString());
        currenthelathplayer.setText(mymonster.getValue().getMonsterByMonsterId1().getMaxHp().toString());
        attackplayer.setText(mymonster.getValue().getMonsterByMonsterId1().getAtk().toString());
    }

    public double elementCheck(Element element1, Element element2) {
            switch (element1.getElement()) {
                case "water":
                    switch (element2.getElement()) {
                        case "electric":
                            return 2.0;
                        case "fire":
                            return 0.5;
                    }
                    break;
                case "grass":
                    switch (element2.getElement()) {
                        case "fire":
                            return 2.0;
                        case "ground":
                            return 0.5;
                    }
                    break;
                case "fire":
                    switch (element2.getElement()) {
                        case "water":
                            return 2.0;
                        case "grass":
                            return 0.5;
                    }
                    break;
                case "ground":
                    switch (element2.getElement()) {
                        case "grass":
                            return 2.0;
                        case "electric":
                            return 0.0;
                    }
                    break;
                case "electric":
                    switch (element2.getElement()) {
                        case "ground":
                            return 2.0;
                        case "water":
                            return 0.5;
                    }
                    break;
            }
        return 0.0;
    }

    public String komentar(float m){
        if(m == 2.0){
            return "It was very effective!";
        } else if(m == 0.5){
            return "It wasn't very effective.";
        } else {
            return "It was decent.";
        }
    }
}
