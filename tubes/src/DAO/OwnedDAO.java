package DAO;

import Model.Owned;
import Utility.DAOInterface;
import Utility.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class OwnedDAO implements DAOInterface<Owned> {
    @Override
    public int addData(Owned data) {
        return 0;
    }

    @Override
    public int deleteData(Owned data) {
        return 0;
    }

    @Override
    public int updateData(Owned data) {
        return 0;
    }

    @Override
    public ObservableList<Owned> showData() {
        Session s = HibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Owned> query = builder.createQuery(Owned.class);
        query.from(Owned.class);

        List<Owned> cList = s.createQuery(query).getResultList();
        s.close();

        return FXCollections.observableArrayList(cList);
    }
}
