package Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Monster {
    private int id;
    private String name;
    private Integer maxHp;
    private Integer atk;
    private Integer def;
    private String picture;
    private Skill skill1;
    private Skill skill2;
    private Element element;
    private User user;

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Max HP")
    public Integer getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(Integer maxHp) {
        this.maxHp = maxHp;
    }

    @Basic
    @Column(name = "Atk")
    public Integer getAtk() {
        return atk;
    }

    public void setAtk(Integer atk) {
        this.atk = atk;
    }

    @Basic
    @Column(name = "Def")
    public Integer getDef() {
        return def;
    }

    public void setDef(Integer def) {
        this.def = def;
    }

    @Basic
    @Column(name = "Picture")
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monster monster = (Monster) o;
        return id == monster.id &&
                Objects.equals(name, monster.name) &&
                Objects.equals(maxHp, monster.maxHp) &&
                Objects.equals(atk, monster.atk) &&
                Objects.equals(def, monster.def) &&
                Objects.equals(picture, monster.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, maxHp, atk, def, picture);
    }

    @ManyToOne
    @JoinColumn(name = "Skill1", referencedColumnName = "Id", nullable = false)
    public Skill getSkill1() {
        return skill1;
    }

    public void setSkill1(Skill skill1) {
        this.skill1 = skill1;
    }

    @ManyToOne
    @JoinColumn(name = "Skill2", referencedColumnName = "Id", nullable = false)
    public Skill getSkill2() {
        return skill2;
    }

    public void setSkill2(Skill skill2) {
        this.skill2 = skill2;
    }

    @ManyToOne
    @JoinColumn(name = "Element_Id", referencedColumnName = "Id", nullable = false)
    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    @ManyToOne
    @JoinColumn(name = "User", referencedColumnName = "Username", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
