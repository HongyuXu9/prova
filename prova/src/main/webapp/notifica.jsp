<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="org.example.entity.Notifica" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
    <head>
        <title>Notifiche</title>
        <style>
            body {
                margin: 20px;
            }
            .container {
                width: 50%;
                margin: auto;
                padding: 20px;
                border: 1px solid grey;
                border-radius: 5px;
            }

            .notifica{
                margin-top: 5px;
                margin-bottom: 5px;
                border: 1px solid grey;
                border-radius: 5px;
                padding:10px;
                background-color: lightgrey;
            }

            h2 {
                top : 30px;
                text-align: center;
                padding: 20px;
                background-color: orange;
            }


        </style>
    </head>
    <body>

    <%String username = (String)request.getAttribute("username");
    String password = (String)request.getAttribute("password");
    ArrayList<Notifica> notifiche = (ArrayList<Notifica>) request.getAttribute("notifiche");%>
    <div class="container">
        <h2>Dettagli Notifica</h2>
        <% if(notifiche != null && !notifiche.isEmpty()){
            for(Notifica notifica : notifiche ){%>
        <div class="notifica">
            <p><strong>Username:</strong> <%= notifica.getUsername() %></p>
            <p><strong>Testo:</strong> <%= notifica.getTesto() %></p>
            <p><strong>Data Notifica:</strong> <%= notifica.getDataNotifica() %></p>
        </div>
        <%}
        }else{%>
            <p style="text-align: center">Nessuna notifica</p>
        <%}%>
    </div>
    <br>
    <a href="home?username=<%= username %>&password=<%=password%>">Torna alla ricerca</a>
    </body>
</html>
