package es.matercero.spc.daos;

import es.matercero.spc.hibernate.CustomHibernateDaoSupport;
import es.matercero.spc.hibernate.User;
import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

/**
 * Clase Dao para el objeto User
 *
 * @author mtercero
 */
@Repository("userDao")
public class UserDao extends CustomHibernateDaoSupport implements
        IDao<User>, Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private static final String TABLE_NAME = "user";

    /**
     * Constructor por defecto
     */
    public UserDao() {
    }

    /* (non-Javadoc)
     * @see IDao#save(User entity)
     */
    @Override
    public void save(User user) {
        getSession().save(user);
    }

    /* (non-Javadoc)
     * @see IDao#update(User)
     */
    @Override
    public void update(User user) {
        getSession().update(user);
    }

    /* (non-Javadoc)
     * @see IDao#delete(User)
     */
    @Override
    public void delete(User user) {
        getSession().delete(user);
    }

    /* (non-Javadoc)
     * @see IDao#find(org.hibernate.criterion.DetachedCriteria)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<User> find(DetachedCriteria criteria) {
        if (criteria == null) {
            return getSession().createQuery("FROM " + TABLE_NAME + " ORDER BY id")
                    .list();
        } else {
            return criteria.getExecutableCriteria(getSession()).list();
        }
    }

    /* (non-Javadoc)
     * @see IDao#find(org.hibernate.criterion.DetachedCriteria, int)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<User> find(DetachedCriteria criteria,
            int numResults) {
        if (criteria == null) {
            return getSession().createQuery("FROM " + TABLE_NAME + " ORDER BY id")
                    .setMaxResults(numResults).list();
        } else {
            return criteria.getExecutableCriteria(getSession())
                    .setMaxResults(numResults).list();
        }
    }
    
}
