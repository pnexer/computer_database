package com.excilys.formation.cdb.service;

import com.excilys.formation.cdb.model.Company;

public class CompanyPage extends Page<Company> {
	
    private CompanyService companyService  = new CompanyService();


    public CompanyPage(int size) {
        super(size);
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
