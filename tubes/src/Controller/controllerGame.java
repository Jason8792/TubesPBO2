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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public ImageView ImgPlayer;
    public ImageView ImgCPU;
    User user;
    Random rand = new Random();
    ObservableList<Owned> npclist = FXCollections.observableArrayList();
    float damage;
    float curHpPc;
    float curHpPlayer;
    float curmons1;
    float curmons2;
    float curmons3;
    float multi;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    Date date = new Date();

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
        curmons1 = olist.get(0).getMonsterByMonsterId1().getMaxHp();
        curmons2 = olist.get(1).getMonsterByMonsterId1().getMaxHp();
        curmons3 = olist.get(2).getMonsterByMonsterId1().getMaxHp();

        mymonster.getSelectionModel().select(0);
        hptotalplayer.setText(String.valueOf(curmons1));
        skill1.setText(mymonster.getValue().getMonsterskill1().getName());
        skill2.setText(mymonster.getValue().getMonsterskill2().getName());
        currenthelathplayer.setText(mymonster.getValue().getMonsterByMonsterId1().getMaxHp().toString());
        attackplayer.setText(mymonster.getValue().getMonsterByMonsterId1().getAtk().toString());
        playerusername.setText(this.user.getUsername());
        ImgPlayer.setImage(new Image("Image/" + mymonster.getSelectionModel().getSelectedItem().getMonsterByMonsterId1().getPicture() + ".jpg"));
        // buat Monster
        monsterpc();
        setMonsterPc();
    }

    public void setMonsterPc(){
        Computer.setText(npclist.get(0).getMonsterByMonsterId1().getName());
        currenthealthpc.setText(npclist.get(0).getMonsterByMonsterId1().getMaxHp().toString());
        System.out.println(npclist.get(0).getMonsterByMonsterId1().getMaxHp().toString());
        hptotalpc.setText(npclist.get(0).getMonsterByMonsterId1().getMaxHp().toString());
        attackpc.setText(npclist.get(0).getMonsterByMonsterId1().getAtk().toString());
        ImgCPU.setImage(new Image("Image/" + npclist.get(0).getMonsterByMonsterId1().getPicture() + ".jpg"));
    }


    public void enemyattack() throws IOException{
        Element eplayer = mymonster.getValue().getMonsterByMonsterId1().getElementmonster();
        Element eenemy = npclist.get(0).getMonsterByMonsterId1().getElementmonster();
        float multiplier = elementCheck(eplayer,eenemy);
        String comment = komentar(multiplier);
        float curAtkPc = Float.parseFloat(attackpc.getText());
        curHpPlayer = Float.parseFloat(currenthelathplayer.getText());
        float curDefPlayer = Float.valueOf(mymonster.getValue().getMonsterByMonsterId1().getDef());
        int random = rand.nextInt(2);
        float purenpc;
        if(random==0) {
            purenpc = curAtkPc + npclist.get(0).getMonsterskill1().getEffect();

            historyArea.appendText("PC  commanded "+ npclist.get(0).getMonsterByMonsterId1().getName()+
                    "\nto use Skill "+npclist.get(0).getMonsterskill1().getName()+"\n");
        }
        else{
            purenpc = curAtkPc + npclist.get(0).getMonsterskill2().getEffect();
            historyArea.appendText("PC  commanded "+ npclist.get(0).getMonsterByMonsterId1().getName()+
                    "\nto use Skill "+npclist.get(0).getMonsterskill2().getName()+"\n");

        }

        damage = purenpc * multiplier - curDefPlayer;
        if(damage<0) {
            if (eplayer.getElement().equals("Ground") && eenemy.getElement().equals("Electric")) {
                damage =0;
            }
            else{
                damage =1;
            }
        }
        historyArea.appendText(npclist.get(0).getMonsterByMonsterId1().getName() + " dealt " + damage + " " + eenemy.getElement()
                + " damage to \n"+ mymonster.getValue().getMonsterByMonsterId1().getName()+"! " + comment+"\n");
        if(curHpPlayer - damage <=0){
            curHpPlayer=0.0f;
            actionplayer();
        }
        else{
            curHpPlayer = curHpPlayer - damage;
            currenthelathplayer.setText(String.valueOf(curHpPlayer));
        }

        if(mymonster.getSelectionModel().getSelectedIndex()==0){
            this.curmons1 = Float.parseFloat (currenthelathplayer.getText());
        }
        else if(mymonster.getSelectionModel().getSelectedIndex()==1){
            this.curmons2 = Float.parseFloat(currenthelathplayer.getText());
        }
        else{
            this.curmons3 = Float.parseFloat(currenthelathplayer.getText());
        }
    }

    public void useskill1(ActionEvent actionEvent) throws IOException {
        Element eplayer = mymonster.getValue().getMonsterByMonsterId1().getElementmonster();
        Element eenemy = npclist.get(0).getMonsterByMonsterId1().getElementmonster();
        float multiplier = elementCheck(eenemy,eplayer);
        String comment = komentar(multiplier);
        float curAtkPlayer = Float.parseFloat(attackplayer.getText());
        curHpPc = Float.parseFloat(currenthealthpc.getText());
        float curDefPc = Float.valueOf(npclist.get(0).getMonsterByMonsterId1().getDef());
        float pure = curAtkPlayer + mymonster.getValue().getMonsterskill1().getEffect();

        damage = pure*multiplier-curDefPc;
        if(damage<0) {
            if (eenemy.getElement().equals("Ground") && eplayer.getElement().equals("Electric")) {
                damage =0;
            }
            else{
                damage =1;
            }
        }

        historyArea.appendText("Player "+playerusername.getText()+" commanded "+ mymonster.getValue().getMonsterByMonsterId1().getName()+
                "\nto use Skill "+mymonster.getValue().getMonsterskill1().getName()+"\n");
        historyArea.appendText(mymonster.getValue().getMonsterByMonsterId1().getName() + " dealt " + damage + " " + eplayer.getElement()
                + " damage to \n"+ npclist.get(0).getMonsterByMonsterId1().getName()+"! " + comment+"\n");
        if(curHpPc-damage<=0){
            curHpPc = 0.0f;
            actionnpc();
        }
        else {
            curHpPc = curHpPc - damage;
            currenthealthpc.setText(String.valueOf(curHpPc));
            enemyattack();
        }
    }

    public void useskill2(ActionEvent actionEvent) throws IOException {
        Element eplayer = mymonster.getValue().getMonsterByMonsterId1().getElementmonster();
        Element eenemy = npclist.get(0).getMonsterByMonsterId1().getElementmonster();
        float multiplier = elementCheck(eenemy,eplayer);
        String comment = komentar(multiplier);
        float curAtkPlayer = Float.parseFloat(attackplayer.getText());
        curHpPc = Float.parseFloat(currenthealthpc.getText());
        float curDefPc = Float.valueOf(npclist.get(0).getMonsterByMonsterId1().getDef());
        float pure = curAtkPlayer+mymonster.getValue().getMonsterskill2().getEffect();
        damage = pure * multiplier- curDefPc;
        if(damage<0) {
            if (eenemy.getElement().equals("Ground") && eplayer.getElement().equals("Electric")) {
                damage =0;
            }
            else{
                damage =1;
            }
        }

        historyArea.appendText("Player "+playerusername.getText()+" commanded "+ mymonster.getValue().getMonsterByMonsterId1().getName()+
                "\nto use Skill "+mymonster.getValue().getMonsterskill2().getName()+"\n");
        historyArea.appendText(mymonster.getValue().getMonsterByMonsterId1().getName() + " dealt " + damage + " " + eplayer.getElement()
                + " damage to \n"+ npclist.get(0).getMonsterByMonsterId1().getName()+"! " + comment+"\n");

        if(curHpPc-damage<=0){
            curHpPc = 0.0f;
            actionnpc();
        }
        else {
            curHpPc = curHpPc - damage;
            currenthealthpc.setText(String.valueOf(curHpPc));
            enemyattack();

        }

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
        controllerBeranda controllerBeranda = loader.getController();
        controllerBeranda.initialize(user);
        stage.show();
    }

    public void actionnpc() throws IOException {
        if(npclist.size()>1){
            historyArea.appendText(npclist.get(0).getMonsterByMonsterId1().getName()+" has reached 0 hp!\n");
            npclist.remove(0);
            setMonsterPc();
            }
        else{
            String path = "histori/save.txt";
            StringBuilder text = new StringBuilder();
            text.append(user.getUsername()).append(";").append( formatter.format(date)).append(";").append("win").append("\n");
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(path,true));
                writer.write(text.toString());
                writer.close();
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
            historyArea.appendText(playerusername.getText()+" Has been Win !");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Kamu Menang");
            alert.setHeaderText("Selamat Kamu menang");
            alert.setContentText("back to main menu");
            alert.showAndWait();
            Stage stage = (Stage) leavebattle.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/beranda.fxml"));
            Parent root = loader.load();
            stage.setTitle("Beranda");
            stage.setScene(new Scene(root, 600, 400));
            controllerBeranda controllerBeranda = loader.getController();
            controllerBeranda.initialize(user);
            stage.show();
        }

    }

    public void actionplayer() throws IOException {
        curHpPlayer = Float.parseFloat(currenthelathplayer.getText());
            if(mymonster.getItems().size()>1){
                historyArea.appendText(mymonster.getValue().getMonsterByMonsterId1().getName()+" has reached 0 hp!\n");

                Owned deadmonster = mymonster.getSelectionModel().getSelectedItem();
                mymonster.getItems().remove(deadmonster);
                mymonster.getSelectionModel().select(0);
                hptotalplayer.setText(mymonster.getValue().getMonsterByMonsterId1().getMaxHp().toString());
                currenthelathplayer.setText(mymonster.getValue().getMonsterByMonsterId1().getMaxHp().toString());
                attackplayer.setText(mymonster.getValue().getMonsterByMonsterId1().getAtk().toString());
                playerusername.setText(this.user.getUsername());
                ImgPlayer.setImage(new Image("Image/" + mymonster.getSelectionModel().getSelectedItem().getMonsterByMonsterId1().getPicture() + ".jpg"));
            }
            else{
                String path = "histori/save.txt";
                StringBuilder text = new StringBuilder();
                text.append(user.getUsername()).append(";").append(formatter.format(date)).append(";").append("Lose").append("\n");
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(path,true));
                    writer.write(text.toString());
                    writer.close();
                }
                catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                historyArea.appendText(playerusername.getText()+" Lose !");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Kamu Kalah");
                alert.setHeaderText("Coba lagi ya");
                alert.setContentText("back to main menu");
                alert.showAndWait();
                Stage stage = (Stage) leavebattle.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/beranda.fxml"));
                Parent root = loader.load();
                stage.setTitle("Beranda");
                stage.setScene(new Scene(root, 600, 400));
                controllerBeranda controllerBeranda = loader.getController();
                controllerBeranda.initialize(user);
                stage.show();
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
        if(mymonster.getSelectionModel().getSelectedIndex()==0){
            currenthelathplayer.setText(String.valueOf(this.curmons1));
        }
        else if(mymonster.getSelectionModel().getSelectedIndex()==1){
            currenthelathplayer.setText(String.valueOf(this.curmons2));
        }
        else {
            currenthelathplayer.setText(String.valueOf(this.curmons3));
        }
        skill1.setText(mymonster.getValue().getMonsterskill1().getName());
        skill2.setText(mymonster.getValue().getMonsterskill2().getName());
        hptotalplayer.setText(mymonster.getValue().getMonsterByMonsterId1().getMaxHp().toString());
        attackplayer.setText(mymonster.getValue().getMonsterByMonsterId1().getAtk().toString());
        ImgPlayer.setImage(new Image("Image/" + mymonster.getSelectionModel().getSelectedItem().getMonsterByMonsterId1().getPicture() + ".jpg"));
    }

    public float elementCheck(Element element1, Element element2) {
            switch (element1.getElement().toLowerCase()) {
                case "water":
                    switch (element2.getElement().toLowerCase()) {
                        case "electric":
                            this.multi = 2.0f;
                            break;
                        case "fire":
                            this.multi = 0.5f;
                            break;
                        default:
                            this.multi=1.0f;
                            break;
                    }
                    break;
                case "grass":
                    switch (element2.getElement().toLowerCase()) {
                        case "fire":
                            this.multi = 2.0f;
                            break;
                        case "ground":
                            this.multi = 0.5f;
                            break;
                        default:
                            this.multi=1.0f;
                            break;
                    }
                    break;
                case "fire":
                    switch (element2.getElement().toLowerCase()) {
                        case "water":
                            this.multi = 2.0f;
                            break;
                        case "grass":
                            this.multi = 0.5f;
                            break;
                        default:
                            this.multi=1.0f;
                            break;
                    }
                    break;
                case "ground":
                    switch (element2.getElement().toLowerCase()) {
                        case "grass":
                            this.multi = 2.0f;
                            break;
                        case "electric":
                            this.multi = 0.0f;
                            break;
                        default:
                            this.multi=1.0f;
                            break;
                    }
                    break;
                case "electric":
                    switch (element2.getElement().toLowerCase()) {
                        case "ground":
                            this.multi = 2.0f;
                            break;
                        case "water":
                            this.multi = 0.5f;
                            break;
                        default:
                            this.multi=1.0f;
                            break;
                    }
                    break;



            }
            return this.multi;

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
