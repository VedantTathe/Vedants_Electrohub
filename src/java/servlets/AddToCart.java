
package servlets;

import Bean.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddToCart extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
            PrintWriter out = response.getWriter();
            out.println("GET");
            out.println("6");
            HttpSession ses = request.getSession();
            out.println("5");
            Product product = (Product)ses.getAttribute("product");
           if(product == null)
           {
               out.println("4");
            ses.setAttribute("err", "Something went wrong..!");
            response.sendRedirect(request.getContextPath()+"/product_details.jsp");
           }
           else{
               
            out.println("3");
            Cookie cookie1 = new Cookie("mycart"+product.getPImageName(), product.getPImageName());
            response.addCookie(cookie1);
            out.println("2");
            ses.setAttribute("msg", "Product Added to Your Cart");
            response.sendRedirect(request.getContextPath()+"/product_details.jsp");
            out.println("1");
           }
                
    }
    
}
