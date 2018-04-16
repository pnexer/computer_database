package com.excilys.formation.cdb.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum ConnexionManager {
    INSTANCE;

    private Connection conn;
    private Properties props;

    public Connection getConn() {
    	
        props = new Properties();
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e1) {
        }
        try {
            Class.forName(props.getProperty("jdbc.driver"));
        } catch (ClassNotFoundException e1) {
        }
        try {
            conn = DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.username"),props.getProperty("jdbc.password"));
        } catch (SQLException e) {
        }
        return conn;
    }
}
