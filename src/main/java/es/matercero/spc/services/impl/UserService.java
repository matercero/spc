package es.matercero.spc.services.impl;

import es.matercero.spc.daos.IDao;
import es.matercero.spc.hibernate.Role;
import es.matercero.spc.hibernate.User;
import es.matercero.spc.services.IUserService;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mtercero
 */
@Service("userService")
@Transactional
public class UserService implements IUserService, Serializable {

    /** 
     * * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(UserService.class);
    @Autowired
    private IDao<User> userDao;
    @Autowired
    private IDao<Role> roleDao;

    /**
     * Constructor por defecto
     */
    public UserService() {
    }

    /**
     *
     * @return the UserDao
     */
    public IDao<User> getUserDao() {
        return userDao;
    }

    /**
     *
     * @param userDao the UserDao to set
     */
    public void setUserDao(IDao<User> userDao) {
        this.userDao = userDao;
    }

    /**
     *
     * @return dao
     */
    public IDao<Role> getRoleDao() {
        return roleDao;
    }

    /**
     *
     * @param roleDao roleDao
     */
    public void setRoleDao(IDao<Role> roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public User getUserLogado() {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        org.springframework.security.core.userdetails.User username = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        User u = queryUserByUsername(username.getUsername());
        return u;
    }

  
    @Override
    @Transactional(readOnly = true)
    public List<User> queryAllUsers() {
        DetachedCriteria dc = DetachedCriteria.forClass(User.class, "user");
        dc.addOrder(Order.asc("username"));
        dc.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return getUserDao().find(dc);
    }

    @Override
    @Transactional(readOnly = true)
    public User queryUserByUsername(String username) {
        DetachedCriteria dc = DetachedCriteria.forClass(User.class, "user");
        dc.add(Restrictions.ilike("username", username));
        return getUserDao().find(dc).get(0);
    }

    @Override
    public List<User> queryUsersInRole(Role role) {
        DetachedCriteria dc = DetachedCriteria.forClass(User.class, "user");
        dc.createAlias("user.roles", "role");
        dc.add(Restrictions.eq("role.id", role.getId()));
        dc.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return getUserDao().find(dc);
    }

    @Override
    public UpdateResult updateUser(User user, boolean digestPassword) {
        UpdateResult result = null;
        if (digestPassword && user.getPassword() != null) {
            user.setPassword(sha1(user.getPassword()));
        }
        // Rol por defecto
        Role userRole = queryAuthorityRole(Role.AUTHORITY_USER);
        user.getRoles().add(userRole);
        if (user.getId() == null) {
            IDao<User> localUserDao = getUserDao();
            DetachedCriteria dc = DetachedCriteria.forClass(User.class, "user");
            dc.add(Restrictions.eq("user.username", user.getUsername()));
            dc.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            List<User> find = localUserDao.find(dc, 1);
            if (find == null || find.isEmpty()) {
                localUserDao.save(user);
            } else {
                result = UpdateResult.EXISTING_USERNAME;
            }
        } else {
            getUserDao().update(user);
        }
        return result;
    }

    @Override
    public void deleteUser(User user) {
        getUserDao().delete(user);
    }

    @Override
    public List<Role> queryAllRoles() {
        DetachedCriteria dc = DetachedCriteria.forClass(Role.class, "role");
        dc.add(Restrictions.ne("role.authority", Role.AUTHORITY_USER));
        return getRoleDao().find(dc);
    }

    @Override
    public Role queryAuthorityRole(String authority) {
        Role role = null;
        DetachedCriteria dc = DetachedCriteria.forClass(Role.class, "role");
        dc.add(Restrictions.eq("role.authority", authority));
        List<Role> lst = getRoleDao().find(dc);
        if (lst != null && !lst.isEmpty()) {
            role = lst.get(0);
        }
        return role;
    }

    /**
     * Aplica el algoritmo sha1
     *
     * @param input String de entrada
     * @return String con el sha1 aplicado
     */
    private static String sha1(String input) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA1");
            byte[] result = mDigest.digest(input.getBytes());
            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).
                        substring(1));
            }
        } catch (NoSuchAlgorithmException ex) {
            LOG.error("Error creating sha1", ex);
        }
        return sb.toString();
    }
}
