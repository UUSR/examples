package com.examples.db.my;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by UUSR on 23.03.16.
 */

public class MyDb {
	
    public static void main(String[] args)throws Exception {
		createTable();
		post();                
	}
    
    public static void createTable() throws Exception{
    	try {
    		 Connection con = getConnection();
    		 PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS mybrain"
    		 		+"(id int NOT NULL AUTO_INCREMENT,"
    		 		+"question varchar(255),"
    		 		+"answer varchar(255),"
    		 		+"PRIMARY KEY(id),"
    		 		+"Date DATE NOT NULL,"
    		 		+"Time TIME NOT NULL)");
    		 
    		 create.executeUpdate();    	    		
    	}  catch(Exception e){System.out.println(e);}
    	finally{ 
    	    System.out.println("Таблица создана");
        }	
    }
    
    public static void post() throws Exception{    	
    	java.util.Date myDate = new java.util.Date();
    	java.util.Date myTime = new java.util.Date();
    	java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
    	java.sql.Time sqlTime = new java.sql.Time(myTime.getTime());
        final String var1 = "вопрос1";
        final String var2 = "ответ1";
        try {
        	 Connection con = getConnection();
			 PreparedStatement posted = con.prepareStatement("INSERT INTO mybrain"
        	 		+"(question, answer, Date, Time)"
        	 		+ "VALUES ('"+var1+"', '"+var2+"', ?, ?)");
			 posted.setDate(1, sqlDate);
			 posted.setTime(2, sqlTime);
        	 posted.executeUpdate();
        }  catch(Exception e){System.out.println(e);}
        finally {
        	System.out.println("Запись добавлена"); 
        }
    }    
    
    public static Connection getConnection() throws Exception{
        try{
        	String Driver = "com.mysql.jdbc.Driver";
        	String url = "jdbc:mysql://localhost:3306/mybrain";
        	String login = "root";
        	String pass = "toor";
        	Class.forName(Driver);
        	
        	Connection conn = DriverManager.getConnection(url, login, pass);
        	System.out.println("Подключено");
        	return conn;
        	}catch(Exception e ){System.out.println(e);}
				
	    return null;	
	}
	
}