package es.matercero.spc.hibernate;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Parameter;

/**     
 *
 * @author mtercero
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    /**
     * * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "username", unique = true, nullable = false, length = 10)
    private String username;
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @Column(name = "enabled")
    private boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "rol_id")})
    private List<Role> roles;

    /**
     * Constructor por defecto
     */
    public User() {
    }

    /**
     *
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     *  
     *
     * @param id identificador
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     *
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     *
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     *
     * @return enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     *
     *
     * @param enabled enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     *
     *
     * @return roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     *
     *
     * @param roles roles
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getStringRoles() {
        final StringBuilder sb = new StringBuilder();
        boolean primero = true;
        if (roles != null && !roles.isEmpty()) {
            for (Role role : roles) {
                if (!Role.AUTHORITY_USER.equals(role.getAuthority())) {
                    if (primero) {
                        primero = false;
                    } else {
                        sb.append(", ");
                    }
                    sb.append(role.getRolname());
                }
            }
        }
        return sb.toString();
    }
}
