package com.excilys.formation.cdb.persistence;

import java.util.List;
import java.util.Optional;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.excilys.formation.cdb.model.Company;

@Repository
public class CompanyDAO {

	@Autowired
	SessionFactory sessionFactory;

	private final String selectListRequest = "FROM " + Company.class.getName();
	private final String countRequest =  "SELECT COUNT(*) FROM " + Company.class.getName();
	private final String selectCompanyByIdRequest = "FROM " + Company.class.getName() + " company WHERE company.id = %d";
	private final String selectCompanyByNameRequest = "SELECT company.id FROM " + Company.class.getName() + " company WHERE company.name LIKE \'%s\'";

	public int countCompany() {
		 try (Session session = sessionFactory.openSession();){
	            Query<Long> querry = session.createQuery(countRequest,Long.class);
	            return querry.uniqueResult().intValue();
	}
	}

	public List<Company> list() {
		try(Session session = sessionFactory.openSession();){
			TypedQuery<Company> querry = session.createQuery(selectListRequest,Company.class);
			return querry.getResultList();
		}
	}

	public Optional<Company> selectCompany(int id) {
		  try (Session session = sessionFactory.openSession();){
	            TypedQuery<Company> querry = session.createQuery(String.format(selectCompanyByIdRequest,id), Company.class);
	            List<Company> results = querry.getResultList();
	            if (results == null) {
	                return Optional.ofNullable(null);
	            } else if (results.isEmpty()){
	                return Optional.ofNullable(null);
	            }else {
	                return Optional.ofNullable(results.get(0));
	            }
	}
	}

	public List<Company> subList(int offset, int limit) {
		try(Session session = sessionFactory.openSession();){
            TypedQuery<Company> querry = session.createQuery(selectListRequest,Company.class);
            querry.setFirstResult(offset);
            querry.setMaxResults(limit);
            return querry.getResultList();
}
	}

	

}