/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.matercero.spc.services;

import es.matercero.spc.hibernate.Cliente;
import java.util.List;

/**
 *
 * @author mangel.tercero
 */
public interface IClienteService {


    /**
     * Devuelve la lista de todos los Clientes
     *
     * @return lista de objetos Clientes
     */
    List<Cliente> queryAllClientes();
    
    /**
     * crea un nuevo Cliente
     *
     * @param entity Cliente a crear
     */
    void createCliente(Cliente entity);

    /**
     * actualiza el elemento Cliente
     *
     * @param entity Cliente a actualizar
     */
    void updateCliente(Cliente entity);
    
}
