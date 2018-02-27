

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


public class SbiTransfer extends HttpServlet {
	String url="jdbc:oracle:thin:@//localhost:1521/XE";
	String userid="system";
	String passwd="root";
	Connection con6=null;
	PreparedStatement pstmt6 = null;
	PreparedStatement pstmt7 = null;
	PreparedStatement pstmt77 = null;
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
			con6=DriverManager.getConnection(url,userid,passwd);
			System.out.println("connection established");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
public void service(HttpServletRequest req,HttpServletResponse rep){
	try{
		con6.setAutoCommit(false);
		pstmt7=con6.prepareStatement("update SBI_BANK set balance=balance-? where accno=? AND password=?");
		pstmt77=con6.prepareStatement("update SBI_BANK set balance=balance+? where accno=? ");
		pstmt6=con6.prepareStatement("select accno,password,balance from SBI_BANK where accno=?");
	     String fan=req.getParameter("fan");
	     String pwd=req.getParameter("pwd") ;
	     if(fan==""||pwd=="") {
	    	 rep.sendRedirect("/SbiNetBanking/Tfincorrect.html");
	     }
		  pstmt6.setString(1, fan);
		ResultSet res=pstmt6.executeQuery();
		if(res.next()){
		String a=res.getString("accno");
		String b=res.getString("password");
		int    c=res.getInt("balance");
		
	    if(a.equals(fan) && b.equals(pwd))
	    {
		String tan=req.getParameter("tan");
		String  amount=req.getParameter("amt");
		int amt= Integer.parseInt(amount);
		if(amt<=c){
		pstmt7.setInt(1, amt);
		pstmt7.setString(2, fan);
		pstmt7.setString(3,pwd);
		pstmt7.executeUpdate();
		
		pstmt77.setInt(1, amt);
		pstmt77.setString(2, tan);
		pstmt77.executeUpdate();
	     con6.commit();
	     rep.sendRedirect("TransactionSuccessful.html");
		}
		else{
			rep.sendRedirect("Insufficentamt.html");
		}
		}
		else{
			rep.sendRedirect("IncorrectDetails.html");
		}
		}
		
	}
	catch(Exception e){
        try{
        	con6.rollback();
        }
        catch(Exception e1){
      e.printStackTrace();
        }

	}
}
	
}
	


