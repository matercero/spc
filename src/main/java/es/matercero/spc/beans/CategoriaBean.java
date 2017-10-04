/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.matercero.spc.beans;

import es.matercero.spc.hibernate.Categoria;
import es.matercero.spc.services.IMantenimientoService;
import es.matercero.spc.utils.Utilidades;
import java.io.Serializable;
import java.text.SimpleDateFormat;
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
@Component("categoriaBean")
@Scope("session")
public class CategoriaBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(CategoriaBean.class);

    @Autowired
    private IMantenimientoService categoriaService;
    private List<Categoria> categorias;
    private Categoria selectedCategoria;

    /**
     * @return the categoriaService
     */
    public IMantenimientoService getCategoriaService() {
        return categoriaService;
    }

    /**
     * @param categoriaService the categoriaService to set
     */
    public void setCategoriaService(IMantenimientoService categoriaService) {
        this.categoriaService = categoriaService;
    }

    /**
     *
     * @return título de edición
     */
    public String getEditTitle() {
        return ((selectedCategoria == null || selectedCategoria.getId() == null) ? "Nuevo categoria" : "Editar categoria");
    }

    /**
     * @return the categorias
     */
    public List<Categoria> getCategorias() {
        if (categorias == null) {
            categorias = getCategoriaService().queryAllCategorias();
        }
        return categorias;
    }

    /**
     * @param categorias the categorias to set
     */
    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    /**
     * @return the selectedCategoria
     */
    public Categoria getSelectedCategoria() {
        return selectedCategoria;
    }

    /**
     * @param selectedCategoria the selectedCategoria to set
     */
    public void setSelectedCategoria(Categoria selectedCategoria) {
        this.selectedCategoria = selectedCategoria;
    }

    private void reiniciarListaCategorias() {
        categorias = getCategoriaService().queryAllCategorias();
    }

    /**
     * prepara la creación
     *
     * @param actionEvent objeto actionEvent
     */
    public void newCategoria(ActionEvent actionEvent) {
        selectedCategoria = new Categoria();

    }

    /**
     * actioListener que actualiza los cambios del usuario
     *
     * @param actionEvent objeto actionEvent
     * @return
     */
    public String createOrUpdateCategoria(ActionEvent actionEvent) {

        if (selectedCategoria.getId() == null) {
            selectedCategoria.setDateCreated(Calendar.getInstance().getTime());
            selectedCategoria.setLastUpdated(Calendar.getInstance().getTime());
            selectedCategoria.setEnabled(true);
            categoriaService.createCategoria(selectedCategoria);
            Utilidades.setMessage(FacesMessage.SEVERITY_INFO, "Categoria creado",
                    "La Categoria se ha creado correctamente.");
        } else {
            selectedCategoria.setLastUpdated(Calendar.getInstance().getTime());
            categoriaService.updateCategoria(selectedCategoria);
            Utilidades.setMessage(FacesMessage.SEVERITY_INFO, "Categoria actualizado",
                    "La Categoria se ha actualizado correctamente.");
        }
        reiniciarListaCategorias();
        return "categoria?faces-redirect=true";
    }

}
