package net.BITF.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class SqlManager {
	public SqlManager(){

		System.out.println("SQL Start");

		try{
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			String url = "jdbc:sqlserver://172.16.31.8/SQLEXPRESS;database=BITF;integratedSecurity=false;user=BITFkagi810;password=123456";

			Connection connection = driver.connect(url, new Properties());

			String select = "select name, score from ScoreBoard order by score desc";
			Statement state = connection.createStatement();

			ResultSet result = state.executeQuery(select);


			while(result.next()){
				System.out.println(result.getString("name" + " : " + result.getString("score")));
			}

			result.close();
			state.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}

		System.out.println("SQL End");

	}

}
