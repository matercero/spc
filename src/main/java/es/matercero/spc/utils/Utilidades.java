/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.matercero.spc.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author mangel.tercero
 */
public class Utilidades {
    
      public static void setMessage(FacesMessage.Severity severity, String messageSumario, String messageDetalle) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(severity, messageSumario, messageDetalle));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }
    
}
