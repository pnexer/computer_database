 package com.excilys.formation.cdb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.service.CompanyService;
import com.excilys.formation.cdb.service.ComputerService;
import com.excilys.formation.cdb.service.ComputerPage;

@Controller
public class GlobalController {
	
	private ComputerService computerService;
    private CompanyService companyService;
    private ComputerMapper computerMapper;

    public GlobalController(ComputerService computerService, CompanyService companyService, ComputerMapper computerMapper) {
        this.computerService = computerService;
        this.companyService = companyService;
        this.computerMapper = computerMapper;
    }
    
    @GetMapping("/dashboard")
    public ModelAndView doGetDashboard(@RequestParam(value = "search", defaultValue = "") String search,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "index", defaultValue = "1") int index){
       
        ModelAndView modelAndView = new ModelAndView();
        ComputerPage page = new ComputerPage(size,computerService);

        int nbComputer = computerService.countComputers();
        List<ComputerDTO> computerDTOList = new ArrayList<>();
        page.getContent().forEach(computer -> computerDTOList.add(computerMapper.computerToComputerDTO(computer)));
        modelAndView.addObject("nbComputers", nbComputer);
        modelAndView.addObject("computerlist", computerDTOList);
        modelAndView.addObject("size", page.getSize());
        modelAndView.addObject("keywords", page.getKeywords());
        modelAndView.addObject("maxIndex", page.getLastPageIndex() + 1);
        modelAndView.addObject("currentIndex", page.getCurrentPageIndex() + 1);
        return modelAndView;
    }

    @PostMapping("/dashboard")
    public ModelAndView doPostDashboard(@RequestParam(value = "selection") int id) {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
    
    @GetMapping("/add")
    public ModelAndView doGetAddComputer() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyList", companyService.selectListCompany());
        modelAndView.addObject("computer", new ComputerDTO());
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView doPostAddComputer(@ModelAttribute("computer") ComputerDTO computerDTO, Model model) {
        ModelAndView modelAndView = new ModelAndView();   
        return modelAndView;
    }
    
    @GetMapping("/edit")
    public ModelAndView doGetEditComputer(@RequestParam(value = "id",defaultValue = "1") int id) {
    	 
    	ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companyList", companyService.selectListCompany());
        ComputerDTO computerDTO = null;
		try {
			computerDTO = computerMapper.computerToComputerDTO(computerService.selectOneComputer(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
        modelAndView.addObject("computer", computerDTO);
      
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView doPostEditComputer(@ModelAttribute("computer") ComputerDTO computerDTO, Model model) {
        ModelAndView modelAndView = new ModelAndView();
   
        return modelAndView;
    }
    

}