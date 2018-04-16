package com.excilys.formation.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.formation.cdb.dto.CompanyDTO;
import com.excilys.formation.cdb.model.Company;

public enum CompanyMapper {
    INSTANCE;

    public Company resultSetToCompany(ResultSet resultSet) throws SQLException {
    	
        int id = resultSet.getInt("caId");
        String name = resultSet.getString("caName");
        Company company = new Company(id, name);
        return company;
    }

}