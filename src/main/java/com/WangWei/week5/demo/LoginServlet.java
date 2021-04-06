package com.WangWei.week5.demo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    Connection con;
    @Override
    public void init(){//link sqlserver
        String driver = getServletConfig().getServletContext().getInitParameter("driver");
        String url = getServletConfig().getServletContext().getInitParameter("url");
        String username = getServletConfig().getServletContext().getInitParameter("Username");
        String password = getServletConfig().getServletContext().getInitParameter("Password");
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,username,password);
            System.out.println("hell0");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }
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
            if(st.executeQuery().next()){
                writer.println("successful!!!");
                writer.println("Welcome," + username );
            }else{
                writer.println("Username or Password Error!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
