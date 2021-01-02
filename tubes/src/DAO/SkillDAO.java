package DAO;

import Model.Skill;
import Utility.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class SkillDAO {
    public Skill addskill(int n){
        Session s = HibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Skill> query = builder.createQuery(Skill.class);
        Root<Skill> root = query.from(Skill.class);

        Predicate p1 = builder.equal(root.get("id"),n);
        List<Skill> skills = s.createQuery(query.where(p1)).getResultList();
        Skill skill = skills.get(0);

        return skill;
    }
    public Long countSkill() {
        Session s = HibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Skill> root = query.from(Skill.class);

        List<Long> cList = s.createQuery(query.select(builder.count(root))).getResultList();
        Long result = cList.get(0);
        s.close();

        return result;
    }
}
