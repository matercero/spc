package es.matercero.spc.hibernate;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mtercero
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

    /**
     * * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * AUTHORITY USER
     */
    public static final String AUTHORITY_USER = "ROLE_USER";
    /**
     * AUTHORITY ADMIN  
     */
    public static final String AUTHORITY_ADMIN = "ROLE_ADMIN";
    /**
     * AUTHORITY COORD
     */
    public static final String AUTHORITY_TECNICO = "ROLE_TECNICO";
    /**
     * AUTHORITY CONSULTA
     */
    public static final String AUTHORITY_CONSULTA = "ROLE_CONSULTA";
   
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "authority", unique = true, nullable = false, length = 50)
    private String authority;
    @Column(name = "rolname", length = 50)
    private String rolname;

    /**
     * Constructor por defecto
     */
    public Role() {
    }

    /**
     *
     *
     * @param id identificador
     */
    public Role(Integer id) {
        this.id = id;
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
     * @return authority
     */
    public String getAuthority() {
        return authority;
    }

    /**
     *
     *
     * @param authority authority
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

   
    @Override
    public String toString() {
        return rolname == null ? "Todos" : rolname;
    }

    @Override
    public int hashCode() {
        return 7;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Role other = (Role) obj;
        if (this.id != other.id
                && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * @return the rolname
     */
    public String getRolname() {
        return rolname;
    }

    /**
     * @param rolname the rolname to set
     */
    public void setRolname(String rolname) {
        this.rolname = rolname;
    }
}
