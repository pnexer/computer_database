package com.excilys.formation.cdb.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.CompanyDAO;
import com.excilys.formation.cdb.persistence.ComputerDAO;

@Component
public class Validator {
	
	@Autowired
	private CompanyDAO companyDAO ;
	
	@Autowired
	private ComputerDAO computerDAO;

    protected Computer computerExistValidation(int id) throws Exception {
    	
        if (computerDAO.selectComputer(id).isPresent()) {
        	
            return computerDAO.selectComputer(id).get();
        } else {
            throw new Exception("No Computer");
        }
    }

    protected Company companyExistValidation(int id) throws Exception {
    	
        if (companyDAO.selectCompany(id).isPresent()) {
            return companyDAO.selectCompany(id).get();
        } else {
            throw new Exception("No Company");
        }
    }
}