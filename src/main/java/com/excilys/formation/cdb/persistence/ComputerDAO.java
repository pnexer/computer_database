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

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.model.Computer;

@Repository
public class ComputerDAO {

	
	private ComputerMapper computerMapper;
	private JdbcTemplate jdbcTemplate;
	
	 @Autowired
	 public ComputerDAO(DataSource dataSource, ComputerMapper computerMapper) {
	      this.jdbcTemplate = new JdbcTemplate(dataSource);
	      this.computerMapper = computerMapper;
	 }
   
    private  String selectListRequest = "SELECT cu.id as cuId, cu.name as cuName, introduced, discontinued, ca.id as caId, ca.name as caName FROM computer cu LEFT JOIN company ca ON ca.id = cu.company_id";
    private  String createRequest = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?);";
    private  String updateRequest = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?;";
    private  String deleteRequest = "DELETE FROM computer WHERE id=?;";
    private  String countRequest = "SELECT count(id) FROM computer;";
    private  String selectComputer= "SELECT cu.id as cuId, cu.name as cuName, introduced, discontinued, ca.id as caId, ca.name as caName FROM computer cu LEFT JOIN company ca ON ca.id = cu.company_id WHERE cu.id = ?;";


    public int countAllComputer() {  
    	int res = this.jdbcTemplate.queryForObject(countRequest, Integer.class);
         return res;
    }

    public List<Computer> listComputer() {
    	List<Computer> res =this.jdbcTemplate.queryForList(selectListRequest, Computer.class);
    	return res;
    }

    public void createComputer(Computer computer) {
    	  this.jdbcTemplate.update(createRequest, preparedStatement -> {computerToPreparedStatement(computer, preparedStatement);});
    	
    }

    public void updateComputer(Computer computer) {
     	  this.jdbcTemplate.update(updateRequest, preparedStatement -> {computerToPreparedStatement(computer, preparedStatement);});

    }

    public void deleteComputer(int id) {
    	this.jdbcTemplate.update(deleteRequest, id);
    }

    public Optional<Computer> selectComputer(int id) {
    	  Computer computer;         
          computer = this.jdbcTemplate.query(selectComputer, (ResultSet resultSet, int row) -> computerMapper.resultSetToComputer(resultSet), id).get(0);     
          return Optional.ofNullable(computer);
    }
     
    public List<Computer> subList(int offset, int limit,String keyword) {
    	   String like = "";
           Object[] object;
 	
    	 if (keyword.length() > 0) {
             object = new Object[] {"%" + keyword + "%", limit, offset};
             like = " WHERE cu.name LIKE ?";
         } else {
             object = new Object[] {limit, offset};
         }
    	 
         String request = selectListRequest  + like + " LIMIT ? OFFSET ?;";
         return this.jdbcTemplate.query(request, object, (ResultSet resultSet, int row) -> computerMapper.resultSetToComputer(resultSet));
    }
    
    //Jirr's method
    public PreparedStatement computerToPreparedStatement(Computer computer, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, computer.getName());
        if (computer.getDateIntroduced().isPresent()) {
            preparedStatement.setDate(2, Date.valueOf(computer.getDateIntroduced().get()));
        } else {
            preparedStatement.setNull(2, java.sql.Types.DATE);
        }
        if (computer.getDateDiscontinued().isPresent()) {
            preparedStatement.setDate(3, Date.valueOf(computer.getDateDiscontinued().get()));
        } else {
            preparedStatement.setNull(3, java.sql.Types.DATE);
        }
        if (computer.getManufactor().isPresent()) {
            preparedStatement.setInt(4, computer.getManufactor().get().getId());
        } else {
            preparedStatement.setNull(4, java.sql.Types.INTEGER);
        }
        return preparedStatement;
    }
}