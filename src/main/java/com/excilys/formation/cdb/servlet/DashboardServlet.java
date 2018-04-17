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

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.ComputerPage;
import com.excilys.formation.cdb.service.ComputerService;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 2741128895945909738L;
    
    @Autowired
    private ComputerService computerService = new ComputerService();

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
 	 
         page.getContent().forEach(computer -> computersDTO.add(ComputerMapper.INSTANCE.computerToComputerDTO(computer)));
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