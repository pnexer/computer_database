package com.excilys.formation.cdb.service;

import java.util.List;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.*;

public enum CompanyService {
    INSTANCE;

    public List<Company> selectListCompany() {
        return CompanyDAO.INSTANCE.list();
    }
    public int countCompany() {
        return CompanyDAO.INSTANCE.countCompany();
    }
    public Company getCompany(int id) throws Exception {
        return Validator.INSTANCE.companyExistValidation(id);
    }
    
    public List<Company> selectSubListCompany(int offset, int numberToDisplay) {
        return CompanyDAO.INSTANCE.subList(offset, numberToDisplay);
    }
}
