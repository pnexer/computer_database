package com.excilys.formation.cdb.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.CompanyService;
import com.excilys.formation.cdb.service.ComputerService;

@WebServlet("/editComputer")
public class EditComputerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        // int computerId = Integer.parseInt(request.getParameter("id"));
         ComputerDTO computerDTO = null;
		try {
			computerDTO = ComputerMapper.INSTANCE.computerToComputerDTO(ComputerService.INSTANCE.selectOneComputer(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         request.setAttribute("computer", computerDTO);
         request.setAttribute("companyList", CompanyService.INSTANCE.selectListCompany());
          RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/editComputer.jsp");
         requestDispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String name = request.getParameter("computerName");
        String strIntroduced = request.getParameter("introduced");
        String strDiscontinued = request.getParameter("discontinued");
        String strCompanyId = request.getParameter("companyId");
        Company company = null;	
        
        
        int companyId = Integer.parseInt(strCompanyId);
        try {
			company = CompanyService.INSTANCE.getCompany(companyId);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
       LocalDate introduced = LocalDate.parse(strIntroduced);  
       LocalDate discontinued = LocalDate.parse(strDiscontinued);         

  
        try {
			ComputerService.INSTANCE.updateComputer(new Computer.ComputerBuilder(name)
			         .dateIntroduced(introduced)
			         .dateDiscontinued(discontinued)
			         .manufactor(company)
			         .build());
		} catch (Exception e) {
			e.printStackTrace();
		}
           	
    }


}