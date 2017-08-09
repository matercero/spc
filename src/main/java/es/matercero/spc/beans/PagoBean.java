/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.matercero.spc.beans;

import es.matercero.spc.hibernate.Pago;
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
@Component("pagoBean")
@Scope("session")
public class PagoBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(PagoBean.class);

    @Autowired
    private IMantenimientoService pagoService;
    private List<Pago> pagos;
    private Pago selectedPago;

    private void reiniciarListaPagos() {
        pagos = getPagoService().queryAllPagos();
    }

    /**
     * prepara la creación
     *
     * @param actionEvent objeto actionEvent
     */
    public void newPago(ActionEvent actionEvent) {
        selectedPago = new Pago();

    }

    /**
     * @return the pagoService
     */
    public IMantenimientoService getPagoService() {
        return pagoService;
    }

    /**
     * @param pagoService the pagoService to set
     */
    public void setPagoService(IMantenimientoService pagoService) {
        this.pagoService = pagoService;
    }

    /**
     * @return the pagos
     */
    public List<Pago> getPagos() {
        if (pagos == null) {
            pagos = getPagoService().queryAllPagos();
        }
        return pagos;
    }

    /**
     * @param pagos the pagos to set
     */
    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    /**
     * @return the selectedPago
     */
    public Pago getSelectedPago() {
        return selectedPago;
    }

    /**
     * @param selectedPago the selectedPago to set
     */
    public void setSelectedPago(Pago selectedPago) {
        this.selectedPago = selectedPago;
    }

    /**
     *
     * @return título de edición
     */
    public String getEditTitle() {
        return ((selectedPago == null || selectedPago.getId() == null) ? "Nuevo pago" : "Editar pago");
    }

    /**
     * @param actionEvent objeto actionEvent
     * @return
     */
    public String createOrUpdatePago(ActionEvent actionEvent) {
        String vista = "pago";

        if (selectedPago.getId() == null) {
            selectedPago.setDateCreated(Calendar.getInstance().getTime());
            selectedPago.setLastUpdated(Calendar.getInstance().getTime());
            selectedPago.setEnabled(true);
            pagoService.createPago(selectedPago);
            Utilidades.setMessage(FacesMessage.SEVERITY_INFO, vista + "  creado",
                    "El " + vista + " se ha creado correctamente.");
        } else {
            selectedPago.setLastUpdated(Calendar.getInstance().getTime());
            pagoService.updatePago(selectedPago);
            Utilidades.setMessage(FacesMessage.SEVERITY_INFO, vista + " actualizado",
                    "El " + vista + " se ha actualizado correctamente.");
        }
        reiniciarListaPagos();
        return vista + "?faces-redirect=true";
    }
}
