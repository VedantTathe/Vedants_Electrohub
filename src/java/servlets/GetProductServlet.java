
package servlets;

import Bean.Product;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

public class GetProductServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession ses = request.getSession();
        ses.setAttribute("msg", null);
        ses.setAttribute("err", null);
        ses.setAttribute("product", null);
        
        out.println("Get");
        
        String elem = request.getParameter("ClickedElem");
        out.println(elem);
        
        try
        {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyProject", "root", "super");
            String sql = "select * from products where imgname = ?"; 
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, elem);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                String PName = rs.getString("pname");
                String PDesc = rs.getString("pdesc");
                String PCat = rs.getString("pcat"); 
                String PPrice = rs.getString("price");
                String PReviewsNum = rs.getString("reviewsnum");
                String PRating = rs.getString("rating");
                String PImageName= rs.getString("imgname");
                
                out.println(PName);
                out.println(PDesc);
                out.println(PCat);
                out.println(PPrice);
                out.println(PReviewsNum);
                out.println(PRating);
                out.println(PImageName);
                
                Product product = new Product(PName, PDesc, PCat, PPrice, PReviewsNum, PRating, PImageName);
                ses.setAttribute("product", product);
//                ses.setAttribute("msg", "Successfull..!");
                response.sendRedirect(request.getContextPath()+"/product_details.jsp");
                return;
            }
            else
            {
                throw new Exception("Product Not Found..!");
            }
            
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            ses.setAttribute("error", ex);
            response.sendRedirect(request.getContextPath()+"/product_details.jsp");
            return;
        }
        
        
    }
    
    
}
