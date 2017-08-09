/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.matercero.spc.daos;

import es.matercero.spc.hibernate.CustomHibernateDaoSupport;
import es.matercero.spc.hibernate.Seguimiento;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mangel.tercero
 */
@Repository("seguimientoDao")
public class SeguimientoDao extends CustomHibernateDaoSupport implements
        IDao<Seguimiento>, Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private static final String TABLE_NAME = "seguimiento";

    @Override
    public void save(Seguimiento entity) {
        getSession().save(entity);
    }

    @Override
    public void update(Seguimiento entity) {
        getSession().update(entity);
    }

    @Override
    public void delete(Seguimiento entity) {
        getSession().delete(this);
    }

    @Override
    public List<Seguimiento> find(DetachedCriteria criteria) {
        if (criteria == null) {
            return getSession().createQuery("FROM " + TABLE_NAME)
                    .list();
        } else {
            return criteria.getExecutableCriteria(getSession()).list();
        }
    }

    @Override
    public List<Seguimiento> find(DetachedCriteria criteria, int numResults) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
