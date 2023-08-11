package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.http.HttpSession;

public class MessageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post");
        PrintWriter out = response.getWriter();
        HttpSession ses = request.getSession();

        ses.setAttribute("msg", null);
        ses.setAttribute("error", null);

        String Name = request.getParameter("Name").trim().toUpperCase();
        String Sname = request.getParameter("Sname").trim().toUpperCase();
        String Email = request.getParameter("Email").trim().toLowerCase();
        String Mnumber = request.getParameter("MNumber");
        String Message = request.getParameter("Message").trim().toUpperCase();

        try {
            if (Name != "" && Sname != "" && Email != "" && Mnumber != "" && Message != "") {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyProject", "root", "super");

                String sql = "insert into messages(uname, sname, email, mno, msg) values(?,?,?,?,?)";

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, Name);
                ps.setString(2, Sname);
                ps.setString(3, Email);
                ps.setString(4, Mnumber);
                ps.setString(5, Message);

                int n = ps.executeUpdate();

                if (n == 1) {
                    ses.setAttribute("msg", "Message Submitted Successfully..!");
                    response.sendRedirect(request.getContextPath());
                    return;
                } else {
                    throw new Exception("Record Not Inserted..!");
                }

            } else {
                throw new Exception("Please Fill All the Values");
            }
        } catch (Exception ex) {
            System.out.println(ex);
            ses.setAttribute("error", ex);
            response.sendRedirect(request.getContextPath());
            return;
        }

    }

}
