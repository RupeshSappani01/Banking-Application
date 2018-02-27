

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ChangePwdValidation extends HttpServlet {
	public void service(HttpServletRequest req,HttpServletResponse rep){
		try{
		   String accno=req.getParameter("fan");
		   String pwd=req.getParameter("pwd");
		   String npwd=req.getParameter("tan");
		   String cpwd=req.getParameter("cpwd");
		   if(accno==""||pwd==""||npwd==""||cpwd=="") {
			   rep.sendRedirect("/SbiNetBanking/Cpincorrect.html");
		   }
		   else if(accno.length()!=6 && pwd.length()!=7 &&npwd.length()!=7 && cpwd.length()!=7){
			   rep.sendRedirect("/SbiNetBanking/incorrectcp.html");
		   }
		   else{
			   req.getServletContext().getRequestDispatcher("/ChangePassword").forward(req, rep);
		   }
	}
		catch(Exception e){
			e.printStackTrace();
		}

}
}
