package es.matercero.spc.services;

import es.matercero.spc.hibernate.Role;
import es.matercero.spc.hibernate.User;

import java.util.List;

/**
 * Interfacz del servicio con los métodos para el usuario
 */
/**
 *
 * @author mtercero
 */
public interface IUserService {

    /**
     * Enumerado que indica el resultado de la actualización
     */
    public static enum UpdateResult {
        EXISTING_USERNAME,
        UNKNOWN_ERROR;
    }



    /**
     * Devuelve la lista de todos los usuarios ordenados por nombre de usuario
     *
     * @return lista de objetos User
     */
    List<User> queryAllUsers();

    /**
     * Devuelve un objeto usuario buscado por username
     *
     * @param username
     * @return Objeto User
     */
    User queryUserByUsername(String username);

    /**
     * Devuelve la lista de usuarios pertenecientes a un rol concreto
     *
     * @param role rol
     * @return lista de objetos User
     */
    List<User> queryUsersInRole(Role role);

    /**
     * Actualiza o crea el usuario en la base de datos
     *
     * @param user usuario a actualizar
     * @param digestPassword si al password se le debe aplicar sha1
     * @return resultado de la actualización
     */
    UpdateResult updateUser(User user, boolean digestPassword);

    /**
     * Elimina a un usuario
     *
     * @param user usuario
     */
    void deleteUser(User user);

    /**
     * Devuelve la lista de los roles
     *
     * @return lista de objetos Role
     */
    List<Role> queryAllRoles();

    /**
     * Devuelve el rol identificado por la autoridad
     *
     * @param authority autoridad
     * @return objeto Role
     */
    Role queryAuthorityRole(String authority);

    /**
     * Devuelve el usuario que está logado en ese momento
     *
     * @return objeto User
     */
    User getUserLogado();

   
}
