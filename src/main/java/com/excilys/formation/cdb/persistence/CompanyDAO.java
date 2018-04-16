package com.excilys.formation.cdb.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.model.Company;


public enum CompanyDAO {
    INSTANCE;

    private String selectListRequest = "SELECT ca.id as caId, ca.name as caName FROM company ca";
    private String countRequest = "SELECT count(id) FROM company;";

    public int countCompany() {
    	int r = -1;
    	
        try (Connection connection = ConnexionManager.INSTANCE.getConn();
             Statement statement = connection.createStatement();
             ResultSet res = statement.executeQuery(countRequest);) {
           
            res.next();
            r = res.getInt(1);
            return r;
            
        } catch (SQLException e) {
        }
        
        return r;
    }

    public List<Company> list() {
    	
        List<Company> companyList = new ArrayList<>();
        
        try (Connection connection = ConnexionManager.INSTANCE.getConn();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(selectListRequest + " ;");) {

            while (result.next()) {
                companyList.add(CompanyMapper.INSTANCE.resultSetToCompany(result));
            }
        } catch (SQLException e) {
        }
        
        return companyList;
    }

    public Optional<Company> selectCompany(int id) {
    	
        Company company = null;
        ResultSet result;

        try (Connection connection = ConnexionManager.INSTANCE.getConn();
        	 PreparedStatement preparedStatement = connection.prepareStatement(selectListRequest + " WHERE ca.id = ?;");) {
        	           
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();
            
            if (result.next()) {
                company = CompanyMapper.INSTANCE.resultSetToCompany(result);
            }
            
        } catch
        (SQLException e) {
        }
        return Optional.ofNullable(company);
    }
    
    public List<Company> subList(int offset, int limit) {
        List<Company> computerList = new ArrayList<>();
        try (Connection conn = ConnexionManager.INSTANCE.getConn();
             PreparedStatement preparedStatement = conn.prepareStatement(selectListRequest + " LIMIT ? OFFSET ?;");) {
            
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
            	
                computerList.add(CompanyMapper.INSTANCE.resultSetToCompany(res));
            }
        } catch (SQLException e) {
        }
        return computerList;
    }
}