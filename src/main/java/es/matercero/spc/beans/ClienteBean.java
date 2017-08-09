/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.matercero.spc.beans;

import es.matercero.spc.hibernate.Cliente;
import es.matercero.spc.services.IClienteService;
import es.matercero.spc.utils.Utilidades;
import java.util.List;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author mangel.tercero
 */
@Component("clienteBean")
@Scope("session")
public class ClienteBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ClienteBean.class);

    @Autowired
    private IClienteService clienteService;
    private List<Cliente> clientes;
    private Cliente selectedCliente;

    /**
     * @return the clientes
     */
    public List<Cliente> getClientes() {
        if (clientes == null) {
            clientes = getClienteService().queryAllClientes();
        }
        return clientes;
    }

    /**
     * @param clientes the clientes to set
     */
    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    /**
     * @return the clienteService
     */
    public IClienteService getClienteService() {
        return clienteService;
    }

    /**
     * @param clienteService the clienteService to set
     */
    public void setClienteService(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * @return the selectedCliente
     */
    public Cliente getSelectedCliente() {
        return selectedCliente;
    }

    /**
     * @param selectedCliente the selectedCliente to set
     */
    public void setSelectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }

    /**
     *
     * @return título de edición
     */
    public String getEditTitle() {
        return ((selectedCliente == null || selectedCliente.getId() == null) ? "Nuevo cliente" : "Editar cliente");
    }

    /**
     * actioListener que actualiza los cambios del usuario
     *
     * @param actionEvent objeto actionEvent
     * @return
     */
    public String createOrUpdateCliente(ActionEvent actionEvent) {

        if (selectedCliente.getId() == null) {
            selectedCliente.setDateCreated(Calendar.getInstance().getTime());
            selectedCliente.setLastUpdated(Calendar.getInstance().getTime());
            selectedCliente.setEnabled(true);

            clienteService.createCliente(selectedCliente);
            // el codigo del cliente se forma con 
            // Anyo actual 'yy + id'
            SimpleDateFormat sf = new SimpleDateFormat("yy");
            String id =  String.format("%02d", (Integer) selectedCliente.getId());
            String codigo = sf.format(Calendar.getInstance().getTime()) + id ;
            selectedCliente.setCodigo(codigo);
            clienteService.updateCliente(selectedCliente);
            reiniciarListaClientes();
            Utilidades.setMessage(FacesMessage.SEVERITY_INFO, "Cliente creado",
                    "El Cliente se ha creado correctamente.");
        } else {
            selectedCliente.setLastUpdated(Calendar.getInstance().getTime());
            clienteService.updateCliente(selectedCliente);
            reiniciarListaClientes();
            Utilidades.setMessage(FacesMessage.SEVERITY_INFO, "Cliente actualizado",
                    "El Cliente se ha actualizado correctamente.");
        }
        return "cliente?faces-redirect=true";
    }

    private void reiniciarListaClientes() {
        clientes = getClienteService().queryAllClientes();
    }

    /**
     * prepara la creación de nuevo usuario
     *
     * @param actionEvent objeto actionEvent
     */
    public void newCliente(ActionEvent actionEvent) {
        selectedCliente = new Cliente();

    }

}
