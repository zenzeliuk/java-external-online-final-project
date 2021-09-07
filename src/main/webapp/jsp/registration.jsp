<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file="/css/styles.css" %>
</style>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="main">
    <form action="/controller" method="post">
        <input type="hidden" name="command" value="registration"/>

        <label for="login">Login:</label><br>
        <input type="text" id="login" name="login"><br>

        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password"><br><br>

        <input type="submit" value="Registration">
    </form>
</div>
</body>
</html>
