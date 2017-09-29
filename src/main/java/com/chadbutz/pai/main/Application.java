package com.chadbutz.pai.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import com.mysql.jdbc.Statement;

@SpringBootApplication(
    scanBasePackages = {
        "com.chadbutz.pai.config", "com.chadbutz.pai.rest"
    }
)
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
		Connection conn = null;
		
		try {
		    conn =
		       DriverManager.getConnection("jdbc:mysql://db:3306/pai?" +
		                                   "user=mysql&password=mysql");

		    // Do something with the Connection
		    Statement stmt = (Statement)conn.createStatement();
		    stmt.execute("select * from test");
		    ResultSet rs = stmt.getResultSet();
		    String test = "";
		    while (rs.next()) {
		    	test = rs.getString("name");
		    }
		    System.out.println(test);
		    
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		
		}
	}
}