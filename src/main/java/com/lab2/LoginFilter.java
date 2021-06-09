package com.lab2;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter",
urlPatterns={"/lab2/validate.jsp","/lab2/welcome.jsp"})
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
        System.out.println("i am in LoginFilter---init()");
    }

    public void destroy() {
        System.out.println("i am in LoginFilter---destroy()");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("i am in LoginFilter---doFilter()--request before chain");
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;
        HttpSession session = request1.getSession(false);
        if(session.isNew()){
            request.getRequestDispatcher("/lab2/welcome.jsp").forward(request1,response1);
        }else if(!session.isNew() && request1.getParameter("username")==null){
            response1.sendRedirect(request1.getContextPath()+"/lab2/login.jsp");
        }

            chain.doFilter(request, response);

        System.out.println("i am in LoginFilter---destroy()--response after chain");
    }
}
