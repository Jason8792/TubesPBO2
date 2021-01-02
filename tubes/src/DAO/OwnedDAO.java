package DAO;

import Model.Owned;
import Model.User;
import Utility.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class OwnedDAO {
    public ObservableList<Owned> showData(User user) {
        Session s = HibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Owned> query = builder.createQuery(Owned.class);
        Root<Owned> root = query.from(Owned.class);

        Predicate p = builder.equal(root.get("username"), user);
        List<Owned> cList = s.createQuery(query.where(p)).getResultList();
        s.close();

        return FXCollections.observableArrayList(cList);
    }
    public int addData(Owned data){
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.save(data);
        t.commit();
        s.close();
        return 0;
    }
}
