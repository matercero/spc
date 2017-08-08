package es.matercero.spc.daos;

import es.matercero.spc.hibernate.CustomHibernateDaoSupport;
import es.matercero.spc.hibernate.Role;
import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

/**
 * Clase Dao para el objeto Role
 *
 * @author mtercero
 *
 */
@Repository("roleDao")
public class RoleDao extends CustomHibernateDaoSupport implements
        IDao<Role>, Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private static final String TABLE_NAME = "role";

    /**
     * Constructor por defecto
     */
    public RoleDao() {
    }

    /* (non-Javadoc)
     * @see IDao#save(Role entity)
     */
    @Override
    public void save(Role role) {
        getSession().save(role);
    }

    /* (non-Javadoc)
     * @see IDao#update(Role)
     */
    @Override
    public void update(Role role) {
        getSession().update(role);
    }

    /* (non-Javadoc)
     * @see IDao#delete(Role)
     */
    @Override
    public void delete(Role role) {
        getSession().delete(role);
    }

    /* (non-Javadoc)
     * @see IDao#find(org.hibernate.criterion.DetachedCriteria)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Role> find(DetachedCriteria criteria) {
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
    public List<Role> find(DetachedCriteria criteria,
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
