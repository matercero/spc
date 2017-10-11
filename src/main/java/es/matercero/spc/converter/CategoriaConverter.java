/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.matercero.spc.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import es.matercero.spc.hibernate.Categoria;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.log4j.Logger;

/**
 *
 * @author mangel.tercero
 */
@FacesConverter(value = "categoriaConverter")
public class CategoriaConverter implements Converter, Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(CategoriaConverter.class);
    private final Gson gson;

    public CategoriaConverter() {
         gson = new Gson();
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Categoria categoria = null;
        try {
            if (value != null && !value.equalsIgnoreCase("Seleccionar")) {
                categoria = gson.fromJson(value, Categoria.class);
            }
        } catch (JsonSyntaxException e) {
            LOG.error("Error en fromJson", e);
        }
        return categoria;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        String output = null;
        if (o instanceof Categoria) {
            output = gson.toJson(o);
        }
        return output;
    }

}
