

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginValidation extends HttpServlet {
	public void service(HttpServletRequest x,HttpServletResponse y){
		try{
		         String accno=x.getParameter("accno");
		         String password=x.getParameter("pwd");
		         if(accno==""||password=="" ){
		        	 y.sendRedirect("/SbiNetBanking/loginpageError2.html");
		         }
		         else{
		       if(accno.length()!=6 && password.length()!=7 ){
		    	   y.sendRedirect("/SbiNetBanking/loginpageError.html");
		       }
		       else
		       {
		    	   x.getServletContext().getRequestDispatcher("/LoginResult").forward(x, y);
		       }
		         }
	}
    catch(Exception e){
    	e.printStackTrace();
    }
	

}
}
