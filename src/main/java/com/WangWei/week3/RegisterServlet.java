package com.WangWei.week3;

import com.WangWei.week4.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@WebServlet(name="RegisterServlet", value="/register")
public class RegisterServlet extends HttpServlet {
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
        PrintWriter writer = response.getWriter();
        writer.println("doGet()...");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String birthDate = request.getParameter("birthdate");
        /*HW-week3
        PrintWriter writer = response.getWriter();
        writer.println("<br>username: " + username);
        writer.println("<br>password: " + password);
        writer.println("<br>email: " + email);
        writer.println("<br>gender: " + gender);
        writer.println("<br>birthDate: " + birthDate);
        writer.close();*/
        String sql = "insert into Usertable values(?,?,?,?,?)";
        try {
            //insert sqlserver；
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            st.setString(3, email);
            st.setString(4, gender);
            st.setString(5, birthDate);
            if(st.executeUpdate() > 0){
                System.out.println("successful!!!");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        List<User> retList = new ArrayList<User>();//学生集合
        String sqlSearch = "select * from Usertable";
        try {
            PreparedStatement stml = con.prepareStatement(sqlSearch);
            ResultSet sea = stml.executeQuery();
            while(sea.next()){//将查询的信息存入学生集合；
                User s = new User();
                s.setUsername(sea.getString("username"));
                s.setPassword(sea.getString("password"));
                s.setEmail(sea.getString("email"));
                s.setGender(sea.getString("gender"));
                s.setBirthDate(sea.getString("birthdate"));

                retList.add(s);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        PrintWriter writer = response.getWriter();
        writer.println("<table border = \"1\"> <tr> <td>username</td> <td>password</td> <td>email</td>  " +
                "<td>gender</td> <td>birthDate</td> </tr>");
        for(User s: retList){
            writer.println("<tr>" + "<td>" + s.getUsername() + "</td> ");
            writer.println("<td>" + s.getPassword() + "</td> ");
            writer.println("<td>" + s.getEmail() + "</td> ");
            writer.println("<td>" + s.getGender() + "</td> ");
            writer.println("<td>" + s.getBirthDate() + "</td> </tr> ");
        }
        writer.println("</table> ");
        writer.close();
    }
    public void destroy(){
        super.destroy();
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
