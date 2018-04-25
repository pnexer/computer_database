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
      
        return new Computer.ComputerBuilder(name)
                            .id(id)
                            .dateIntroduced(introducedComputer)
                            .dateDiscontinued(discontinuedComputer)
                            .manufactor(manufactor)
                            .build();
    }


    public ComputerDTO computerToComputerDTO(Computer computer) {
    	
        return new ComputerDTO.ComputerDTOBuilder(computer.getName())
                                .id(computer.getId())
                                .dateIntroduced(optionalDateToString(computer.getDateIntroduced()))
                                .dateDiscontinued(optionalDateToString(computer.getDateDiscontinued()))
                                .companyDTO(companyMapper.companyToCompanyDTO(computer.getManufactor()))
                                .build();
    }
    
    public Computer dtoToComputer(ComputerDTO computerDTO) {
        return new Computer.ComputerBuilder(computerDTO.getName())
                            .id(computerDTO.getId())
                            .dateIntroduced(stringToLocalDate(computerDTO.getDateIntroduced()))
                            .dateDiscontinued(stringToLocalDate(computerDTO.getDateDiscontinued()))
                            .manufactor(companyMapper.dtoToCompany((computerDTO.getCompanyDTO())))
                            .build();
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
