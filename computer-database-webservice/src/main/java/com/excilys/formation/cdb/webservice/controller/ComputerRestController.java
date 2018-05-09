package com.excilys.formation.cdb.webservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.model.Computer.ComputerBuilder;
import com.excilys.formation.cdb.service.ComputerService;
import com.excilys.formation.cdb.service.ServiceException;

@RestController
public class ComputerRestController {

	private ComputerService computerService;
	private ComputerMapper computerMapper;

	@Autowired
	public ComputerRestController(ComputerService computerService,ComputerMapper computerMapper) {
		this.computerService = computerService;
		this.computerMapper = computerMapper;

	}

	@GetMapping("/computers")
	public ResponseEntity<List<ComputerDTO>> getComputers() {
		
		HttpHeaders responseHeaders = new HttpHeaders();				
		List<ComputerDTO> computerDTOList = new ArrayList<>();
		this.computerService.ComputerList().forEach(computer -> computerDTOList.add(this.computerMapper.computerToDTO(computer)));
		return new ResponseEntity<List<ComputerDTO>>(computerDTOList,responseHeaders, HttpStatus.OK);
	}
	
	  @GetMapping("/computer/{id}")
	    public ResponseEntity<ComputerDTO> getComputer(@PathVariable int id) {
	
	            try {
					return new ResponseEntity<ComputerDTO>(this.computerMapper.computerToDTO(this.computerService.selectComputer(id)), HttpStatus.OK);
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					 return new ResponseEntity<ComputerDTO>(HttpStatus.NOT_FOUND);				}
	        
	}

}
