<%@ page import="org.example.entity.Ordine" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Tabella degli ordini dei clienti</title>
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
        <h1>Tabella degli ordini dei clienti</h1>
        <% ArrayList<Ordine> ordini= (ArrayList<Ordine>) request.getAttribute("ordini");
           String username = (String)request.getAttribute("username");
           String password = (String)request.getAttribute("password");
            if (ordini != null){%>

        <table>
            <tr>
                <th>ID Ordine</th>
                <th>Username</th>
                <th>ID Prodotto</th>
                <th>Quantità</th>
                <th>Stato Attuale</th>
                <th>Modifica Stato</th>
            </tr>
                <% for (Ordine ordine : ordini) {%>
            <tr>
                <td><%= ordine.getId_ordine() %></td>
                <td><%= ordine.getUsername() %></td>
                <td><%= ordine.getId_prodotto() %></td>
                <td><%= ordine.getQuantita() %></td>
                <td><%= ordine.getStato() %></td>
                <td>
                    <form action="gestione" method="post">
                    <input type="hidden" name="ordineId" value="<%= ordine.getId_ordine()%>">
                    <input type="hidden" name="cliente" value="<%= ordine.getUsername() %>">
                    <input type="hidden" name="username" value="<%= username %>">
                    <input type="hidden" name="password" value="<%= password %>">
                    <select name="nuovoStato">
                        <option value="Inviato">Inviato</option>
                        <option value="Rifiutato">Rifiutato</option>
                    </select>
                    <button type="submit">Aggiorna</button>
                    </form>
                </td>
            </tr>
                <%}
            }%>
        </table>
        <br>
        <a href="login.jsp">Logout</a>
    </body>
</html>
