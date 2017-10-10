/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.matercero.spc.beans;

import es.matercero.spc.hibernate.Componente;
import es.matercero.spc.hibernate.Seguimiento;
import es.matercero.spc.hibernate.SeguimientoComponente;
import es.matercero.spc.services.IMantenimientoService;
import es.matercero.spc.utils.Utilidades;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
@Component("seguimientoBean")
@Scope("session")
public class SeguimientoBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(SeguimientoBean.class);

    @Autowired
    private IMantenimientoService seguimientoService;
    private List<Seguimiento> seguimientos;
    private Seguimiento selectedSeguimiento;

    /**
     * @return the seguimientoService
     */
    public IMantenimientoService getSeguimientoService() {
        return seguimientoService;
    }

    /**
     * @param seguimientoService the seguimientoService to set
     */
    public void setSeguimientoService(IMantenimientoService seguimientoService) {
        this.seguimientoService = seguimientoService;
    }

    /**
     * @return the seguimientos
     */
    public List<Seguimiento> getSeguimientos() {
        return seguimientos;
    }

    /**
     * @param seguimientos the seguimientos to set
     */
    public void setSeguimientos(List<Seguimiento> seguimientos) {
        this.seguimientos = seguimientos;
    }

    /**
     * @return the selectedSeguimiento
     */
    public Seguimiento getSelectedSeguimiento() {
        return selectedSeguimiento;
    }

    /**
     * @param selectedSeguimiento the selectedSeguimiento to set
     */
    public void setSelectedSeguimiento(Seguimiento selectedSeguimiento) {
        this.selectedSeguimiento = selectedSeguimiento;
    }

    public String createOrUpdateSeguimiento(ActionEvent actionEvent) {
        String vista = " seguimiento ";
        if (selectedSeguimiento.getId() == null) {
            selectedSeguimiento.setDateCreated(Calendar.getInstance().getTime());
            selectedSeguimiento.setLastUpdated(Calendar.getInstance().getTime());
            selectedSeguimiento.setEnabled(true);

            seguimientoService.createSeguimiento(selectedSeguimiento);

            Utilidades.setMessage(FacesMessage.SEVERITY_INFO, vista + " creado",
                    "El " + vista + " se ha creado correctamente.");
        } else {
            selectedSeguimiento.setLastUpdated(Calendar.getInstance().getTime());
            seguimientoService.updateSeguimiento(selectedSeguimiento);
            Utilidades.setMessage(FacesMessage.SEVERITY_INFO, vista + " actualizado",
                    "El " + vista + " se ha actualizado correctamente.");
        }
        reiniciarListaProveedors();
        return "seguimiento?faces-redirect=true";
    }

    private void reiniciarListaProveedors() {
        seguimientos = getSeguimientoService().queryAllSeguimientos();
    }

    /**
     *
     * @return título de edición
     */
    public String getEditTitle() {
        return ((selectedSeguimiento == null || selectedSeguimiento.getId() == null) ? 
                "Nuevo seguimiento" : "Editar seguimiento: ");
    }

    public void newSeguimiento(ActionEvent actionEvent) {
        selectedSeguimiento = new Seguimiento();
        //Cjto de componentes para el seguimiento
        Set<SeguimientoComponente> seguimientoComponente = new HashSet<SeguimientoComponente>();

        // Componentes por defecto
        List<Componente> componentesPorDefecto = seguimientoService.queryAllComponentesPorDefecto();
        for (Componente componente : componentesPorDefecto) {
            SeguimientoComponente e = new SeguimientoComponente();
            e.setComponente(componente);
            e.setSeguimiento(selectedSeguimiento);
            e.setCantidad(0);
            seguimientoComponente.add(e);
        }
        selectedSeguimiento.setSeguimientoComponenteSet(seguimientoComponente);
    }

    public void deleteCliente(ActionEvent actionEvent) {
        selectedSeguimiento.setEnabled(false);
        seguimientoService.createSeguimiento(selectedSeguimiento);
    }

}
