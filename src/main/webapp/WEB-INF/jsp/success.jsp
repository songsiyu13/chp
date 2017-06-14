<%--
  Created by IntelliJ IDEA.
  User: 滩涂上的芦苇
  Date: 2017/3/5
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
<form action="/addCart" method="post">
    buy book:
    bookID:<input name="bookID" type="number" />
    number:<input name="number" type="number" />
    <input type="submit" value="submit">
</form>

<form action="/showCart"><input type="submit" value="show cart"></form>
</body>
</html>
