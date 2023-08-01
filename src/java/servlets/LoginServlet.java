package servlets;

import Bean.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import javax.servlet.ServletException;

public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        PrintWriter out = response.getWriter();
        out.print("GET");
        System.out.println("GET");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POST");
        HttpSession ses = request.getSession();
        ses.setAttribute("user", null);
        ses.setAttribute("msg", null);
        ses.setAttribute("error", null);

//        System.out.println("a");
        PrintWriter out = response.getWriter();
        try {
//          System.out.println("b");

            String email = request.getParameter("Email");
            String pswd = request.getParameter("Password");

            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyProject", "root", "super");
            String sql = "select uname, sname, email, mno, pswd, addrs, utype from user where email = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String Password = rs.getString("pswd");
                System.out.println(pswd);
                System.out.println(Password);
                if (pswd.equals(Password)) {

                    String Name = rs.getString("uname");
                    String Sname = rs.getString("sname");
                    String Email = rs.getString("email");
                    String Mnumber = rs.getString("mno");
                    String Address = rs.getString("addrs");
                    String Type = rs.getString("utype");
                  

                    User user = new User(Name, Sname, Email, Mnumber, Password, Address, Type);

                    ses.setAttribute("user", user);
                    ses.setAttribute("msg", "Login Successfull...!");
                    response.sendRedirect(request.getContextPath());
                    return;
                } 
                else {
                    
                    ses.setAttribute("error", "Password is Wrong..!");
                    response.sendRedirect((request.getContextPath() + "/components/login.jsp"));
                    return;
                }
            } 
            else {
                
                ses.setAttribute("error", "Account Not Exist. Please First Register Here");
                response.sendRedirect((request.getContextPath() + "/components/sign_up.jsp"));
                return;
            }
        } catch (Exception ex) {
            System.out.println(ex);
            ses.setAttribute("error", ex);
            response.sendRedirect((request.getContextPath() + "/components/login.jsp"));
            return;

        }

    }

}
