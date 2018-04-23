package com.excilys.formation.cdb.ui;




import java.util.Scanner;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.CompanyService;
import com.excilys.formation.cdb.service.ComputerService;

public class CLI {
	
	private ComputerService computerService ;
	private CompanyService companyService ;
	
	Scanner scCommande = new Scanner(System.in);
	Scanner sc = new Scanner(System.in);
	Scanner sc2 = new Scanner(System.in);
	
	public CLI(){
		
		  System.out.println("Saisir commande : ");
			 int numcomputer = computerService.countComputers();
	
		  String str = "";
		  switch (str) {
		  

		  			             
		  case "Show Computer":     showComputer();
			                        break;			             	
		  			             	
		  case "Create Computer":      createComputer();
		  							break;
		  							
		  case "Delete Computer":   deleteComputer();
	                            	break;
	                            	
		  case "Update Computer":   updateComputer();
		                        	break;
		  }
		
	}
	 

	

	
    public void showComputer() {
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

  public static void main(String[] args){
		
	      CLI ui = new CLI();
	  
	  }
	
	
}

