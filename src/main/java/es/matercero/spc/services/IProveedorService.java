/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.matercero.spc.services;

import es.matercero.spc.hibernate.Proveedor;
import java.util.List;

/**
 *
 * @author mangel.tercero
 */
public interface IProveedorService {
    
    /**
     * Devuelve la lista de todos los Proveedores
     *
     * @return lista de objetos Proveedores
     */
    List<Proveedor> queryAllProveedores();
    
    /**
     * crea un nuevo Proveedores
     *
     * @param entity Proveedor a crear
     */
    void createProveedor(Proveedor entity);

    /**
     * actualiza el elemento Proveedor
     *
     * @param entity Proveedor a actualizar
     */
    void updateProveedor(Proveedor entity);
    
    /**
     * 
     * @param id
     * @return 
     */
    Proveedor queryProveedorCategoriaById(Integer id);
    
}
