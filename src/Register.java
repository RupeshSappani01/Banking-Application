

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


public class Register extends HttpServlet {
	String url="jdbc:oracle:thin:@//localhost:1521/XE";
	String userid="system";
	String passwd="root";
	Connection con1=null;
	 PreparedStatement pstmt1 = null;
	 PreparedStatement pstmt2=null;
	 ResultSet res=null;
public void init(){
   try
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con1=DriverManager.getConnection(url,userid,passwd);

	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
public void service (HttpServletRequest x,HttpServletResponse y){
try{
	pstmt1=con1.prepareStatement("insert into SBI_BANK values(?,?,?,?,?)");
	pstmt2=con1.prepareStatement("Select accno from SBI_BANK  where accno=?");
	         String accno= x.getParameter("raccno");
	     
	    	  pstmt1.setString(1, accno);
	          pstmt2.setString(1, accno);
	         ResultSet res1=pstmt2.executeQuery();
	          if(res1.next()){
	        	  String r=res1.getString("accno");
	          if(accno.equals(r)){
	        	y.sendRedirect("/SbiNetBanking/exists.html");  
	          }
	          }
	           String name = x.getParameter("name");  
	           
	        	  pstmt1.setString(2, name);
	           
	        String acctype= x.getParameter("accnotype"); 
	       
	        	pstmt1.setString(4, acctype);
	        
	             String pwd=x.getParameter("rpwd");
	             String cpwd=x.getParameter("cpwd");
	             if(pwd.equals(cpwd)){
	            	 pstmt1.setString(3, cpwd);
	             }
	             else{
	            	 y.sendRedirect("/SbiNetBanking/Validation1.html");
	             }
	              String balance= x.getParameter("amt");
	                      int b1 =Integer.parseInt(balance);
	                    if(b1<100000) {
	                    	pstmt1.setInt(5,b1);
	                    }
	                    else{
	                    	y.sendRedirect("/SbiNetBanking/Validation2.html");
	                    }
	              pstmt1.executeQuery();      
	            y.sendRedirect("/SbiNetBanking/login1.html");
          
} 
catch (Exception e) {
	
	e.printStackTrace();
}



}
}
