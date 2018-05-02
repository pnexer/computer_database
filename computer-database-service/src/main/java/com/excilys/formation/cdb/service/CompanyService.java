package com.excilys.formation.cdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.*;

@Service
public class  CompanyService {
	
	private CompanyDAO companyDAO;
	private Validator validator;
	
	@Autowired
    public CompanyService(CompanyDAO companyDAO,Validator validator) {
        this.companyDAO = companyDAO;
        this.validator = validator;
    }


    public List<Company> selectListCompany() {
        return companyDAO.list();
    }
    public int countCompany() {
        return companyDAO.countCompany();
    }
    public Company getCompany(int id) throws Exception {
        return validator.companyExistValidation(id);
    }
    
    public List<Company> selectSubListCompany(int offset, int numberToDisplay) {
        return companyDAO.subList(offset, numberToDisplay);
    }
}
