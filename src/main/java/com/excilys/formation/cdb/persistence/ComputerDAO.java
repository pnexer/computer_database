package com.excilys.formation.cdb.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.model.Computer;

@Repository
public class ComputerDAO {

	@Autowired
	private ComputerMapper computerMapper = new ComputerMapper();

    private final Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
   
    private  String selectListRequest = "SELECT cu.id as cuId, cu.name as cuName, introduced, discontinued, ca.id as caId, ca.name as caName FROM computer cu LEFT JOIN company ca ON ca.id = cu.company_id";
    private  String createRequest = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?);";
    private  String updateRequest = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?;";
    private  String deleteRequest = "DELETE FROM computer WHERE id=?;";
    private  String countRequest = "SELECT count(id) FROM computer;";

    public int countAllComputer() {
    	int res = -1;
    	try (Connection connection = ConnexionManager.INSTANCE.getConn();
    		 Statement statement = connection.createStatement();
    		 ResultSet result = statement.executeQuery(countRequest);) {   
   	
            result.next();
            res = result.getInt(1);
            return res;
            
        } catch (SQLException e) {
            logger.error("Requete indisponible", e.getMessage(), e);
        }
    	return res;
    }

    public List<Computer> listComputer() {
    	
        List<Computer> computerList = new ArrayList<>();
        
        try (Connection conn = ConnexionManager.INSTANCE.getConn();
        	 Statement statement = conn.createStatement();
        	 ResultSet res = statement.executeQuery(selectListRequest + " ;");) {
           
            while (res.next()) {
                computerList.add(computerMapper.resultSetToComputer(res));
            }
            
        } catch (SQLException e) {
            logger.error("Requete indisponible", e.getMessage(), e);
        }
        return computerList;
    }

    public void createComputer(Computer computer) {
    	
        try (Connection conn = ConnexionManager.INSTANCE.getConn();
        	 PreparedStatement preparedStatement = conn.prepareStatement(createRequest);) {
            
            preparedStatement.setString(1, computer.getName());
            preparedStatement.setDate(2, Date.valueOf(computer.getDateIntroduced().get()));
            preparedStatement.setDate(3, Date.valueOf(computer.getDateDiscontinued().get()));
            preparedStatement.setInt(4, computer.getManufactor().get().getId());     
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
        	
            logger.error("Requete indisponible", e.getMessage(), e);
            
        }
    }

    public void updateComputer(Computer computer) {
        try (Connection conn = ConnexionManager.INSTANCE.getConn();
        	 PreparedStatement preparedStatement = conn.prepareStatement(updateRequest);) {
            
            preparedStatement.setString(1, computer.getName());
            preparedStatement.setDate(2, Date.valueOf(computer.getDateIntroduced().get()));
            preparedStatement.setDate(3, Date.valueOf(computer.getDateDiscontinued().get()));
            preparedStatement.setInt(4, computer.getManufactor().get().getId());   
            preparedStatement.setInt(5, computer.getId());
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
        	 logger.error("Requete indisponible", e.getMessage(), e);
        }
    }

    public void deleteComputer(int id) {
        try (Connection conn = ConnexionManager.INSTANCE.getConn();
             PreparedStatement preparedStatement = conn.prepareStatement(deleteRequest);) {
           
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            logger.error("Requete indisponible", e.getMessage(), e);
        }
    }

    public Optional<Computer> selectComputer(int id) {
     
    	Computer computer = null;
        ResultSet res;
        
        try (Connection conn = ConnexionManager.INSTANCE.getConn();
             PreparedStatement preparedStatement = conn.prepareStatement(selectListRequest + " WHERE cu.id = ?;");) {
        	
        	preparedStatement.setInt(1, id);
            res = preparedStatement.executeQuery();
            
            if (res.next()) {
                computer = computerMapper.resultSetToComputer(res);
            }
            
        } catch (SQLException e) {

        }
        return Optional.ofNullable(computer);
    }

    public List<Computer> subList(int offset, int limit,String keyword) {
        List<Computer> listComputer = new ArrayList<>();
        int indice = 0;        
        String addKeywordRequest;
        
        if(keyword.length() > 0) {
        	addKeywordRequest =  " WHERE (cu.name LIKE ?) ";
        } else {
        	addKeywordRequest = "WHERE (cu.name LIKE ?)";
        }
        
        //String selectListRequestNew = selectListRequest + addKeywordRequest;
        
        

        
        try (Connection conn = ConnexionManager.INSTANCE.getConn();
        	 PreparedStatement preparedStatement = conn.prepareStatement(selectListRequest + " LIMIT ? OFFSET ?;");	) 
        {    if(indice > 0) {       	
             preparedStatement.setString(1,"%" + keyword + "%");  	
             }
             preparedStatement.setInt(1 + indice, limit);
             preparedStatement.setInt(2 + indice, offset);
            ResultSet res = preparedStatement.executeQuery();
            
            while (res.next()) {
                listComputer.add(computerMapper.resultSetToComputer(res));
            }
            
        } catch (SQLException e) {
            logger.error("Requete indisponible", e.getMessage(), e);
        }
        return listComputer;
    }
}