<%@ page import="org.example.entity.Prodotto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Prodotti</title>
        <style>
            h1{
                top : 30px;
                text-align: center;
                padding: 20px;
                background-color: orange;
            }

            .listProdotto {
                display: flex;
                flex-wrap: wrap;
                margin-left: 10px;
                margin-right: 10px;
            }

            .prodotto {
                margin: 10px;
            }

            .prodotto img {
                width: 100%;
                height: 200px;
                object-fit: cover;
            }

            form button:hover {
                color: red;
            }

            form button:visited {
                color: green;
            }
        </style>
    </head>

    <body>
    <%String username = (String)request.getAttribute("username");
    String password = (String)request.getAttribute("password");%>
    <h1>Carrello di <%= username %></h1>
    <%  ArrayList<Prodotto> risultati = (ArrayList<Prodotto>) request.getAttribute("risultati");
        ArrayList<Integer> quantitaP = (ArrayList<Integer>) request.getAttribute("quantitaP");%>
    <%  if (risultati != null && quantitaP != null && risultati.size() == quantitaP.size()
    && !risultati.isEmpty() && !quantitaP.isEmpty()) {%>
    <div class="listProdotto">
           <%for (int i = 0; i < risultati.size(); i++) {
                Prodotto p = risultati.get(i);
                int quantita = quantitaP.get(i);%>
        <div class="prodotto">
            <img src="<%= p.getImmagine() %>"/>
            <h3><%= p.getNome() %></h3>
            <p><%= p.getDescrizione() %></p>
            <p>Prezzo: €<%= p.getPrezzo() %></p>
            <p>Quantita: <%=quantita %></p>
            <form action="elimina" method="POST">
                <input type="hidden" name="id" value="<%= p.getId_prodotto() %>">
                <input type="hidden" name="username" value="<%= username %>">
                <input type="hidden" name="password" value="<%= password %>">
                <button type="submit">Elimina</button>
            </form>
        </div>
    <%}%>
    </div>

    <form action="ordine" method="POST" >
        <input type="hidden" name="username" value="<%= username %>">
        <input type="hidden" name="password" value="<%= password %>">
        <% for (int i = 0; i < risultati.size(); i++) { %>
        <input type="hidden" name="idProdotto" value="<%= risultati.get(i).getId_prodotto() %>">
        <input type="hidden" name="quantita" value="<%= quantitaP.get(i) %>">
        <%}%>
        <button type="submit">Ordina ORA!</button>
    </form>
    <%
        String error = request.getParameter("error");
        if (error != null) {
    %>
    <p style="color: red;"><%= error %></p>
    <% } %>

    <% }else{%>
        <p style="text-align: center">Non ci sono prodotti nel carrello.</p>
        <%
            String error = request.getParameter("error");
            if (error != null) {
        %>
        <p style="color: red;"><%= error %></p>
        <% } %>
     <%}%>
    <br>
    <a href="home?username=<%= username %>&password=<%=password%>">Torna alla ricerca</a>
    </body>
</html>

