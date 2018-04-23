package com.excilys.formation.cdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.ComputerDAO;

@Service
public class ComputerService {
  
	@Autowired
	private ComputerDAO computerDAO;
	
	@Autowired
	private Validator validator;
	
	 
	public ComputerService(ComputerDAO computerDAO, Validator validator) {
        this.computerDAO = computerDAO;
        this.validator = validator;
    }


    public List<Computer> subListComputer(int offset, int numberToDisplay,String keyword) {
        return computerDAO.subList(offset, numberToDisplay,keyword);
    }

    public int countComputers() {
        return computerDAO.countAllComputer();
    }

    public Computer selectOneComputer(int id) throws Exception {
        return validator.computerExistValidation(id);
    }

    public void createComputer(Computer computer) throws Exception {

        if (computer.getManufactor().isPresent()) {
            validator.companyExistValidation(computer.getManufactor().get().getId());
        }
        
        computerDAO.createComputer(computer);

    }

    public void updateComputer(Computer computer) throws Exception {
    	
        validator.computerExistValidation(computer.getId());

        if (computer.getManufactor().isPresent()) {
            validator.companyExistValidation(computer.getManufactor().get().getId());
        }
        
        computerDAO.updateComputer(computer);

    }

    public void deleteComputer(int id) throws Exception {
    	
        validator.computerExistValidation(id);       
        computerDAO.deleteComputer(id);
    }
}
