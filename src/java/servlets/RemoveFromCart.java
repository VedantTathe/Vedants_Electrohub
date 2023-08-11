
package servlets;

import Bean.Product;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RemoveFromCart extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
            PrintWriter out = response.getWriter();
            out.println("GET");
           
            HttpSession ses = request.getSession();
            
            String imgname = request.getParameter("ClickedElem");
               
            
            Cookie cookie1 = new Cookie("mycart"+imgname, "");
            cookie1.setMaxAge(0);
            response.addCookie(cookie1);
          
            ses.setAttribute("msg", "Product Removed From Your Cart");
            response.sendRedirect(request.getContextPath()+"/mycart.jsp");
          
           
                
    }
    
}
