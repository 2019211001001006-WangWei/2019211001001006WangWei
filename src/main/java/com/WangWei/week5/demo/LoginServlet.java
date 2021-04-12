package com.WangWei.week5.demo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    Connection con = null;
    @Override
    public void init() throws ServletException {//link sqlserver
        super.init();
//        String driver = getServletConfig().getServletContext().getInitParameter("driver");
//        String url = getServletConfig().getServletContext().getInitParameter("url");
//        String username = getServletConfig().getServletContext().getInitParameter("Username");
//        String password = getServletConfig().getServletContext().getInitParameter("Password");
//        try {
//            Class.forName(driver);
//            con = DriverManager.getConnection(url,username,password);
//            System.out.println("hell0");
//        } catch (ClassNotFoundException | SQLException e) {
//            System.out.println("error");
//            e.printStackTrace();
//        }
        con = (Connection) getServletContext().getAttribute("con");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sql = "select * from  Usertable where username = ? and password = ?";
        PreparedStatement st = null;
        PrintWriter writer = response.getWriter();
        try {
            st = con.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                //week5-HW
//                writer.println("successful!!!");
//                writer.println("Welcome," + username );
                //week6-HW
                request.setAttribute("id",rs.getInt("id"));
                request.setAttribute("username",rs.getString("username"));
                request.setAttribute("password",rs.getString("password"));
                request.setAttribute("email",rs.getString("email"));
                request.setAttribute("gender",rs.getString("gender"));
                request.setAttribute("birthdate",rs.getString("birthdate"));
                request.getRequestDispatcher("userInfo.jsp").forward(request,response);
            }else{
                //week5-HW
//                writer.println("Username or Password Error!");
                request.setAttribute("message", "Username or Password Error!");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
