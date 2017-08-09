/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.matercero.spc.services.impl;

import es.matercero.spc.daos.IDao;
import es.matercero.spc.hibernate.Cliente;
import es.matercero.spc.hibernate.Proveedor;
import es.matercero.spc.services.IProveedorService;
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
@Service("proveedorService")
@Transactional
public class ProveedorService implements IProveedorService, Serializable {
    
    
      /**
     * * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    private IDao<Proveedor> proveedorDao;

    public ProveedorService() {
    }
    
    @Override
    public List<Proveedor> queryAllProveedores() {
        DetachedCriteria dc = DetachedCriteria.forClass(Proveedor.class, "proveedor");
        dc.addOrder(Order.asc("nombre"));
        dc.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return getProveedorDao().find(dc);
    }

    @Override
    public void createProveedor(Proveedor entity) {
        getProveedorDao().save(entity);
    }

    @Override
    public void updateProveedor(Proveedor entity) {
        getProveedorDao().update(entity);
    }

    /**
     * @return the proveedorDao
     */
    public IDao<Proveedor> getProveedorDao() {
        return proveedorDao;
    }

    /**
     * @param proveedorDao the proveedorDao to set
     */
    public void setProveedorDao(IDao<Proveedor> proveedorDao) {
        this.proveedorDao = proveedorDao;
    }
    
}
