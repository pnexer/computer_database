package com.excilys.formation.cdb.service;

import com.excilys.formation.cdb.model.Company;

public class CompanyPage extends Page<Company> {

    public CompanyPage(int size) {
        super(size);
    }
    
    @Override
    public void setLastPageIndex() {
        this.lastPageIndex = (CompanyService.INSTANCE.countCompany() / this.getSize());
    }
    
    @Override
    public void setContent(int offset) {
        this.content = CompanyService.INSTANCE.selectSubListCompany(this.getOffset(), this.getSize());
    }
}
