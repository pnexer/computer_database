package com.excilys.formation.cdb.persistence.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.TypedQuery;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.cdb.model.Company;


@Repository
public class CompanyDAO {

	private SessionFactory sessionFactory;

	private final String selectListRequest = "FROM " + Company.class.getName();
	private final String countRequest =  "SELECT COUNT(*) FROM " + Company.class.getName();
	private final String selectCompanyByIdRequest = "FROM " + Company.class.getName() + " company WHERE company.id = %d";
	private final String selectCompanyByNameRequest = "SELECT company.id FROM " + Company.class.getName() + " company WHERE company.name LIKE \'%s\'";

	@Autowired 
	public CompanyDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public Optional<Company> selectCompany(int id) {
		try (Session session = sessionFactory.openSession();){
			TypedQuery<Company> query = session.createQuery(String.format(selectCompanyByIdRequest,id), Company.class);
			List<Company> results = query.getResultList();
			if (results == null) {
				return Optional.ofNullable(null);
			} else if (results.isEmpty()){
				return Optional.ofNullable(null);
			}else {
				return Optional.ofNullable(results.get(0));
			}
		}   
	}

	public List<Company> selectCompanyList() {
		try(Session session = sessionFactory.openSession();){
			TypedQuery<Company> query = session.createQuery(selectListRequest,Company.class);
			return query.getResultList();
		}	}

	public int countCompany() {
		try (Session session = sessionFactory.openSession();){
			Query<Long> query = session.createQuery(countRequest,Long.class);
			return query.uniqueResult().intValue();
		}    }

	public List<Company> selectCompanySubList(int offset, int limit) {
		try(Session session = sessionFactory.openSession();){
			TypedQuery<Company> query = session.createQuery(selectListRequest,Company.class);
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			return query.getResultList();
		}    }

	public void deleteCompany(int id) {
		//todo
	}
}
