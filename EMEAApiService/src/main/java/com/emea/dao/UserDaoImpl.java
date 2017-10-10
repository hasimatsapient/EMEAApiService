package com.emea.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.emea.model.User;

/**
 * Class to fetch user information.
 * 
 * @author hmolla
 *
 */
@Component
public class UserDaoImpl implements UserDao {
    private static final Logger LOG = Logger.getLogger(AccountInfoDao.class);
    private static final String USERNAME = "username";
    @PersistenceContext
    private EntityManager entityManager;
    /**
     * Method to find user by name (non-Javadoc)
     * 
     * @see com.emea.dao.UserDao#findByUsername(java.lang.String)
     */
    @Override
    public User findByUsername(String username) {
        User result = null;
        LOG.info("Started executing findByUsername");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> user = cq.from(User.class);
        cq.select(user);
        cq.where(cb.equal(user.get(USERNAME), username));
        TypedQuery<User> q = entityManager.createQuery(cq);
        List<User> allUsers = q.getResultList();

        if (allUsers != null && allUsers.size() > 0) {
            result = allUsers.get(0);
        }
        LOG.info("Finished executing findByUsername");
        return result;
    }

}
