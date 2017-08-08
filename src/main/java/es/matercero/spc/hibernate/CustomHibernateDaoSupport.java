package es.matercero.spc.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author mtercero
 */
public class CustomHibernateDaoSupport {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Constructor por defecto
     */
    public CustomHibernateDaoSupport() {
    }

    /**
     * Método setter
     *
     * @param sessionFactory sessionFactory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     *
     * Método getter
     *
     * @return sessionFactory
     */
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
