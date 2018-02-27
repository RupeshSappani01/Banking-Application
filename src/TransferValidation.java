

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TransferValidation extends HttpServlet {
	public void service(HttpServletRequest req,HttpServletResponse rep){
		try {
		 String fan=req.getParameter("fan");
		 String pwd=req.getParameter("pwd");
		 if(fan==""||pwd=="") {
			 rep.sendRedirect("/SbiNetBanking/Tfincorrect.html");
		 }
		 else if(fan.length()!=6 && pwd.length()!=7){
			 
				rep.sendRedirect("Incorrect.html");
		
		 }
		 else{
			 try {
				req.getServletContext().getRequestDispatcher("/SbiTransfer").forward(req, rep);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}
		 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}
}