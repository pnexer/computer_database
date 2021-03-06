package com.excilys.formation.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;


public enum ComputerMapper {
    INSTANCE;

    public Computer resultSetToComputer(ResultSet resultSet) throws SQLException {
        
    	int id = resultSet.getInt("cuId");
        String name = resultSet.getString("cuName");
        Date DateIntro = resultSet.getDate("introduced");
        Date DateDisco = resultSet.getDate("discontinued");
        
        LocalDate introducedComputer = DateIntro == null ? null : resultSet.getDate("introduced").toLocalDate();
        LocalDate discontinuedComputer = DateDisco == null ? null : resultSet.getDate("discontinued").toLocalDate();
        
        Company manufactor = CompanyMapper.INSTANCE.resultSetToCompany(resultSet);
      
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
                                .manufactorName(optionalCompanyToString(computer.getManufactor()))
                                .build();
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
