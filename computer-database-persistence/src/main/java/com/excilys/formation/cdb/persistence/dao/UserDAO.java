package com.excilys.formation.cdb.persistence.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.User;


@Repository
public class UserDAO {
    
	private final String selectUserByNameRequest = "SELECT user.id FROM " + User.class.getName() + " user WHERE user.name LIKE \'%s\'";

	
    private SessionFactory sessionFactory;

    
    @Autowired 
    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<User> selectUser(String name) {
    	try (Session session = sessionFactory.openSession();){
			TypedQuery<User> query = session.createQuery(String.format(selectUserByNameRequest,name), User.class);
			List<User> results = query.getResultList();
			if (results == null) {
				return Optional.ofNullable(null);
			} else if (results.isEmpty()){
				return Optional.ofNullable(null);
			}else {
				return Optional.ofNullable(results.get(0));
			}
		}     }
    
    public Optional<User> selectUserTest(String name) {
   
				return Optional.ofNullable(new User(1,"name", "pw", "ADMIN"));
			}
	
}
