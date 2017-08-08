package es.matercero.spc.beans;

import es.matercero.spc.hibernate.Role;
import es.matercero.spc.hibernate.User;
import es.matercero.spc.services.IUserService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Bean que actúa como enlace entre la lógica de negocio y la vista para las
 * pantallas de usuarios
 *
 * @author mtercero
 */
@Component("userBean")
@Scope("session")
public class UserBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(UserBean.class);

    @Autowired
    private IUserService userService;
    private List<User> users;
    private List<Role> roles;
    private User selectedUser;
    private boolean editPassword;
    private DualListModel<Role> selectedUserRoles;
    private User loginUser;
    private boolean rolAdministrador;
    private boolean rolTecnico;
    private boolean rolConsultor;

    /**
     * Constructor por defecto
     */
    public UserBean() {
    }

    /**
     *
     *
     * @return UserService
     */
    public IUserService getUserService() {
        return userService;
    }

    /**
     *
     * @param userService userService
     */
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    /**
     * saber si el usuario seleccionado es administrador
     *
     * @return true si es admin
     */
    public boolean isAdminUser() {
        return isAuthority(Role.AUTHORITY_ADMIN);
    }

    /**
     * saber si el usuario seleccionado es tecnico
     *
     * @return true si es Tecnico
     */
    public boolean isTecnico() {
        return isAuthority(Role.AUTHORITY_ADMIN, Role.AUTHORITY_TECNICO);
    }

    /**
     * saber si el usuario seleccionado es médico El codificador está por encima
     * del médico también
     *
     * @return true si es consultor
     */
    public boolean isConsulta() {
        return isAuthority(Role.AUTHORITY_ADMIN, Role.AUTHORITY_TECNICO, Role.AUTHORITY_CONSULTA);
    }

    private boolean isAuthority(String... authoritiesArray) {
        boolean isAuthority = false;
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        if (auth != null) {
            String username = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();
            User userLogado = getUserService().queryUserByUsername(username);
            setLoginUser(userLogado);
            Collection authorities = auth.getAuthorities();
            if (authorities != null) {
                for (Iterator it = authorities.iterator(); it.hasNext();) {
                    GrantedAuthority authority = (GrantedAuthority) it.next();
                    for (String authorityString : authoritiesArray) {
                        if (authorityString.equals(authority.getAuthority())) {
                            isAuthority = true;
                            break;
                        }
                    }
                    if (isAuthority) {
                        break;
                    }
                }
            }
        }
        return isAuthority;
    }

    /**
     *
     *
     * @return lista de usuarios
     */
    public List<User> getUsers() {
        if (users == null) {
            users = getUserService().queryAllUsers();
        }
        return users;
    }

    /**
     *
     * @param users users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     *
     *
     * @return lista de roles
     */
    public List<Role> getRoles() {
        if (roles == null) {
            roles = getUserService().queryAllRoles();
        }
        return roles;
    }

    /**
     *
     * @param roles roles
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     *
     *
     * @return usuario seleccionado
     */
    public User getSelectedUser() {
        return selectedUser;
    }

    /**
     *
     * @param selectedUser selectedUser
     */
    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
        setEditPassword(false);
        setSelectedUserRoles(null);
    }

    /**
     *
     *
     * @return true si está editando el password
     */
    public boolean isEditPassword() {
        return editPassword;
    }

    /**
     *
     * @param editPassword editPassword
     */
    public void setEditPassword(boolean editPassword) {
        this.editPassword = editPassword;
    }

    /**
     * Devuelve la lista de roles del usuario
     *
     * @return lista DualListModel para usar en el componente JSF
     */
    public DualListModel<Role> getSelectedUserRoles() {
        List<Role> allRoles = new ArrayList<Role>(getRoles());
        List<Role> userRoles;
        if (selectedUser.getRoles() == null) {
            userRoles = new ArrayList<Role>(0);
        } else {
            userRoles = new ArrayList<Role>(selectedUser.getRoles());
        }
        for (Iterator<Role> it = userRoles.iterator(); it.hasNext();) {
            Role role = it.next();
            if (role != null && Role.AUTHORITY_USER.equals(role.getAuthority())) {
                it.remove();
                break;
            }
        }
        for (Iterator<Role> it = allRoles.iterator(); it.hasNext();) {
            Role role = it.next();
            if (role != null && userRoles.contains(role)) {
                it.remove();
            }
        }
        selectedUserRoles = new DualListModel<Role>(allRoles, userRoles);
        return selectedUserRoles;
    }

    /**
     *
     * @param selectedUserRoles selectedUserRoles
     */
    public void setSelectedUserRoles(DualListModel<Role> selectedUserRoles) {
        this.selectedUserRoles = selectedUserRoles;
    }

    /**
     *
     * @return título de edición
     */
    public String getEditUserTitle() {
        if (selectedUser == null || selectedUser.getId() == null) {
            return "Nuevo usuario";
        } else {
            return "Editar usuario";
        }
    }

    /**
     * actioListener que actualiza los cambios del usuario
     *
     * @param actionEvent objeto actionEvent
     */
    public void updateSelectedUser(ActionEvent actionEvent) {
        if (selectedUser != null) {
            if (selectedUserRoles != null
                    && selectedUserRoles.getTarget() != null) {
                selectedUser.setRoles(selectedUserRoles.getTarget());
            }
            FacesMessage message;
            if (selectedUser.getRoles() == null || selectedUser.getRoles().
                    isEmpty()) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error",
                        "Error: Debe asignar al menos un rol al usuario");
            } else {
                boolean newUser = selectedUser.getId() == null;
                IUserService.UpdateResult result = getUserService()
                        .updateUser(selectedUser, editPassword);
                if (result == null) {
                    if (newUser) {
                        message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Usuario creado",
                                "El usuario se ha creado correctamente");
                    } else {
                        message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Usuario actualizado",
                                "El usuario ha sido actualizado correctamente");
                    }
                    setUsers(null);
                } else if (result == IUserService.UpdateResult.EXISTING_USERNAME) {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error",
                            "Error: El usuario ya existe");
                } else if (newUser) {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "Error: No se pudo crear el usuario");
                } else {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error",
                            "Error: No se pudo actualizar el usuario");
                }
            }
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    /**
     * prepara la creación de nuevo usuario
     *
     * @param actionEvent objeto actionEvent
     */
    public void newUser(ActionEvent actionEvent) {
        selectedUser = new User();
        selectedUser.setEnabled(true);
        setEditPassword(true);
    }

    /**
     * @return the loginUser
     */
    public User getLoginUser() {
        return loginUser;
    }

    /**
     * @param loginUser the loginUser to set
     */
    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    @PostConstruct
    public void init() {
        LOG.info("Init userBean");
        setRolAdministrador(isAdminUser());
        if (isRolAdministrador()) {
            setRolTecnico(true);
            setRolConsultor(true);
        } else {
            setRolTecnico(isTecnico());
            if (isRolTecnico()) {
                setRolConsultor(true);
            } else {
                setRolConsultor(isConsulta());
            }
        }
    }

    /**
     * @return the rolAdministrador
     */
    public boolean isRolAdministrador() {
        return rolAdministrador;
    }

    /**
     * @param rolAdministrador the rolAdministrador to set
     */
    public void setRolAdministrador(boolean rolAdministrador) {
        this.rolAdministrador = rolAdministrador;
    }

    /**
     * @return the rolTecnico
     */
    public boolean isRolTecnico() {
        return rolTecnico;
    }

    /**
     * @param rolTecnico the rolTecnico to set
     */
    public void setRolTecnico(boolean rolTecnico) {
        this.rolTecnico = rolTecnico;
    }

    /**
     * @return the rolConsultor
     */
    public boolean isRolConsultor() {
        return rolConsultor;
    }

    /**
     * @param rolConsultor the rolConsultor to set
     */
    public void setRolConsultor(boolean rolConsultor) {
        this.rolConsultor = rolConsultor;
    }
}
