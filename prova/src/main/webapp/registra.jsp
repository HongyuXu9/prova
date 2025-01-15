<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Registra</title>
        <style>
            body {
                background-color: gray;
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            #sitoTitle{
                width: 100%;
                max-width: 400px;
                border-radius: 8px;
                top : 30px;
                text-align: center;
                padding: 10px;
                background-color: orange;
            }

            .container{
                background-color: white;
                padding: 30px;
                border-radius: 8px;
                width: 100%;
                max-width: 400px;
                margin-top: 50px;
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            .container h1{
                font-size: 20px;
                margin-bottom: 20px;
            }

            .container form {
                width: 100%;
                display: flex;
                flex-direction: column;
                gap: 2px;
            }

            input[type="text"],
            input[type="password"],
            select[name="ruolo"]{
                padding: 5px;
                font-size: 15px;
                border-radius: 5px;
            }

            form button{
                margin-top: 10px;
                background-color: orange;
                font-size: 15px;
                border-radius: 10px;
                padding: 5px;
                border: none;
            }

            form button:hover {
                background-color: yellow;
            }
        </style>
    </head>

    <body>
    <h1 id="sitoTitle">sito.it</h1>
    <div class="container">
        <h1>Registra</h1>
        <form action="registra" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            <br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <br>
            <label for="ruolo">Ruolo:</label>
            <select name="ruolo" id="ruolo">
                <option value="cliente">cliente</option>
                <option value="amministratore">amministratore</option>
            </select>

            <button type="submit">Registra</button>
        </form>
        <a href="login.jsp">Torna al login</a>
        <%
            String error = request.getParameter("error");
            if (error != null) {
        %>
        <p style="color: red;"><%= error %></p>
        <% } %>
    </div>

    </body>
</html>
