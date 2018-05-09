package com.excilys.formation.cdb.ui;

import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

import com.excilys.formation.cdb.config.AppConfiguration;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.CompanyService;
import com.excilys.formation.cdb.service.ComputerService;
import com.excilys.formation.cdb.service.ServiceException;
import com.excilys.formation.cdb.pagination.CompanyPage;
import com.excilys.formation.cdb.pagination.ComputerPage;
import com.excilys.formation.cdb.pagination.Page;

@Controller
public class Cli {

    private ComputerService computerService;
    private CompanyService companyService;

    @Autowired
    public Cli(ComputerService computerService, CompanyService companyService) {
        this.companyService = companyService;
        this.computerService = computerService;
    }
    
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        Cli cli = context.getBean(Cli.class);
        cli.run();
        context.close();
    }

    private void run() {
    	
    	int res = companyService.countCompany();
    	System.out.println(res);
    	
    }

}