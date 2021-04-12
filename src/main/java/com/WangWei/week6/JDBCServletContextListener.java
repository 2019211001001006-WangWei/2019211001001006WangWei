package com.WangWei.week6;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@WebListener
public class JDBCServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String driver = context.getInitParameter("driver");
        String url = context.getInitParameter("url");
        String username = context.getInitParameter("Username");
        String password = context.getInitParameter("Password");
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url,username,password);
            System.out.println("hell0");
            context.setAttribute("con",con);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute("con");

    }
}
