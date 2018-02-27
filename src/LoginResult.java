

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


public class LoginResult extends HttpServlet {
	
	String url="jdbc:oracle:thin:@//localhost:1521/XE";
	String userid="system";
	String passwd="root";
	Connection con=null;
	 PreparedStatement pstmt = null;
	 ResultSet res=null;
	
public void init(){
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection(url,userid,passwd);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
}
public void service (HttpServletRequest x,HttpServletResponse y){
	try
	{
	pstmt  =con.prepareStatement("select accno,password from sbi_bank where accno=? AND password=?");
	
           String    accno= x.getParameter("accno");
        
	
	        pstmt.setString(1, accno);
 
	      String password=x.getParameter("pwd");
	     pstmt.setString(2, password);
	       ResultSet res=pstmt.executeQuery();
	          
	       if (res.next()) {
	   
	      String c=res.getString("accno");
	      String d=res.getString("password");
	      
	      
	      if(accno.equals(c)&& password.equals(d)){
	    	 y.sendRedirect("/SbiNetBanking/LoginSuccessful.html"); 
	      }
	      

			}
	       else{
		    	  y.sendRedirect("/SbiNetBanking/LoginPageError1.html");
	       }
	      
	       
	       
	      
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
}
public void destroy(){
	try{
		con.close();
		pstmt.close();
		res.close();
		
	}
	catch(Exception e){
		e.printStackTrace();
	}
}
}
