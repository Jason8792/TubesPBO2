package DAO;

import Model.Monster;
import Utility.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class MonsterDAO {
    public Monster Gatcha(int n){
        Session s = HibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Monster> query = builder.createQuery(Monster.class);
        Root<Monster> root = query.from(Monster.class);

        Predicate p1 = builder.equal(root.get("id"),n);
        List<Monster> monsters = s.createQuery(query.where(p1)).getResultList();
        Monster monster = monsters.get(0);

        return monster;

    }
    public Long count() {
        Session s = HibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Monster> root = query.from(Monster.class);

        List<Long> cList = s.createQuery(query.select(builder.count(root))).getResultList();
        Long result = cList.get(0);
        s.close();

        return result;
    }

}


