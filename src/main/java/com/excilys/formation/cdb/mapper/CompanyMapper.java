package com.excilys.formation.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.excilys.formation.cdb.dto.CompanyDTO;
import com.excilys.formation.cdb.model.Company;

@Component
public class CompanyMapper {
    

    public Company resultSetToCompany(ResultSet resultSet) throws SQLException {
    	
        int id = resultSet.getInt("caId");
        String name = resultSet.getString("caName");
        Company company = new Company(id, name);
        return company;
    }
    
    public Company dtoToCompany(CompanyDTO companyDTO) {
        return companyDTO.getId() > 0 ? new Company(companyDTO.getId(), companyDTO.getName()) : null;
    }
    
    public CompanyDTO companyToCompanyDTO(Optional<Company> company) {
        return company.isPresent() ? new CompanyDTO(company.get().getId(), company.get().getName()) : null;
    }

}