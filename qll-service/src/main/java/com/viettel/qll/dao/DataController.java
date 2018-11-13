package com.viettel.qll.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataController {

	private static Connection connection = null;
	private static DataController _instantce = null;

	private DataController() {

	}

	public static synchronized DataController getInstance() {
		try {
			if (_instantce == null || connection == null) {
				_instantce = new DataController();

				if (connection != null)
					connection.close();

				connection = DriverManager.getConnection("jdbc:oracle:thin:@10.61.19.199:1521/dbtest", "PMTL2",
						"PMTL2");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _instantce;
	}

	public void closeDB() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Statement getStatement() {
		Statement stm = null;
		try {
			if (connection != null)
				stm = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stm;
	}

}
