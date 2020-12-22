package Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Owned {
    private int idOwned;
    private Skill skill1;
    private Skill skill2;
    private User user;
    private Monster monsterId;

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
    public Skill getSkill1() {
        return skill1;
    }

    public void setSkill1(Skill skill1) {
        this.skill1 = skill1;
    }

    @ManyToOne
    @JoinColumn(name = "Skill2_Id", referencedColumnName = "Id", nullable = false)
    public Skill getSkill2() {
        return skill2;
    }

    public void setSkill2(Skill skill2) {
        this.skill2 = skill2;
    }

    @ManyToOne
    @JoinColumn(name = "User_Username", referencedColumnName = "Username", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "Monster_Id1", referencedColumnName = "Id", nullable = false)
    public Monster getMonsterId() {
        return monsterId;
    }

    public void setMonsterId(Monster monsterId) {
        this.monsterId = monsterId;
    }
}
