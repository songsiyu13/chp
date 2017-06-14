<%@ page import="com.entity.Item" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.entity.Book" %><%--
  Created by IntelliJ IDEA.
  User: 滩涂上的芦苇
  Date: 2017/4/6
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
</head>
<body>
<%
    ArrayList<Book> list = (ArrayList<Book>)request.getAttribute("books");
%>
<table>
    <tr>
        <th>bookID</th>
        <th>number</th>
        <th>category</th>
    </tr>
    <%
        for (Book bk:list
                ) {
    %><tr>
    <th><%=bk.getBookID()%></th><th id="<%=bk.getBookID()%>" onmousemove="getjson(this)" onmouseout="clearData()"><%=bk.getBookName()%></th><th><%=bk.getCategory()%></th>
</tr>
    <%
        }
    %>
</table>
<div style="position:absolute;" id="popup">
    <table bgcolor="#FFFAFA" border="0" cellspacing="2" cellpadding="2">
        <tbody id="dataBody"></tbody>
    </table>
</div>
<script type="text/javascript">
    var dataDiv;
    var dataTableBody;
    var curElement;

    function getjson(element) {
        dataTableBody=document.getElementById("dataBody");
        dataDiv=document.getElementById("popup");
        var url = "/json?bookID="+element.id;
        $.ajax( {
            type : "get",
            url : url,
            dataType:"text",
            success : function(msg) {
                setData(msg);
            },
            error:function (response) {
                alert("网络异常")
            }
        });
    }

    function setData(data) {
        clearData();
        setOffsets();
        var row=createRow(data);
        dataTableBody.appendChild(row);
    }

    function createRow(data) {
        var row,cell,txtNode;
        row=document.createElement("tr");
        cell=document.createElement("td");
        cell.setAttribute("bgcolor","#FFFAFA");
        cell.setAttribute("border",0);
        txtNode=document.createTextNode(data);
        cell.appendChild(txtNode);
        row.appendChild(cell);
        return row;
    }

    function setOffsets() {
        dataDiv.style.border="black 1px solid";
        var top=0;
        while(curElement){
            top+=curElement["offsetTop"];
            curElement=curElement.offsetParent;
        }
        dataDiv.style.left=50+"px";
        dataDiv.style.top=top+"px";
    }

    function clearData() {
        var ind=dataTableBody.childNodes.length;
        for(var i=ind-1;i>=0;i--){
            dataTableBody.removeChild(dataTableBody.childNodes[i]);
        }
        dataDiv.style.border="none";
    }
</script>
</body>
</html>
