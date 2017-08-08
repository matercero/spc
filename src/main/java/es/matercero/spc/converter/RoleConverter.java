package es.matercero.spc.converter;

import es.matercero.spc.hibernate.Role;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Convertidos de Role a String y viceversa
 *
 * @author mtercero
 */
@FacesConverter(value = "roleConverter")
public class RoleConverter implements Converter, Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * A partir de la representación en String devuelve un objeto Role
     *
     * @param facesContext contexto JSF
     * @param uiComponent componente
     * @param value valor en String
     ** @return objeto Role
     */
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent,
            String value) {
        Role role = null;
        if (value != null) {
            String[] array = value.split(":");
            if (array.length == 3) {
                role = new Role(Integer.valueOf(array[0]));
                role.setAuthority(array[1]);
                role.setRolname(array[2]);
            }
        }
        return role;
    }

    /**
     * A partir de un objeto Role devuelve su representación en String
     *
     * @param facesContext contexto JSF
     * @param uiComponent componente
     * @param value objeto Role
     ** @return String
     */
    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent,
            Object value) {
        if (value instanceof Role) {
            Role role = (Role) value;
            return role.getId() + ":" + role.getAuthority() + ":" + role.getRolname();
        } else {
            return null;
        }
    }
}
