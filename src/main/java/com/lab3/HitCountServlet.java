package com.lab3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HitCountServlet", value = "/HitCountServlet")
public class HitCountServlet extends HttpServlet {
    private int count;
    @Override
    public void init() throws ServletException {
        super.init();
        count = 0;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        count++;
        PrintWriter writer = response.getWriter();
        writer.println("<h1>" + "Total Number of Hits" + "</h1>");
        writer.println("<h2>" + count + "</h2>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        count++;
        PrintWriter writer = response.getWriter();
        writer.println("<h1>" + "Total Number of Hits" + "</h1>");
        writer.println("<h2>" + count + "</h2>");
    }
}
