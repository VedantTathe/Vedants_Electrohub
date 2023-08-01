package servlets;

import Bean.Product;
import Bean.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//package servlets;

public class BuyNowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.print("POST");

        HttpSession ses = request.getSession();

        Object check = ses.getAttribute("user");
        if (check != null) {

            User user = (User) ses.getAttribute("user");

            
        
            Product product = (Product) ses.getAttribute("product");
            
            String pcat = product.getPCat();
            
            
            ses.setAttribute("msg", "Your order is placed successfuly \n And your "+pcat.substring(0,pcat.length()-1)+" will be delivered soon...");
            response.sendRedirect(request.getContextPath() + "/product_details.jsp");
            
        } else {
            ses.setAttribute("direct_product", "true");
            ses.setAttribute("err", "Please Login Before Buying your Order..!");
            response.sendRedirect(request.getContextPath() + "/components/login.jsp");

        }

    }

}
