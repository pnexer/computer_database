package com.excilys.formation.cdb.ui;




import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

import com.excilys.formation.cdb.config.AppConfiguration;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.CompanyService;
import com.excilys.formation.cdb.service.ComputerService;

@Controller
public class CLI {
	
	private static ComputerService computerService;
	private CompanyService companyService;
	
	Scanner scCommande = new Scanner(System.in);
	Scanner sc = new Scanner(System.in);
	static Scanner sc2 = new Scanner(System.in);
	
	public CLI(ComputerService computerService, CompanyService companyService){
		  this.companyService = companyService;
		  this.computerService = computerService;
		 
		  }
	
	public static void main(String[] args){
			
		    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
	        CLI cli = context.getBean(CLI.class);
	        cli.showListComputer();
	        context.close();	  
	  }
	
    public  void showComputer() {
    	int id;
    	Computer c = null;
    	System.out.println("Selectionner l'id de l'ordinateur à afficher : ");
    	id = sc2.nextInt();
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

	    	System.out.println("Selectionner l'id de l'ordinateur à creer : ");
	    	int id = sc2.nextInt(); 
    	
	    	System.out.println("creation dans la base de donnée ... ");
	  //  	ComputerService.INSTANCE.createComputer(computer);
        
	    	System.out.println("affichage du computer : ");
	
	}
	
    public void deleteComputer() {

    	System.out.println("Selectionner l'id de l'ordinateur à supprimer : ");
    	int id = sc2.nextInt();    
    	System.out.println("suppression dans la base de donnée ... "); 
    	System.out.println("affichage du computer : ");
    
    	
	}
    
    public void updateComputer() {
 
    		System.out.println("Selectionner l'id de l'ordinateur à changer : ");
    		int id = sc2.nextInt();    
    		System.out.println("update dans la base de donnée ... "); 
    		System.out.println("affichage du computer : ");
    	
    	}


	
	
}

