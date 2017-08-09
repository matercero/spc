/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.matercero.spc.beans;

import es.matercero.spc.hibernate.Componente;
import es.matercero.spc.services.IMantenimientoService;
import es.matercero.spc.utils.Utilidades;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
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
@Component("componenteBean")
@Scope("session")
public class ComponenteBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ComponenteBean.class);

    @Autowired
    private IMantenimientoService componenteService;
    private List<Componente> componentes;
    private Componente selectedComponente;

    /**
     * @return the componenteService
     */
    public IMantenimientoService getComponenteService() {
        return componenteService;
    }

    /**
     * @param componenteService the componenteService to set
     */
    public void setComponenteService(IMantenimientoService componenteService) {
        this.componenteService = componenteService;
    }

    /**
     * @return the componentes
     */
    public List<Componente> getComponentes() {
        if (componentes == null) {
            componentes = getComponenteService().queryAllComponentes();
        }
        return componentes;
    }

    /**
     * @param componentes the componentes to set
     */
    public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
    }

    /**
     * @return the selectedComponente
     */
    public Componente getSelectedComponente() {
        return selectedComponente;
    }

    /**
     * @param selectedComponente the selectedComponente to set
     */
    public void setSelectedComponente(Componente selectedComponente) {
        this.selectedComponente = selectedComponente;
    }

    private void reiniciarListaComponentes() {
        componentes = getComponenteService().queryAllComponentes();
    }

    /**
     * prepara la creación
     *
     * @param actionEvent objeto actionEvent
     */
    public void newComponente(ActionEvent actionEvent) {
        selectedComponente = new Componente();
    }

    /**
     * @param actionEvent objeto actionEvent
     * @return
     */
    public String createOrUpdateComponente(ActionEvent actionEvent) {
        String vista = "componente";

        if (selectedComponente.getId() == null) {
            selectedComponente.setDateCreated(Calendar.getInstance().getTime());
            selectedComponente.setLastUpdated(Calendar.getInstance().getTime());
            selectedComponente.setEnabled(true);
            componenteService.createComponente(selectedComponente);
            Utilidades.setMessage(FacesMessage.SEVERITY_INFO, vista + "  creado",
                    "El " + vista + " se ha creado correctamente.");
        } else {
            selectedComponente.setLastUpdated(Calendar.getInstance().getTime());
            componenteService.updateComponente(selectedComponente);
            Utilidades.setMessage(FacesMessage.SEVERITY_INFO, vista + " actualizado",
                    "El " + vista + " se ha actualizado correctamente.");
        }
        reiniciarListaComponentes();
        return vista + "?faces-redirect=true";
    }
    
     /**
     *
     * @return título de edición
     */
    public String getEditTitle() {
        return ((selectedComponente == null || selectedComponente.getId() == null) ? "Nuevo componente" : "Editar componente");
    }

}
