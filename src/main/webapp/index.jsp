<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<%--Week5-HW--%>
<%--<a href="hello-servlet">Hello Servlet-week1</a>
<br>
<a href="hello">Hello Servlet-week2</a>
<br>

<a href="week2/register.jsp">Register Servlet-week3</a>
<br>
<a href="config">ConfigDemo Servlet-week4</a>
<br>
<a href="login.jsp">Login Servlet-week5</a>--%>
<form method="get" target="_blank" action="search">
    <input type="text" name="txt" size=30>
    <select name="search">
        <option value="baidu">Baidu</option>
        <option value="bing">Bing</option>
        <option value="google">Google</option>
        <input type="submit" value="Search">
    </select>
</form>
<%@include file="footer.jsp" %>