package com.excilys.formation.cdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.dao.CompanyDAO;
import com.excilys.formation.cdb.persistence.dao.ComputerDAO;
import com.excilys.formation.cdb.validator.Validator;

@Service
@EnableTransactionManagement
public class CompanyService {

    private CompanyDAO companyDAO;
    private ComputerDAO computerDAO;
    private Validator validator;

    @Autowired
    public CompanyService(CompanyDAO companyDAO, ComputerDAO computerDAO, Validator validator) {
        this.companyDAO = companyDAO;
        this.computerDAO = computerDAO;
        this.validator = validator;
    }

    public List<Company> CompanySubList(int offset, int numberToDisplay) {
        return companyDAO.selectCompanySubList(offset, numberToDisplay);
    }

    public List<Company> CompanyList() {
        return companyDAO.selectCompanyList();
    }

    public int countCompany() {
        return companyDAO.countCompany();
    }

    public Company getCompany(int id) throws ServiceException {
        return validator.manufactorValidation(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public String deleteCompany(int idCompany) throws ServiceException {
        validator.manufactorValidation(idCompany);
        for (int idComputer : computerDAO.getComputersWithCompanyId(idCompany)) {
            computerDAO.deleteComputer(idComputer);
        }
        companyDAO.deleteCompany(idCompany);
        return "Companny " + idCompany + " removed from database.";
    }
}
