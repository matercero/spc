/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.matercero.spc.services;

import es.matercero.spc.hibernate.Categoria;
import es.matercero.spc.hibernate.Componente;
import es.matercero.spc.hibernate.Pago;
import es.matercero.spc.hibernate.Seguimiento;
import java.util.List;

/**
 *
 * @author mangel.tercero
 */
public interface IMantenimientoService {

    /**
     * Categorias
     */
    void createCategoria(Categoria entity);

    void updateCategoria(Categoria entity);

    List<Categoria> queryAllCategorias();

    /**
     * Componente
     */
    List<Componente> queryAllComponentes();
    
    void createComponente(Componente entity);
    
    void updateComponente(Componente entity);
    /**
     * Pago
     *
     * @param entity Pago
     */
    List<Pago> queryAllPagos();
    
    void createPago(Pago entity);
    
    void updatePago(Pago entity);
    
    /**
     * seguimiento
     *
     * @param entity seguimiento
     */
    List<Seguimiento> queryAllSeguimientos();
    
    void createSeguimiento(Seguimiento entity);
    
    void updateSeguimiento(Seguimiento entity);
}
