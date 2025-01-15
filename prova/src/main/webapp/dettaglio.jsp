<%@ page import="org.example.entity.Prodotto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Dettaglio Prodotto</title>
        <style>
            .prodotto {
                background-color: lightgrey;
                padding: 10px;
                width: 300px;
                border: 1px solid;
                border-radius: 8px;
            }

            .prodotto img {
                width: 100%;
                height: 200px;
                object-fit: cover;
            }
        </style>
    </head>
    <body>
    <% Prodotto prodotto = (Prodotto) request.getAttribute("prodotto");
        if(prodotto != null){%>
        <div class="prodotto">
            <img src="<%= prodotto.getImmagine() %>"/>
            <h3><%= prodotto.getNome() %></h3>
            <p><%= prodotto.getDescrizione() %></p>
            <p>Prezzo: €<%= prodotto.getPrezzo() %></p>
            <p>Disponibilità: <%= prodotto.getDisponibilita() %></p>
        </div>
    <%}else{%>
    <p>Nessun prodotto corrispondente</p>
    <%}%>
    </body>
</html>
