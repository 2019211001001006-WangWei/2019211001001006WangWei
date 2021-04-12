package com.WangWei.week6;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SearchServlet", value = "/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String txt = request.getParameter("txt");
        String select = request.getParameter("search");
        response.sendRedirect("SearchServlet" );
        if(txt == null|| "".equals(txt)){
            response.sendRedirect("index.jsp");
        }else{
            if(select.equals("baidu")){
                response.sendRedirect("https://www.baidu.com/s?wd=" + txt);
            }else if(select.equals("bing")){
                response.sendRedirect("http://cn.bing.com/search?q=" + txt);
            }else{
                response.sendRedirect("http://cn.google.com/search?q=" + txt);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
