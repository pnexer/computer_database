package com.excilys.formation.cdb.ui;


import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

import com.excilys.formation.cdb.config.Config;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.CompanyService;
import com.excilys.formation.cdb.service.ComputerService;

@Controller
public class CLI {

	private ComputerService computerService;
	private CompanyService companyService;
	
	static Scanner sc = new Scanner(System.in);

	public CLI(ComputerService computerService, CompanyService companyService){
		this.companyService = companyService;
		this.computerService = computerService;

	}

	public static void main(String[] args){

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(Config.class);
		context.refresh();
		context.getBean(CLI.class).start();
		context.close();	  
	}

	public void start() {
		showListComputer();
	}

	public  void showComputer() {
		int id;
		Computer c = null;
		System.out.println("Selectionner l'id de l'ordinateur à afficher : ");
		id = sc.nextInt();
		System.out.println("Recherche dans la base de donnée ... ");

		try {
			c =  computerService.selectOneComputer(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("affichage du computer : ");
		System.out.println(c);


	}

	public  void showListComputer() {
		int id;
		Computer c = null;

		System.out.println("Recherche dans la base de donnée ... ");
		for(int i = 0;i< computerService.countComputers();i++) {
			try {
				if(computerService.selectOneComputer(i) != null) {
					try {				
						c =  computerService.selectOneComputer(i);
					} catch (Exception e) {

					}
					System.out.println(c);}
			} catch (Exception e) {
			}
		}

	}

	public void createComputer()  {

	}

	public void deleteComputer() {

	}

	public void updateComputer() {

	}




}

