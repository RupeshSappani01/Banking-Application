

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ChangePassword extends HttpServlet {
	String url="jdbc:oracle:thin:@//localhost:1521/XE";
 	String userid="system";
 	String passwd="root";
 	Connection con2=null;
 	 PreparedStatement pstmt2 = null;
 	PreparedStatement pstmt33 = null;       
    public void init(){
    	try
     	{
     		Class.forName("oracle.jdbc.driver.OracleDriver");
     		System.out.println("driver loaded");
     	}
     	catch(ClassNotFoundException e)
     	{
     		e.printStackTrace();
     	}
     	try
     	{
     		con2=DriverManager.getConnection(url,userid,passwd);
     	System.out.println("connection established");
     	}
     	catch(SQLException e)
     	{
     		e.printStackTrace();
     	}
    }
 public void service(HttpServletRequest req,HttpServletResponse rep){
	 try{
  	    pstmt2=con2.prepareStatement("select accno,password from SBI_BANK where accno=? AND password=? ");
        pstmt33=con2.prepareStatement("update sbi_bank set password=? where accno=?");
  	                 String accno=req.getParameter("fan");
  	           pstmt2.setString(1, accno);
  	         String password=req.getParameter("pwd");
  	           pstmt2.setString(2, password);
  	        ResultSet  res=pstmt2.executeQuery();
  	        if(res.next()){
  	        	String a=res.getString("accno");
  	        	String b=res.getString("password");
  	        	if(a.equals(accno)&& b.equals(password)){
  	        		  String npwd=req.getParameter("tan");
  	        		  String cpwd=req.getParameter("cpwd");
  	        		           if(npwd.equals(cpwd)){
  	        		        	  pstmt33.setString(1,cpwd); 
  	        		        	  pstmt33.setString(2,accno);
  	        		        	   pstmt33.executeUpdate();
  	        		        	  rep.sendRedirect("/SbiNetBanking/ChangePasswordSuccessful.html");
  	        		           }
  	        		           else{
  	        		          rep.sendRedirect("/SbiNetBanking/Incorrect11.html");
  	        	        }
  	        		           }
  	        	else{
  	        		rep.sendRedirect("/SbiNetBanking/Incorrect12.html");
  	        	}
  	        }
  	}
  	catch(Exception e){
  		e.printStackTrace();
  	}
 }
}
