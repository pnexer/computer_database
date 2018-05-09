package com.excilys.formation.cdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.excilys.formation.cdb.model.User;
import com.excilys.formation.cdb.persistence.dao.UserDAO;

@Service
public class UserService implements UserDetailsService {

    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User loadUserByUsername(String name) throws UsernameNotFoundException {
        if(userDAO.selectUserTest(name).isPresent()) {
            return userDAO.selectUserTest(name).get();
        } else {
            throw new UsernameNotFoundException("Username not found !");
        }
    }
}
