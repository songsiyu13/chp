<%--
  Created by IntelliJ IDEA.
  User: Song
  Date: 2017/3/17
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>bill</title>

    <script type="text/javascript">
        function rsalogin(){
            var thisPwd = document.getElementById("password").value;
            var result = encryptedString(document.getElementById("pk").value, encodeURIComponent(thisPwd));
            //alert(encodeURIComponent(thisPwd)+"\r\n"+result);
            document.getElementById("password").value = result;
            loginForm.submit();
        }
    </script>
</head>
<body>
<form method="post" name="loginForm" action="/bill">
    <table border="0">
        <tr>
            <td>
                Password:
            </td>
            <td>
                <input type='text' name="password" id=password style='width:400px' value="my passwd"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="button" value="SUBMIT" onclick="rsalogin();" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>
