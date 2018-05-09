package com.excilys.formation.cdb.persistence.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.hibernate.query.Query;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;


@Repository
public class ComputerDAO {

	private SessionFactory sessionFactory;

	private final String selectListRequest = "FROM" + Computer.class.getName();
	private final String countRequest =  "SELECT COUNT(*) FROM " + Computer.class.getName();
	private final String LIKE = " computer WHERE computer IN ((FROM computer WHERE computer.name LIKE \'%%%s%%\'),(FROM computer WHERE computer.company.name LIKE \'%%%s%%\'))";



	@Autowired 
	public ComputerDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}

	public List<Computer> selectComputerList() {
		try (Session session = sessionFactory.openSession();){
			TypedQuery<Computer> querry = session.createQuery(selectListRequest,Computer.class);
			return querry.getResultList();
		}	}

	public void createComputer(Computer computer) {
		Session session = this.sessionFactory.openSession();
		session.save(computer);
		session.flush();
	}

	public void updateComputer(Computer computer) {
		Session session = this.sessionFactory.openSession();
		session.update(computer);
		session.flush();
	}

	public void deleteComputer(int... ids) {
		//todo
	}

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
		}    }

	public List<Integer> getComputersWithCompanyId(int id) {
		//todo
		return new ArrayList<Integer>();
	}

	public int countComputer(String keywords) {
		try (Session session = sessionFactory.openSession();){
			Query<Long> querry = session.createQuery(countRequest,Long.class);
			return querry.uniqueResult().intValue();
		}    }

	public List<Computer> selectComputerSubList(int offset, int limit, String keywords, String sortBy, boolean asc) {
		return new ArrayList<Computer>();
	}


}
