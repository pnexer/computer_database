package com.excilys.formation.cdb.service;


import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.cdb.model.Computer;

public class ComputerPage extends Page<Computer> {

    private ComputerService computerService;

	
    public ComputerPage(int size,ComputerService computerService) {
        super(size);
        this.computerService = computerService;
        this.setLastPageIndex();
        this.setContent(this.getOffset());
    }

    @Override
    public void setLastPageIndex() {
        this.lastPageIndex = computerService.countComputers() / this.getSize();
    }

    @Override
    public void setContent(int offset) {
        this.content = computerService.subListComputer(this.getOffset(), this.getSize());
    }
}
