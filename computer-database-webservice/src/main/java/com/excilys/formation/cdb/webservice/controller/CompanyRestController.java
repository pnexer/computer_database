package com.excilys.formation.cdb.webservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.excilys.formation.cdb.dto.CompanyDTO;
import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.service.CompanyService;

@RestController
public class CompanyRestController {

	private CompanyService companyService;
	private CompanyMapper companyMapper;

	@Autowired
	public CompanyRestController(CompanyService companyService,CompanyMapper companyMapper) {
		this.companyService = companyService;
		this.companyMapper = companyMapper;

	}

	@GetMapping("/companies")
	public ResponseEntity<List<CompanyDTO>> getCompanies() {
		
		HttpHeaders responseHeaders = new HttpHeaders();
		
		Company company1 = new Company(1,"yaaaadtrtaaa");
		
		List<CompanyDTO> companyDTOList = new ArrayList<>();
		companyDTOList.add(this.companyMapper.companyToDTO(company1));
		this.companyService.CompanyList().forEach(company -> companyDTOList.add(this.companyMapper.companyToDTO(company)));
		return new ResponseEntity<List<CompanyDTO>>(companyDTOList,responseHeaders, HttpStatus.OK);
	}

}
