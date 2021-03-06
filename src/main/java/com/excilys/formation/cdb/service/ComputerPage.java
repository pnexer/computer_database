package com.excilys.formation.cdb.service;

import com.excilys.formation.cdb.model.Computer;

public class ComputerPage extends Page<Computer> {

    public ComputerPage(int size) {
        super(size);
    }

    @Override
    public void setLastPageIndex() {
        this.lastPageIndex = ComputerService.INSTANCE.countComputers() / this.getSize();
    }

    @Override
    public void setContent(int offset) {
        this.content = ComputerService.INSTANCE.subListComputer(this.getOffset(), this.getSize());
    }
}
