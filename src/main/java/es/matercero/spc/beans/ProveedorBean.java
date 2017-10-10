/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.matercero.spc.beans;

import es.matercero.spc.hibernate.Categoria;
import es.matercero.spc.hibernate.Proveedor;
import es.matercero.spc.services.IMantenimientoService;
import es.matercero.spc.services.IProveedorService;
import es.matercero.spc.utils.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author mangel.tercero
 */
@Component("proveedorBean")
@Scope("session")
public class ProveedorBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ProveedorBean.class);

    @Autowired
    private IProveedorService proveedorService;
    @Autowired
    private IMantenimientoService mantenimientoService;
    private List<Proveedor> proveedores;
    private Proveedor selectedProveedor;
    private DualListModel<Categoria> selectedCategoria;

    /**
     * @return the proveedorService
     */
    public IProveedorService getProveedorService() {
        return proveedorService;
    }

    /**
     * @param proveedorService the proveedorService to set
     */
    public void setProveedorService(IProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    /**
     * @return the proveedores
     */
    public List<Proveedor> getProveedores() {
        if (proveedores == null) {
            proveedores = getProveedorService().queryAllProveedores();
        }
        return proveedores;
    }

    /**
     * @param proveedores the proveedores to set
     */
    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    /**
     * @return the selectedProveedor
     */
    public Proveedor getSelectedProveedor() {
        return selectedProveedor;
    }

    /**
     * @param selectedProveedor the selectedProveedor to set
     */
    public void setSelectedProveedor(Proveedor selectedProveedor) {
        this.selectedProveedor = selectedProveedor;
    }

    /**
     * prepara la creación de nuevo usuario
     *
     * @param actionEvent objeto actionEvent
     */
    public void newProveedor(ActionEvent actionEvent) {
        selectedProveedor = new Proveedor();
        selectedProveedor.setCodigoPostal(0);
    }

    /**
     * actioListener que actualiza los cambios del usuario
     *
     * @param actionEvent objeto actionEvent
     * @return
     */
    public String createOrUpdateProveedor(ActionEvent actionEvent) {
        if (selectedProveedor.getId() == null) {
            selectedProveedor.setDateCreated(Calendar.getInstance().getTime());
            selectedProveedor.setLastUpdated(Calendar.getInstance().getTime());
            selectedProveedor.setEnabled(true);

            proveedorService.createProveedor(selectedProveedor);

            Utilidades.setMessage(FacesMessage.SEVERITY_INFO, "Proveedor creado",
                    "El Proveedor se ha creado correctamente.");
        } else {
            selectedProveedor.setLastUpdated(Calendar.getInstance().getTime());
            proveedorService.updateProveedor(selectedProveedor);
            Utilidades.setMessage(FacesMessage.SEVERITY_INFO, "Proveedor actualizado",
                    "El Proveedor se ha actualizado correctamente.");
        }
        reiniciarListaProveedors();
        return "proveedor?faces-redirect=true";
    }

    private void reiniciarListaProveedors() {
        proveedores = getProveedorService().queryAllProveedores();
    }

    /**
     *
     * @return título de edición
     */
    public String getEditTitle() {
        return ((selectedProveedor == null || selectedProveedor.getId() == null) ? "Nuevo proveedor" : "Editar proveedor: ");
    }

    /**
     * @return the selectedCategoria
     */
    public DualListModel<Categoria> getSelectedCategoria() {
        List<Categoria> allCategorias = new ArrayList<Categoria>(getCategorias());
        List<Categoria> proveedorCategorias;
        if (selectedProveedor.getCategoriaList() == null) {
            proveedorCategorias = new ArrayList<Categoria>(0);
        } else {
            proveedorCategorias = new ArrayList<Categoria>(selectedProveedor.getCategoriaList());
        }
        // Vamos quitando de la lista de todas las categor
//        for (Iterator<Categoria> it = proveedorCategorias.iterator(); it.hasNext();) {
//            Categoria cat = it.next();
//            if (cat != null && Role.AUTHORITY_USER.equals(role.getAuthority())) {
//                it.remove();
//                break;
//            }
//        }
        for (Iterator<Categoria> it = allCategorias.iterator(); it.hasNext();) {
            Categoria cat = it.next();
            if (cat != null && proveedorCategorias.contains(cat)) {
                it.remove();
            }
        }
        selectedCategoria = new DualListModel<Categoria>(allCategorias, proveedorCategorias);
        return selectedCategoria;
    }

    /**
     * @param selectedCategoria the selectedCategoria to set
     */
    public void setSelectedCategoria(DualListModel<Categoria> selectedCategoria) {
        this.selectedCategoria = selectedCategoria;
    }

    /**
     * Recupera todas las categorias
     *
     * @return
     */
    private List<Categoria> getCategorias() {
        return getMantenimientoService().queryAllCategorias();
    }

    /**
     * @return the mantenimientoService
     */
    public IMantenimientoService getMantenimientoService() {
        return mantenimientoService;
    }

    /**
     * @param mantenimientoService the mantenimientoService to set
     */
    public void setMantenimientoService(IMantenimientoService mantenimientoService) {
        this.mantenimientoService = mantenimientoService;
    }

}
