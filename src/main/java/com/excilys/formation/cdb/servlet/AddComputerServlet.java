package com.excilys.formation.cdb.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.CompanyService;
import com.excilys.formation.cdb.service.ComputerService;

@WebServlet("/addComputer")
public class AddComputerServlet extends HttpServlet {
 

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        request.setAttribute("companyList", CompanyService.INSTANCE.selectListCompany());
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/addComputer.jsp");
        requestDispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
         String name = request.getParameter("computerName");
       //  String strDiscontinued = request.getParameter("discontinued");
         String strCompanyId = request.getParameter("companyId");
         Company company = null;

         
         int companyId = Integer.parseInt(strCompanyId);
         try {
			company = CompanyService.INSTANCE.getCompany(companyId);
		} catch (Exception e) {
			e.printStackTrace();
		}
         
        LocalDate introduced = LocalDate.now();
        LocalDate discontinued = LocalDate.now();         

   
         try {
			ComputerService.INSTANCE.createComputer(new Computer.ComputerBuilder(name)
			         .dateIntroduced(introduced)
			         .dateDiscontinued(discontinued)
			         .manufactor(company)
			         .build());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }


}
