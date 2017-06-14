<%--
  Created by IntelliJ IDEA.
  User: 滩涂上的芦苇
  Date: 2017/6/14
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lucene</title>

    <script type="text/javascript" src="/js/jquery.js"></script>
    <style type="text/css">
        .container {
            margin: 0 auto;
            width: 80%;
            height: 140px;
            border: 1px solid green;
            border-bottom: none;
        }

        .container2 {
            margin: 0 auto;
            width: 80%;
            border: 1px solid green;
            border-top: none;
        }

        #show {
            width: 100%;
        }

        #tip {
            width: 100%;
            text-align: center;
            float: none;
            color: green;
        }

        .box {
            border: 1px solid rgb(0, 0, 0);
            color: rgb(255, 255, 255);
            margin: 5px;
            background: rgb(83, 83, 83);
        }

        .box:hover {
            background: green;
            font-weight: bold;
        }

        button {
            border: 1px solid black;
            background: white;
            color: black;
        }

        button:hover {
            border: 1px solid black;
            background: gray;
            color: white;
        }

        fieldset {
            border: 1px solid black;
            width: 30%;
            height: 120px;
            float: left;
        }

        select {
            border: 1px solid green;
            color: green;
        }

        textarea {
            width: 100%;
            height: 50px;
        }

    </style>
    <script type="text/javascript">

        function tip(text)
        {
            $("#tip").html(text);
        }

        function crawl()
        {
            var cat_val = $("#category").val();
            var cat_text = $("#category").find("option:selected").text();
            tip("正在抓取网易新闻 『" + cat_text + "』 频道，请稍后");
            $.ajax({
                type: "post",
                url: "crawl/ntes",
                data: {"category": cat_val},
                dataType: "json",
                success: function (data)
                {
                    var html = "";
                    $.each(data, function (i, n)
                    {
                        html += "<a href='" + n.url + "'>" + n.title + "</a><p>" + n.shortContent + "</p>";
                    });
                    tip("<font color=blue>抓取成功</font>");
                    $("#show").empty();
                    $("#show").append(html);
                }, error: function (data)
                {
                    tip("<font color=red>抓取失败</font>");
                }
            });
        }

        function split()
        {
            tip("正在对输入的文本进行分词，请稍后");

            var maxLength = 0;

            if ($("#isMaxLength").is(":checked"))
            {
                maxLength = 1;
            }

            $.ajax({
                type: "post",
                url: "ik/split",
                data: {"text": $("#content").val(), "isMaxLength": maxLength},
                dataType: "json",
                success: function (data)
                {
                    var html = "";
                    $.each(data, function (i, n)
                    {
                        html += "<button class='box'>" + n + "</button>";
                    });
                    tip("<font color=blue>分词结束</font>");
                    $("#show").empty();
                    $("#show").append(html);
                }, error: function (data)
                {
                    tip("<font color=red>抓取失败</font>");
                }
            });
        }

        function indexFiles()
        {
            tip("正在执行");
            $.ajax({
                type: "post",
                url: "lucene/indexFiles",
                data: {},
                dataType: "json",
                success: function (data)
                {
                    var html = "<ul>";
                    $.each(data, function (i, n)
                    {
                        html += "<li class=''>" + n + "</li>";
                    });
                    html += "<ul>";
                    tip("<font color=blue>操作结束</font>");
                    $("#show").empty();
                    $("#show").append(html);
                },
                error: function (data)
                {
                    tip("<font color=red>操作失败</font>");
                }
            });
        }
        function deleteIndexes()
        {
            tip("正在执行");
            $.ajax({
                type: "post",
                url: "lucene/deleteIndexes",
                data: {},
                dataType: "text",
                success: function (data)
                {
                    if (data == "suc")
                    {
                        tip("已删除所有索引");
                    }
                    indexFiles();
                }, error: function (data)
                {
                    tip("<font color=red>操作失败</font>");
                }
            });
        }
        function listIndexes()
        {
            tip("正在执行");
            $.ajax({
                type: "post",
                url: "lucene/listIndexed",
                data: {},
                dataType: "json",
                success: function (data)
                {
                    var html = "<ul>";
                    $.each(data, function (i, n)
                    {
                        html += "<li><a href='" + n.url + "'>" + n.title + "</a></li>";
                    });
                    html += "</ul>";
                    tip("<font color=blue>操作成功</font>");
                    $("#show").empty();
                    $("#show").append(html);
                }, error: function (data)
                {
                    tip("<font color=red>操作失败</font>");
                }
            });
        }

        function search()
        {
            tip("正在查询");
            $.ajax({
                type: "post",
                url: "lucene/search",
                data: {"text": $("#keyword").val()},
                dataType: "json",
                success: function (data)
                {
                    console.log(data);
                    var html = "<ul>";
                    $.each(data, function (i, n)
                    {
                        html += "<li>docId:" + n.other1 + "<a href='" + n.url + "'>" + n.title + "</a>-" + n.date + "<p>" + n.content + "</p></li>";
                    });
                    html += "</ul>";
                    console.log(html);
                    tip("<font color=blue>查询结束</font>");
                    $("#show").empty();
                    $("#show").append(html);
                }, error: function (data)
                {
                    tip("<font color=red>操作失败</font>");
                }
            });
        }
    </script>
</head>
<body>
<div class="container">
    <fieldset>
        <legend>Lucene操作</legend>
        <button id="listIndexes" onclick="listIndexes()">列出所有索引数据</button>
        <textarea id="keyword"></textarea>
        <button id="search" onclick="search()">查询</button>
    </fieldset>
</div>
<div class="container2">
    <div id="tip">...</div>
    <div id="show"></div>
</div>
</body>
</html>
