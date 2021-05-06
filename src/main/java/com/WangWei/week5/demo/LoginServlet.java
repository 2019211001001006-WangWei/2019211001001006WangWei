package com.WangWei.week5.demo;

import com.WangWei.dao.UserDao;
import com.WangWei.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
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
        request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
/*     UserDao Text
       List<User> retList = new ArrayList<User>();

        try {

            UserDao userDao = new UserDao();
            retList = userDao.findAllUser(con);

            retList = userDao.findByBirthdate(con, date);
            retList = userDao.findByGender(con, "male");
            retList = userDao.findByEmail(con, "2019211001001006@ecjtu.edu.cn");
            retList = userDao.findByPassword(con,"1111111111111111");
            retList = userDao.findByUsername(con,"bb");
            User user = userDao.findById(con,11);
            int d = userDao.deleteUser(con,user);
            User user = new User(8,"baba","123456123","9999@qq.com","male",new Date("1111/11/11"));
            User s = new User();
            String dateString = "1231-11-15";
            Date date= new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            s.setUsername("99");
            s.setPassword("44444444444444444");
            s.setEmail("1111@qq.com");
            s.setGender("Female");
            s.setBirthDate(date);
            Boolean d = userDao.saveUser(con,s);

            System.out.println(d);
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
        for(User u:retList){
            System.out.println(u);
        }*/

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDao userDao = new UserDao();
        try {
            User u = userDao.findByUsernamePassword(con,username,password);
            if(u !=null){
               String remember = request.getParameter("remember");
                if(remember != null && remember.equals("1")){
                    Cookie usernameCookie = new Cookie("cUsername",u.getUsername());
                    Cookie passwordCookie = new Cookie("cPassword",u.getPassword());
                    Cookie rememberMeCookie = new Cookie("cRememberMe",request.getParameter("RememberMe"));
                    usernameCookie.setMaxAge(10);
                    passwordCookie.setMaxAge(10);
                    rememberMeCookie.setMaxAge(10);
                    response.addCookie(usernameCookie);
                    response.addCookie(passwordCookie);
                    response.addCookie(rememberMeCookie);
                };

                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(60*60*24);
                session.setAttribute("user",u);
                //request.setAttribute("user",u);
                request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request,response);
            }else{
                request.setAttribute("message", "Username or Password Error!");
                request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
/*
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
*/

    }
}
