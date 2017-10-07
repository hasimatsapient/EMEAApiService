package com.emea.service;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.emea.dao.UserDao;
import com.emea.exception.UserNotActivatedException;
import com.emea.model.Authority;
import com.emea.model.User;


/**
 * Class to validate authorized user.
 * @author hmolla
 *
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private UserDao userDao;

    /**
     * Method to fetch user data based on user name
     * (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String userName) {

        log.debug("Authenticating {}", userName);

        User userFromDatabase;
        userFromDatabase = userDao.findByUsername(userName);

        if (userFromDatabase == null) {
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        } else if (!userFromDatabase.isActivated()) {
            throw new UserNotActivatedException("User " + userName + " is not activated");
        }

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : userFromDatabase.getAuthorities()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
            grantedAuthorities.add(grantedAuthority);
        }

        return new org.springframework.security.core.userdetails.User(userFromDatabase.getUsername(), userFromDatabase.getPassword(), grantedAuthorities);

    }

}
