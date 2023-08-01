package servlets;

import Bean.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

public class RegisterServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException{
		
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
//            System.out.println("b");
            StringBuffer Name = new StringBuffer(request.getParameter("Name").trim().toLowerCase());
            char x = Name.charAt(0);
            x = Character.toUpperCase(x);
            Name.setCharAt(0, x);
            StringBuffer Sname = new StringBuffer(request.getParameter("Sname").trim().toLowerCase());
            char y = Sname.charAt(0);
            y = Character.toUpperCase(y);
            Sname.setCharAt(0, y);
            String Email = request.getParameter("Email");
            String Mnumber = request.getParameter("MNumber");
            String Password = request.getParameter("Password");
            String Address = request.getParameter("Address").trim().toUpperCase();
            String Type = "normal";
            
            System.out.println(Name);
            System.out.println(Sname);
            System.out.println(Email);
            System.out.println(Mnumber);
            System.out.println(Password);
            System.out.println(Address);

//            System.out.println("c");
//        ses.setAttribute("msg", Mnumber);
//        ses.setAttribute("user", user);
//        ses.setAttribute("error", null);
//        response.sendRedirect(request.getContextPath());
            if (!Name.equals("") && !Sname.equals("") && Email != "" && Mnumber !="" && Password != "" && Address!="") 
            {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyProject", "root", "super");

                String sql = "insert into user(uname,sname,email,mno,pswd,addrs, utype) values(?,?,?,?,?,?,?)";
//                System.out.println("d");

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, Name.toString());
                ps.setString(2, Sname.toString());
                ps.setString(3, Email);
                ps.setString(4, Mnumber);
                ps.setString(5, Password);
                ps.setString(6, Address);
                ps.setString(7, Type);

                int n = ps.executeUpdate();
                

                if (n == 1) {

//                    System.out.println("e");
                    User user = new User(Name.toString(), Sname.toString(), Email, Mnumber, Password, Address, Type);

                    ses.setAttribute("user", user);
                    ses.setAttribute("msg", "You Registered Successfully..!");
                    response.sendRedirect(request.getContextPath());
                    return;
                } else {
                    throw new Exception("Rows Not Affected..!");
                }
            }
            else
            {
                throw new Exception("Please Fill All the Values");
            }

        } catch (Exception ex) {

            System.out.println(ex);
            ses.setAttribute("error", ex);
            response.sendRedirect((request.getContextPath() + "/components/sign_up.jsp"));
            return;

        }

    }

}
