<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.entity.Ordine" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Tabella degli ordini</title>
        <style>
            h1{
                top : 30px;
                text-align: center;
                padding: 20px;
                background-color: orange;
            }
            table {
                width: 100%;
                margin: 20px auto;
            }

            table, th, td {
                border: 1px solid;
            }
            td {
                text-align: center;
            }
            tr:hover {background-color: coral;}
        </style>
    </head>

    <body>
    <h1>Ordini effettuati da ${username}</h1>
    <% ArrayList<Ordine> ordini= (ArrayList<Ordine>) request.getAttribute("ordini");
        String username = (String)request.getAttribute("username");
        String password = (String)request.getAttribute("password");
    if (ordini != null){%>
    <table>
        <tr>
            <th>ID Ordine</th>
            <th>ID Prodotto</th>
            <th>Stato</th>
            <th>Quantità</th>
        </tr>
            <% for (Ordine ordine : ordini) {%>
        <tr>
            <td><%= ordine.getId_ordine() %></td>
            <td><%= ordine.getId_prodotto() %></td>
            <td><%= ordine.getStato() %></td>
            <td><%= ordine.getQuantita() %></td>
        </tr>

            <%}%>
        </table>
    <%}else{%>
        <p>Nessun ordine presente!</p>
        <%}%>
        <br>
        <a href="home?username=<%= username %>&password=<%=password%>">Torna alla ricerca</a>
    </body>
</html>
