<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.entity.Prodotto" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Prodotti</title>
        <style>
        .listProdotto {
            display: flex;
            flex-wrap: wrap;

        }
        </style>
    </head>


    <body>
    <h1>Prodotti</h1>
    <%String username = (String) request.getAttribute("username");
    String password = (String) request.getAttribute("password");%>

    <form method="get" action="search">
        <input type="text" name="search" placeholder="Cerca prodotti">
        <input type="hidden" name="username" value="<%= username %>">
        <input type="hidden" name = "password" value="<%= password %>">
        <button type="submit">Cerca</button>
    </form>

    <h2>Lista Prodotti</h2>
        <%ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) request.getAttribute("prodotti");%>
         <div class="listProdotto">
            <% for (Prodotto p : prodotti) {%>
            <div class="prodotto">
                <img src="<%= p.getImmagine() %>"/>
                <h3><%= p.getNome() %></h3>
                <p><%= p.getDescrizione() %></p>
                <p>Prezzo: €<%= p.getPrezzo() %></p>
                <p>Disponibilità: <%= p.getDisponibilita() %></p>
                <form action="carrello" method="POST" >
                    <input type="hidden" name="id" value="<%= p.getId_prodotto() %>">
                    <input type="hidden" name="username" value="<%= username %>">
                    <input type="hidden" name = "password" value="<%= password %>">
                    <input type="number" name="quantita" value="1" min="1" required>
                    <button type="submit">Aggiungi al carrello</button>
                </form>
            </div>

            <%}%>
         </div>
    </body>
</html>
