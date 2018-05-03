package com.excilys.formation.cdb.persistence;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.excilys.formation.cdb.model.User;

@Repository
public class UserDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	private final String selectUserByNameRequest = "SELECT user.id FROM " + User.class.getName() + " user WHERE user.name LIKE \'%s\'";


	public Optional<User> selectUser(String name) {
		 try (Session session = sessionFactory.openSession();){
	            TypedQuery<User> querry = session.createQuery(String.format(selectUserByNameRequest,name), User.class);
	            List<User> results = querry.getResultList();
	            if (results == null) {
	                return Optional.ofNullable(null);
	            } else if (results.isEmpty()){
	                return Optional.ofNullable(null);
	            }else {
	                return Optional.ofNullable(results.get(0));
	            }
	}
	}
}
