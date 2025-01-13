<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.entity.Prodotto" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Prodotti</title>
        <style>
            .cerca {
                display: flex;
                justify-content: space-between;
                padding: 10px;
                margin-top: 10px;
                background-color: orange;
            }

            #titoloLista{
                top : 30px;
                text-align: center;
                padding: 20px;
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

            .prodotto form input[type=number] {
                width: 20%;
            }
            form{
                margin-top: auto;
                margin-bottom: auto;
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
    <%String username = (String) request.getAttribute("username");
    String password = (String) request.getAttribute("password");%>

    <div class="cerca">
        <h1>Sito.it</h1>
        <form method="get" action="search">
            <input type="text" name="search" placeholder="Cerca prodotti">
            <input type="hidden" name="username" value="<%= username %>">
            <input type="hidden" name = "password" value="<%= password %>">
            <button type="submit">Cerca</button>
        </form>
    </div>

    <h2 id="titoloLista">Lista Prodotti</h2>
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
                    <input type="number" name="quantita" value="1" min="1" max="<%= p.getDisponibilita()%>" required>
                    <button type="submit">Aggiungi al carrello</button>
                </form>
            </div>

            <%}%>
         </div>
    <a href="login.jsp">Torna al login</a>
    </body>
</html>
