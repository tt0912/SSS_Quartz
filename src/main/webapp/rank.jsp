<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
        <h1>新增队伍信息</h1>
        <form action="/rankadd.do" method="get">
            <input placeholder="请输入组号" name="groupNo"><br>
            <input placeholder="请输入项目名称" name="projectName"><br>
            <input placeholder="请输入分数" name="score"><br>
            <input type="submit" value="提交">
        </form>
    </div>
    <hr>
<div>
    <input type="button" value="刷数据" onclick="getData()">
    <table border="1">
        <tr>
            <th>序号</th>
            <th>组号</th>
            <th>项目名称</th>
            <th>分数</th>
            <th>操作</th>
        </tr>
        <span id="p1"></span>
    </table>
</div>

<script type="text/javascript" src="/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
    function getData() {
        $.ajax({
            url:"rankadd.do",
            data:"pageNum=1&pageSize=3",
            type:"POST",
            success:function (data) {
                if(data.code==200){
                    $("#p1").append("总页数:"+data.data.totalPages);
                    for(i in data.data.content){

                    }
                }
            }
        })
    }
</script>
</body>
</html>
