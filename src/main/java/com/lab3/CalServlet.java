package com.lab3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CalServlet", value = "/CalServlet")
public class CalServlet extends HttpServlet {
    private int add(int first,int second){
        return first + second;
    }
    private int subtract(int first,int second){
        return first - second;
    }
    private int multiply(int first,int second){
        return first * second;
    }
    private int divide(int first,int second){
        return first / second;
    }
    private int computerLength(String name){
        return name.length();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int first = Integer.parseInt(request.getParameter("first"));
        int second = Integer.parseInt(request.getParameter("second"));
        String name = request.getParameter("name");
        int action = Integer.parseInt(request.getParameter("action"));
        double result = 0;
        int length = 0;
        if(action == 0){
            result = add(first,second);
            //System.out.println(result);
        }else if(action == 1){
            result = subtract(first,second);
        }else if(action == 2){
            result = multiply(first,second);
        }else if(action == 3){
            result = divide(first,second);
        }else if(action == 4){
            length = computerLength(name);
        }
        if(action<=3){
            response.addCookie(new Cookie("cFirstValue",request.getParameter("first")));
            response.addCookie(new Cookie("cSecondValue",request.getParameter("second")));
            response.addCookie(new Cookie("cResult",String.valueOf(result)));
        }else{
            response.addCookie(new Cookie("cComputerLength",String.valueOf(length)));
            response.addCookie(new Cookie("cName",name));
        }
        response.sendRedirect(request.getContextPath()+"/lab3/cal.jsp");
    }
}
