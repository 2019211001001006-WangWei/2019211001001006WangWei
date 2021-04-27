<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2021/3/31
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<body>
    <h1>Login</h1>
    <%
        if(!(request.getAttribute("message") == null)){
            out.println("<h3 style=\"color:red;\">" + request.getAttribute("message") + "</h3>");
        }
        Cookie[] allCookie = request.getCookies();
        String username = "",password = "",rememberMe = "";
        if(allCookie != null){
            for(Cookie c:allCookie){
                if(c.getName().equals("cusername")){
                    username = c.getValue();
                }
                if(c.getName().equals("cpassword")){
                    password = c.getValue();
                }
                if(c.getName().equals("crememberMe")){
                    rememberMe = c.getValue();
                }
            }
        }

    %>
    <form action="${pageContext.request.contextPath}/login" method="post">
        UserName: <input type="text" name="username" value="<%=username%>">
        <br>
        Password: <input type="password" name="password" value="<%=password%>">
        <br>
        <input type="checkbox" value="1" name="RememberMe" <%=rememberMe.equals("1")? "checked":""%>>Remember Me
        <br>
        <input type="submit" value="Login">
        <br>

    </form>
</body>
<%@include file="footer.jsp" %>
