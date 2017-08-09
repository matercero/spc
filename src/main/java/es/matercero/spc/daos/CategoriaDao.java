/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.matercero.spc.daos;

import es.matercero.spc.hibernate.Categoria;
import es.matercero.spc.hibernate.CustomHibernateDaoSupport;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mangel.tercero
 */
@Repository("categoriaDao")
public class CategoriaDao extends CustomHibernateDaoSupport implements
        IDao<Categoria>, Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private static final String TABLE_NAME = "categoria";

    @Override
    public void save(Categoria entity) {
        getSession().save(entity);
    }

    @Override
    public void update(Categoria entity) {
        getSession().update(entity);
    }

    @Override
    public void delete(Categoria entity) {
        getSession().update(entity);
    }

    @Override
    public List<Categoria> find(DetachedCriteria criteria) {
        if (criteria == null) {
            return getSession().createQuery("FROM " + TABLE_NAME + " ORDER BY id")
                    .list();
        } else {
            return criteria.getExecutableCriteria(getSession()).list();
        }
    }

    @Override
    public List<Categoria> find(DetachedCriteria criteria, int numResults) {
        if (criteria == null) {
            return getSession().createQuery("FROM " + TABLE_NAME + " ORDER BY id")
                    .setMaxResults(numResults).list();
        } else {
            return criteria.getExecutableCriteria(getSession())
                    .setMaxResults(numResults).list();
        }
    }

}
