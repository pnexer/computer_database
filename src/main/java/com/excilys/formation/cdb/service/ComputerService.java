package com.excilys.formation.cdb.service;

import java.util.List;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.ComputerDAO;

public enum ComputerService {
    INSTANCE;

    public List<Computer> subListComputer(int offset, int numberToDisplay) {
        return ComputerDAO.INSTANCE.subList(offset, numberToDisplay);
    }

    public int countComputers() {
        return ComputerDAO.INSTANCE.countAllComputer();
    }

    public Computer selectOneComputer(int id) throws Exception {
        return Validator.INSTANCE.computerExistValidation(id);
    }

    public void createComputer(Computer computer) throws Exception {

        if (computer.getManufactor().isPresent()) {
            Validator.INSTANCE.companyExistValidation(computer.getManufactor().get().getId());
        }
        
        ComputerDAO.INSTANCE.createComputer(computer);

    }

    public void updateComputer(Computer computer) throws Exception {
    	
        Validator.INSTANCE.computerExistValidation(computer.getId());

        if (computer.getManufactor().isPresent()) {
            Validator.INSTANCE.companyExistValidation(computer.getManufactor().get().getId());
        }
        
        ComputerDAO.INSTANCE.updateComputer(computer);

    }

    public void deleteComputer(int id) throws Exception {
    	
        Validator.INSTANCE.computerExistValidation(id);       
        ComputerDAO.INSTANCE.deleteComputer(id);
    }
}
