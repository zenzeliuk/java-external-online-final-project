<html>
<head>
    <title>Login Page</title>
</head>
<body>
<div id="main">
    <form action="/controller" method="post">
        <input type="hidden" name="command" value="login"/>

        <label for="login">Login:</label><br>
        <input type="text" id="login" name="login"><br>

        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password"><br><br>

        <input type="submit" value="Login">
    </form>
</div>

</body>
</html>
