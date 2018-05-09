package com.excilys.formation.cdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.dao.ComputerDAO;
import com.excilys.formation.cdb.validator.Validator;

@Service
@EnableTransactionManagement
public class ComputerService {

    private ComputerDAO computerDAO;
    private Validator validator;

    @Autowired
    public ComputerService(ComputerDAO computerDAO, Validator validator) {
        this.computerDAO = computerDAO;
        this.validator = validator;
    }

    public List<Computer> ComputerSubList(int offset, int numberToDisplay, String keywords, String sortBy, boolean asc) {
        return computerDAO.selectComputerSubList(offset, numberToDisplay, keywords, sortBy, asc);
    }


    public int countComputer(String keywords) {
        return computerDAO.countComputer(keywords);
    }

    public Computer selectComputer(int id) throws ServiceException {
        return validator.computerIdValidation(id);
    }

    public List<Computer> ComputerList() {
        return computerDAO.selectComputerList();
    }

    public String createComputer(Computer computer) throws ServiceException {
        validator.nameValidation(computer.getName());
        if (computer.getDateIntroduced().isPresent() && computer.getDateDiscontinued().isPresent()) {
            validator.datesCompatibilityValidation(computer.getDateIntroduced().get(), computer.getDateDiscontinued().get());
        }
        if (computer.getDateIntroduced().isPresent()) {
            validator.dateValidation(computer.getDateIntroduced().get());
        }
        if (computer.getDateDiscontinued().isPresent()) {
            validator.dateValidation(computer.getDateDiscontinued().get());
        }
        if (computer.getManufactor().isPresent()) {
            validator.manufactorValidation(computer.getManufactor().get().getId());
        }
        computerDAO.createComputer(computer);
        return "New computer added to database.";
    }


    public String updateComputer(Computer computer) throws ServiceException {
        validator.computerIdValidation(computer.getId());
        validator.nameValidation(computer.getName());
        if (computer.getDateIntroduced().isPresent() && computer.getDateDiscontinued().isPresent()) {
            validator.datesCompatibilityValidation(computer.getDateIntroduced().get(), computer.getDateDiscontinued().get());
        }
        if (computer.getManufactor().isPresent()) {
            validator.manufactorValidation(computer.getManufactor().get().getId());
        }
        computerDAO.updateComputer(computer);
        return "Computer " + computer.getId() + " updated.";
    }

    @Transactional(rollbackFor = Exception.class)
    public String deleteComputer(int... ids) throws ServiceException {
        String strIds = "";
        for (int id : ids) {
            validator.computerIdValidation(id);
            computerDAO.deleteComputer(id);
            strIds += "(" + id + ")";
        }
        return "Computer(s) " + strIds + " removed from database.\n";
    }
}
