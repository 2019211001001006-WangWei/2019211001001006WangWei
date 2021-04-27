package com.WangWei.controller;

import com.WangWei.dao.UserDao;
import com.WangWei.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.sql.Date;

@WebServlet(name = "UpdateUserServlet", value = "/updateUser")
public class UpdateUserServlet extends HttpServlet {
    Connection con = null;
    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/UpdateUser.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date date = null;
        try {
            date = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthdate")).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        User u = new User(Integer.parseInt(request.getParameter("id")),request.getParameter("username"),
                request.getParameter("password"), request.getParameter("email"),
                request.getParameter("gender"), date);
        UserDao userdao = new UserDao();
        con = (Connection) getServletContext().getAttribute("con");
        try {

            if( userdao.updateUser(con,u) > 0){
                HttpSession session = request.getSession();
                session.setAttribute("user",u);
                request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request,response);
            }
            else{
                request.setAttribute("message","Update failed!");
                request.getRequestDispatcher("WEB-INF/views/UpdateUser.jsp").forward(request,response);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
