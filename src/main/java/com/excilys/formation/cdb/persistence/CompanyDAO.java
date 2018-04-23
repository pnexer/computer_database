package com.excilys.formation.cdb.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.model.Company;

@Repository
public class CompanyDAO {
	
	
	private CompanyMapper companyMapper;	
	private JdbcTemplate jdbcTemplate;
	
	 @Autowired
     public CompanyDAO(DataSource dataSource, CompanyMapper companyMapper) {
	     this.jdbcTemplate = new JdbcTemplate(dataSource);
	     this.companyMapper = companyMapper;
	 }
   
    private String selectListRequest = "SELECT ca.id as caId, ca.name as caName FROM company ca";
    private String countRequest = "SELECT count(id) FROM company;";
    private String selectSublistRequest = "SELECT ca.id as caId, ca.name as caName FROM company ca LIMIT ? OFFSET ?;";
    private String selectCompanyByIdRequest = "SELECT ca.id as caId, ca.name as caName FROM company ca WHERE ca.id = ?;";

    public int countCompany() {
    	int res = this.jdbcTemplate.queryForObject(countRequest, Integer.class);
    	return res;
    }

    public List<Company> list() {
    	List<Company> res = this.jdbcTemplate.query(selectListRequest, (resultSet, row) -> companyMapper.resultSetToCompany(resultSet));	    
    	return res;
    	    
    }

    public Optional<Company> selectCompany(int id) {
    	
        Company company; 
        company = this.jdbcTemplate.query(selectCompanyByIdRequest, (ResultSet resultSet, int row) -> companyMapper.resultSetToCompany(resultSet), id).get(0); 
        return Optional.ofNullable(company);
    }
    
    public List<Company> subList(int offset, int limit) {
    	List<Company> res = this.jdbcTemplate.queryForList(selectSublistRequest, new Object[] {limit, offset}, Company.class);
    	return res;
    }
}