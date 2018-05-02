package com.excilys.formation.cdb.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.cdb.model.Company;

public class CompanyPage extends Page<Company> {
	
    private CompanyService companyService;

	@Autowired
    public CompanyPage(int size,CompanyService companyService) {
        super(size);
        this.companyService = companyService;
    }
    
    @Override
    public void setLastPageIndex() {
        this.lastPageIndex = (companyService.countCompany() / this.getSize());
    }
    
    @Override
    public void setContent(int offset) {
        this.content = companyService.selectSubListCompany(this.getOffset(), this.getSize());
    }
}
