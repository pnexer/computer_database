package com.excilys.formation.cdb.persistence;


import java.util.List;
import java.util.Optional;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.excilys.formation.cdb.model.Computer;

@Repository
public class ComputerDAO {

	@Autowired
	SessionFactory sessionFactory;

	private final String selectListRequest = "FROM" + Computer.class.getName();
	private final String countRequest =  "SELECT COUNT(*) FROM " + Computer.class.getName();
	private final String LIKE = " computer WHERE computer IN ((FROM computer WHERE computer.name LIKE \'%%%s%%\'),(FROM computer WHERE computer.company.name LIKE \'%%%s%%\'))";
	public int countAllComputer() {  
		try (Session session = sessionFactory.openSession();){
			Query<Long> querry = session.createQuery(countRequest,Long.class);
			return querry.uniqueResult().intValue();
		}
	}

	public List<Computer> listComputer() {
		try (Session session = sessionFactory.openSession();){
			TypedQuery<Computer> querry = session.createQuery(selectListRequest,Computer.class);
			return querry.getResultList();
		}
	}

	public int createComputer(Computer computer) {
		try (Session session = sessionFactory.openSession();){
			return (int)session.save(computer);
		}    	
	}

	public void updateComputer(Computer computer,int id) {
		try (Session session = sessionFactory.openSession();){
			computer.setId(id);
			session.getTransaction().begin();
			session.update(computer);
			session.getTransaction().commit();
		}
	}

	public void deleteComputer(int id) {
		try (Session session = sessionFactory.openSession();){
			Optional<Computer> computer = selectComputer(id);
			if (computer.isPresent()) {
				session.getTransaction().begin();
				session.delete(computer.get());
				session.getTransaction().commit();
			}

		}    }

	public Optional<Computer> selectComputer(int id) {
		try (Session session = sessionFactory.openSession();){
			TypedQuery<Computer> querry = session.createQuery(String.format(selectListRequest,id), Computer.class);
			List<Computer> results = querry.getResultList();
			if (results == null) {
				return Optional.ofNullable(null);
			} else if (results.isEmpty()){
				return Optional.ofNullable(null);
			}else {
				return Optional.ofNullable(results.get(0));
			}
		}
	}

	public List<Computer> subList(int offset, int limit,String keyword) {
		try (Session session = sessionFactory.openSession();){
			TypedQuery<Computer> querry = session.createQuery(selectListRequest + String.format(LIKE, keyword, keyword), Computer.class);
			querry.setMaxResults(limit);
			querry.setFirstResult(offset);
			return querry.getResultList();
		}
	}


}