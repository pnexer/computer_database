package com.excilys.formation.cdb.service;

import java.time.LocalDate;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.CompanyDAO;
import com.excilys.formation.cdb.persistence.ComputerDAO;

public enum Validator {
    INSTANCE;





    protected Computer computerExistValidation(int id) throws Exception {
    	
        if (ComputerDAO.INSTANCE.selectComputer(id).isPresent()) {
        	
            return ComputerDAO.INSTANCE.selectComputer(id).get();
        } else {
            throw new Exception("No Computer");
        }
    }

    protected Company companyExistValidation(int id) throws Exception {
    	
        if (CompanyDAO.INSTANCE.selectCompany(id).isPresent()) {
            return CompanyDAO.INSTANCE.selectCompany(id).get();
        } else {
            throw new Exception("No Company");
        }
    }
}