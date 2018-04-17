package com.excilys.formation.cdb.servlet;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.ComputerPage;
import com.excilys.formation.cdb.service.ComputerService;

@WebServlet("/dashboard")
@Controller
public class DashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 2741128895945909738L;
    
    @Autowired
    private ComputerService computerService = new ComputerService();
    
	@Autowired
	private ComputerMapper computerMapper = new ComputerMapper();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        ComputerPage page = new ComputerPage(10,computerService);

    	List<ComputerDTO> computersDTO = new ArrayList<>();    	
      	
    	if (!(request.getParameter("index") == null)) {
              try {
                   int index = Integer.parseInt(request.getParameter("index"));
                  page.goToPage(index + 2);
              } catch (NumberFormatException e1) {
              }
          }    	
    	
    	  if (!(request.getParameter("search") == null)) {
              page.setKeywords(request.getParameter("search"));
          }
 	 
         page.getContent().forEach(computer -> computersDTO.add(computerMapper.computerToComputerDTO(computer)));
     	 int nbComputer = computerService.countComputers();   
         request.setAttribute("nbComputers", nbComputer);
         request.setAttribute("computerlist", computersDTO);
         RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/dashboard.jsp");
         requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}