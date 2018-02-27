

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class Balance extends HttpServlet {
	String url="jdbc:oracle:thin:@//localhost:1521/XE";
	String userid="system";
	String passwd="root";
	Connection con=null;
	 PreparedStatement pstmt = null;
	 ResultSet res=null;
	 PrintWriter pw=null;
public void init(){
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection(url,userid,passwd);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
}
public void service (HttpServletRequest req,HttpServletResponse rep){
	try
	{
	pstmt  =con.prepareStatement("select balance,name,accno from sbi_bank where accno=? AND password=?");
	
           String    accno= req.getParameter("accno1");
        
	        pstmt.setString(1, accno);
      
	      String    password=req.getParameter("pwd1");
	     pstmt.setString(2, password);
	       ResultSet res=pstmt.executeQuery();
	          pw=rep.getWriter();
	       if (res.next()) {
	     String b=res.getString("name");
	      int a=res.getInt("balance");
	     String c=res.getString("accno");
	      
	       pw.println("<html>");
	        pw.println("<head>");
	        pw.println("<title>Success</title>");
	        pw.println("<link href=\"balance11.css\" rel=\"stylesheet\" type=\"text/css\">");
	        pw.println("</head");
	        pw.println("<body>");
	        pw.println("</br> ");
	        pw.println(" </br>");
	        pw.println(" </br>");
	        pw.println(" </br>");
	        pw.println(" </br>");
	        pw.println("<center>");
	        pw.println("<h2>");
	        pw.println("Hello     "+b);
	        pw.println("</h2>");
	        pw.println("</br> ");
	        pw.println(" </br>");
	        pw.println("</br> ");
	        pw.println("</br> ");
	        pw.println("<table border=2 cellspacing=10px>");
	        pw.println("<tr>");
	        pw.println("<pre>");
	        pw.println("<th>");
	        pw.println("   Accno");
	        pw.println("</th>");
	        pw.println("</pre>");
	        pw.println("<th>");
	        pw.println("Current Balance");
	        pw.println("</th>");
	        pw.println("</tr>");
	        pw.println("<tr>");
	        pw.println("<pre>");
	        pw.println("<td style=font:fantasy>");
	        pw.println("  "+c);
	        pw.println("</td>");
	        pw.println("</pre>");
	        pw.println("<td style=font-size:15px>");
	        pw.println("<pre>");
	        pw.println("      RS:"+a);
	        pw.println("</pre>");
	        pw.println("</td>");
	        pw.println("</tr>");
	        pw.println("</table>");
	        pw.println("</br> ");
	        pw.println("</br> ");
	        pw.println("</br> ");
	        pw.println("</br> ");
	        pw.println("<a style=font-size:25px href=\"Logout.html\" >LogOut</a>");
	        pw.println("</center>");
	        pw.println("</body>");
	       
	        pw.println("</html");
			}
	       else{
	    	  rep.sendRedirect("Error.html");
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
		pw.close();
	}
	catch(Exception e){
		e.printStackTrace();
	}
}
}
	 

