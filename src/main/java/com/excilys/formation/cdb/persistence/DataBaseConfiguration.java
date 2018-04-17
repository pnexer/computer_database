package com.excilys.formation.cdb.persistence;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
public class DataBaseConfiguration {

    @Resource
    private Environment env;
     
    @Bean
    public DataSource dataSource() {
        Properties props = new Properties();
        try {
			props.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
         
        dataSource.setDriverClassName(env.getRequiredProperty("com.mysql.cj.jdbc.Driver"));
        dataSource.setUrl(env.getRequiredProperty("jdbc:mysql://localhost:3306/computer-database-db?useSSL=FALSE&amp;useUnicode=true&amp;useJDBCCompliantTimezoneShift=true&amp;serverTimezone=CET"));
        dataSource.setUsername(env.getRequiredProperty("admincdb"));
        dataSource.setPassword(env.getRequiredProperty("qwerty1234"));
         
        return dataSource;
    }
}