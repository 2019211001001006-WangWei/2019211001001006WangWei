package com.WangWei.week4;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name="ConfigDemoServlet", value="/config",
    initParams={
            @WebInitParam(name="name",value="WangWei"),
            @WebInitParam(name="studentId",value="2019211001001006"),
    }
)
public class ConfigDemoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        String name = getServletConfig().getInitParameter("name");
        String studentId = getServletConfig().getInitParameter("studentId");
        writer.println("name:" + name + "\n" +"studentId:" + studentId);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
