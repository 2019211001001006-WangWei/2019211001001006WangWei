<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2021/6/12
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        window.onload=function (){
            let cli = document.querySelectorAll(".cli")
            for(let j = 0; j < cli.length ; j++){
                cli[j].onclick=function (){
                    this.index = j;
                    document.getElementById("action").value = this.index
                    let input = document.querySelectorAll(".input")
                    for(let i = 0; i < input.length; i++){
                        if(i==1){
                            if(!isNaN(Number(input[i].value))){
                                alert("Name is not valid!")
                            }
                        }else if(i!=1 && isNaN(Number(input[i].value))){
                            alert("First or Second Value is not a number!")
                        }
                    }
                }
            }
           let re = document.getElementById("reset")
            re.onclick = function (){
               let inp = document.getElementsByTagName("input")
                for(let i = 0;i<5;i++){
                    inp[i].value = ""
                }
            }
        }


    </script>
</head>
<body>
<%
    Cookie[] allCookies = request.getCookies();
    String firstValue = null,secondValue = null,result = null,name =null,length = null;
    if(allCookies!=null){
        for(Cookie c:allCookies){
            if(c.getName().equals("cFirstValue")){
                firstValue = c.getValue();
            }else if(c.getName().equals("cSecondValue")){
                secondValue = c.getValue();
            }else if(c.getName().equals("cResult")){
                result = c.getValue();
            }else if(c.getName().equals("cComputerLength")){
                length = c.getValue();
            }else if(c.getName().equals("cName")){
                name = c.getValue();
            }
        }
    }

%>
<form method="post" action="${pageContext.request.contextPath}/CalServlet">
    <table>
        <colgroup width="100px"/>
        <tr>
            <td>First:val: </td>
            <td><input type="text" class="input" name="first" value="<%=firstValue!=null?firstValue:""%>" ></td>
            <td>Enter a name: </td>
            <td><input type="text" class="input" name="name" value="<%=name!=null?name:""%>"></td>
        </tr>
        <tr>
            <td>Second:val: </td>
            <td><input type="text" class="input" name="second" value="<%=secondValue!=null?secondValue:""%>"></td>
            <td>Length: </td>
            <td><input type="text" value="<%=length!=null?length:""%>"></td>
        </tr>
        <tr>
            <td>Result: </td>
            <td><input type="text" value="<%=result!=null?result:""%>"></td>
        </tr>
        <tr>
            <td colspan="4">
                <input type="hidden" name="action" id="action">
                <input type="submit" class="cli" value="Add"></submit>
                <input type="submit" class="cli" value="Subtract"></input>
                <input type="submit" class="cli" value="Multiply"></input>
                <input type="submit" class="cli" value="Divide"></input>
                <input type="submit" class="cli" value="Compute Length"></input>
            </td>
        </tr>
        <tr><td colspan="4" align="center" id="reset"><button type="button">Reset</button></td></tr>
    </table>
</form>
</body>
</html>
