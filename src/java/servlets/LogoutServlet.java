
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        HttpSession ses = req.getSession();
        ses.removeAttribute("user");
        ses.setAttribute("msg", "Logout Successfull...!");
        resp.sendRedirect(req.getContextPath());
        return;
    }
    
}
