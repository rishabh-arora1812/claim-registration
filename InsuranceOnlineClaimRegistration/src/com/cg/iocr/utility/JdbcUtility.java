package com.cg.iocr.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.cg.iocr.exception.ClaimRegException;

public class JdbcUtility {

	private static Connection connection = null;
	static Logger logger = Logger.getLogger(JdbcUtility.class);

	public static Connection getConnection() throws ClaimRegException {

		File file = null;
		FileInputStream inputStream = null;
		Properties properties = null;

		file = new File("resources/jdbc.properties");

		try {
			inputStream = new FileInputStream(file);
			properties = new Properties();
			properties.load(inputStream);

			String driver = properties.getProperty("db.driver");
			String url = properties.getProperty("db.url");
			String username = properties.getProperty("db.username");
			String password = properties.getProperty("db.password");
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
			
			
			logger.info("Connected to data base.....");
			
		} catch (FileNotFoundException e) {
			logger.error("File not found.....");
			throw new ClaimRegException("File not found.....");

		} catch (IOException e) {
			logger.error("Could not able to read from the file.....");
			throw new ClaimRegException("Could not able to read from the file.....");
		} catch (ClassNotFoundException e) {
			logger.error("Class not loadeded.....");
			throw new ClaimRegException("Class not loadeded.....");
		} catch (SQLException e) {
			logger.error("Connection issues.....");
			throw new ClaimRegException("Connection issues(connecting to database).....");
		}

		return connection;
	}

}
