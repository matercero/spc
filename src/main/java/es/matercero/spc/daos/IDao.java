package es.matercero.spc.daos;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * Interfaz de los dao
 *
 * @author mtercero
 * @param <T> tipo del dao
 */
public interface IDao<T> {

    /**
     * Crear una nueva entidad
     *
     * @param entity entidad
     */
    void save(T entity);

    /**
     * Actualizar una entidad
     *
     * @param entity entidad
     */
    void update(T entity);

    /**
     * Eliminar una entidad
     *
     * @param entity entidad
     */
    void delete(T entity);

   
    /**
     * Obtener la lista de entidades que enajan con un criterio
     *
     * @param criteria criterio
     * @return lista de entidades
     */
    List<T> find(DetachedCriteria criteria);

    /**
     * Obtener la lista de entidades que enajan con un criterio
     *
     * @param criteria criterio
     * @param numResults número máximo de resultados
     * @return lista de entidades
     */
    List<T> find(DetachedCriteria criteria, int numResults);
}
