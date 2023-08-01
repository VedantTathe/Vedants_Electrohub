package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        HttpSession ses = request.getSession();

        try {

            ses.setAttribute("msg", null);
            ses.setAttribute("error", null);

            String CName = request.getParameter("CName").trim().toUpperCase();
            String CDesc = request.getParameter("CDesc").trim().toUpperCase();
            Part part = request.getPart("CImage");

            out.println(CName);
            out.println(CDesc);

            String CImageName = part.getSubmittedFileName();

            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyProject", "root", "super");

            String sql = "insert into category(cname, cdesc, cimgname) values(?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, CName);
            ps.setString(2, CDesc);
            ps.setString(3, CImageName);
            
            String path = request.getRealPath("components" + File.separator + "images" + File.separator + "category" + File.separator + CImageName);

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

                

                ses.setAttribute("msg", "Category Added Successfuly..!");
                response.sendRedirect("admin.jsp");
                out.println("success");
                return;

            } else {
                throw new Exception("Category Not Added...!");
            }

        } catch (Exception ex) {
            out.println(ex);

            System.out.println(ex);
            ses.setAttribute("error", ex);
            response.sendRedirect("admin.jsp");
            return;
        }

    }

}
