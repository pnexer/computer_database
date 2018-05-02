package com.excilys.formation.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;

@Component
public class ComputerMapper {
	
	@Autowired
	private  CompanyMapper companyMapper = new CompanyMapper();

    public Computer resultSetToComputer(ResultSet resultSet) throws SQLException {
 	
    	int id = resultSet.getInt("cuId");
        String name = resultSet.getString("cuName");
        Date DateIntro = resultSet.getDate("introduced");
        Date DateDisco = resultSet.getDate("discontinued");       
        LocalDate introducedComputer = DateIntro == null ? null : resultSet.getDate("introduced").toLocalDate();
        LocalDate discontinuedComputer = DateDisco == null ? null : resultSet.getDate("discontinued").toLocalDate();     
        Company manufactor = companyMapper.resultSetToCompany(resultSet);
      
        return new Computer(id,name,introducedComputer,discontinuedComputer,manufactor);
                          
    }


    public ComputerDTO computerToComputerDTO(Computer computer) {
    	
        return new ComputerDTO.ComputerDTOBuilder(computer.getName())
                                .id(computer.getId())
                                .dateIntroduced(optionalDateToString(computer.getIntroduced()))
                                .dateDiscontinued(optionalDateToString(computer.getDiscontinued()))
                                .companyDTO(companyMapper.companyToCompanyDTO(computer.getCompany()))
                                .build();
    }
    
    public Computer dtoToComputer(ComputerDTO computerDTO) {
        return new Computer(computerDTO.getId(),computerDTO.getName(),stringToLocalDate(computerDTO.getDateIntroduced()),stringToLocalDate(computerDTO.getDateDiscontinued()),companyMapper.dtoToCompany((computerDTO.getCompanyDTO())));
                      
    }
    
  

   
    private LocalDate stringToLocalDate(String stringDate) {
        return Optional.ofNullable(stringDate).isPresent() && !stringDate.isEmpty() ? LocalDate.parse(stringDate) : null;
    }

    private String optionalDateToString(Optional<LocalDate> date) {
        if (date.isPresent()) {
            return date.get().toString();
        } else {
            return "";
        }
    }

 
    private String optionalCompanyToString(Optional<Company> company) {
        if (company.isPresent()) {
            return company.get().getName();
        } else {
            return "";
        }
    }
}
