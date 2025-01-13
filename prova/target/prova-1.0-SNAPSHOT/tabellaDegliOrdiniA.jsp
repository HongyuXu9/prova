<%@ page import="org.example.entity.Ordine" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Tabella degli ordini dei clienti</title>
    </head>
    <body>
        <h1>Tabella degli ordini dei clienti</h1>
        <% ArrayList<Ordine> ordini= (ArrayList<Ordine>) request.getAttribute("ordini");
           String username = (String)request.getAttribute("username");
            if (ordini != null){%>

        <table>
            <tr>
                <th>ID Ordine</th>
                <th>Username</th>
                <th>ID Prodotto</th>
                <th>Quantità</th>
                <th>Stato</th>
            </tr>
                <% for (Ordine ordine : ordini) {%>
            <tr>
                <td><%= ordine.getId_ordine() %></td>
                <td><%= ordine.getUsername() %></td>
                <td><%= ordine.getId_prodotto() %></td>
                <td><%= ordine.getQuantita() %></td>
                <td><%= ordine.getStato() %>
                    <form action="gestione" method="post">
                        <input type="hidden" name="ordineId" value="<%= ordine.getId_ordine()%>">
                        <input type="hidden" name="username" value="<%= username %>">
                        <select name="nuovoStato">
                            <option value="Inviato" <%= ordine.getStato().equals("Inviato") ? "selected" : "" %>>Inviato</option>
                            <option value="Rifiutato" <%= ordine.getStato().equals("Rifiutato") ? "selected" : "" %>>Rifiutato</option>
                        </select>
                        <button type="submit">Aggiorna</button>
                    </form>
                </td>
            </tr>
                <%}
            }%>
        </table>
    </body>
</html>
