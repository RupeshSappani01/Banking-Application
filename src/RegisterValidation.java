

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisterValidation extends HttpServlet {
	public void service(HttpServletRequest x,HttpServletResponse y){
		try{
			    String accno=x.getParameter("raccno");
			    String name=x.getParameter("name");
			    String pwd=x.getParameter("rpwd");
			    String acctype=x.getParameter("accnotype");
			            String amt=x.getParameter("amt");
			 if(name=="" ||amt==""){
	        	   y.sendRedirect("/SbiNetBanking/Validation.html");
	           }
		      else{
		    	 
			    	  if(acctype=="")
			   	        {
			   	        	y.sendRedirect("/SbiNetBanking/Validation.html");
			   	        
			      }
		           else{
		        	   
		        	   if(accno.length()!=6 && pwd.length()!=7){
					    	  y.sendRedirect("/SbiNetBanking/Validation3.html");
		        	   
		        	   
		        	   }
		   	        
		           }
		      }
			 x.getServletContext().getRequestDispatcher("/Register").forward(x, y);
		}
		catch(Exception e){
			
		}
	}

}
