package Utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static Session getSession() {
        SessionFactory sf;
        Session s;

        sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        s = sf.openSession();
        return s;
    }
}