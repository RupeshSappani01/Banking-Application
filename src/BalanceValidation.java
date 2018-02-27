

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BalanceValidation extends HttpServlet {
	
public void service(HttpServletRequest req,HttpServletResponse rep){
	try{
	        String accno=req.getParameter("accno1");
	        String pwd=req.getParameter("pwd1");
	        if(accno==""||pwd=="") {
	        	rep.sendRedirect("/SbiNetBanking/Bincorrect.html");
	        }
	        else if(accno.length()!=6 && pwd.length()!=7){
        rep.sendRedirect("/SbiNetBanking/Error1.html");
   }
       req.getServletContext().getRequestDispatcher("/Balance").forward(req, rep);

}
catch(Exception e){
	e.printStackTrace();
}
}

}
