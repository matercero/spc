/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.matercero.spc.daos;

import es.matercero.spc.hibernate.Cliente;
import es.matercero.spc.hibernate.CustomHibernateDaoSupport;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mangel.tercero
 */
@Repository("clienteDao")
public class ClienteDao extends CustomHibernateDaoSupport implements
        IDao<Cliente>, Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private static final String TABLE_NAME = "cliente";

    public ClienteDao() {
    }

    @Override
    public void save(Cliente entity) {
        getSession().save(entity);
    }

    @Override
    public void update(Cliente entity) {
        getSession().update(entity);
    }

    @Override
    public void delete(Cliente entity) {
          getSession().delete(entity);
    }

    @Override
    public List<Cliente> find(DetachedCriteria criteria) {
        if (criteria == null) {
            return getSession().createQuery("FROM " + TABLE_NAME + " ORDER BY id")
                    .list();
        } else {
            return criteria.getExecutableCriteria(getSession()).list();
        }
    }

    @Override
    public List<Cliente> find(DetachedCriteria criteria, int numResults) {
        if (criteria == null) {
            return getSession().createQuery("FROM " + TABLE_NAME + " ORDER BY id")
                    .setMaxResults(numResults).list();
        } else {
            return criteria.getExecutableCriteria(getSession())
                    .setMaxResults(numResults).list();
        }
    }

}
