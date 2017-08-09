/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.matercero.spc.services.impl;

import es.matercero.spc.daos.IDao;
import es.matercero.spc.hibernate.Categoria;
import es.matercero.spc.hibernate.Componente;
import es.matercero.spc.hibernate.Pago;
import es.matercero.spc.services.IMantenimientoService;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mangel.tercero
 */
@Service("mantenimientoService")
@Transactional
public class MantenimientoService implements IMantenimientoService, Serializable {

    /**
     * * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    private IDao<Componente> componenteDao;
    @Autowired
    private IDao<Categoria> categoriaDao;
    @Autowired
    private IDao<Pago> pagoDao;

    @Override
    public void createCategoria(Categoria entity) {
        getCategoriaDao().save(entity);
    }

    @Override
    public void updateCategoria(Categoria entity) {
        getCategoriaDao().update(entity);
    }

    @Override
    public List<Categoria> queryAllCategorias() {
        DetachedCriteria dc = DetachedCriteria.forClass(Categoria.class, "categoria");
        dc.addOrder(Order.asc("nombre"));
        dc.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return getCategoriaDao().find(dc);
    }

    @Override
    public List<Componente> queryAllComponentes() {
        DetachedCriteria dc = DetachedCriteria.forClass(Componente.class, "componente");
        dc.addOrder(Order.asc("nombre"));
        dc.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return getComponenteDao().find(dc);
    }

    @Override
    public void createComponente(Componente entity) {
        getComponenteDao().save(entity);
    }

    @Override
    public void updateComponente(Componente entity) {
        getComponenteDao().update(entity);
    }

    @Override
    public List<Pago> queryAllPagos() {
        DetachedCriteria dc = DetachedCriteria.forClass(Pago.class, "pago");
        dc.addOrder(Order.asc("pago"));
        dc.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return getPagoDao().find(dc);
    }

    @Override
    public void createPago(Pago entity) {
        getPagoDao().save(entity);
    }

    @Override
    public void updatePago(Pago entity) {
        getPagoDao().update(entity);
    }

    /**
     * @return the componenteDao
     */
    public IDao<Componente> getComponenteDao() {
        return componenteDao;
    }

    /**
     * @param componenteDao the componenteDao to set
     */
    public void setComponenteDao(IDao<Componente> componenteDao) {
        this.componenteDao = componenteDao;
    }

    /**
     * @return the categoriaDao
     */
    public IDao<Categoria> getCategoriaDao() {
        return categoriaDao;
    }

    /**
     * @param categoriaDao the categoriaDao to set
     */
    public void setCategoriaDao(IDao<Categoria> categoriaDao) {
        this.categoriaDao = categoriaDao;
    }

    /**
     * @return the pagoDao
     */
    public IDao<Pago> getPagoDao() {
        return pagoDao;
    }

    /**
     * @param pagoDao the pagoDao to set
     */
    public void setPagoDao(IDao<Pago> pagoDao) {
        this.pagoDao = pagoDao;
    }

}
