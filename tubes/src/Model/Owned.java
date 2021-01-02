package Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Owned {
    private int idOwned;
    private Skill monsterskill1;
    private Skill monsterskill2;
    private User username;
    private Monster monsterByMonsterId1;

    @Override
    public String toString() {
        return monsterByMonsterId1.getName();
    }

    @Id
    @Column(name = "Id_owned")
    public int getIdOwned() {
        return idOwned;
    }


    public void setIdOwned(int idOwned) {
        this.idOwned = idOwned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owned owned = (Owned) o;
        return idOwned == owned.idOwned;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOwned);
    }

    @ManyToOne
    @JoinColumn(name = "Skill_Id", referencedColumnName = "Id", nullable = false)
    public Skill getMonsterskill1() {
        return monsterskill1;
    }

    public void setMonsterskill1(Skill monsterskill1) {
        this.monsterskill1 = monsterskill1;
    }

    @ManyToOne
    @JoinColumn(name = "Skill2_Id", referencedColumnName = "Id", nullable = false)
    public Skill getMonsterskill2() {
        return monsterskill2;
    }

    public void setMonsterskill2(Skill monsterskill2) {
        this.monsterskill2 = monsterskill2;
    }

    @ManyToOne
    @JoinColumn(name = "User_Username", referencedColumnName = "Username", nullable = false)
    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    @ManyToOne
    @JoinColumn(name = "Monster_Id1", referencedColumnName = "Id", nullable = false)
    public Monster getMonsterByMonsterId1() {
        return monsterByMonsterId1;
    }

    public void setMonsterByMonsterId1(Monster monsterByMonsterId1) {
        this.monsterByMonsterId1 = monsterByMonsterId1;
    }
}
