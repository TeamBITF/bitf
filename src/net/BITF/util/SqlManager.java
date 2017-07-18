package net.BITF.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SqlManager {

	public ResultSet execute(String palam){

		try {
			Statement state = init();
			ResultSet result = state.executeQuery(palam);

			result.close();
			state.close();

			return result;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private Statement init(){
		System.out.println("SQL Start");

		try{
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			String url = "jdbc:sqlserver://172.16.31.8/SQLEXPRESS;database=BITF;integratedSecurity=false;user=BITFkagi810;password=123456";

			Connection connection = driver.connect(url, new Properties());
			Statement state = connection.createStatement();

			return state;
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return null;
	}
}
