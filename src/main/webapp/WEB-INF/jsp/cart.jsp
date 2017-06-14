<%--
  Created by IntelliJ IDEA.
  User: Song
  Date: 2017/3/17
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" import="com.entity.Item" language="java" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<%
    ArrayList<Item> list = (ArrayList<Item>)request.getAttribute("cart");
%>
<table>
    <tr>
        <th>bookID</th>
        <th>number</th>
    </tr>
    <%
        for (Item it:list
             ) {
    %><tr>
    <th><%=it.getBookId()%></th><th><%=it.getNumber()%></th>
</tr>
    <%
        }
    %>
</table>

<form action="/pay" method="post">
    <input type="submit" value="toPay" />
</form>
</body>
</html>
