package com.excilys.formation.cdb.pagination;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.service.CompanyService;
import com.excilys.formation.cdb.service.ServiceException;

public class CompanyPage extends Page<Company> {

    @Autowired
    private CompanyService companyService;

    public CompanyPage(int size) throws ServiceException {
        super(size);
        this.setLastPageIndex();
        this.setContent(this.getOffset());
    }

    @Override
    public void setLastPageIndex() throws ServiceException {
        this.lastPageIndex = (companyService.countCompany()/ this.getSize());
    }

    @Override
    public void setContent(int offset) throws ServiceException {
        this.content = companyService.CompanySubList(this.getOffset(), this.getSize());
    }
}
