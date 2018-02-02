<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>今日新闻</title>
</head>
<body>
    <h1>新闻列表</h1>
    <hr>
    <#--ftl循环-->
    <#list newsList as n>
        <a href="${n.urlhtml}">${n.title}</a>
    </#list>

</body>
</html>