package Controller;

import DAO.MonsterDAO;
import DAO.SkillDAO;
import Model.Element;
import Model.Owned;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.Random;

public class controllerGame {
    public Label currenthelathplayer;
    public Label hptotalplayer;
    public Label hptotalpc;
    public Label currenthealthpc;
    public Label attackplayer;
    public Label attackpc;
    public ComboBox<Owned> mymonster;
    public Button skill1;
    public Button skill2;
    public Button leavebattle;
    public Label Computer;
    User user;
    Random rand = new Random();
    ObservableList<Owned> npclist = FXCollections.observableArrayList();
    float atk = 0;
    float def =0;

    public ObservableList<Owned> monsterpc(){
        for(int i =0 ; i<3 ;i++){
            npclist.add(random());
        }
        return npclist;
    }

    public void initialize(User user, ObservableList<Owned> olist){
        //buat player
        this.user=user;
        mymonster.setItems(olist);
        mymonster.getSelectionModel().select(0);
        hptotalplayer.setText(mymonster.getValue().getMonsterByMonsterId1().getMaxHp().toString());
        currenthelathplayer.setText(mymonster.getValue().getMonsterByMonsterId1().getMaxHp().toString());
        attackplayer.setText(mymonster.getValue().getMonsterByMonsterId1().getAtk().toString());
        // buat Monster
        monsterpc();
        Computer.setText(npclist.get(0).getMonsterByMonsterId1().getName());
        currenthealthpc.setText(npclist.get(0).getMonsterByMonsterId1().getMaxHp().toString());
        hptotalpc.setText(npclist.get(0).getMonsterByMonsterId1().getMaxHp().toString());
        attackpc.setText(npclist.get(0).getMonsterByMonsterId1().getAtk().toString());
    }

    public void useskill1(ActionEvent actionEvent) {
        actionnpc();
        String elementplayer =mymonster.getValue().getMonsterByMonsterId1().getElementmonster().getElement();
        String elementenemy =npclist.get(0).getMonsterByMonsterId1().getElementmonster().getElement();
        if(elementplayer.equals("Water")){
            if(elementenemy.equals("Fire")){
                atk = ((Float.parseFloat(mymonster.getValue().getMonsterByMonsterId1().getAtk().toString())+Float.parseFloat(mymonster.getValue().getMonsterskill1().getEffect().toString()))*0.5f)-Float.parseFloat(npclist.get(0).getMonsterByMonsterId1().getDef().toString());
                def = Float.parseFloat(currenthealthpc.getText())-atk;
                currenthealthpc.setText(String.valueOf(def));
            }
            else if(elementenemy.equals("Water")){
                atk = (Float.parseFloat(mymonster.getValue().getMonsterByMonsterId1().getAtk().toString())+Float.parseFloat(mymonster.getValue().getMonsterskill1().getEffect().toString()))-Float.parseFloat(npclist.get(0).getMonsterByMonsterId1().getDef().toString());
                def = Float.parseFloat(currenthealthpc.getText())-atk;
                currenthealthpc.setText(String.valueOf(def));
            }
            else{
                atk = ((Float.parseFloat(mymonster.getValue().getMonsterByMonsterId1().getAtk().toString())+Float.parseFloat(mymonster.getValue().getMonsterskill1().getEffect().toString()))*1.5f)-Float.parseFloat(npclist.get(0).getMonsterByMonsterId1().getDef().toString());
                def = Float.parseFloat(currenthealthpc.getText())-atk;
                currenthealthpc.setText(String.valueOf(def));
            }
        }
        else if (elementplayer.equals("Fire")){

            if(elementenemy.equals("Grass")){
                atk = ((Float.parseFloat(mymonster.getValue().getMonsterByMonsterId1().getAtk().toString())+Float.parseFloat(mymonster.getValue().getMonsterskill1().getEffect().toString()))*0.5f)-Float.parseFloat(npclist.get(0).getMonsterByMonsterId1().getDef().toString());
                def = Float.parseFloat(currenthealthpc.getText())-atk;
                currenthealthpc.setText(String.valueOf(def));
            }
            else if(elementenemy.equals("Fire")){
                atk = (Float.parseFloat(mymonster.getValue().getMonsterByMonsterId1().getAtk().toString())+Float.parseFloat(mymonster.getValue().getMonsterskill1().getEffect().toString()))-Float.parseFloat(npclist.get(0).getMonsterByMonsterId1().getDef().toString());
                def = Float.parseFloat(currenthealthpc.getText())-atk;
                currenthealthpc.setText(String.valueOf(def));
            }
            else{
                atk = ((Float.parseFloat(mymonster.getValue().getMonsterByMonsterId1().getAtk().toString())+Float.parseFloat(mymonster.getValue().getMonsterskill1().getEffect().toString()))*1.5f)-Float.parseFloat(npclist.get(0).getMonsterByMonsterId1().getDef().toString());
                def = Float.parseFloat(currenthealthpc.getText())-atk;
                currenthealthpc.setText(String.valueOf(def));
            }
        }
        else{

            if(elementenemy.equals("Water")){
                atk = ((Float.parseFloat(mymonster.getValue().getMonsterByMonsterId1().getAtk().toString())+Float.parseFloat(mymonster.getValue().getMonsterskill1().getEffect().toString()))*0.5f)-Float.parseFloat(npclist.get(0).getMonsterByMonsterId1().getDef().toString());
                def = Float.parseFloat(currenthealthpc.getText())-atk;
                currenthealthpc.setText(String.valueOf(def));
            }
            else if(elementenemy.equals("Grass")){
                atk = (Float.parseFloat(mymonster.getValue().getMonsterByMonsterId1().getAtk().toString())+Float.parseFloat(mymonster.getValue().getMonsterskill1().getEffect().toString()))-Float.parseFloat(npclist.get(0).getMonsterByMonsterId1().getDef().toString());
                def = Float.parseFloat(currenthealthpc.getText())-atk;
                currenthealthpc.setText(String.valueOf(def));
            }
            else{
                atk = ((Float.parseFloat(mymonster.getValue().getMonsterByMonsterId1().getAtk().toString())+Float.parseFloat(mymonster.getValue().getMonsterskill1().getEffect().toString()))*1.5f)-Float.parseFloat(npclist.get(0).getMonsterByMonsterId1().getDef().toString());
                def = Float.parseFloat(currenthealthpc.getText())-atk;
                currenthealthpc.setText(String.valueOf(def));
            }
        }
    }

    public void useskill2(ActionEvent actionEvent) {
    }

    public void retreat(ActionEvent actionEvent) {
    }
    public void actionnpc(){
        if(currenthealthpc.equals(0)){
            npclist.remove(0);
        }
    }
    public Owned random(){
        Owned npc = new Owned();
        int gatcha,skll1,skll2;
        MonsterDAO monster = new MonsterDAO();
        SkillDAO skill = new SkillDAO();

        int countm = monster.count().intValue();
        int sk1 = skill.countSkill().intValue();
        int sk2 = skill.countSkill().intValue();

        gatcha = rand.nextInt(countm) + 1;
        skll1 = rand.nextInt(sk1)+1;
        skll2 = rand.nextInt(sk2)+1;
        while (skill2==skill1){
            skll2 = rand.nextInt(sk2)+1;
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
}
