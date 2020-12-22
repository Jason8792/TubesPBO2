package DAO;

import Model.User;
import Utility.HibernateUtil;
import org.hibernate.Session;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDAO implements DaoInterface<User>{
    public User showData(String username) {
        Session s = HibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root =  query.from(User.class);

        Predicate p = builder.equal(root.get("username"), username);
        List<User> users = s.createQuery(query.where(p)).getResultList();
        User user = users.get(0);
        s.close();

        return user;
    }

    @Override
    public int AddData(User data) {
        return 0;
    }

    @Override
    public int DelData(User data) {
        return 0;
    }

    @Override
    public int UpdData(User data) {
        return 0;
    }

    @Override
    public List<User> ShowData() {
        return null;
    }
}
