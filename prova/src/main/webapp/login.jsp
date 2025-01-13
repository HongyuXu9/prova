<%--
  Created by IntelliJ IDEA.
  User: DH012025
  Date: 12/01/2025
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>

    <body>
    <h1>Login</h1>
    <form action="login" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <button type="submit">Login</button>
    </form>
    <%
        String error = request.getParameter("error");
        if (error != null) {
    %>
    <p style="color: red;"><%= error %></p>
    <% } %>
    </body>
</html>
