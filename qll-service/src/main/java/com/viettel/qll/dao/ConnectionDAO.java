package com.viettel.qll.dao;


import java.sql.ResultSet;
 
public class ConnectionDAO {
	public static void main(String args[]){  
		try{  
		//step1 load the driver class  
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		  
//		//step2 create  the connection object  
//		Connection con=DriverManager.getConnection(  
//		"jdbc:oracle:thin:@10.30.145.55:1521/qlct","CTCT_COMS_OWNER","Viettel#1235");  
//		  
//		//step3 create the statement object  
//		Statement stmt=con.createStatement();  
		  
//		//step4 execute query  
//		ResultSet rs=DataController.getInstance().getStatement().executeQuery("select DEPARTMENT_NAME departmentName from KPI_DEPARTMENT");
//		rs.next();
//		String departmentName = rs.getString("departmentName");
//		while(rs.next())  
//		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
		  
		
		//step5 close the connection object  
		DataController.getInstance().closeDB();
		  
		}catch(Exception e){ System.out.println(e);}  
		  
		}  
}
