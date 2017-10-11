package com.emea.dao;

import com.emea.model.User;

/**
 * Interface to fetch user data.
 * 
 * @author hmolla
 *
 */
public interface UserDao {

    /**
     * Method to fetch user data.
     * 
     * @param username
     * @return
     */
    public User findByUsername(String username);

}
