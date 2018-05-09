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
    private CompanyMapper companyMapper;

    public Computer resToComputer(ResultSet resultSet) throws SQLException {
        int idComputer = resultSet.getInt("cuId");
        String nameComputer = resultSet.getString("cuName");
        Date intro = resultSet.getDate("introduced");
        Date disco = resultSet.getDate("discontinued");
        LocalDate introducedComputer = Optional.ofNullable(intro).isPresent() ? resultSet.getDate("introduced").toLocalDate() : null;
        LocalDate discontinuedComputer = Optional.ofNullable(disco).isPresent() ? resultSet.getDate("discontinued").toLocalDate() : null;
        Company manufactor = companyMapper.resToCompany(resultSet);
        return new Computer.ComputerBuilder(nameComputer)
                            .id(idComputer)
                            .dateIntroduced(introducedComputer)
                            .dateDiscontinued(discontinuedComputer)
                            .manufactor(manufactor)
                            .build();
    }
    
    public ComputerDTO computerToDTO(Computer computer) {
        return new ComputerDTO.ComputerDTOBuilder(computer.getName())
                                .id(computer.getId())
                                .dateIntroduced(optionalDateToString(computer.getDateIntroduced()))
                                .dateDiscontinued(optionalDateToString(computer.getDateDiscontinued()))
                                .manufactor(companyMapper.companyToDTO(computer.getManufactor()))
                                .build();
    }


    public Computer dtoToComputer(ComputerDTO computerDTO) {
        return new Computer.ComputerBuilder(computerDTO.getName())
                            .id(computerDTO.getId())
                            .dateIntroduced(stringToLocalDate(computerDTO.getDateIntroduced()))
                            .dateDiscontinued(stringToLocalDate(computerDTO.getDateDiscontinued()))
                            .manufactor(companyMapper.dtoToCompany((computerDTO.getManufactor())))
                            .build();
    }


    private String optionalDateToString(Optional<LocalDate> date) {
        return date.isPresent() ? date.get().toString() : "";
    }

    private LocalDate stringToLocalDate(String stringDate) {
        return Optional.ofNullable(stringDate).isPresent() && !stringDate.isEmpty() ? LocalDate.parse(stringDate) : null;
    }
}
