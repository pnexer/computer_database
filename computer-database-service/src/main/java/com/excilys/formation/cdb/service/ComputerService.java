package com.excilys.formation.cdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.ComputerDAO;

@Service
public class ComputerService {

	private ComputerDAO computerDAO;
	private Validator validator;

	@Autowired
	public ComputerService(ComputerDAO computerDAO, Validator validator) {
		this.computerDAO = computerDAO;
		this.validator = validator;
	}

	public ComputerService() {  
	}

	public List<Computer> subListComputer() {
		return computerDAO.listComputer();
	}

	public int countComputers() {
		return computerDAO.countAllComputer();
	}

	public Computer selectOneComputer(int id) throws Exception {
		return validator.computerExistValidation(id);
	}

	public void createComputer(Computer computer) throws Exception {

		if (computer.getCompany().isPresent()) {
			validator.companyExistValidation(computer.getCompany().get().getId());
		}      
		computerDAO.createComputer(computer);
	}

	public void updateComputer(Computer computer,int id ) throws Exception {

		validator.computerExistValidation(computer.getId());

		if (computer.getCompany().isPresent()) {
			validator.companyExistValidation(computer.getCompany().get().getId());
		}

		computerDAO.updateComputer(computer,id);

	}

	public void deleteComputer(int id) throws Exception {

		validator.computerExistValidation(id);       
		computerDAO.deleteComputer(id);
	}
}
