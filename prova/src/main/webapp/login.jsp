<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
        <style>
            body {
                background-color: gray;
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            #sitoTitle{
                top : 30px;
                text-align: center;
                width: 100%;
                border-radius: 60px;
                max-width: 300px;
                padding: 10px;
                background-color: orange;
            }

           .container{
               background-color: white;
               padding: 30px;
               border-radius: 8px;
               width: 100%;
               max-width: 400px;
               height: 30%;
               min-height: 200px;
               margin-top: 50px;
               display: flex;
               flex-direction: column;
           }

           button:hover {
               color: red;
           }

           button:visited {
               color: green;
           }
        </style>
    </head>

    <body>
    <h1 id="sitoTitle">sito.it</h1>
    <div class="container">
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
        <a href="registra.jsp">Registra ora</a>
        <%
            String error = request.getParameter("error");
            if (error != null) {
        %>
        <p style="color: red;"><%= error %></p>
        <% } %>
    </div>
    </body>
</html>
