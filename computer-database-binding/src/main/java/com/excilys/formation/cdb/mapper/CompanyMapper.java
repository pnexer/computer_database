package com.excilys.formation.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.excilys.formation.cdb.dto.CompanyDTO;
import com.excilys.formation.cdb.model.Company;

@Component
public class CompanyMapper {

    public Company resToCompany(ResultSet resultSet) throws SQLException {
        int idCompany = resultSet.getInt("caId");
        String nameCompany = resultSet.getString("caName");
        return new Company(idCompany, nameCompany);
    }

    public CompanyDTO companyToDTO(Optional<Company> company) {
        return company.isPresent() ? new CompanyDTO(company.get().getId(), company.get().getName()) : null;
    }

    public CompanyDTO companyToDTO(Company company) {
        return new CompanyDTO(company.getId(), company.getName());
    }
    
    public Company dtoToCompany(CompanyDTO companyDTO) {
        return companyDTO.getId() > 0 ? new Company(companyDTO.getId(), companyDTO.getName()) : null;
    }
}