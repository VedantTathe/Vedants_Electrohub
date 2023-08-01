package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.sql.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;

@MultipartConfig
public class ProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("POST Products");

        HttpSession ses = request.getSession();
        try {

            ses.setAttribute("msg", null);
            ses.setAttribute("error", null);

            String PName = request.getParameter("PName");
            String PDesc = request.getParameter("PDesc");
            String PCat = request.getParameter("PCat");
            String price = request.getParameter("PPrice");
            String reviewsnum = request.getParameter("PReviewsNum");
            String rating = request.getParameter("PRating");
            Part part = request.getPart("PImage");

            out.println(PName);
            out.println(PDesc);
            out.println(PCat);
            out.println(price);
            out.println(reviewsnum);
            out.println(rating);

//          System.out.println("0");
            String PImageName = part.getSubmittedFileName();

            out.println(PImageName);

            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyProject", "root", "super");

            String sql = "insert into products(pname, pdesc, pcat, price, reviewsnum, rating, imgname) values(?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, PName);
            ps.setString(2, PDesc);
            ps.setString(3, PCat);
            ps.setString(4, price);
            ps.setString(5, reviewsnum);
            ps.setString(6, rating);
            ps.setString(7, PImageName);

            String path = request.getRealPath("components" + File.separator + "images" + File.separator + "products" + File.separator + PImageName);

            FileOutputStream fout = new FileOutputStream(path);

            InputStream is = part.getInputStream();

            byte data[] = new byte[is.available()];

            is.read(data);

            fout.write(data);

            is.close();

            fout.close();

            int n = ps.executeUpdate();

            ps.close();
            con.close();

            if (n == 1) {
//              System.out.println("2");

                ses.setAttribute("msg", "Product Added Successfuly..!");
                response.sendRedirect("admin.jsp");
//                out.println("success");
                return;

            } else {
//            System.out.println("3");
                throw new Exception("Product Not Added...!");
            }

        } catch (Exception ex) {
            System.out.println(ex);
            ses.setAttribute("error", ex);
            response.sendRedirect("admin.jsp");
//out.println(ex);
        }

//        System.out.println("4");
    }

}
